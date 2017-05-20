package top.maweihao.weather;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.gson.HourlyWeather;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.view.HScrollView;
import top.maweihao.weather.view.LineChartView;
import top.maweihao.weather.view.SemiCircleView;
import top.maweihao.weather.view.SunTimeView;

import static top.maweihao.weather.util.Utility.handleCurrentWeatherResponse;
import static top.maweihao.weather.util.Utility.intRoundString;

public class WeatherActivity extends AppCompatActivity {

    static final String TAG = "WeatherActivity";
    static final int THROUGH_IP = 0;
    static final int THROUGH_CHOOSE_POSITION = 1;
    static final int THROUGH_LOCATE = 2;
    static final int THROUGH_COORDINATE = 3;
    public static final int MINUTELY_MODE = 4;
    public static final int HOURLY_MODE = 5;

    static final int HANDLE_POSITION = 0;

    private boolean isDone = false;
    private String countyName = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CardView appBar;
    private String locationCoordinates;
    private perDayWeatherView[] day = new perDayWeatherView[5];

    private Toolbar toolbar;
    private TextView PM25_tv;
    private TextView temperature_text;
    private TextView skycon_text;
    private TextView aqi_text;
    private TextView hum_text;
    private TextView sunrise_text;
    private TextView sunset_text;
    private SunTimeView sunTimeView;
    private SemiCircleView AQICircle;
    private SemiCircleView PMCircle;

    private static Handler handler;

    private Boolean autoLocate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        PM25_tv = (TextView) findViewById(R.id.pm25_Tv);
        temperature_text = (TextView) findViewById(R.id.temperature_text);
        skycon_text = (TextView) findViewById(R.id.skycon_text);
        appBar = (CardView) findViewById(R.id.app_bar);

        day[0] = (perDayWeatherView) findViewById(R.id.daily_weather_0);
        day[1] = (perDayWeatherView) findViewById(R.id.daily_weather_1);
        day[2] = (perDayWeatherView) findViewById(R.id.daily_weather_2);
        day[3] = (perDayWeatherView) findViewById(R.id.daily_weather_3);
        day[4] = (perDayWeatherView) findViewById(R.id.daily_weather_4);

        aqi_text = (TextView) findViewById(R.id.aqi);
        hum_text = (TextView) findViewById(R.id.humidity);
        sunrise_text = (TextView) findViewById(R.id.sunrise);
        sunset_text = (TextView) findViewById(R.id.sunset);
        sunTimeView = (SunTimeView) findViewById(R.id.stv);
        AQICircle = (SemiCircleView) findViewById(R.id.AQI_Circle);
        PMCircle = (SemiCircleView) findViewById(R.id.PM_Circle);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beforeRequestWeather(THROUGH_LOCATE);
            }
        }));

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLE_POSITION:
                        if (msg.obj instanceof String) {
                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setTitle(countyName);
                            } else {
                                Log.e(TAG, "handleMessage: toolBar == null");
                            }
                        }
                        break;
                }
            }
        };

        loadPreferences();

        readCache();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_position:
                Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.start_service:
                Intent startIntent = new Intent(WeatherActivity.this, SyncService.class);
                startService(startIntent);
                break;
            case R.id.setting:
                Intent intent1 = new Intent(WeatherActivity.this, SettingActivity.class);
                startActivityForResult(intent1, 2);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
            case 2:
                if (resultCode == RESULT_OK) {
                    countyName = data.getStringExtra("countyName");
                    Message message = handler.obtainMessage();
                    message.what = HANDLE_POSITION;
                    message.obj = countyName;
                    handler.sendMessage(message);
//                        getSupportActionBar().setTitle(countyName);
                    Log.d(TAG, "onActivityResult: county_return: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.putBoolean("auto_locate_sp", false);
                    editor.apply();
                    beforeRequestWeather(THROUGH_CHOOSE_POSITION);
                }
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locate();
                } else {
                    Toast.makeText(this, getResources().getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "onActivityResult: Locate permission denied, switch to ip mode");
                    beforeRequestWeather(THROUGH_IP);
                }
                break;
            default:
        }
    }

    private void loadPreferences() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        autoLocate = prefs.getBoolean("auto_locate", true);
        countyName = prefs.getString("countyName", null);
    }

    private void readCache() {
        int minInterval = 5;
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
            handleFullWeatherData(weatherFull);
        } else {
            beforeRequestWeather(autoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
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
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
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
            setCountyByCoordinate(locationCoordinates);
            AfterGetCoordinate();
        } else {
            Log.d(TAG, "requestLocation: location == null, switch to IP method");
            GetCoordinateByIp();
        }
    }

    private void setCountyByCoordinate(String coordinate) {
        String url;
        if (!TextUtils.isEmpty(coordinate)) {
            String[] part = coordinate.split(",");
            String reverseCoordinate = part[1] + ',' + part[0];
//            Log.d(TAG, "WeatherActivity: reverseCoordinate: " + reverseCoordinate);
            url = "http://api.map.baidu.com/geocoder/v2/?location=" + reverseCoordinate + "&output=json&pois=1&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
        } else {
            Log.e(TAG, "WeatherActivity::setCountyByCoordinate: coordinate == null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                try {
                    JSONObject text = new JSONObject(responseText);
                    JSONObject result = text.getJSONObject("result");
                    JSONObject addressComponent = result.getJSONObject("addressComponent");
                    countyName = addressComponent.getString("district");
                    Log.d(TAG, "setCountyByCoordinate.onResponse: countyName: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.apply();
                    Message message = handler.obtainMessage();
                    message.what = HANDLE_POSITION;
                    message.obj = countyName;
                    handler.sendMessage(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException ee) {
                    Log.e(TAG, "onResponse: toolBar not found");
                    ee.printStackTrace();
                }
            }
        });
    }

    private void GetCoordinateByChoosePosition() {
        if (TextUtils.isEmpty(countyName)) {
            Log.e(TAG, "GetCoordinateByChoosePosition: choosed countyName == null");
            Toast.makeText(WeatherActivity.this, getResources().getString(R.string.choose_your_position),
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
            startActivityForResult(intent, 1);
        } else {
            Message message = handler.obtainMessage();
            message.what = HANDLE_POSITION;
            message.obj = countyName;
            handler.sendMessage(message);
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

    public void GetCoordinateByIp() {
        String url = "http://api.map.baidu.com/location/ip?ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo&coor=bd09ll";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: fetch locationCoordinates by IP failed");
//                Toast.makeText(WeatherActivity.this, getResources().getString(R.string.access_network_failed), Toast.LENGTH_SHORT).show();
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
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
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
                handleFullWeatherData(responseText);
            }
        });
    }

    private void handleFullWeatherData(String responseText) {
        extendWeatherData[] weatherDatas = new extendWeatherData[5];
        HourlyWeather[] hourlyWeathers = new HourlyWeather[24];
        JSONArray[] jsonArrays = Utility.handleDailyWeatherResponse(responseText);
        if (jsonArrays.length == 8) {
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
            for (int i = 0; i < 24; i++) {
                try {
                    hourlyWeathers[i] = new HourlyWeather();
                    JSONObject skyon = jsonArrays[5].getJSONObject(i);
                    JSONObject temperatures = jsonArrays[6].getJSONObject(i);
                    JSONObject precipitation = jsonArrays[7].getJSONObject(i);
                    hourlyWeathers[i].setDatetime(skyon.getString("datetime"));
                    hourlyWeathers[i].setSkyon(skyon.getString("value"));
                    hourlyWeathers[i].setPrecipitation(precipitation.getString("value"));
                    hourlyWeathers[i].setTemperature(temperatures.getString("value"));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "handleFullWeatherData: parse jsonArrays error(hourly)");
                }
            }
            showDailyWeatherInfo(weatherDatas);
            showHourlyWeatherInfo(hourlyWeathers);
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

    private void showHourlyWeatherInfo(final HourlyWeather[] hourlyWeathers) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                HScrollView hScrollView = (HScrollView) findViewById(R.id.HScrollView);
                LineChartView mLineChartView = (LineChartView) findViewById(R.id.simpleLineChart);
                ArrayList xItemArray = new ArrayList<String>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    xItemArray.add(hourlyWeather.getDatetime().substring(11, 16));
                }
                //天气
                ArrayList<String> weatherArray = new ArrayList<>();
                for (int i = 0; i < hourlyWeathers.length; i++) {
                    weatherArray.add(hourlyWeathers[i].getSkyon());
                }
                //温度
                ArrayList<Integer> yItemArray = new ArrayList<>();
                for (int i = 0; i < hourlyWeathers.length; i++) {
                    yItemArray.add(intRoundString(hourlyWeathers[i].getTemperature()));
                }

                ArrayList<Float> precipitation = new ArrayList<>();
                for (int i = 0; i < hourlyWeathers.length; i++) {
                    precipitation.add(Float.parseFloat(hourlyWeathers[i].getPrecipitation()));
                }

                mLineChartView.setXItem(xItemArray);
                mLineChartView.setYItem(yItemArray);
                mLineChartView.setWeather(weatherArray);
                mLineChartView.setPrecipitation(precipitation);
                mLineChartView.setmHScrollView(hScrollView);
                mLineChartView.applyChanges();
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
                    sunTimeView.setTime(weatherDatas[0].getSunriseTime(), weatherDatas[0].getSunsetTime());
                    if (isDone) {
                        stopSwipe();
                        isDone = false;
                    } else {
                        isDone = true;
                    }
                }
            });
        }
    }

    private void showCurrentWeatherInfo(WeatherData weatherData) {
        String temperature = Utility.roundString(weatherData.getTemperature());
        String skycon = weatherData.getSkycon();
        String humidity = weatherData.getHumidity();
        String PM25 = weatherData.getPm25();
        float intensity = Float.parseFloat(weatherData.getIntensity());
        String aqi = weatherData.getAqi();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String countyName = prefs.getString("countyName", null);
        if (!TextUtils.isEmpty(countyName)) {
            Message message = handler.obtainMessage();
            message.what = HANDLE_POSITION;
            message.obj = countyName;
            handler.sendMessage(message);
        } else {
            Log.d(TAG, "showCurrentWeatherInfo: countyName == null");
        }
//        PM25_tv.setText(getResources().getString(R.string.pm25) + PM25);
        PMCircle.setValue(Integer.valueOf(PM25));
        temperature_text.setText(temperature);
//        aqi_text.setText(aqi);
        AQICircle.setValue(Integer.valueOf(aqi));
        Float hum = Float.parseFloat(humidity) * 100;
        hum_text.setText(hum.toString().substring(0, 2) + "%");
        String weatherString = chooseWeatherIcon(skycon, intensity, MINUTELY_MODE);
        if (weatherString != null) {
            String[] ws = weatherString.split("and");
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

    private void AfterGetCoordinate() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initRequireUrl();
            }
        });
    }

    public static String chooseWeatherIcon(String skycon, float precipitation, int mode) {
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

    public static int chooseWeatherIconOnly(String skycon, float precipitation, int mode) {
        String response = chooseWeatherIcon(skycon, precipitation, mode);
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
