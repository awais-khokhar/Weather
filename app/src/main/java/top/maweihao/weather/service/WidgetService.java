package top.maweihao.weather.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import top.maweihao.weather.R;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.LocationModel;
import top.maweihao.weather.model.WeatherModel;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.http.NetworkSubscriber;
import top.maweihao.weather.util.remoteView.WidgetUtils;

public class WidgetService extends IntentService {

    private static final String TAG = WidgetService.class.getSimpleName();

//    private CompositeDisposable compositeDisposable;
    public static final String DELAY = "delay";
    public static final String CACHE = "cache";
    public static final String START_SERVICE = "start_service";
    public static final String CONFIG_CHANGED = "config_changed";
    public static final String SHOW_TOAST = "show_toast";

    public WidgetService() {
        super("WidgetService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            pushForeground();
        }
//        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }


    public static void refreshWidgets(Context context, boolean delay, boolean allowFromCache,
                                      boolean startService, boolean configChanged, boolean showToast) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(DELAY,delay);
        intent.putExtra(CACHE, allowFromCache);
        intent.putExtra(START_SERVICE, startService);
        intent.putExtra(CONFIG_CHANGED, configChanged);
        intent.putExtra(SHOW_TOAST, showToast);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.d(TAG, "onHandleIntent: ");
//            final String action = intent.getAction();
            final MLocation location = LocationModel.INSTANCE.getLocationCached();
            boolean delay = intent.getBooleanExtra(DELAY, false);
            boolean configChanged = intent.getBooleanExtra(CONFIG_CHANGED, false);
            boolean showToast = intent.getBooleanExtra(SHOW_TOAST, false);
            if (showToast) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), R.string.refreshing, Toast.LENGTH_SHORT).show();
                    }
                });
            }
            if (location == null) {
                return;
            }
            SyncService.scheduleSyncService(getApplicationContext(), false, configChanged);
            if (delay) {
                SystemClock.sleep(2000);
            }

            WeatherModel.INSTANCE.getWeather(location.getLocationStringReversed(), true)
                    .subscribe(new NetworkSubscriber<NewWeather>() {
                        @Override
                        public void onSuccess(NewWeather data, boolean isDbCache) {
                            if (data != null && data.getStatus().equals("ok")) {
                                WidgetUtils.refreshWidget(getApplicationContext(), data, location.getCoarseLocation());
                            }
                        }

                        @Override
                        public void onNetError(@org.jetbrains.annotations.Nullable String msg) {
                        }
                    });
//            Disposable disposable = mRepository.getLocalWeatherAllowCached()
//                    .subscribe(new Consumer<NewWeather>() {
//                        @Override
//                        public void accept(NewWeather weather) throws Exception {
//                            if (weather != null && weather.getStatus().equals("ok")) {
//                            }
//                        }
//                    }, new Consumer<Throwable>() {
//                        @Override
//                        public void accept(Throwable throwable) throws Exception {
//                            Log.d(TAG, "accept: ");
//                        }
//                    });
//            compositeDisposable.add(disposable);
        }
    }

    private void pushForeground() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(getResources().getString(R.string.refreshing_weather))
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_refresh_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        startForeground(Constants.ID_NOTIFICATION_FOREGROUND, builder.build());
    }

    @Override
    public void onDestroy() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            stopForeground(true);
        }

        super.onDestroy();
    }

}
