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
import android.widget.ImageView;
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

public class WeatherActivity extends AppCompatActivity {

    static final String TAG = "WeatherActivity";
    static final int THROUGH_IP = 0;
    static final int THROUGH_CHOOSE_POSITION = 1;
    static final int THROUGH_LOCATE = 2;

    static final int MINUTELY_MODE = 3;
    static final int HOURLY_MODE = 4;

    String countyName = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private String locationCoordinates;
//    private String location;

    private TextView position_text;
    private ImageView skycon_image;
    private TextView temperature_text;
    private TextView skycon_text;

    private perDayWeatherView[] day = new perDayWeatherView[5];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        position_text = (TextView) findViewById(R.id.position_text);
        skycon_image = (ImageView) findViewById(R.id.skycon_image);
        temperature_text = (TextView) findViewById(R.id.temperature_text);
        skycon_text = (TextView) findViewById(R.id.skycon_text);

        day[0] = (perDayWeatherView) findViewById(R.id.daily_weather_0);
        day[1] = (perDayWeatherView) findViewById(R.id.daily_weather_1);
        day[2] = (perDayWeatherView) findViewById(R.id.daily_weather_2);
        day[3] = (perDayWeatherView) findViewById(R.id.daily_weather_3);
        day[4] = (perDayWeatherView) findViewById(R.id.daily_weather_4);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beforeRequestWeather(THROUGH_LOCATE);
            }
        });

        beforeRequestWeather(THROUGH_LOCATE);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.change_position) {
                    Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                    startActivityForResult(intent, 1);
                    drawerLayout.closeDrawers();
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    countyName = data.getStringExtra("countyName");
                    Log.d(TAG, "onActivityResult: county_return == " + countyName);
                    beforeRequestWeather(THROUGH_CHOOSE_POSITION);
                }
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
        }
    }

    private void GetCoordinateByLocate() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        if (location != null) {
            locationCoordinates = String.valueOf(location.getLongitude()) + ',' + String.valueOf(location.getLatitude());
            Log.d(TAG, "GetCoordinateByLocate: locationCoordinates = " + locationCoordinates);
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
                    GetCoordinateByLocate();
                } else {
                    Toast.makeText(this, "you have denied the location permission", Toast.LENGTH_SHORT).show();
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

    private void startSwipe() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
    }

    private void initRequireUrl() {
        if (!TextUtils.isEmpty(locationCoordinates)) {
            String cUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/realtime.json";
            String fUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/forecast.json";
            requestCurrentWeather(cUrl);
            requestFullWeather(fUrl);
            stopSwipe();
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
                JSONArray[] jsonArrays = Utility.handleDailyWeatherResponse(responseText);
                if (jsonArrays.length == 3) {
                    WeatherData[] weatherDatas = new WeatherData[5];
                    for (int i=0; i<5; i++) {
                        try {
                            weatherDatas[i] = new WeatherData();
                            JSONObject skycon = jsonArrays[0].getJSONObject(i);
                            JSONObject temperatures = jsonArrays[2].getJSONObject(i);
                            JSONObject humidity = jsonArrays[1].getJSONObject(i);
                            weatherDatas[i].setDate(temperatures.getString("date"));
                            weatherDatas[i].setMaxTemperature(temperatures.getString("max"));
                            weatherDatas[i].setMinTemperature(temperatures.getString("min"));
                            weatherDatas[i].setSkycon(skycon.getString("value"));
                            weatherDatas[i].setHumidity(humidity.getString("max"));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.e(TAG, "onResponse: consolve jsonArrays error");
                        }
                    }
                    showDailyWeatherInfo(weatherDatas);
                }
            }
        });
    }

    private void showDailyWeatherInfo(final WeatherData[] weatherDatas) {
        if (weatherDatas.length == 5) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    for (int i=0; i<5; i++) {
                        String[] simpleDate = weatherDatas[i].getDate().split("-");
                        day[i].setDate(simpleDate[1] + '/' + simpleDate[2]);
                        day[i].setTemperature(String.valueOf((int)Float.parseFloat(weatherDatas[i].getMinTemperature())) + '~' + (int)Float.parseFloat(weatherDatas[i].getMaxTemperature()));
                        day[i].setIcon(chooseWeatherOnlyIcon(weatherDatas[i].getSkycon(), Float.parseFloat(weatherDatas[i].getHumidity()), HOURLY_MODE));
                    }
                    day[0].setDate(getResources().getString(R.string.today));
                    day[1].setDate(getResources().getString(R.string.tomorrow));
                }
            });
        }
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
                final WeatherData weatherData = Utility.handleCurrentWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherData != null) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather_now", responseText);
                            editor.putLong("last_update_time", System.currentTimeMillis());
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

    private void showCurrentWeatherInfo(WeatherData weatherData) {
        String temperature = String.valueOf((int)Float.parseFloat(weatherData.getTemperature()));
        String skycon = weatherData.getSkycon();
//        String PM25 = weatherData.getPm25();
//        String cloudrate = weatherData.getCloudrate();
        float humidity = Float.parseFloat(weatherData.getHumidity());
//        String aqi = weatherData.getAqi();
        if (position_text.getText().length() == 0) {
            position_text.setText(countyName);
        }
        temperature_text.setText(temperature);
        String weatherString = chooseWeatherIcon(skycon, humidity, MINUTELY_MODE);
        if (weatherString != null) {
//            Log.d(TAG, "showCurrentWeatherInfo: weatherString: " + weatherString);
            String[] ws = weatherString.split("and");
            skycon_image.setImageResource(Integer.parseInt(ws[0]));
            skycon_text.setText(ws[1]);
        }

        stopSwipe();
    }

    private void stopSwipe() {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
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

    private String chooseWeatherIcon(String skycon, float precipitation, int mode) {
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
                        if (precipitation <= 0.15)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (precipitation <= 0.35)
                            return R.mipmap.weather_rain_day + "and" + "中雨";
                        else
                            return R.mipmap.weather_showers_day + "and" + "大雨";
                    case HOURLY_MODE:
                        if (precipitation <= 10)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (precipitation <= 25)
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

    private int chooseWeatherOnlyIcon(String skycon, float precipitation, int mode) {
        String response = chooseWeatherIcon(skycon, precipitation, mode);
        String[] responses = response.split("and");
        return Integer.parseInt(responses[0]);
    }

}
