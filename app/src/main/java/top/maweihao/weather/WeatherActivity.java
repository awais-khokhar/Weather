package top.maweihao.weather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.util.BaiduApiUtility;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Utility.handleCurrentWeatherResponse;

public class WeatherActivity extends AppCompatActivity {

    static final String TAG = "WeatherActivity";
    static final int THROUGH_IP = 0;
    static final int THROUGH_CHOOSE_POSITION = 1;
    static final int THROUGH_LOCATE = 2;
    static final int THROUGH_COORDINATE = 3;
    static final int MINUTELY_MODE = 4;
    static final int HOURLY_MODE = 5;

    private boolean isDone = false;
    private String countyName = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private View appBar;
    private String locationCoordinates;
    private perDayWeatherView[] day = new perDayWeatherView[5];

    private TextView position_text;
    //    private ImageView skycon_image;
    private TextView temperature_text;
    private TextView skycon_text;
    private TextView aqi_text;
    private TextView hum_text;
    private TextView sunrise_text;
    private TextView sunset_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        position_text = (TextView) findViewById(R.id.position_text);
        temperature_text = (TextView) findViewById(R.id.temperature_text);
        skycon_text = (TextView) findViewById(R.id.skycon_text);
        appBar = findViewById(R.id.app_bar);

        day[0] = (perDayWeatherView) findViewById(R.id.daily_weather_0);
        day[1] = (perDayWeatherView) findViewById(R.id.daily_weather_1);
        day[2] = (perDayWeatherView) findViewById(R.id.daily_weather_2);
        day[3] = (perDayWeatherView) findViewById(R.id.daily_weather_3);
        day[4] = (perDayWeatherView) findViewById(R.id.daily_weather_4);

        aqi_text = (TextView) findViewById(R.id.aqi);
        hum_text = (TextView) findViewById(R.id.humidity);
        sunrise_text = (TextView) findViewById(R.id.sunrise);
        sunset_text = (TextView) findViewById(R.id.sunset);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        final NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(navView);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beforeRequestWeather(THROUGH_LOCATE);
            }
        }));

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.change_position:
                        Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                        startActivityForResult(intent, 1);
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.start_service:
                        Intent startIntent = new Intent(WeatherActivity.this, SyncService.class);
                        startService(startIntent);
                        Toast.makeText(WeatherActivity.this, "weather started", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawers();
                        return true;
                    case R.id.setting:
                        Intent in = new Intent(WeatherActivity.this, SettingActivity.class);
                        startActivity(in);
                        drawerLayout.closeDrawers();
                        return true;
                }
                return false;
            }
        });

        readCache();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    countyName = data.getStringExtra("countyName");
                    position_text.setText(countyName);
                    Log.d(TAG, "onActivityResult: county_return: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.apply();
                    beforeRequestWeather(THROUGH_CHOOSE_POSITION);
                }
        }
    }

    private void readCache() {
        int minInterval = 1;
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String weatherNow = prefs.getString("weather_now", null);
        long weatherNowLastUpdateTime = prefs.getLong("weather_now_last_update_time", 0);
        String weatherFull = prefs.getString("weather_full", null);
        long weatherFullLastUpdateTime = prefs.getLong("weather_full_last_update_time", 0);
        if (weatherNow != null && System.currentTimeMillis() - weatherNowLastUpdateTime < minInterval * 60 * 1000
                && weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
            Log.d(TAG, "readCache: last nowWeather synced: "
                    + (System.currentTimeMillis() - weatherNowLastUpdateTime) / 1000 + "s ago");
            Log.d(TAG, "readCache: last fullWeather synced: "
                    + (System.currentTimeMillis() - weatherFullLastUpdateTime) / 1000 + "s ago");
            WeatherData wd = Utility.handleCurrentWeatherResponse(weatherNow);
            showCurrentWeatherInfo(wd);
            extendWeatherData[] weatherDatas = handleFullWeatherData(weatherFull);
            showDailyWeatherInfo(weatherDatas);
        } else {
            beforeRequestWeather(THROUGH_LOCATE);
        }
    }

    private void beforeRequestWeather(int requestCode) {
        startSwipe();
        switch (requestCode) {
            case THROUGH_IP:
                GetCoordinateByIp();
                break;
            case THROUGH_CHOOSE_POSITION:
                GetCoordinateByChoosePosition();
                break;
            case THROUGH_LOCATE:
                GetCoordinateByLocate();
                break;
            case THROUGH_COORDINATE:
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                locationCoordinates = prefs.getString("coordinate", null);
                initRequireUrl();
                break;
        }
    }

    private void GetCoordinateByLocate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        } else {
            locate();
        }
    }

    private void locate() {
        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            locationCoordinates = String.valueOf(location.getLongitude()) + ',' + String.valueOf(location.getLatitude());
            Log.d(TAG, "GetCoordinateByLocate: locationCoordinates = " + locationCoordinates);
            SharedPreferences.Editor editor = PreferenceManager
                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
            editor.putString("coordinate", locationCoordinates);
            editor.putLong("coordinate_last_update", System.currentTimeMillis());
            editor.apply();
            BaiduApiUtility.setCountyByCoordinate(locationCoordinates);
            AfterGetCoordinate();
        } else {
            Log.d(TAG, "requestLocation: location == null, switch to IP method");
            GetCoordinateByIp();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locate();
                } else {
                    Log.d(TAG, "onActivityResult: Locate permission denied, switch to ip mode");
                    beforeRequestWeather(THROUGH_IP);
                }
                break;
            default:
        }
    }

    private void GetCoordinateByChoosePosition() {
        if (TextUtils.isEmpty(countyName)) {
            Log.e(TAG, "GetCoordinateByChoosePosition: choosed countyName == null");
        } else {
            String url = "http://api.map.baidu.com/geocoder/v2/?output=json&address=%" + countyName + "&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e(TAG, "GetCoordinateByChoosePosition: failed");
                            stopSwipe();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseText = response.body().string();
                    try {
                        JSONObject res = new JSONObject(responseText);
                        JSONObject JSONresult = res.getJSONObject("result");
                        JSONObject location = JSONresult.getJSONObject("location");
                        locationCoordinates = location.getString("lng") + ',' + location.getString("lat");
                        SharedPreferences.Editor editor = PreferenceManager
                                .getDefaultSharedPreferences(WeatherActivity.this).edit();
                        editor.putString("coordinate", locationCoordinates);
                        editor.putLong("coordinate_last_update", System.currentTimeMillis());
                        editor.apply();
                        AfterGetCoordinate();
                    } catch (JSONException e) {
                        Log.e(TAG, "GetCoordinateByChoosePosition: parse json error");
                        Log.d(TAG, "GetCoordinateByChoosePosition: json result: " + responseText);
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void initRequireUrl() {
        if (!TextUtils.isEmpty(locationCoordinates)) {
            String cUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/realtime.json";
            String fUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/forecast.json";
            SharedPreferences.Editor editor = PreferenceManager
                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
            editor.putString("curl", cUrl);
            editor.putString("furl", fUrl);
            editor.apply();
            requestCurrentWeather(cUrl);
            requestFullWeather(fUrl);
        } else {
            Toast.makeText(WeatherActivity.this, "locationCoordinates = null", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "initRequireUrl: locationCoordinates = null");
        }
    }

    private void requestFullWeather(String url) {
        startSwipe();
        if (TextUtils.isEmpty(url)) {
            Log.d(TAG, "requestFullWeather: url = null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "load full weather failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(WeatherActivity.this).edit();
                editor.putString("weather_full", responseText);
                editor.putLong("weather_full_last_update_time", System.currentTimeMillis());
                editor.apply();
                extendWeatherData[] weatherDatas = handleFullWeatherData(responseText);
                showDailyWeatherInfo(weatherDatas);
            }
        });
    }

    private extendWeatherData[] handleFullWeatherData(String responseText) {
        extendWeatherData[] weatherDatas = new extendWeatherData[5];
        JSONArray[] jsonArrays = Utility.handleDailyWeatherResponse(responseText);
        if (jsonArrays.length == 5) {
            for (int i = 0; i < 5; i++) {
                try {
                    weatherDatas[i] = new extendWeatherData();
                    JSONObject skycon = jsonArrays[0].getJSONObject(i);
                    JSONObject temperatures = jsonArrays[2].getJSONObject(i);
                    JSONObject humidity = jsonArrays[1].getJSONObject(i);
                    JSONObject precipitation = jsonArrays[3].getJSONObject(i);
                    JSONObject astro = jsonArrays[4].getJSONObject(i);
                    weatherDatas[i].setDate(temperatures.getString("date"));
                    weatherDatas[i].setMaxTemperature(temperatures.getString("max"));
                    weatherDatas[i].setMinTemperature(temperatures.getString("min"));
                    weatherDatas[i].setSkycon(skycon.getString("value"));
                    weatherDatas[i].setHumidity(humidity.getString("max"));
                    weatherDatas[i].setIntensity(precipitation.getString("max"));
                    weatherDatas[i].setSunriseTime(astro.getJSONObject("sunrise").getString("time"));
                    weatherDatas[i].setSunsetTime(astro.getJSONObject("sunset").getString("time"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "handleFullWeatherData: parse jsonArrays error");
                }
            }
        }
        return weatherDatas;
    }


    private void requestCurrentWeather(String url) {
        startSwipe();
        if (TextUtils.isEmpty(url)) {
            Log.d(TAG, "requestCurrentWeather: url = null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "load current weather failed", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final WeatherData weatherData = handleCurrentWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherData != null) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather_now", responseText);
                            editor.putLong("weather_now_last_update_time", System.currentTimeMillis());
                            editor.apply();
                            showCurrentWeatherInfo(weatherData);
                        } else {
                            Toast.makeText(WeatherActivity.this, "weatherDate = null", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void showDailyWeatherInfo(final extendWeatherData[] weatherDatas) {
        if (weatherDatas.length == 5) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        String[] simpleDate = weatherDatas[i].getDate().split("-");
                        day[i].setDate(simpleDate[1] + '/' + simpleDate[2]);
                        day[i].setTemperature(Utility.roundString(weatherDatas[i].getMinTemperature()) + '~'
                                + Utility.roundString(weatherDatas[i].getMaxTemperature()) + "ºC");
                        day[i].setIcon(chooseWeatherIconOnly(weatherDatas[i].getSkycon(), Float.parseFloat(weatherDatas[i].getIntensity()), HOURLY_MODE));
                    }
                    day[0].setDate(getResources().getString(R.string.today));
                    day[1].setDate(getResources().getString(R.string.tomorrow));
                    sunrise_text.setText(weatherDatas[0].getSunriseTime());
                    sunset_text.setText(weatherDatas[0].getSunsetTime());
                }
            });
        }
        if (isDone) {
            stopSwipe();
            isDone = false;
        } else {
            isDone = true;
        }
    }

    private void showCurrentWeatherInfo(WeatherData weatherData) {
        String temperature = Utility.roundString(weatherData.getTemperature());
        String skycon = weatherData.getSkycon();
        String humidity = weatherData.getHumidity();
//        String PM25 = weatherData.getPm25();
//        String cloudrate = weatherData.getCloudrate();
        float intensity = Float.parseFloat(weatherData.getIntensity());
        String aqi = weatherData.getAqi();
        if (position_text.getText().length() == 0) {
            position_text.setText(countyName);
        }
        temperature_text.setText(temperature);
        aqi_text.setText(aqi);
        hum_text.setText(humidity.substring(2) + "%");
        String weatherString = chooseWeatherIcon(skycon, intensity, MINUTELY_MODE);
        if (weatherString != null) {
            String[] ws = weatherString.split("and");
//            skycon_image.setImageResource(Integer.parseInt(ws[0]));
            skycon_text.setText(ws[1]);
        }
        appBar.setBackgroundResource(Utility.chooseBgImage(skycon));
        if (isDone) {
            stopSwipe();
            isDone = false;
        } else {
            isDone = true;
        }
    }

    public void GetCoordinateByIp() {
        String url = "http://api.map.baidu.com/location/ip?ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo&coor=bd09ll";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: fetch locationCoordinates through IP failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                try {
                    JSONObject allAttributes = new JSONObject(responseText);
                    JSONObject content = allAttributes.getJSONObject("content");
                    JSONObject address_detail = content.getJSONObject("address_detail");
                    countyName = address_detail.getString("city") + " " + address_detail.getString("district");
                    JSONObject point = content.getJSONObject("point");
                    String x = point.getString("x");
                    String y = point.getString("y");
                    locationCoordinates = x + ',' + y;
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("coordinate", locationCoordinates);
                    editor.putLong("coordinate_last_update", System.currentTimeMillis());
                    editor.apply();
                    Log.d(TAG, "GetCoordinateByIp: locationCoordinates = " + locationCoordinates);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "GetCoordinateByIp: parse IP address json error");
                    Log.d(TAG, "response: " + responseText);
                }
                AfterGetCoordinate();
            }
        });

    }

    private void AfterGetCoordinate() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRequireUrl();
            }
        });
    }

    private String chooseWeatherIcon(String skycon, float intensity, int mode) {
        switch (skycon) {
            case "CLEAR_DAY":
                return R.mipmap.weather_clear + "and" + "晴";
            case "CLEAR_NIGHT":
                return R.mipmap.weather_clear_night + "and" + "晴";
            case "PARTLY_CLOUDY_DAY":
                return R.mipmap.weather_few_clouds + "and" + "多云";
            case "PARTLY_CLOUDY_NIGHT":
                return R.mipmap.weather_few_clouds_night + "and" + "多云";
            case "CLOUDY":
                return R.mipmap.weather_clouds + "and" + "阴";
            case "RAIN":
                switch (mode) {
                    case MINUTELY_MODE:
                        if (intensity <= 0.15)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (intensity <= 0.35)
                            return R.mipmap.weather_rain_day + "and" + "中雨";
                        else
                            return R.mipmap.weather_showers_day + "and" + "大雨";
                    case HOURLY_MODE:
                        if (intensity <= 10)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (intensity <= 25)
                            return R.mipmap.weather_rain_day + "and" + "中雨";
                        else
                            return R.mipmap.weather_showers_day + "and" + "大雨";
                }
            case "SNOW":
                return R.mipmap.weather_snow + "and" + "雪";
            case "WIND":
                return R.mipmap.weather_wind + "and" + "多风";
            case "FOG":
                return R.mipmap.weather_fog + "and" + "雾";
            default:
                return null;
        }
    }

    private int chooseWeatherIconOnly(String skycon, float intensity, int mode) {
        String response = chooseWeatherIcon(skycon, intensity, mode);
        assert response != null;
        String[] responses = response.split("and");
        return Integer.parseInt(responses[0]);
    }

    private void startSwipe() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    private void stopSwipe() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

}
