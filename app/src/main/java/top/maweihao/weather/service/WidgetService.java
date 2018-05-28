package top.maweihao.weather.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import top.maweihao.weather.R;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.util.remoteView.WidgetUtils;

public class WidgetService extends IntentService {

    private static final String TAG = WidgetService.class.getSimpleName();
    private WeatherData mRepository;
    private CompositeDisposable compositeDisposable;
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
        mRepository = WeatherRepository.getInstance(this.getApplicationContext());
        compositeDisposable = new CompositeDisposable();
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
            final MLocation location = mRepository.getLocationCached();
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
            Disposable disposable = mRepository.getLocalWeatherAllowCached()
                    .subscribe(new Consumer<NewWeather>() {
                        @Override
                        public void accept(NewWeather weather) throws Exception {
                            if (weather != null && weather.getStatus().equals("ok")) {
                                Log.d(TAG, "accept: ok");
                                WidgetUtils.refreshWidget(getApplicationContext(), weather, location.getCoarseLocation());
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            Log.d(TAG, "accept: ");
                        }
                    });
            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void onDestroy() {
        mRepository = null;
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
