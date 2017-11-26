package top.maweihao.weather.presenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.NonNull;
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
import top.maweihao.weather.contract.NewWeatherActivityContract;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.refactor.MLocation;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.LocationUtil;

import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Utility.GPSEnabled;

/* mvp presenter for WeatherActivity
 * Created by maweihao on 28/10/2017.
 */

public class NewWeatherPresenter implements NewWeatherActivityContract.newPresenter {

    private static final String TAG = NewWeatherPresenter.class.getSimpleName();

    private final WeatherData model;
    private final NewWeatherActivityContract.newView view;
    private final CompositeDisposable compositeDisposable;
    private Context context;
    private LocationClient mLocationClient;

    private long locateTime;  //for Baidu locate

    public NewWeatherPresenter(@NonNull NewWeatherActivityContract.newView view, WeatherData model) {
        this.model = model;
        this.view = view;
        compositeDisposable = new CompositeDisposable();

        context = (Context) view;
        mLocationClient = new LocationClient(context);

    }

    @Override
    public void subscribe() {
        fetchData();
    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
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
                        view.setUpdateTime(weather.getServer_time() * 1000);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        view.showError("fetch data error, please refresh");
                    }
                });
        compositeDisposable.add(disposable);
    }

    @Override
    public void locate() {
        initBaiduLocate();

    }

    @Override
    public void refreshWeather(MLocation location) {

    }

    private void initBaiduLocate() {
        LocationClientOption option = new LocationClientOption();
        option.setScanSpan(1000);
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setIsNeedAddress(true);
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
        Location location = null;
        if (mLocationManager != null &&
                mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            if (location != null) {
                MLocation loc = LocationUtil.convertType(location);
                loc.setNeedGeocode(true);
                if (DEBUG) {
                    Log.d(TAG, "LocationManager: locationCoordinates = " + loc.getLocationString());
                }
//                configContact.applyCoordinateLastUpdateTimerdinate(locationCoordinates);
//                configContact.applyCoordinateLastUpdateTime(System.currentTimeMillis());

                refreshWeather(loc);
            } else {
                Log.e(TAG, "LocationManager: get null answer, switch to IP method");
                initIpLocate();
            }
        } else {
            initIpLocate();
        }

    }

    private void stopBaiduLocate() {
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    private void initIpLocate() {
//        String url = "http://api.map.baidu.com/title/ip?ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo&coor=bd09ll";
//        HttpUtil.sendOkHttpRequest(url, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                if (DEBUG)
//                    Log.e(TAG, "onFailure: fetch locationCoordinates by IP failed");
//                presenter.toastMessage("Refresh failed");
//                presenter.stopSwipe();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                final String responseText = response.body().string();
//                BaiDuIPLocationBean bean = JSON.parseObject(responseText, BaiDuIPLocationBean.class);
//                if (bean != null && bean.getStatus() == 0) {
//                    locationCoordinates = bean.getContent().getPoint().getX() + "," + bean.getContent().getPoint().getY();
//                    String city = bean.getContent().getAddress_detail().getCity();
//                    String district = bean.getContent().getAddress_detail().getDistrict();
//                    countyName = TextUtils.isEmpty(district) ? city : district;
//                    long time = System.currentTimeMillis();
//                    String ip = Utility.getIP(context);
//
//                    configContact.applyCoordinate(locationCoordinates);
//                    configContact.applyCoordinateLastUpdateTime(time);
//                    configContact.applyCountyName(countyName);
//                    configContact.applyCountyNameLastUpdateTime(time);
//                    configContact.applyIp(ip);
//                    configContact.applyLocationDetail(ip);
//                    if (DEBUG)
//                        Log.d(TAG, "GetCoordinateByIp: locationCoordinates = " + locationCoordinates);
//                    afterGetCoordinate();
//                } else {
//                    presenter.toastMessage("根据IP定位失败！");
//                }
//            }
//        });
        Disposable disposable = HttpUtil.getIpLocation()
                .subscribe(new Consumer<BaiDuIPLocationBean>() {
                    @Override
                    public void accept(BaiDuIPLocationBean baiDuIPLocationBean) throws Exception {
                        // TODO: 26/11/2017
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void locateFailed(boolean isPermissionOn) {
        if (isPermissionOn) {

        }
    }

}
