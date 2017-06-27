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
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.BigWeatherWidget;

import static top.maweihao.weather.util.Constants.DEBUG;

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
        if (DEBUG) {
            Log.d(TAG, "onStartCommand: ");
        }
        updateWidget();
        return super.onStartCommand(intent, flags, startId);
    }

    private void updateWidget() {

//        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        int minInterval = prefs.getInt("refresh_interval", 5);
//        String weatherFull = prefs.getString("weather_full", null);
//        long weatherFullLastUpdateTime = prefs.getLong("weather_full_last_update_time", 0);
//        final String countyName = prefs.getString("countyName", "error");
        PreferenceConfigContact configContact = Utility.creatSimpleConfig(getApplicationContext()).create(PreferenceConfigContact.class);
        int minInterval = configContact.getRefreshInterval(5);
        String weatherFull = configContact.getWeatherFull();
        long weatherFullLastUpdateTime = configContact.getWeatherFullLastUpdateTime(0);
        final String countyName = configContact.getCountyName() == null ? "error" : configContact.getCountyName();
        if (weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
            updateWeather(weatherFull, countyName);
        } else {
            if (DEBUG) {
                Log.d(TAG, "updateWidget: weather data out of date");
            }
//            final String fUrl = prefs.getString("furl", null);
            final String fUrl = configContact.getFurl();
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

    private void updateWeather(String weatherJson, String countyName) {
        RemoteViews bigViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.big_weather_widget);
//        try {
        ForecastBean bean = JSON.parseObject(weatherJson, ForecastBean.class);

        String description = bean.getResult().getMinutely().getDescription();

//            JSONObject jsonObject = new JSONObject(weatherNow);
//            JSONObject result = jsonObject.getJSONObject("result");
//            JSONObject minutely = result.getJSONObject("minutely");
//            JSONObject hourly = result.getJSONObject("hourly");
//            String description = minutely.getString("description");
        bigViews.setTextViewText(R.id.big_widget_description, description);
        int tem = Utility.intRoundFloat(bean.getResult().getHourly().getTemperature().get(0).getValue());
        String skycon = bean.getResult().getHourly().getSkycon().get(0).getValue();
        float intensity = bean.getResult().getHourly().getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(getApplicationContext(), skycon, intensity, WeatherActivity.HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, intensity, WeatherActivity.HOURLY_MODE);
//            int tem = Utility.intRoundString(((JSONObject) (hourly.getJSONArray("temperature").get(0))).getString("value"));
//            String skycon = ((JSONObject) (hourly.getJSONArray("skycon").get(0))).getString("value");
//            String intensity = ((JSONObject) (hourly.getJSONArray("precipitation").get(0))).getString("value");
//            String skyconString = Utility.chooseWeatherSkycon(skycon, Float.parseFloat(intensity), WeatherActivity.MINUTELY_MODE);
        bigViews.setImageViewResource(R.id.big_widget_skycon, icon);
        bigViews.setTextViewText(R.id.big_widget_info, countyName + "\n" + skyconString + ' ' + tem + '°');
        bigViews.setTextViewText(R.id.big_widget_refresh_time, Utility.parseTime());
        Log.d(TAG, "successful" + tem + skycon + intensity);
//        }
//        catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, "parseNowJson: error");
//        }
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), BigWeatherWidget.class), bigViews);
        stopSelf();
    }
}
