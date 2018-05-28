package top.maweihao.weather.presenter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.maweihao.weather.WeatherApplication;
import top.maweihao.weather.contract.NewWeatherActivityContract;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean;
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.service.SyncService;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.LocationUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.remoteView.WidgetUtils;

import static top.maweihao.weather.util.Utility.isGPSEnabled;

/* mvp presenter for WeatherActivity
 * Created by maweihao on 28/10/2017.
 */

public class NewWeatherPresenter extends BasePresenter
        implements NewWeatherActivityContract.NewPresenter {

    private static final String TAG = NewWeatherPresenter.class.getSimpleName();

    private final WeatherData model;
    private final NewWeatherActivityContract.NewView<NewWeatherActivityContract.NewPresenter> view;

    private final CompositeDisposable compositeDisposable;
    private PreferenceConfigContact configContact;
    private MLocation cachedLocation;
    private volatile boolean workingFlag = false;

    private Context context;
    private LocationClient mLocationClient;
    private LocationClientOption option;

    private long lastUpdateTime;
    private long locateTime;  //for Baidu locate
    private NewWeather weather4widget;  //for widget refresh
    private String countyName4widget; //for widget refresh
    private volatile boolean isWidgetOn = false;

    public NewWeatherPresenter(@NonNull NewWeatherActivityContract.NewView<NewWeatherActivityContract.NewPresenter> view,
                               @NonNull LifecycleProvider<ActivityEvent> provider) {
        super(provider);
        this.model = WeatherRepository.getInstance(WeatherApplication.getContext());
        this.view = view;
        compositeDisposable = new CompositeDisposable();
//        configContact = contact;
        configContact = Utility.createSimpleConfig(WeatherApplication.getContext()).create(PreferenceConfigContact.class);

        context = (Context) view;
        mLocationClient = new LocationClient(context);
    }

    @Override
    public void subscribe() {
        fetchCachedData(false);

//        locate();
    }

    @Override
    public void unSubscribe() {
//        stopBaiduLocate();
        compositeDisposable.clear();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {
        stopBaiduLocate();
    }

    @Override
    public void onDestroy() {
//        compositeDisposable.clear();
        weather4widget = null;
        countyName4widget = null;
    }

    @Override
    public void fetchCachedData(final boolean ignoreInterval) {
        Disposable disposable = model.getWeatherCached()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<NewWeather>bindUntilEvent(ActivityEvent.DESTROY)) // onDestroy取消订阅
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        view.showWeather(weather);
                        MLocation location = model.getLocationCached();
                        view.showLocation(location);
                        if (ignoreInterval) {
                            locate();
                        } else {
                            long interval = (System.currentTimeMillis() - weather.getServer_time() * 1000)
                                    / (60 * 1000);
                            if (interval > 5) {  //hardcode the minimum refresh interval temporary
                                locate();
                            } else {
                                Log.d(TAG, "fetchCachedData: no need to refresh, last " + interval + " ago");
                                if (WidgetUtils.hasAnyWidget(context)) {
                                    updateWidget(weather, location.getCoarseLocation());
                                }
                            }
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError("waiting for locating", false);
                        locate();
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void locate() {
        view.setRefreshingState(true);
        if (configContact.getAutoLocate(true)) {
//            Log.d(TAG, "locate: true");
            checkPermissionAndLocate();
        } else {
            MLocation location = model.getLocationCached();
            if (location != null) {
                cachedLocation = location;
                refreshWeather(cachedLocation);
            } else {
                view.setRefreshingState(false);
                view.askForChoosePosition();
            }
        }
    }

    @Override
    public void refreshLocalWeather() {
        if (cachedLocation == null) {
            MLocation location = model.getLocationCached();
            if (location != null) {
                cachedLocation = location;
                refreshWeather(cachedLocation);
            } else {
                view.askForChoosePosition();
            }
        } else {
            refreshWeather(cachedLocation);
        }
    }

    @Override
    public void onPermissionDenied() {
        if (ActivityCompat.shouldShowRequestPermissionRationale((Activity) view,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            checkPermissionAndLocate();
        } else {
            initIpLocate();
        }
    }

    private void checkPermissionAndLocate() {
        Log.d(TAG, "checkPermissionAndLocate: start checking for permissions");
        String[] permission = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,};
        Log.d(TAG, "permission: " + (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED));
        Log.d(TAG, "permission: " + (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED));
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            initBaiduLocate();
        } else {
            ActivityCompat.requestPermissions((Activity) view, permission, Constants.newRequestLocationCode);
        }
    }

    private boolean checkForPermission() {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                        == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void refreshWeather(final MLocation location) {
        final boolean needGeo = location.isNeedGeocode();
        isWidgetOn = WidgetUtils.hasAnyWidget(context);
        Log.d(TAG, "refreshWeather " +
                location.getLocationStringReversed() + " " + location.getLocateType());
        if (needGeo) {
            workingFlag = true;
            geocodeLocationAndSave(location, true);
        } else {
            view.showLocation(location);
        }
        Disposable disposable = model.getWeather(location.getLocationStringReversed())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(getProvider().<NewWeather>bindUntilEvent(ActivityEvent.DESTROY)) // onDestroy取消订阅
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        if (needGeo) {
                            if (!workingFlag) {
                                view.setRefreshingState(false);
                                if (isWidgetOn) {
                                    updateWidget(weather, countyName4widget);
                                }
                            } else {
                                Log.d(TAG, "get weather: cannot stop swipe now");
                                workingFlag = false;
                                if (isWidgetOn) {
                                    weather4widget = weather;
                                }
                            }
                        } else {
                            view.setRefreshingState(false);
                            updateWidget(weather, location.getCoarseLocation());
                        }
                        if (weather.getStatus().equals("ok")) {
                            view.showWeather(weather);
                        } else {
                            view.showError("api error", true);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        if (needGeo) {
                            if (!workingFlag) {
                                view.setRefreshingState(false);
                            } else {
//                                Log.d(TAG, "get weather: cannot stop swipe now");
                                workingFlag = false;
                            }
                        } else {
                            view.setRefreshingState(false);
                        }
                        Log.e(TAG, "refreshWeather: " + throwable);
                        view.showNetworkError();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void geocodeLocationAndSave(final MLocation location, final boolean showLocation) {
        Disposable disposable =
                HttpUtil.getAddressDetail(location.getLocationString())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .compose(getProvider().<BaiDuCoordinateBean>bindUntilEvent(ActivityEvent.DESTROY)) // onDestroy取消订阅
                        .subscribe(new Consumer<BaiDuCoordinateBean>() {
                            @Override
                            public void accept(BaiDuCoordinateBean baiDuCoordinateBean) throws Exception {
                                if (baiDuCoordinateBean != null && baiDuCoordinateBean.getStatus() == 0) {
                                    LocationUtil.fillLocation(location, baiDuCoordinateBean);
                                    if (showLocation) {
                                        if (!workingFlag) {
                                            view.setRefreshingState(false);
                                            if (isWidgetOn) {
                                                updateWidget(weather4widget, location.getCoarseLocation());
                                            }
                                        } else {
                                            Log.d(TAG, "get location: cannot stop swipe now");
                                            workingFlag = false;
                                            if (isWidgetOn) {
                                                countyName4widget = location.getCoarseLocation();
                                            }
                                        }
                                        view.showLocation(location);
                                    }
                                } else {
                                    Log.e(TAG, "geocodeLocationAndSave: bean status error. " +
                                            (baiDuCoordinateBean != null ? baiDuCoordinateBean.getStatus() : ""));
                                }
                                model.saveLocation(location);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "geocodeLocationAndSave: geo failed " + throwable);
                                if (showLocation) {
                                    view.showLocation(location);
                                }
                                model.saveLocation(location);
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void initBaiduLocate() {
        if (option == null) {
            option = new LocationClientOption();
            option.setScanSpan(1000);
            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
            option.setIsNeedAddress(true);
        }
        locateTime = System.currentTimeMillis();
        mLocationClient.setLocOption(option);
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation bdLocation) {
                Log.d(TAG, "BAIDU: received" + (System.currentTimeMillis() - locateTime));
                if (System.currentTimeMillis() - locateTime < 3 * 1000) {  //at most x second
                    if (bdLocation.getLocType() != BDLocation.TypeGpsLocation) {
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == " + bdLocation.getLocType());
                        if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
                            if (!isGPSEnabled(context)) {
                                Log.d(TAG, "BAIDU onReceiveLocation: system GPS is off");
                                mLocationClient.stop();
                                showAndSaveWeather(bdLocation);
                            }
                        }
                    } else {
                        mLocationClient.stop();
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == GPS");
                        showAndSaveWeather(bdLocation);
                    }
                } else {
                    mLocationClient.stop();
                    Log.d(TAG, "BAIDU onReceiveLocation: baidu locate time out, locType == "
                            + bdLocation.getLocType());

                    if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
                        showAndSaveWeather(bdLocation);
                    } else {
                        Log.d(TAG, "BAIDU onReceiveLocation: baidu locate failed, " +
                                "switch to LocationManager method");

                        initSystemLocate();
                    }
                }
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {
            }

            private void showAndSaveWeather(BDLocation bdLocation) {
                MLocation location = LocationUtil.convertType(bdLocation);
//                Log.d(TAG, "showAndSaveWeather: " + bdLocation.getLatitude() + " " + bdLocation.getLongitude());
//                Log.d(TAG, "showAndSaveWeather: " + location.getLocationString());
                model.saveLocation(location);
                refreshWeather(location);
            }
        });
        mLocationClient.start();
    }

    @SuppressLint("MissingPermission")
    private void initSystemLocate() {
        LocationManager mLocationManager =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location;
        Log.d(TAG, "initSystemLocate: " + (mLocationManager.getProviders(true)));
        if (mLocationManager != null && (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)
                || mLocationManager.getProviders(true).contains(LocationManager.PASSIVE_PROVIDER))) {
            if (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            } else {
                location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            }
            if (location != null) {
                Log.d(TAG, "initSystemLocate: " + location.getLatitude() + " " + location.getLongitude());
                MLocation loc = LocationUtil.convertType(location);
                loc.setNeedGeocode(true);
                Log.d(TAG, "LocationManager: locationCoordinates = " + loc.getLocationString());
                refreshWeather(loc);
            } else {
                locateFailed(true);
            }
        } else {
            locateFailed(true);
        }
    }

    private void stopBaiduLocate() {
        if (mLocationClient != null && mLocationClient.isStarted()) {
            mLocationClient.stop();
            mLocationClient = null;
        }
    }

    private void initIpLocate() {
        Disposable disposable =
                HttpUtil.getIpLocation()
                        .compose(getProvider().<BaiDuIPLocationBean>bindUntilEvent(ActivityEvent.DESTROY)) // onDestroy取消订阅
                        .subscribe(new Consumer<BaiDuIPLocationBean>() {
                            @Override
                            public void accept(BaiDuIPLocationBean baiDuIPLocationBean) throws Exception {
                                if (baiDuIPLocationBean != null && baiDuIPLocationBean.getStatus() == 0) {
                                    MLocation location = LocationUtil.convertType(baiDuIPLocationBean);
                                    Log.d(TAG, "ip locate success: " + location.getLocationString());
                                    model.saveLocation(location);
                                    view.showIpLocateMessage();
                                    refreshWeather(location);
                                } else {
                                    Log.e(TAG, "initIpLocate: baidu ip address error. " +
                                            (baiDuIPLocationBean != null ? baiDuIPLocationBean.getStatus() : ""));
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "initIpLocate: ip locate failed");
                                locateFailed(false);
                            }
                        });
        compositeDisposable.add(disposable);
    }

    private void locateFailed(boolean isPermissionOn) {
        Log.e(TAG, "locateFailed, and permission is " + isPermissionOn);
        if (!isPermissionOn) {
            view.showLocateError();
        } else {
            view.showError("Locate failed", true);
        }
    }

    private void updateWidget(NewWeather weatherView, String location) {
        if (weatherView == null || location == null) {
            Log.e(TAG, "updateWidget: null!" + weatherView + location);
        } else {
            if (WidgetUtils.hasAnyWidget(context)) {
//                Log.d(TAG, "updateWidget: here has widget");
//                ServiceUtil.startWidgetSyncService(context, false, false);
                WidgetUtils.refreshWidget(context, weatherView, location);
                SyncService.scheduleSyncService(context.getApplicationContext(), false, false);
            }
        }
    }

    @Override
    public void refreshChosenWeather(final String desc) {
        Disposable disposable =
                HttpUtil.getCoordinateByDesc(desc)
                        .compose(getProvider().<BaiDuChoosePositionBean>bindUntilEvent(ActivityEvent.DESTROY)) // onDestroy取消订阅
                        .subscribe(new Consumer<BaiDuChoosePositionBean>() {
                            @Override
                            public void accept(BaiDuChoosePositionBean positionBean) throws Exception {
                                if (positionBean != null && positionBean.getStatus() == 0 &&
                                        0 != positionBean.getResult().getLocation().getLat() &&
                                        0 != positionBean.getResult().getLocation().getLng()) {
                                    Log.d(TAG, "accept: success");
                                    MLocation location = LocationUtil.convertType(positionBean, desc);
                                    model.saveLocation(location);
                                    refreshWeather(location);
                                } else {
                                    Log.d(TAG, "accept: " + positionBean.getStatus());
                                    Log.e(TAG, "refreshChosenWeather: get coo failed " + desc);
                                    view.showError("Get weather failed, try to enable \"Auto Locate\"",
                                            true);
                                }
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                Log.e(TAG, "refreshChosenWeather: get coo failed" + throwable);
                                view.showNetworkError();
                            }
                        });
        compositeDisposable.add(disposable);
    }
}
