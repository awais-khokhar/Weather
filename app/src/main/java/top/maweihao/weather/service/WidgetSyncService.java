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
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.remoteView.BigWidgetUtils;
import top.maweihao.weather.util.remoteView.WidgetUtils;

import static top.maweihao.weather.util.Constants.DEBUG;

public class WidgetSyncService extends Service {

    private static final String TAG = WidgetSyncService.class.getSimpleName();
    public static final String force_refresh = "FORCE_REFRESH";
    public static boolean working = false;
    private long lastRefreshTime;
    private boolean hasWidget;
    private int interval;
    private boolean forceRefresh;

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
        forceRefresh = intent != null && intent.getBooleanExtra(force_refresh, false);
        int refreshInterval = (int) ((System.currentTimeMillis() - lastRefreshTime)
                / (60 * 1000));
        if (hasWidget && refreshInterval >= interval - 5 || forceRefresh) {
            startAgain(interval);
            saveInterval();
            fetchData();
        } else {
            startAgain(15);
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
        // BigWidget need to be refreshed more frequently (minutes)
        interval = WidgetUtils.hasBigWidget(this) ? 60 : 90;
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
//        int minInterval = 0;
        String weatherFull = configContact.getWeatherFull();
        long weatherFullLastUpdateTime = configContact.getWeatherFullLastUpdateTime(0);
        if (weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
            WidgetUtils.refreshWidget(this, JSON.parseObject(weatherFull, ForecastBean.class), interval);
        } else {
            if (DEBUG) {
                Log.d(TAG, " weather data out of date");
            }
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
                            WidgetUtils.refreshWidget(WidgetSyncService.this, JSON.parseObject(response.body().string(),
                                    ForecastBean.class), interval);
                        } catch (IOException e) {
                            e.printStackTrace();
                            startAgain(15);
                            BigWidgetUtils.refreshTime(WidgetSyncService.this, 15);
                            Log.e(TAG, "run: network error");
                        }
                    } else {
                        startAgain(15);
                        BigWidgetUtils.refreshTime(WidgetSyncService.this, 15);
                        Log.e(TAG, "run: fUrl == null");
                    }
                }
            }).start();
        }
    }

}
