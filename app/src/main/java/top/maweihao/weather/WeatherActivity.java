package top.maweihao.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

public class WeatherActivity extends AppCompatActivity {

    public static final String TAG = "WeatherActivity";
    private TextView tv;
    private SwipeRefreshLayout swipeRefreshLayout;
    private String locationCoordinates;
    private String mUrl;
    private String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        tv = (TextView) findViewById(R.id.main_text);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                beforeRequestWeather();
            }
        });

        beforeRequestWeather();

    }

    private void beforeRequestWeather() {
        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }
        locateIP();
    }

    private void initUrl() {
//        return "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/118.933290,32.111416/realtime.json";
        //locateIP();
        if (!TextUtils.isEmpty(locationCoordinates)) {
            mUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/realtime.json";
            requestWeather(mUrl);
        } else {
            Toast.makeText(WeatherActivity.this, "locationCoordinates = null", Toast.LENGTH_SHORT).show();
        }
    }

    private void requestWeather(String mUrl) {

        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

        if (TextUtils.isEmpty(mUrl)) {
            Toast.makeText(WeatherActivity.this, "murl = null", Toast.LENGTH_LONG).show();
            if (swipeRefreshLayout.isRefreshing()) {
                swipeRefreshLayout.setRefreshing(false);
            }
            return ;
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
        String PM25 = weatherData.getPm25();
        String cloudrate = weatherData.getCloudrate();
        String humidity = weatherData.getHumidity();
        String aqi = weatherData.getAqi();

        tv.setText(temperature + "℃\n" + skycon + '\n' + "PM2.5: " + PM25 + '\n'
                + "cloudrate: " + cloudrate + '\n' + "humidity: " + humidity + '\n' + "aqi: " + aqi);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    public void locateIP() {
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
                    JSONObject point = content.getJSONObject("point");
                    String x = point.getString("x");
                    String y = point.getString("y");
                    locationCoordinates = x + ',' + y;
                    Log.d(TAG, "locateIP: locationCoordinates = " + locationCoordinates);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.d(TAG, "responseText: " + responseText);
                    Log.e(TAG, "locateIP: parse IP address json error");
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initUrl();
                    }
                });
            }
        });

    }


}
