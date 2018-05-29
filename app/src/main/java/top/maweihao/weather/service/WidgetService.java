package top.maweihao.weather.service;

import android.app.IntentService;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import top.maweihao.weather.R;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.LocationModel;
import top.maweihao.weather.model.WeatherModel;
import top.maweihao.weather.util.http.DataResult;
import top.maweihao.weather.util.http.Status;
import top.maweihao.weather.util.remoteView.WidgetUtils;

public class WidgetService extends IntentService  implements LifecycleOwner {

    private static final String TAG = WidgetService.class.getSimpleName();

    private final ServiceLifecycleDispatcher mDispatcher = new ServiceLifecycleDispatcher(this);

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
        mDispatcher.onServicePreSuperOnCreate();
//        compositeDisposable = new CompositeDisposable();
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
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            LiveData<DataResult<NewWeather>> tempWeatherData = WeatherModel.INSTANCE.getWeather(location.getLocationStringReversed(), true);
            tempWeatherData.observe(this, new Observer<DataResult<NewWeather>>() {
                @Override
                public void onChanged(@Nullable DataResult<NewWeather> newWeatherDataResult) {
                    if (newWeatherDataResult != null && (newWeatherDataResult.getStatus() == Status.SUCCESS || newWeatherDataResult.getStatus() == Status.CACHE)) {
                        Log.d(TAG, "accept: ok");
                        WidgetUtils.refreshWidget(getApplicationContext(), newWeatherDataResult.getData(), location.getCoarseLocation());

                    }
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

    @Override
    public void onDestroy() {
        mDispatcher.onServicePreSuperOnDestroy();
        super.onDestroy();
    }

    @NonNull
    @Override
    public Lifecycle getLifecycle() {
        return mDispatcher.getLifecycle();
    }
}
