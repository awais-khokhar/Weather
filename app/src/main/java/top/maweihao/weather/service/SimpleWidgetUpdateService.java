package top.maweihao.weather.service;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
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
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.SimpleWeatherWidget;

import static top.maweihao.weather.util.Constants.DEBUG;

public class SimpleWidgetUpdateService extends Service {

    public static final String TAG = "sWidgetUpdateService";
    PreferenceConfigContact configContact;
    public SimpleWidgetUpdateService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        configContact = Utility.createSimpleConfig(SimpleWidgetUpdateService.this).create(PreferenceConfigContact.class);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (DEBUG) {
            Log.d(TAG, "onStartCommand: ");
        }
        updateWidget();
        stopSelf();
        return START_NOT_STICKY;
    }

    private void updateWidget() {

        int minInterval = configContact.getRefreshInterval(5);
        String weatherNow = configContact.getWeatherNow();
        long weatherNowLastUpdateTime = configContact.getWeatherNowLastUpdateTime(0);
        final String countyName = configContact.getCountyName() == null ? "error" : configContact.getCountyName();

        if (weatherNow != null && System.currentTimeMillis() - weatherNowLastUpdateTime < minInterval * 60 * 1000) {
            updateWeather(weatherNow, countyName);
        } else {
            if (DEBUG) {
                Log.d(TAG, "updateWidget: weather data out of date");
            }
//            final String cUrl = prefs.getString("curl", null);
            final String cUrl = configContact.getCurl();
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
        configContact.applyWeatherNow(weatherNow);
        configContact.applyWeatherNowLastUpdateTime(System.currentTimeMillis());
        RemoteViews simpleViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);

        RealTimeBean bean = JSON.parseObject(weatherNow, RealTimeBean.class);
        int tem = Utility.intRoundDouble(bean.getResult().getTemperature());
        String skycon = bean.getResult().getSkycon();
        float intensity = bean.getResult().getPrecipitation().getLocal().getIntensity();
        String skyconString = Utility.chooseWeatherSkycon(getApplicationContext(), skycon, intensity, WeatherActivity.MINUTELY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, intensity, WeatherActivity.MINUTELY_MODE, false);
        simpleViews.setImageViewResource(R.id.simple_widget_skycon, icon);
        simpleViews.setTextViewText(R.id.simple_widget_info, countyName + " | " + skyconString + ' ' + tem + '°');
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class), simpleViews);
        stopSelf();
    }

}
