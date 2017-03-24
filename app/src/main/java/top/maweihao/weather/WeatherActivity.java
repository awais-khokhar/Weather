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

    public static final String TAG = "WeatherActivity";
    public static final int THROUGH_IP = 0;
    public static final int THROUGH_CHOOSE_POSITION = 1;
    public static final int THROUGH_LOCATE = 2;
    String countyName = null;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private String locationCoordinates;
//    private String location;

    private TextView position_text;
    private ImageView skycon_image;
    private TextView temperature_text;
    private TextView skycon_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        position_text = (TextView) findViewById(R.id.position_text);
        skycon_image = (ImageView) findViewById(R.id.skycon_image);
        temperature_text = (TextView) findViewById(R.id.temperature_text);
        skycon_text = (TextView) findViewById(R.id.skycon_text);
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
                    Toast.makeText(this, "you have denied the location permission", Toast.LENGTH_SHORT);
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
//        return "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/118.933290,32.111416/realtime.json";
        if (!TextUtils.isEmpty(locationCoordinates)) {
            String mUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/realtime.json";
            requestCurrentWeather(mUrl);
            stopSwipe();
        } else {
            Toast.makeText(WeatherActivity.this, "locationCoordinates = null", Toast.LENGTH_SHORT).show();
            Log.e(TAG, "initRequireUrl: locationCoordinates = null");
        }
    }

    private void requestCurrentWeather(String mUrl) {
        startSwipe();
        if (TextUtils.isEmpty(mUrl)) {
            Log.d(TAG, "requestCurrentWeather: murl = null");
            return;
        }
        HttpUtil.sendOkHttpRequest(mUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WeatherActivity.this, "load failed", Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
//                Log.d(TAG, "responseText: " + responseText);
                final WeatherData weatherData = Utility.handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weatherData != null) {
                            SharedPreferences.Editor editor = PreferenceManager
                                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
                            editor.putString("weather_now", responseText);
                            editor.putLong("last_update_time", System.currentTimeMillis());
                            editor.apply();
                            showWeatherInfo(weatherData);
                        } else {
                            Toast.makeText(WeatherActivity.this, "weatherDate = null", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private void showWeatherInfo(WeatherData weatherData) {
        String temperature = weatherData.getTemperature();
        String skycon = weatherData.getSkycon();
//        String PM25 = weatherData.getPm25();
//        String cloudrate = weatherData.getCloudrate();
//        String humidity = weatherData.getHumidity();
//        String aqi = weatherData.getAqi();
        if (position_text.getText().length() == 0) {
            position_text.setText(countyName);
        }
        skycon_text.setText(skycon);
        temperature_text.setText(temperature);
        switch (skycon) {
            case "CLEAR_DAY":
                skycon_image.setImageResource(R.mipmap.weather_clear);
                skycon_text.setText("晴");
                break;
            case "CLEAR_NIGHT":
                skycon_image.setImageResource(R.mipmap.weather_clear_night);
                skycon_text.setText("晴");
                break;
            case "PARTLY_CLOUDY_DAY":
                skycon_image.setImageResource(R.mipmap.weather_few_clouds);
                skycon_text.setText("多云");
                break;
            case "PARTLY_CLOUDY_NIGHT":
                skycon_image.setImageResource(R.mipmap.weather_few_clouds_night);
                skycon_text.setText("多云");
                break;
            case "CLOUDY":
                skycon_image.setImageResource(R.mipmap.weather_clouds);
                skycon_text.setText("阴");
                break;
            case "RAIN":
                skycon_image.setImageResource(R.mipmap.weather_drizzle_day);
                skycon_text.setText("雨");
                break;
            case "SNOW":
                skycon_image.setImageResource(R.mipmap.weather_snow);
                skycon_text.setText("雪");
                break;
            case "WIND":
                skycon_image.setImageResource(R.mipmap.weather_wind);
                skycon_text.setText("多风");
                break;
            case "FOG":
                skycon_image.setImageResource(R.mipmap.weather_fog);
                skycon_text.setText("雾");
                break;
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


}
