package top.maweihao.weather.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.LocationModel;
import top.maweihao.weather.model.WeatherModel;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.http.NetworkSubscriber;
import top.maweihao.weather.util.remoteView.WidgetUtils;

public class WidgetSyncService extends Service {

    private static final String TAG = WidgetSyncService.class.getSimpleName();

    public static final String force_refresh = "FORCE_REFRESH";
    private boolean forceRefresh;
    public static final String county_name = "COUNTY_NAME";
    private String countyName;
    public static final String from_widget = "from_widget";
    private boolean fromWidget;

    public static boolean working         = false;
    private       long    lastRefreshTime = 0;
    private boolean hasWidget;
    private boolean isBigWidgetOn;

    private int interval;  //刷新成功时再次刷新的间隔
    private int failedInterval;  //刷新失败时再次刷新的间隔

//    private WeatherRepository weatherRepository;
//    private PreferenceConfigContact configContact;

    @Override
    public IBinder onBind(Intent intent) {
        throw null;
    }

    @Override
    public void onCreate() {
//        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();
        working = true;
        Log.d(TAG, "onCreate: ");
        super.onCreate();
        initData();
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        isBigWidgetOn = WidgetUtils.hasBigWidget(this);
        if (intent != null) {
            String name = intent.getStringExtra(county_name);
            forceRefresh = intent.getBooleanExtra(force_refresh, false);
            fromWidget = intent.getBooleanExtra(force_refresh, false);
            countyName = (TextUtils.isEmpty(name)) ? countyName : name;
        }
        int refreshInterval = (int) ((System.currentTimeMillis() - lastRefreshTime) / (60 * 1000));
        if (forceRefresh) {
            if ((hasWidget && refreshInterval >= interval - 5) || fromWidget) {
                startAgain(interval);
                fetchData(LocationModel.INSTANCE.getLocationCached());
            } else {
                if (isBigWidgetOn) {
                    WidgetUtils.refreshBigWidgetTime(this);
                }
                startAgain(interval);
                stopSelf();
            }
        } else {
            startAgain(interval);
            stopSelf();
        }
//        if ((hasWidget && refreshInterval >= interval - 5) || forceRefresh) {
//            startAgain(interval);
//            fetchData(weatherRepository.getLocationCached());
//        } else {
//            startAgain(failedInterval);
//        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        working = false;
    }

    private void initData() {
        Log.d(TAG, "onCreate");
//        working = true;
        hasWidget = WidgetUtils.hasAnyWidget(this);
        interval = 60;
        failedInterval = 20;
//        weatherRepository = WeatherRepository.getInstance(this);
    }

    private void startAgain(int interval) {
//        Toast.makeText(this, "again " + interval, Toast.LENGTH_SHORT).show();
        Log.d(TAG, "startAgain: interval ==" + interval);
        Intent intent = new Intent(this, WidgetSyncService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,
                Constants.WidgetSyncServiceRequestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        if (alarmManager != null) {
//            alarmManager.cancel(pendingIntent);
            alarmManager.set(AlarmManager.ELAPSED_REALTIME,
                    SystemClock.elapsedRealtime() + interval * 60 * 1000, pendingIntent);
        }
    }

    private void fetchData(final MLocation location) {
//        Toast.makeText(this, "fetchData!", Toast.LENGTH_SHORT).show();
        int minInterval = 5;  //hardcode interval temporary
        long now = System.currentTimeMillis();
        long cachedLastUpdateTime = WeatherModel.INSTANCE.getLastUpdateTime();
        if (now - cachedLastUpdateTime <= minInterval * 60 * 1000) {
            lastRefreshTime = cachedLastUpdateTime;
            NewWeather weather = WeatherModel.INSTANCE.getWeatherCache();
            if (weather == null) {
                Log.e(TAG, "fetchData: get cached weather failed " + "-- >  null");
                requestWeatherAndUpdate(location, failedInterval);
            } else {
                Log.d(TAG, "fetchData: use cached weather to refresh the widget");
                WidgetUtils.refreshWidget(WidgetSyncService.this,
                        weather, location.getCoarseLocation());
                stopSelf();
            }
//                    .subscribeOn(Schedulers.io())
//                    .subscribe(new Consumer<NewWeather>() {
//                        @Override
//                        public void accept(NewWeather weather) throws Exception {
//                            Log.d(TAG, "fetchData: use cached weather to refresh the widget");
//                            WidgetUtils.refreshWidget(WidgetSyncService.this,
//                                    weather, location.getCoarseLocation());
//                            stopSelf();
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(Throwable throwable) throws Exception {
//                            Log.e(TAG, "fetchData: get cached weather failed " + throwable);
//                            requestWeatherAndUpdate(location, weatherRepository, failedInterval);
//                        }
//                    });
        } else {
            if (!isBigWidgetOn) {
                long lut = WeatherModel.INSTANCE.getLastHeNowUpdateTime();
                if (now - lut <= minInterval * 60 * 1000) {
                    lastRefreshTime = lut;
                    NewHeWeatherNow weather = WeatherModel.INSTANCE.getHeWeatherNowCached();
                    if (weather != null) {
                        LogUtils.d("fetchData: use cached he weather to refresh the widget");
                        WidgetUtils.refreshWidget(WidgetSyncService.this,
                                weather, location.getCoarseLocation());
                        stopSelf();
                    } else {
                        LogUtils.e("fetchData: get cached weather failed " + "-----> null");
                        requestHeAndUpdate(location, failedInterval);

                    }


//                    weatherRepository.getHeWeatherNowCached()
//                            .subscribeOn(Schedulers.io())
//                            .subscribe(new Consumer<NewHeWeatherNow>() {
//                                @Override
//                                public void accept(NewHeWeatherNow weather) throws Exception {
//                                    Log.d(TAG, "fetchData: use cached he weather to refresh the widget");
//                                    WidgetUtils.refreshWidget(WidgetSyncService.this,
//                                            weather, location.getCoarseLocation());
//                                    stopSelf();
//                                }
//                            }, new Consumer<Throwable>() {
//                                @Override
//                                public void accept(Throwable throwable) throws Exception {
//                                    Log.e(TAG, "fetchData: get cached weather failed " + throwable);
//                                    requestHeAndUpdate(location, weatherRepository, failedInterval);
//                                }
//                            });
                } else {
                    requestHeAndUpdate(location, failedInterval);
                }
            } else {
                requestWeatherAndUpdate(location, failedInterval);
            }


        }
    }

    private void requestWeatherAndUpdate(final MLocation location, final int failedInterval) {
        WeatherModel.INSTANCE.getWeather(location.getLocationStringReversed(), true)
                .subscribe(new NetworkSubscriber<NewWeather>() {
                    @Override
                    public void onSuccess(NewWeather data, boolean isDbCache) {
                        if (data != null && data.getStatus().equals("ok")) {
                            lastRefreshTime = data.getServer_time() * 1000;
                            WidgetUtils.refreshWidget(WidgetSyncService.this, data, location.getCoarseLocation());
                            LogUtils.d("fetchData: fetch weather succeed!");
                        } else {
                            LogUtils.e("fetchData: weather api error");
                            startAgain(failedInterval);
                        }
                    }

                    @Override
                    public void onNetError(@org.jetbrains.annotations.Nullable String msg) {
                        LogUtils.e("fetchData: get weather failed  " + msg);
                        startAgain(failedInterval);
                        stopSelf();
                    }
                });

//        model.getWeather(location.getLocationStringReversed())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<NewWeather>() {
//                    @Override
//                    public void accept(NewWeather weather) throws Exception {
//                        if (weather.getStatus().equals("ok")) {
//                            lastRefreshTime = weather.getServer_time() * 1000;
//                            WidgetUtils.refreshWidget(WidgetSyncService.this,
//                                    weather, location.getCoarseLocation());
//                            Log.d(TAG, "fetchData: fetch weather succeed!");
//                        } else {
//                            Log.e(TAG, "fetchData: weather api error");
//                            startAgain(failedInterval);
//                        }
//                        stopSelf();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG, "fetchData: get weather failed" + throwable);
//                        startAgain(failedInterval);
//                        stopSelf();
//                    }
//                });
    }

    private void requestHeAndUpdate(final MLocation location, final int failedInterval) {
        WeatherModel.INSTANCE.getHeWeatherNow(location.getLocationStringReversed())
                .subscribe(new NetworkSubscriber<NewHeWeatherNow>() {
                    @Override
                    public void onSuccess(NewHeWeatherNow weatherNow, boolean isDbCache) {
                        if (weatherNow != null &&
                                weatherNow.getHeWeather5().get(0).getStatus().equals("ok")) {
                            lastRefreshTime = Utility.getHeWeatherUpdateTime(weatherNow);
                            WidgetUtils.refreshWidget(WidgetSyncService.this,
                                    weatherNow, location.getCoarseLocation());
                            Log.d(TAG, "fetchData: fetch he weather succeed!");
                        } else {
                            Log.e(TAG, "fetchData: he api error");
                            startAgain(failedInterval);
                        }
                        stopSelf();
                    }

                    @Override
                    public void onNetError(@org.jetbrains.annotations.Nullable String msg) {
                        Log.e(TAG, "fetchData: get heWeatherNow failed  " + msg);
                        startAgain(failedInterval);
                        stopSelf();
                    }
                });

//        Log.d(TAG, "fetchData: here" + location.getLocationStringReversed());
//        model.getHeWeatherNow(location.getLocationStringReversed())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new Consumer<NewHeWeatherNow>() {
//                    @Override
//                    public void accept(NewHeWeatherNow weatherNow) throws Exception {
//                        if (weatherNow != null &&
//                                weatherNow.getHeWeather5().get(0).getStatus().equals("ok")) {
//                            lastRefreshTime = Utility.getHeWeatherUpdateTime(weatherNow);
//                            WidgetUtils.refreshWidget(WidgetSyncService.this,
//                                    weatherNow, location.getCoarseLocation());
//                            Log.d(TAG, "fetchData: fetch he weather succeed!");
//                        } else {
//                            Log.e(TAG, "fetchData: he api error");
//                            startAgain(failedInterval);
//                        }
//                        stopSelf();
//                    }
//                }, new Consumer<Throwable>() {
//                    @Override
//                    public void accept(Throwable throwable) throws Exception {
//                        Log.e(TAG, "fetchData: get heWeatherNow failed " + throwable);
//                        startAgain(failedInterval);
//                        stopSelf();
//                    }
//                });
    }

}
