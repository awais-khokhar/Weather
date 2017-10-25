package top.maweihao.weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.HeWeather.HeNowWeather;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HeWeatherUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.remoteView.WidgetUtils;

import static com.alibaba.fastjson.JSON.parseObject;

public class WidgetSyncService extends Service {

    private static final String TAG = WidgetSyncService.class.getSimpleName();
    public static final String force_refresh = "FORCE_REFRESH";
    public static boolean working = false;
    private long lastRefreshTime;
    private boolean hasWidget;
    private int interval;
    private boolean forceRefresh;
    private boolean isBigWidgetOn;

    private PreferenceConfigContact configContact;

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        isBigWidgetOn = WidgetUtils.hasBigWidget(this);
        forceRefresh = intent != null && intent.getBooleanExtra(force_refresh, false);


        int refreshInterval = (int) ((System.currentTimeMillis() - lastRefreshTime)
                / (60 * 1000));
        if (hasWidget && refreshInterval >= interval - 5 || forceRefresh) {
            startAgain(interval);
            saveInterval();
            fetchData();
        } else {
            startAgain(30);
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void initData() {
        Log.d(TAG, "onCreate");
        working = true;
        hasWidget = WidgetUtils.hasAnyWidget(this);
        configContact = Utility.createSimpleConfig(this).create(PreferenceConfigContact.class);
        lastRefreshTime = configContact.getLastSyncTime(0L);
        interval = 90;
    }

    private void startAgain(int interval) {
        Log.d(TAG, "startAgain: interval ==" + interval);
        Intent intent = new Intent(this, WidgetSyncService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, Constants.WidgetSyncServiceRequestCode,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME,
                SystemClock.elapsedRealtime() + interval * 60 * 1000, pendingIntent);
    }

    private void saveInterval() {
        lastRefreshTime = System.currentTimeMillis();
        configContact.applyLastSyncTime(lastRefreshTime);
    }

    private void fetchData() {
        int minInterval = configContact.getRefreshInterval(5);

        String weatherFull = configContact.getWeatherFull();
        long weatherFullLastUpdateTime = configContact.getWeatherFullLastUpdateTime(0);
        String weatherHeNow = configContact.getWeatherHeNow();
        long weatherHeNowLastUpdateTime = configContact.getWeatherHeNowLastUpdateTime(0);
        String coordinate = configContact.getCoordinate();

        if (isBigWidgetOn && (weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000)) {
            WidgetUtils.refreshWidget(this, parseObject(weatherFull, ForecastBean.class));
        } else if (!isBigWidgetOn && (weatherHeNow != null && System.currentTimeMillis() - weatherHeNowLastUpdateTime < minInterval * 60 * 1000)) {
            WidgetUtils.refreshWidget(this, parseObject(weatherHeNow, HeNowWeather.class));
        } else {
            Log.d(TAG, " weather data out of date");
            final String url = isBigWidgetOn ? configContact.getFurl() : HeWeatherUtil.initRequireUrl(HeWeatherUtil.MODE_NOW, coordinate);
            Log.d(TAG, "fetchData: URL HERE" + HeWeatherUtil.initRequireUrl(HeWeatherUtil.MODE_NOW, coordinate));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    if (url != null) {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                    .url(url).build();
                            Response response = client.newCall(request).execute();

                            if (isBigWidgetOn) {
                                String body = response.body().string();
                                configContact.applyWeatherFull(body);
                                configContact.applyWeatherFullLastUpdateTime(System.currentTimeMillis());
                                WidgetUtils.refreshWidget(WidgetSyncService.this, parseObject(body, ForecastBean.class));
                            } else {
                                String body = response.body().string();
                                configContact.applyWeatherHeNow(body);
                                configContact.applyWeatherHeNowLastUpdateTime(System.currentTimeMillis());
                                HeNowWeather heWeather = JSON.parseObject(body, HeNowWeather.class);
                                WidgetUtils.refreshWidget(WidgetSyncService.this, heWeather);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                            startAgain(30);
                            Log.e(TAG, "run: network error");
                        }
                    } else {
                        startAgain(30);
                        Log.e(TAG, "run: fUrl == null");
                    }
                }
            }).start();
        }
    }

}
