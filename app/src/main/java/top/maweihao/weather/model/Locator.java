package top.maweihao.weather.model;

import android.annotation.SuppressLint;
import android.content.Context;

import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.dao.MLocation;

// For locating
public class Locator {

    @SuppressLint("StaticFieldLeak")
    private static Locator singleton;

    private static final String TAG = Locator.class.getSimpleName();
    private Context context;
    private MLocation cache;
    private long lastUpdatedTime;
    private WeatherData repository;

    private Locator(Context context, WeatherData repository) {
        this.context = context.getApplicationContext();
        this.repository = repository;
    }

    public static Locator getInstance(WeatherRepository repository) {
        if (singleton == null) {
            if (repository == null || repository.getContext() == null) {
                throw new RuntimeException("WeatherRepository must be initialized!");
            }
            singleton = new Locator(repository.getContext(), repository);
        }
        return singleton;
    }

//    @SuppressLint("MissingPermission")
//    private void initSystemLocate() {
//        LocationManager mLocationManager =
//                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        Location location;
//        Log.d(TAG, "initSystemLocate: " + (mLocationManager.getProviders(true)));
//        if (mLocationManager != null && (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)
//                || mLocationManager.getProviders(true).contains(LocationManager.PASSIVE_PROVIDER))) {
//            if (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
//                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            } else {
//                location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
//            }
//            if (location != null) {
//                Log.d(TAG, "initSystemLocate: " + location.getLatitude() + " " + location.getLongitude());
//                MLocation loc = LocationUtil.convertType(location);
//                loc.setNeedGeocode(true);
//                Log.d(TAG, "LocationManager: locationCoordinates = " + loc.getLocationString());
//                refreshWeather(loc);
//            } else {
//                locateFailed(true);
//            }
//        } else {
//            locateFailed(true);
//        }
//    }
//
//    private void initBaiduLocate() {
//        if (option == null) {
//            option = new LocationClientOption();
//            option.setScanSpan(1000);
//            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//            option.setIsNeedAddress(true);
//        }
//        locateTime = System.currentTimeMillis();
//        mLocationClient.setLocOption(option);
//        mLocationClient.registerLocationListener(new BDLocationListener() {
//            @Override
//            public void onReceiveLocation(BDLocation bdLocation) {
//                Log.d(TAG, "BAIDU: received" + (System.currentTimeMillis() - locateTime));
//                if (System.currentTimeMillis() - locateTime < 3 * 1000) {  //at most x second
//                    if (bdLocation.getLocType() != BDLocation.TypeGpsLocation) {
//                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == " + bdLocation.getLocType());
//                        if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
//                            if (!isGPSEnabled(context)) {
//                                Log.d(TAG, "BAIDU onReceiveLocation: system GPS is off");
//                                mLocationClient.stop();
//                                showAndSaveWeather(bdLocation);
//                            }
//                        }
//                    } else {
//                        mLocationClient.stop();
//                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == GPS");
//                        showAndSaveWeather(bdLocation);
//                    }
//                } else {
//                    mLocationClient.stop();
//                    Log.d(TAG, "BAIDU onReceiveLocation: baidu locate time out, locType == "
//                            + bdLocation.getLocType());
//
//                    if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
//                        showAndSaveWeather(bdLocation);
//                    } else {
//                        Log.d(TAG, "BAIDU onReceiveLocation: baidu locate failed, " +
//                                "switch to LocationManager method");
//
//                        initSystemLocate();
//                    }
//                }
//            }

    public io.reactivex.Observable<MLocation> getCurrentLocation() {
        return null;
    }

}
