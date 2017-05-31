package top.maweihao.weather.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.maweihao.weather.R;
import top.maweihao.weather.widget.SimpleWeatherWidget;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.util.Utility;

public class SimpleWidgetUpdateService extends Service {

    public static final String TAG = "sWidgetUpdateService";

    public SimpleWidgetUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        updateWidget();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {

        final RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);

        new Thread(new Runnable() {
            @Override
            public void run() {
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                int minInterval = prefs.getInt("refresh_interval", 5);
                String weatherNow = prefs.getString("weather_now", null);
                long weatherNowLastUpdateTime = prefs.getLong("weather_now_last_update_time", 0);
                String countyName = prefs.getString("countyName", "error");
                if (weatherNow == null || System.currentTimeMillis() - weatherNowLastUpdateTime < minInterval * 60 * 1000) {
                    String cUrl = prefs.getString("cUrl", null);
                    if (cUrl != null) {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(cUrl).build();
                            Response response = client.newCall(request).execute();
                            weatherNow = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "run: network error");
                        }
                    } else {
                        Log.e(TAG, "run: cUrl == null");
                    }
                }
                try {
                    JSONObject jsonObject = new JSONObject(weatherNow);
                    JSONObject result = jsonObject.getJSONObject("result");
                    int tem = Utility.intRoundString(result.getString("temperature"));
                    String skycon = result.getString("skycon");
                    String intensity = result.getJSONObject("precipitation").getJSONObject("local").getString("intensity");
                    String icon = Utility.chooseWeatherIcon(skycon, Float.parseFloat(intensity), WeatherActivity.MINUTELY_MODE);
                    if (icon != null) {
                        String[] ws = icon.split("and");
                        remoteViews.setImageViewResource(R.id.widget_clock_day_icon, Integer.parseInt(ws[0]));
                        remoteViews.setTextViewText(R.id.widget_clock_day_subtitle, countyName + " | " + ws[1] + ' ' + tem + '°');
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "parseNowJson: error");
                }
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
                appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class), remoteViews);
                stopSelf();
            }
        }).start();
    }


//    private void updateWeather(String info) {
//        RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);
//        remoteViews.setTextViewText(R.id.widget_clock_day_subtitle, info);
//        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
//        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class),
//                remoteViews);
//    }
}
