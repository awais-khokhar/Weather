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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.maweihao.weather.contract.BasePresenter;
import top.maweihao.weather.contract.NewWeatherActivityContract;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.refactor.MLocation;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.LocationUtil;
import top.maweihao.weather.util.ServiceUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.remoteView.WidgetUtils;

import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Utility.GPSEnabled;

/* mvp presenter for WeatherActivity
 * Created by maweihao on 28/10/2017.
 */

public class NewWeatherPresenter implements NewWeatherActivityContract.newPresenter {

    private static final String TAG = NewWeatherPresenter.class.getSimpleName();

    private final WeatherData model;
    private final NewWeatherActivityContract.newView<BasePresenter> view;
    private final CompositeDisposable compositeDisposable;
    private PreferenceConfigContact configContact;
    private MLocation cachedLocation;
    private volatile boolean isWorkDown = false;

    private Context context;
    private LocationClient mLocationClient;
    LocationClientOption option;

    private long locateTime;  //for Baidu locate

    public NewWeatherPresenter(@NonNull NewWeatherActivityContract.newView<BasePresenter> view, WeatherData model) {
        this.model = model;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
        configContact = Utility.createSimpleConfig(context).create(PreferenceConfigContact.class);

        context = (Context) view;
        mLocationClient = new LocationClient(context);
    }

    @Override
    public void subscribe() {
        fetchData();
        locate();
    }

    @Override
    public void unSubscribe() {
        stopBaiduLocate();
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
    public void fetchData() {
        Disposable disposable = model.getWeatherCached()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        view.showWeather(weather);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError("waiting for locating");
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void locate() {
        if (configContact.getAutoLocate(true)) {
            if (isPermissionDeniedPermanently()) {
                initIpLocate();
            } else {
                checkPermissionAndLocate();
            }
        } else {
            MLocation location = model.getLocationCached();
            if (location != null) {
                cachedLocation = location;
                refreshWeather(cachedLocation);
            } else {
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

    private void checkPermissionAndLocate() {
        String[] permission = {Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(context, Manifest.permission_group.LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            initBaiduLocate();
        } else {
            ActivityCompat.requestPermissions((Activity) view, permission, Constants.newRequestLocationCode);
        }
    }

    private boolean isPermissionDeniedPermanently() {
        return (ContextCompat.checkSelfPermission(context, Manifest.permission_group.LOCATION)
                != PackageManager.PERMISSION_GRANTED) &&
                (!ActivityCompat.shouldShowRequestPermissionRationale((Activity) view,
                        Manifest.permission_group.LOCATION));
    }

    @Override
    public void refreshWeather(MLocation location) {
        if (location.isNeedGeocode()) {
            geocodeLocationAndSave(location, true);
        }
        model.getWeather(location.getLocationStringReversed())
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        view.setRefreshingState(false);
                        if (weather.getStatus().equals("ok")) {
                            view.showWeather(weather);
                        } else {
                            view.showError("api error");
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.setRefreshingState(false);
                        view.showNetworkError();
                    }
                });
    }

    private void geocodeLocationAndSave(final MLocation location, final boolean showLocation) {
        Disposable disposable =
                HttpUtil.getAddressDetail(location.getLocationString())
                .subscribe(new Consumer<BaiDuCoordinateBean>() {
                    @Override
                    public void accept(BaiDuCoordinateBean baiDuCoordinateBean) throws Exception {
                        if (baiDuCoordinateBean != null && baiDuCoordinateBean.getStatus() == 0) {
                            LocationUtil.fillLocation(location, baiDuCoordinateBean);
                            if (showLocation) {
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
                        Log.e(TAG, "geocodeLocationAndSave: geo failed");
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
                        if (DEBUG) {
                            Log.d(TAG, "BAIDU onReceiveLocation: Locate type == " + bdLocation.getLocType());
                        }
                        if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
                            if (!GPSEnabled(context)) {
                                Log.d(TAG, "BAIDU onReceiveLocation: system GPS is off");
                                mLocationClient.stop();
                                refreshWeather(LocationUtil.convertType(bdLocation));
                            }
                        }
                    } else {
                        mLocationClient.stop();
                        if (DEBUG) {
                            Log.d(TAG, "BAIDU onReceiveLocation: Locate type == GPS");
                        }
                        refreshWeather(LocationUtil.convertType(bdLocation));
                    }
                } else {
                    mLocationClient.stop();
                    if (DEBUG) {
                        Log.d(TAG, "BAIDU onReceiveLocation: baidu locate time out, locType == "
                                + bdLocation.getLocType());
                    }
                    if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
                        refreshWeather(LocationUtil.convertType(bdLocation));
                    } else {
                        if (DEBUG)
                            Log.d(TAG, "BAIDU onReceiveLocation: baidu locate failed, " +
                                    "switch to LocationManager method");
                        initSystemLocate();
                    }
                }
            }

            @Override
            public void onConnectHotSpotMessage(String s, int i) {}
        });
        mLocationClient.start();
    }

    @SuppressLint("MissingPermission")
    private void initSystemLocate() {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location;
        if (mLocationManager != null &&
                mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                MLocation loc = LocationUtil.convertType(location);
                loc.setNeedGeocode(true);
                if (DEBUG) {
                    Log.d(TAG, "LocationManager: locationCoordinates = " + loc.getLocationString());
                }
                refreshWeather(loc);
            } else {
//                Log.e(TAG, "LocationManager: get null answer, switch to IP method");
//                initIpLocate();
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
            .subscribe(new Consumer<BaiDuIPLocationBean>() {
                @Override
                public void accept(BaiDuIPLocationBean baiDuIPLocationBean) throws Exception {
                    if (baiDuIPLocationBean != null && baiDuIPLocationBean.getStatus() == 0) {
                        MLocation location = LocationUtil.convertType(baiDuIPLocationBean);
                        Log.d(TAG, "ip locate success: " + location.getLocationString());
                        model.saveLocation(location);
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
        if (isPermissionOn) {
            view.showLocateError();
        } else {
            view.showError("Locate failed");
        }
    }

    private void updateWidget(NewWeather weatherView) {
        if (WidgetUtils.hasAnyWidget(context)) {
            ServiceUtil.startWidgetSyncService(context, true);
            // TODO: 02/12/2017 widget refresh
        }
    }

}
