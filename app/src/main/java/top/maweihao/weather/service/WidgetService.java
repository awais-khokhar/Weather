package top.maweihao.weather.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
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

    public WidgetService() {
        super("WidgetService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mRepository = WeatherRepository.getInstance(this.getApplicationContext());
        compositeDisposable = new CompositeDisposable();
    }

    public static void refreshWidgets(Context context, boolean delay, boolean allowFromCache, boolean startService) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.putExtra(DELAY,delay);
        intent.putExtra(CACHE, allowFromCache);
        intent.putExtra(START_SERVICE, startService);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
//            final String action = intent.getAction();
            final MLocation location = mRepository.getLocationCached();
            boolean delay = intent.getBooleanExtra(DELAY, false);
            if (location == null) {
                return;
            }
            SyncService.scheduleSyncService(getApplicationContext(), false);
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
