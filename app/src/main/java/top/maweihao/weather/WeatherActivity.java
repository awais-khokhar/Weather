package top.maweihao.weather;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

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
                requestWeather();
            }
        });

//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        requestWeather();

    }

    private String initUrl() {
        return "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/118.933290,32.111416/realtime.json";
    }

    private void requestWeather() {

        if (!swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(true);
        }

        String mUrl = initUrl();

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
                Log.d(TAG, "responseText: " + responseText);
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

        tv.setText(temperature + '\n' + skycon + '\n' + "PM2.5: " + PM25 + '\n'
                + "cloudrate: " + cloudrate + '\n' + "humidity: " + humidity + '\n' + "aqi: " + aqi);

        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }
    }


}
