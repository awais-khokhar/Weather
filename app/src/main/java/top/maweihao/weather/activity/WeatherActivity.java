package top.maweihao.weather.activity;

import android.Manifest;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
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
import android.view.View;
import android.widget.ImageView;
import android.widget.RemoteViews;
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
import top.maweihao.weather.ExtendedWeatherData;
import top.maweihao.weather.R;
import top.maweihao.weather.widget.SimpleWeatherWidget;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.gson.HourlyWeather;
import top.maweihao.weather.view.perDayWeatherView;
import top.maweihao.weather.service.SyncService;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.view.HScrollView;
import top.maweihao.weather.view.LineChartView;
import top.maweihao.weather.view.SemiCircleView;
import top.maweihao.weather.view.SunTimeView;

import static top.maweihao.weather.util.Utility.chooseWeatherIcon;
import static top.maweihao.weather.util.Utility.chooseWeatherIconOnly;
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
    static final int HANDLE_TOAST = 1;
    static final int HANDLE_SWIPE_BEGIN = 2;
    static final int HANDLE_SWIPE_STOP = 3;

    private boolean isDone = false;
    private String countyName = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private CardView appBar;
    private String locationCoordinates;
    private perDayWeatherView[] day = new perDayWeatherView[5];

    private TextView temperature_text;
    private TextView skycon_text;
    private TextView hum_text;
    private TextView sunrise_text;
    private TextView sunset_text;
    private TextView windDirection_text;
    private TextView windLevel_text;
    private TextView uv_text;
    private TextView carWashing_text;
    private TextView dressing_text;
    private TextView rainInfo;
    private ImageView locateModeImage;
    private TextView locateMode;
    private TextView lastUpdateTime;
    private SunTimeView sunTimeView;
    private SemiCircleView AQICircle;
    private SemiCircleView PMCircle;

    private Boolean autoLocate;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
                switch (msg.what) {
                    case HANDLE_POSITION:
                        if (msg.obj instanceof String) {
                            if (getSupportActionBar() != null) {
                                getSupportActionBar().setTitle((String) msg.obj);
                            } else {
                                Log.e(TAG, "handleMessage: toolBar == null");
                            }
                        } else {
                            Log.e(TAG, "handleMessage: HANDLE_POSITION obj == " + msg.obj.getClass());
                        }
                        break;
                    case HANDLE_TOAST:
                        if (msg.obj instanceof String) {
                            Toast.makeText(WeatherActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "handleMessage: HANDLE_TOAST obj == " + msg.obj.getClass());
                        }
                        break;
                }
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        rainInfo = (TextView) findViewById(R.id.rain_info_tv);
        temperature_text = (TextView) findViewById(R.id.temperature_text);
        skycon_text = (TextView) findViewById(R.id.skycon_text);
        appBar = (CardView) findViewById(R.id.app_bar);

        day[0] = (perDayWeatherView) findViewById(R.id.daily_weather_0);
        day[1] = (perDayWeatherView) findViewById(R.id.daily_weather_1);
        day[2] = (perDayWeatherView) findViewById(R.id.daily_weather_2);
        day[3] = (perDayWeatherView) findViewById(R.id.daily_weather_3);
        day[4] = (perDayWeatherView) findViewById(R.id.daily_weather_4);

        hum_text = (TextView) findViewById(R.id.humidity);
        sunrise_text = (TextView) findViewById(R.id.sunrise);
        sunset_text = (TextView) findViewById(R.id.sunset);
        windDirection_text = (TextView) findViewById(R.id.wind_direction_tv);
        windLevel_text = (TextView) findViewById(R.id.wind_level_tv);
        uv_text = (TextView) findViewById(R.id.uv);
        carWashing_text = (TextView) findViewById(R.id.carwash);
        dressing_text = (TextView) findViewById(R.id.dressing);
        locateModeImage = (ImageView) findViewById(R.id.lacate_mode_image);
        locateMode = (TextView) findViewById(R.id.locate_mode);
        lastUpdateTime = (TextView) findViewById(R.id.last_update_time);
        sunTimeView = (SunTimeView) findViewById(R.id.stv);
        AQICircle = (SemiCircleView) findViewById(R.id.AQI_Circle);
        PMCircle = (SemiCircleView) findViewById(R.id.PM_Circle);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setDistanceToTriggerSync(200);

    }

    @Override
    protected void onStart() {
        super.onStart();
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
                    Log.d(TAG, "onActivityResult: county_return: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.putBoolean("auto_locate", false);
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

        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beforeRequestWeather(autoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
            }
        }));
    }

    private void readCache() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int minInterval = prefs.getInt("refresh_interval", 10);
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
            lastUpdateTime.setText(Utility.getTime(getApplicationContext(), weatherNowLastUpdateTime));
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
                locateModeImage.setImageResource(R.drawable.ic_location_on_black_24dp);
                locateMode.setText("IP");
                locateModeImage.setVisibility(View.VISIBLE);
                locateMode.setVisibility(View.VISIBLE);
                GetCoordinateByIp();
                break;
            case THROUGH_CHOOSE_POSITION:
                locateModeImage.setImageResource(R.drawable.ic_location_off_black_24dp);
                locateModeImage.setVisibility(View.VISIBLE);
                locateMode.setVisibility(View.INVISIBLE);
                GetCoordinateByChoosePosition();
                break;
            case THROUGH_LOCATE:
                locateModeImage.setImageResource(R.drawable.ic_location_on_black_24dp);
                locateModeImage.setVisibility(View.VISIBLE);
                locateMode.setVisibility(View.INVISIBLE);
                GetCoordinateByLocate();
                break;
            case THROUGH_COORDINATE:
                locateModeImage.setVisibility(View.INVISIBLE);
                locateMode.setVisibility(View.INVISIBLE);
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
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
        }
//        ArrayList<String> providers = (ArrayList<String>) mLocationManager.getProviders(false);
//        for (String s: providers) {
//            Log.d(TAG, "locate: " + s);
//        }
//        Criteria criteria = new Criteria();
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//        Log.d(TAG, "locate: best " + mLocationManager.getBestProvider(criteria, true));
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
            beforeRequestWeather(THROUGH_IP);
        }
    }

    private void setCountyByCoordinate(String coordinate) {
        String url;
        if (!TextUtils.isEmpty(coordinate)) {
            String[] part = coordinate.split(",");
            String reverseCoordinate = part[1] + ',' + part[0];
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
            final Message message = handler.obtainMessage();
            message.what = HANDLE_POSITION;
            message.obj = countyName;
            handler.sendMessage(message);
            String url = "http://api.map.baidu.com/geocoder/v2/?output=json&address=%" + countyName + "&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
            HttpUtil.sendOkHttpRequest(url, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                    Log.e(TAG, "GetCoordinateByChoosePosition: failed");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
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
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lastUpdateTime.setText(Utility.getTime(getApplicationContext()));
                    }
                });
                editor.apply();
                handleFullWeatherData(responseText);
            }
        });
    }

    private void handleFullWeatherData(String responseText) {
        ArrayList<ExtendedWeatherData> weatherDatas = new ArrayList<>(5);
        ArrayList<HourlyWeather> hourlyWeathers = new ArrayList<>(24);
        ArrayList<JSONArray> jsonArrays = moreHandleDailyWeatherResponse(responseText);
        if (jsonArrays.size() == 11) {
            for (int i = 0; i < 5; i++) {
                try {
                    ExtendedWeatherData wd = new ExtendedWeatherData();
                    JSONObject skycon = jsonArrays.get(0).getJSONObject(i);
                    JSONObject humidity = jsonArrays.get(1).getJSONObject(i);
                    JSONObject temperatures = jsonArrays.get(2).getJSONObject(i);
                    JSONObject precipitation = jsonArrays.get(3).getJSONObject(i);
                    JSONObject astro = jsonArrays.get(4).getJSONObject(i);
                    JSONObject uv = jsonArrays.get(8).getJSONObject(i);
                    JSONObject dressing = jsonArrays.get(9).getJSONObject(i);
                    JSONObject carWashing = jsonArrays.get(10).getJSONObject(i);
                    wd.setDate(temperatures.getString("date"));
                    wd.setMaxTemperature(temperatures.getString("max"));
                    wd.setMinTemperature(temperatures.getString("min"));
                    wd.setSkycon(skycon.getString("value"));
                    wd.setHumidity(humidity.getString("max"));
                    wd.setIntensity(precipitation.getString("max"));
                    wd.setSunriseTime(astro.getJSONObject("sunrise").getString("time"));
                    wd.setSunsetTime(astro.getJSONObject("sunset").getString("time"));
                    wd.setUvIndex(uv.getInt("index"));
                    wd.setUvDesc(uv.getString("desc"));
                    wd.setDerssingIndex(dressing.getInt("index"));
                    wd.setDressingDesc(dressing.getString("desc"));
                    wd.setCarWashingIndex(carWashing.getInt("index"));
                    wd.setCarWashingDesc(carWashing.getString("desc"));
                    weatherDatas.add(i, wd);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "handleFullWeatherData: parse jsonArrays error");
                }
            }
            for (int i = 0; i < 24; i++) {
                try {
                    HourlyWeather hw = new HourlyWeather();
                    JSONObject skyon = jsonArrays.get(5).getJSONObject(i);
                    JSONObject temperatures = jsonArrays.get(6).getJSONObject(i);
                    JSONObject precipitation = jsonArrays.get(7).getJSONObject(i);
                    hw.setDatetime(skyon.getString("datetime"));
                    hw.setSkyon(skyon.getString("value"));
                    hw.setPrecipitation(precipitation.getString("value"));
                    hw.setTemperature(temperatures.getString("value"));
                    hourlyWeathers.add(i, hw);
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
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherData != null) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather_now", responseText);
                            editor.putLong("weather_now_last_update_time", System.currentTimeMillis());
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lastUpdateTime.setText(Utility.getTime(getApplicationContext()));
                                }
                            });
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

    private void showHourlyWeatherInfo(final ArrayList<HourlyWeather> hourlyWeathers) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                HScrollView hScrollView = (HScrollView) findViewById(R.id.HScrollView);
                LineChartView mLineChartView = (LineChartView) findViewById(R.id.simpleLineChart);
                ArrayList<String> xItemArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    xItemArray.add(hourlyWeather.getDatetime().substring(11, 16));
                }
                //天气
                ArrayList<String> weatherArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    weatherArray.add(hourlyWeather.getSkyon());
                }
                //温度
                ArrayList<Integer> yItemArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    yItemArray.add(intRoundString(hourlyWeather.getTemperature()));
                }

                ArrayList<Float> precipitation = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    precipitation.add(Float.parseFloat(hourlyWeather.getPrecipitation()));
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

    private void showDailyWeatherInfo(final ArrayList<ExtendedWeatherData> weatherDatas) {
        if (weatherDatas.size() == 5) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        String[] simpleDate = weatherDatas.get(i).getDate().split("-");
                        day[i].setDate(simpleDate[1] + '/' + simpleDate[2]);
                        day[i].setTemperature(Utility.roundString(weatherDatas.get(i).getMinTemperature()) + '/'
                                + Utility.roundString(weatherDatas.get(i).getMaxTemperature()) + "ºC");
                        day[i].setIcon(chooseWeatherIconOnly(weatherDatas.get(i).getSkycon(), Float.parseFloat(weatherDatas.get(i).getIntensity()), HOURLY_MODE));
                    }
                    day[0].setDate(getResources().getString(R.string.today));
                    day[1].setDate(getResources().getString(R.string.tomorrow));
                    sunrise_text.setText(weatherDatas.get(0).getSunriseTime());
                    sunset_text.setText(weatherDatas.get(0).getSunsetTime());
                    sunTimeView.setTime(weatherDatas.get(0).getSunriseTime(), weatherDatas.get(0).getSunsetTime());
                    uv_text.setText(weatherDatas.get(0).getUvDesc());
                    carWashing_text.setText(weatherDatas.get(0).getCarWashingDesc());
                    dressing_text.setText(weatherDatas.get(0).getDressingDesc());
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
        final RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);
        String temperature = Utility.roundString(weatherData.getTemperature());
        String skycon = weatherData.getSkycon();
        String humidity = weatherData.getHumidity();
        String PM25 = weatherData.getPm25();
        float intensity = Float.parseFloat(weatherData.getIntensity());
        String aqi = weatherData.getAqi();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String countyName = prefs.getString("countyName", null);
        if (weatherData.getWind() != null) {
            setWindDirection(weatherData.getWind().getDirection());
            setWindLevel(weatherData.getWind().getSpeed());
        }
        if (!TextUtils.isEmpty(countyName)) {
            Message message = handler.obtainMessage();
            message.what = HANDLE_POSITION;
            message.obj = countyName;
            handler.sendMessage(message);
        } else {
            Log.d(TAG, "showCurrentWeatherInfo: countyName == null");
        }
        PMCircle.setValue(Integer.valueOf(PM25));
        temperature_text.setText(temperature);
        AQICircle.setValue(Integer.valueOf(aqi));
        Float hum = Float.parseFloat(humidity) * 100;
        hum_text.setText(hum.toString().substring(0, 2) + "%");
        String weatherString = chooseWeatherIcon(skycon, intensity, MINUTELY_MODE);
        if (weatherString != null) {
            String[] ws = weatherString.split("and");
            skycon_text.setText(ws[1]);
            remoteViews.setImageViewResource(R.id.widget_clock_day_icon, Integer.parseInt(ws[0]));
            remoteViews.setTextViewText(R.id.widget_clock_day_subtitle, countyName + " | " + ws[1] + ' ' + temperature + '°');
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class),
                remoteViews);
        appBar.setBackgroundResource(Utility.chooseBgImage(skycon));
        if (isDone) {
            stopSwipe();
            isDone = false;
        } else {
            isDone = true;
        }
    }

    private ArrayList<JSONArray> moreHandleDailyWeatherResponse(String url) {
        try {
            JSONObject all = new JSONObject(url);
            JSONObject result = all.getJSONObject("result");
            final JSONObject minutely = result.getJSONObject("minutely");
            final String des = minutely.getString("description");
            handler.post(new Runnable() {
                @Override
                public void run() {
                    rainInfo.setText(des);
                }
            });
        } catch (JSONException e) {
            Log.e(TAG, "moreHandleDailyWeatherResponse: parse weather json error");
            e.printStackTrace();
        }

        return Utility.handleDailyWeatherResponse(url);
    }

    private void AfterGetCoordinate() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                initRequireUrl();
            }
        });
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

    private void setWindDirection(float direction) {
        String dir;
        if (direction <= 22.5 || direction >= 337.5) {
            dir = getResources().getString(R.string.north);
        } else if (direction <= 67.5) {
            dir = getResources().getString(R.string.northeast);
        } else if (direction <= 112.5) {
            dir = getResources().getString(R.string.east);
        } else if (direction <= 157.5) {
            dir = getResources().getString(R.string.southeast);
        } else if (direction <= 202.5) {
            dir = getResources().getString(R.string.south);
        } else if (direction <= 247.5) {
            dir = getResources().getString(R.string.southwest);
        } else if (direction <= 292.5) {
            dir = getResources().getString(R.string.west);
        } else {
            dir = getResources().getString(R.string.northwest);
        }
        windDirection_text.setText(dir);
    }

    private void setWindLevel(float speed) {
        int level;
        String info;
        if (speed <= 0.72) {
            level = 0;
            info = "无风";
        } else if (speed <= 5.4) {
            level = 1;
            info = "软风";
        } else if (speed <= 11.88) {
            level = 2;
            info = "轻风";
        } else if (speed <= 19.44) {
            level = 3;
            info = "微风";
        } else if (speed <= 28.44) {
            level = 4;
            info = "和风";
        } else if (speed <= 38.52) {
            level = 5;
            info = "劲风";
        } else if (speed <= 49.68) {
            level = 6;
            info = "强风";
        } else if (speed <= 61.56) {
            level = 7;
            info = "疾风";
        } else if (speed <= 74.52) {
            level = 8;
            info = "大风";
        } else if (speed <= 87.84) {
            level = 9;
            info = "烈风";
        } else if (speed <= 102.24) {
            level = 10;
            info = "狂风";
        } else if (speed <= 117.36) {
            level = 11;
            info = "暴风";
        } else {
            level = 12;
            info = "飓风";
        }
        if (Utility.isChinese(getApplicationContext())) {
            windLevel_text.setText(level + " 级" + info);
        } else {
            windLevel_text.setText("LEVEL " + level);
        }
    }

}
