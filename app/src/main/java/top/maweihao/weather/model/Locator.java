package top.maweihao.weather.model;

// For locating
public class Locator {

//    @SuppressLint("StaticFieldLeak")
//    private static Locator singleton;
//
//    private static final String TAG = Locator.class.getSimpleName();
//    private Context context;
//    private volatile MLocation cache;
//    private AtomicLong lastUpdatedTime;
//    private WeatherData repository;
//
//    private LocationClient mLocationClient;
//    private LocationClientOption option;
//
//    private LocationManager mLocationManager;
//
//    private Locator(Context context, WeatherData repository) {
//        this.context = context.getApplicationContext();
//        this.repository = repository;
//    }
//
//    public static Locator getInstance(WeatherRepository repository) {
//        if (singleton == null) {
//            if (repository == null || repository.getContext() == null) {
//                throw new RuntimeException("WeatherRepository must be initialized!");
//            }
//            singleton = new Locator(repository.getContext(), repository);
//        }
//        return singleton;
//    }
//
//    public io.reactivex.Observable<MLocation> getCurrentLocation(boolean save) {
//        int minInterval = 1000;
//        if (System.currentTimeMillis() - lastUpdatedTime.get() <= minInterval && cache != null) {
//
//        }
//        return null;
//    }
//
//    public void stop() {
//        stopBaiduLocate();
//    }
//
//    private boolean checkPermission() {
//        return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED;
//    }
//
//    @SuppressLint("MissingPermission")
//    private MLocation locateUsingLM() {
//        if (mLocationManager == null) {
//            mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
//        }
//        Location location;
//        if (mLocationManager != null && (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)
//                || mLocationManager.getProviders(true).contains(LocationManager.PASSIVE_PROVIDER))) {
//            if (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
//                location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            } else {
//                location = mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
//            }
//            if (location != null) {
//                Log.d(TAG, "locateUsingLM: " + location.getLatitude() + " " + location.getLongitude());
//                MLocation loc = LocationUtil.convertType(location);
//                loc.setNeedGeocode(true);
//                return loc;
//            } else {
//                locateFailed(true);
//            }
//        } else {
//            locateFailed(true);
//        }
//    }
//
//    private MLocation locateUsingBaidu() {
//        if (option == null) {
//            option = new LocationClientOption();
//            option.setScanSpan(1000);
//            option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//            option.setIsNeedAddress(true);
//        }
//        final long locateTime = System.currentTimeMillis();
//        mLocationClient.setLocOption(option);
//        mLocationClient.registerLocationListener(new BDLocationListener() {
//            @Override
//            public void onReceiveLocation(BDLocation bdLocation) {
//                Log.i(TAG, "BAIDU: received" + (System.currentTimeMillis() - locateTime));
//                if (System.currentTimeMillis() - locateTime < 3 * 1000) {  //at most 3 seconds
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
//                    Log.d(TAG, "BAIDU onReceiveLocation: locate time out, locType == "
//                            + bdLocation.getLocType());
//
//                    if (LocationUtil.isBaiduLocateSuccess(bdLocation.getLocType())) {
//                        showAndSaveWeather(bdLocation);
//                    } else {
//                        Log.d(TAG, "BAIDU onReceiveLocation: locate failed, " +
//                                "switch to locate using LocationManager");
//                        locateUsingLM();
//                    }
//                }
//            }
//
//            @Override
//            public void onConnectHotSpotMessage(String s, int i) {
//            }
//
//            private void showAndSaveWeather(BDLocation bdLocation) {
//                MLocation location = LocationUtil.convertType(bdLocation);
//                cache = location;
//                repository.saveLocation(location);
////                return location;
//            }
//        });
//        mLocationClient.start();
//    }
//
//    private void stopBaiduLocate() {
//        if (mLocationClient != null && mLocationClient.isStarted()) {
//            mLocationClient.stop();
//            mLocationClient = null;
//        }
//    }
//
//    private void initIpLocate() {
//        Disposable disposable =
//                HttpUtil.getIpLocation()
//                        .subscribe(new Consumer<BaiDuIPLocationBean>() {
//                            @Override
//                            public void accept(BaiDuIPLocationBean baiDuIPLocationBean) throws Exception {
//                                if (baiDuIPLocationBean != null && baiDuIPLocationBean.getStatus() == 0) {
//                                    MLocation location = LocationUtil.convertType(baiDuIPLocationBean);
//                                    Log.d(TAG, "ip locate success: " + location.getLocationString());
//                                    model.saveLocation(location);
//                                    view.showIpLocateMessage();
//                                    refreshWeather(location);
//                                } else {
//                                    Log.e(TAG, "initIpLocate: baidu ip address error. " +
//                                            (baiDuIPLocationBean != null ? baiDuIPLocationBean.getStatus() : ""));
//                                }
//                            }
//                        }, new Consumer<Throwable>() {
//                            @Override
//                            public void accept(Throwable throwable) throws Exception {
//                                Log.e(TAG, "initIpLocate: ip locate failed");
//                                locateFailed(false);
//                            }
//                        });
//    }
//
//    private void locateFailed(boolean isPermissionOn) {
//        Log.e(TAG, "locateFailed, and permission is " + isPermissionOn);
//        if (isPermissionOn) {
//            view.showLocateError();
//        } else {
//            view.showError("Locate failed", true);
//        }
//    }

}
