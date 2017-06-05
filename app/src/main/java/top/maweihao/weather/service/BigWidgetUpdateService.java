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
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.BigWeatherWidget;

public class BigWidgetUpdateService extends Service {

    public static final String TAG = "bWidgetUpdateService";

    public BigWidgetUpdateService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (WeatherActivity.DEBUG) {
            Log.d(TAG, "onStartCommand: ");
        }
        updateWidget();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int minInterval = prefs.getInt("refresh_interval", 5);
        String weatherFull = prefs.getString("weather_full", null);
        long weatherFullLastUpdateTime = prefs.getLong("weather_full_last_update_time", 0);
        final String countyName = prefs.getString("countyName", "error");
        if (weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
            updateWeather(weatherFull, countyName);
        } else {
            if (WeatherActivity.DEBUG) {
                Log.d(TAG, "updateWidget: weather data out of date");
            }
            final String fUrl = prefs.getString("furl", null);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (fUrl != null) {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(fUrl).build();
                            Response response = client.newCall(request).execute();
                            updateWeather(response.body().string(), countyName);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "run: network error");
                            stopSelf();
                        }
                    } else {
                        Log.e(TAG, "run: fUrl == null");
                        stopSelf();
                    }
                }
            }).start();
        }
    }

    private void updateWeather(String weatherNow, String countyName) {
        RemoteViews bigViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.big_weather_widget);
        try {
            JSONObject jsonObject = new JSONObject(weatherNow);
            JSONObject result = jsonObject.getJSONObject("result");
            JSONObject minutely = result.getJSONObject("minutely");
            JSONObject hourly = result.getJSONObject("hourly");
            String description = minutely.getString("description");
            bigViews.setTextViewText(R.id.big_widget_description, description);

            int tem = Utility.intRoundString(((JSONObject) (hourly.getJSONArray("temperature").get(0))).getString("value"));
            String skycon = ((JSONObject) (hourly.getJSONArray("skycon").get(0))).getString("value");
            String intensity = ((JSONObject) (hourly.getJSONArray("precipitation").get(0))).getString("value");
            String icon = Utility.chooseWeatherIcon(skycon, Float.parseFloat(intensity), WeatherActivity.MINUTELY_MODE);
            if (icon != null) {
                String[] ws = icon.split("and");
                bigViews.setImageViewResource(R.id.big_widget_skycon, Integer.parseInt(ws[0]));
                bigViews.setTextViewText(R.id.big_widget_info, countyName + "\n" + ws[1] + ' ' + tem + '°');
                bigViews.setTextViewText(R.id.big_widget_refresh_time, Utility.parseTime());
            } else {
                Log.e(TAG, "updateWeather: icon == null");
            }
            Log.d(TAG, "successful" + tem + skycon + intensity);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "parseNowJson: error");
        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), BigWeatherWidget.class), bigViews);
        stopSelf();
    }
}
