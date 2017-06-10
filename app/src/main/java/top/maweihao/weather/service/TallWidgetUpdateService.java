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

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.maweihao.weather.R;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.TallWeatherWidget;

import static top.maweihao.weather.util.Constants.DEBUG;

public class TallWidgetUpdateService extends Service {

    public static final String TAG = "tWidgetUpdateService";

    public TallWidgetUpdateService() {
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
        if (DEBUG) {
            Log.d(TAG, "onStartCommand: ");
        }
        updateWidget();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int minInterval = prefs.getInt("refresh_interval", 5);
        String weatherNow = prefs.getString("weather_now", null);
        long weatherNowLastUpdateTime = prefs.getLong("weather_now_last_update_time", 0);
        final String countyName = prefs.getString("countyName", "error");
        if (weatherNow != null && System.currentTimeMillis() - weatherNowLastUpdateTime < minInterval * 60 * 1000) {
            updateWeather(weatherNow, countyName);
        } else {
            if (DEBUG) {
                Log.d(TAG, "updateWidget: weather data out of date");
            }
            final String cUrl = prefs.getString("curl", null);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (cUrl != null) {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(cUrl).build();
                            Response response = client.newCall(request).execute();
                            updateWeather(response.body().string(), countyName);
                        } catch (IOException e) {
                            e.printStackTrace();
                            Log.e(TAG, "run: network error");
                        }
                    } else {
                        Log.e(TAG, "run: cUrl == null");
                    }
                }
            }).start();
        }
    }

    private void updateWeather(String weatherNow, String countyName) {
        RemoteViews tallViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.tall_weather_widget);
//        try {
        RealTimeBean bean = JSON.parseObject(weatherNow, RealTimeBean.class);
        int tem = Utility.intRoundFloat(bean.getResult().getTemperature());
        String skycon = bean.getResult().getSkycon();
        float intensity = bean.getResult().getPrecipitation().getLocal().getIntensity();
        String icon = Utility.chooseWeatherIcon(skycon, intensity, WeatherActivity.MINUTELY_MODE);

//            JSONObject jsonObject = new JSONObject(weatherNow);
//            JSONObject result = jsonObject.getJSONObject("result");
//            int tem = Utility.intRoundString(result.getString("temperature"));
//            String skycon = result.getString("skycon");
//            String intensity = result.getJSONObject("precipitation").getJSONObject("local").getString("intensity");
//            String icon = Utility.chooseWeatherIcon(skycon, Float.parseFloat(intensity), WeatherActivity.MINUTELY_MODE);
        if (icon != null) {
            String[] ws = icon.split("and");
            tallViews.setImageViewResource(R.id.tall_widget_skycon, Integer.parseInt(ws[0]));
            tallViews.setTextViewText(R.id.tall_widget_info, countyName + " | " + ws[1] + ' ' + tem + '°');
        }
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, "parseNowJson: error");
//        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), TallWeatherWidget.class), tallViews);
        stopSelf();
    }
}
