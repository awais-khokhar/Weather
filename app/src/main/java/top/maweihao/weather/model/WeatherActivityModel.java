package top.maweihao.weather.model;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.bean.BaiDu.BaiDuChoosePositionBean;
import top.maweihao.weather.bean.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.bean.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.MyLocation;
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.bean.SingleWeather;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Constants.SYSTEM_NOW_TIME;
import static top.maweihao.weather.util.Constants.Through.THROUGH_CHOOSE_POSITION;
import static top.maweihao.weather.util.Constants.Through.THROUGH_COORDINATE;
import static top.maweihao.weather.util.Constants.Through.THROUGH_IP;
import static top.maweihao.weather.util.Constants.Through.THROUGH_LOCATE;
import static top.maweihao.weather.util.Utility.GPSEnabled;

/**
 * WeatherActivity 的 model 层
 * Created by limuyang on 2017/5/31.
 */


public class WeatherActivityModel implements WeatherActivityContract.Model {
    private static final String TAG = "WeatherActivityModel";
    //    private Boolean autoLocate;
    private String countyName = null;

    private String locationCoordinates;

    private Long locateTime;
    private LocationClient mLocationClient;
    private WeatherActivityContract.Presenter presenter;
    private Context context;
    private static ExecutorService singleThreadPool;
    private PreferenceConfigContact configContact;
    // 未来 24h 每小时的天气
    private ArrayList<SingleWeather> hourlyWeatherList;

    private ArrayList<SingleWeather> dailyWeatherList;

    public WeatherActivityModel(Context context, WeatherActivityContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;

        configContact = Utility.createSimpleConfig(context).create(PreferenceConfigContact.class);
        singleThreadPool = Executors.newSingleThreadExecutor();
        mLocationClient = new LocationClient(context);
        mLocationClient.registerLocationListener(new MainLocationListener());
        initLocation();
        hourlyWeatherList = new ArrayList<>();
        dailyWeatherList = new ArrayList<>();
    }

    /**
     * @return 24h 天气数据的 list
     */
    @Override
    public ArrayList<SingleWeather> getHourWeatherList() {
        return hourlyWeatherList;
    }

    /**
     * @return 5day 天气数据的 list
     */
    @Override
    public ArrayList<SingleWeather> getDailyWeatherList() {
        return dailyWeatherList;
    }

    /**
     * 刷新天气
     *
     * @param forceAllRefresh 是否强制全量刷新
     * @param getCountyName   需要刷新的城市名
     */
    @Override
    public void refreshWeather(boolean forceAllRefresh, @Nullable String getCountyName) {
        presenter.startSwipe();
        presenter.initHourlyView();
        presenter.initDailyView();
        //读取配置
        boolean getAutoLocate = configContact.getAutoLocate(false);
        countyName = configContact.getCountyName();
        if (!forceAllRefresh) {
            /*
             * 判断是否超过刷新间隔。
             * 如果需要刷新的城市和配置文件中的城市一样 或 传递进来的城市为空，则进行刷新间隔判断。
             */
            if ((!TextUtils.isEmpty(getCountyName) && !TextUtils.isEmpty(countyName) && countyName.equals(getCountyName)) || TextUtils.isEmpty(getCountyName)) {

//                最低刷新间隔
                int minInterval = configContact.getRefreshInterval(10);
//               未来的天气， 原始json
                String weatherFull = configContact.getWeatherFull();
                long weatherFullLastUpdateTime = configContact.getWeatherFullLastUpdateTime(0);
//               若保存的天气刷新时间和现在相差小于 minInterval，则直接使用
                long nowTime = System.currentTimeMillis();
                if (!TextUtils.isEmpty(weatherFull) && nowTime - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
                    if (DEBUG) {
                        Log.d(TAG, "readCache: last fullWeather synced: "
                                + (nowTime - weatherFullLastUpdateTime) / 1000 + "s ago");
                    }
                    presenter.setLastUpdateTime(weatherFullLastUpdateTime);

                    ForecastBean bean = JSON.parseObject(weatherFull, ForecastBean.class);
                    presenter.setCurrentWeatherInfo(bean);
                    jsonFullWeatherData(weatherFull);

                    presenter.setLocateModeImage(getAutoLocate);

                    String ip;
                    if (countyName != null)
                        presenter.setCounty(countyName);
                    else if ((ip = configContact.getIp()) != null) {
                        presenter.setCounty(ip);
                    }
                } else {
//            全量刷新
                    beforeRequestWeather(getAutoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
                }
            } else {
                beforeRequestWeather(getAutoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
            }
        } else {
//            全量刷新
            if (!TextUtils.isEmpty(getCountyName))
                countyName = getCountyName;
            beforeRequestWeather(getAutoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
        }
    }

    private void beforeRequestWeather(@Constants.Through int requestCode) {

//        locateMode.setVisibility(View.VISIBLE);
        switch (requestCode) {
            case THROUGH_IP:
//                主界面显示当前为 ip 定位
//                locateMode 和 locateModeImage 用来显示当前定位方式
                presenter.setLocateModeImage(true);
                presenter.setCounty(Utility.getIP(context));
                getCoordinateByIp();
                break;
            case THROUGH_CHOOSE_POSITION:
                presenter.setLocateModeImage(false);
                getCoordinateByChoosePosition(countyName);
                break;
            case THROUGH_LOCATE:
                presenter.setLocateModeImage(true);
                Log.i(TAG, "THROUGH_LOCATE");
                bdLocate();
                break;
            case THROUGH_COORDINATE:
//                locateModeImage.setVisibility(View.INVISIBLE);
//                locateMode.setVisibility(View.INVISIBLE);

                locationCoordinates = configContact.getCoordinate();
//                initRequireUrl();
                afterGetCoordinate();
                break;
        }
    }

    /**
     * 初始化百度定位
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
//        刷新间隔（ms）
        option.setScanSpan(1000);
//        定位模式：精确
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 需要地址信息
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    /**
     * 使用百度定位
     */
    private void bdLocate() {
        locateTime = System.currentTimeMillis();
        //等着回调 MainLocationListener
        mLocationClient.start();
    }

    /**
     * 停止百度定位
     */
    @Override
    public void stopBdLocation() {
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    /**
     * 百度定位成功
     *
     * @param location 自定义的位置类
     */
    private void bdLocateSuccess(MyLocation location) {
        locationCoordinates = location.getCoordinate();
        String countyName = location.getDistrict();
        if (DEBUG) {
            Log.d(TAG, "locateSuccess: locationCoordinates == " + locationCoordinates);
            Log.d(TAG, "locateSuccess: location: " + countyName + location.getStreet());
        }

        configContact.applyCountyName(countyName);
        configContact.applyCoordinate(locationCoordinates);
        configContact.applyCountyNameLastUpdateTime(System.currentTimeMillis());
        configContact.applyCoordinateLastUpdateTime(System.currentTimeMillis());

        presenter.setCounty(countyName);
        afterGetCoordinate();
    }

    /**
     * 当百度定位失败时，使用 locationManager 定位
     */
    private void bdLocateFail() {
        LocationManager mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            locationCoordinates = String.valueOf(location.getLongitude()) + ',' + String.valueOf(location.getLatitude());
            if (DEBUG)
                Log.d(TAG, "GetCoordinateByLocate: locationCoordinates = " + locationCoordinates);
//            SharedPreferences.Editor editor = PreferenceManager
//                    .getDefaultSharedPreferences(context).edit();
//            editor.putString("coordinate", locationCoordinates);
//            editor.putLong("coordinate_last_update", System.currentTimeMillis());
//            editor.apply();
            configContact.applyCoordinate(locationCoordinates);
            configContact.applyCoordinateLastUpdateTime(System.currentTimeMillis());

            getCountyByCoordinate(locationCoordinates);
            afterGetCoordinate();
        } else {
            if (DEBUG)
                Log.d(TAG, "requestLocation: location == null, switch to IP method");
            beforeRequestWeather(THROUGH_IP);
        }
    }

    /**
     * 百度定位监听类
     */
    private class MainLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.d(TAG, "BAIDU: received" + (System.currentTimeMillis() - locateTime));
            if (System.currentTimeMillis() - locateTime < 2 * 1000) {  //at most x second
                if (bdLocation.getLocType() != BDLocation.TypeGpsLocation) {
                    // do nothing
                    if (DEBUG) {
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == " + bdLocation.getLocType());
                    }
                    if (MyLocation.locateSuccess(bdLocation.getLocType())) {
                        if (!GPSEnabled(context)) {
                            Log.d(TAG, "BAIDU onReceiveLocation: system GPS is off");
                            mLocationClient.stop();
                            bdLocateSuccess(simplifyBDLocation(bdLocation));
                        }
                    }
                } else {
                    mLocationClient.stop();
                    if (DEBUG) {
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == GPS");
                        presenter.toastMessage("GPS!");
                    }
                    bdLocateSuccess(simplifyBDLocation(bdLocation));
                }
            } else {
                mLocationClient.stop();
                if (DEBUG) {
                    Log.d(TAG, "BAIDU onReceiveLocation: baidu locate time out, locType == " + bdLocation.getLocType());
                }
                if (MyLocation.locateSuccess(bdLocation.getLocType())) {
                    bdLocateSuccess(simplifyBDLocation(bdLocation));
                } else {
                    if (DEBUG)
                        Log.d(TAG, "BAIDU onReceiveLocation: baidu locate failed, switch to LocationManager method");
                    bdLocateFail();
                }
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
//      nothing to do
        }

        private MyLocation simplifyBDLocation(BDLocation bdLocation) {
            MyLocation location = new MyLocation(bdLocation.getLongitude(), bdLocation.getLatitude());
            location.setType(bdLocation.getLocType());
            location.setProvince(bdLocation.getProvince());
            location.setCity(bdLocation.getCity());
            location.setDistrict(bdLocation.getDistrict());
            location.setStreet(bdLocation.getStreet());
            return location;
        }
    }
    //=========================================================================================================

    /**
     * 网络请求未来天气数据
     *
     * @param url 网址
     */
    private void requestFullWeather(String url) {
        if (TextUtils.isEmpty(url)) {
            if (DEBUG)
                Log.d(TAG, "requestFullWeather: url = null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                presenter.toastMessage("load full weather failed");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                configContact.applyWeatherFull(responseText);
                configContact.applyWeatherFullLastUpdateTime(System.currentTimeMillis());

//                presenter.isUpdate(true);
                presenter.setLastUpdateTime(SYSTEM_NOW_TIME);
                ForecastBean bean = JSON.parseObject(responseText, ForecastBean.class);
                presenter.setCurrentWeatherInfo(bean);
                jsonFullWeatherData(responseText);
            }
        });
    }


    /**
     * 网络请求现在的天气
     * 此方法未使用，直接在 requestFullWeather() 中使用 Forecast 结果刷新当前天气
     */
    private void requestCurrentWeather(String url) {

        if (TextUtils.isEmpty(url)) {
            if (DEBUG)
                Log.d(TAG, "requestCurrentWeather: url = null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

                presenter.toastMessage("load current weather failed");
                presenter.stopSwipe();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
//                final WeatherData weatherData = handleCurrentWeatherResponse(responseText);
                RealTimeBean bean = JSON.parseObject(responseText, RealTimeBean.class);

                if (bean != null && bean.getStatus().equals(Constants.STATUS_OK)) {

                    configContact.applyWeatherNow(responseText);
                    configContact.applyWeatherNowLastUpdateTime(System.currentTimeMillis());
//                    presenter.setCurrentWeatherInfo(bean);
                    presenter.setLastUpdateTime(SYSTEM_NOW_TIME);
                } else {
                    presenter.toastMessage("api source error");
                }
            }
        });
    }

    /**
     * 解析获得的未来天气json
     *
     * @param responseText json
     */
    @SuppressWarnings("unchecked")
    private void jsonFullWeatherData(String responseText) {
        //fastJson解析数据
        ForecastBean forecastBean = JSON.parseObject(responseText, ForecastBean.class);
        if (forecastBean != null && forecastBean.getStatus().equals(Constants.STATUS_OK)) {
            List list;
            //返回的json是48小时的预报，截取前24小时
            if ((list = forecastBean.getResult().getHourly().getSkycon()) != null)
                forecastBean.getResult().getHourly().setSkycon(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getCloudrate()) != null)
                forecastBean.getResult().getHourly().setCloudrate(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getAqi()) != null)
                forecastBean.getResult().getHourly().setAqi(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getHumidity()) != null)
                forecastBean.getResult().getHourly().setHumidity(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getPm25()) != null)
                forecastBean.getResult().getHourly().setPm25(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getPrecipitation()) != null)
                forecastBean.getResult().getHourly().setPrecipitation(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getWind()) != null)
                forecastBean.getResult().getHourly().setWind(list.subList(0, 24));
            if ((list = forecastBean.getResult().getHourly().getTemperature()) != null)
                forecastBean.getResult().getHourly().setTemperature(list.subList(0, 24));
            //截取前5天
            if ((list = forecastBean.getResult().getDaily().getColdRisk()) != null)
                forecastBean.getResult().getDaily().setColdRisk(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getTemperature()) != null)
                forecastBean.getResult().getDaily().setTemperature(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getSkycon()) != null)
                forecastBean.getResult().getDaily().setSkycon(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getCloudrate()) != null)
                forecastBean.getResult().getDaily().setCloudrate(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getAqi()) != null)
                forecastBean.getResult().getDaily().setAqi(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getHumidity()) != null)
                forecastBean.getResult().getDaily().setHumidity(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getAstro()) != null)
                forecastBean.getResult().getDaily().setAstro(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getUltraviolet()) != null)
                forecastBean.getResult().getDaily().setUltraviolet(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getPm25()) != null)
                forecastBean.getResult().getDaily().setPm25(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getDressing()) != null)
                forecastBean.getResult().getDaily().setDressing(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getCarWashing()) != null)
                forecastBean.getResult().getDaily().setCarWashing(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getPrecipitation()) != null)
                forecastBean.getResult().getDaily().setPrecipitation(list.subList(0, 5));
            if ((list = forecastBean.getResult().getDaily().getWind()) != null)
                forecastBean.getResult().getDaily().setWind(list.subList(0, 5));
//            if ((list = forecastBean.getResult().getDaily().getDesc()) != null)
//                forecastBean.getResult().getDaily().setDesc(list.subList(0, 5));


            ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();
            ForecastBean.ResultBean.DailyBean dailyBean = forecastBean.getResult().getDaily();

            presenter.setDailyWeatherInfo(dailyBean);
            presenter.setHourlyWeatherChart(hourlyBean);
            presenter.rainInfo(forecastBean.getResult().getMinutely().getDescription());
        }
    }

    //=========================================================================================================


    /**
     * 通过获取的坐标获得位置描述， 使用 baidu web api
     *
     * @param coordinate 坐标
     */
    private void getCountyByCoordinate(String coordinate) {
        String url;
        if (!TextUtils.isEmpty(coordinate)) {
            String[] part = coordinate.split(",");
            String reverseCoordinate = part[1] + ',' + part[0];
            url = "http://api.map.baidu.com/geocoder/v2/?location=" + reverseCoordinate + "&output=json&pois=1&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
        } else {
            if (DEBUG)
                Log.e(TAG, "WeatherActivity::setCountyByCoordinate: coordinate == null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                BaiDuCoordinateBean bean = JSON.parseObject(responseText, BaiDuCoordinateBean.class);
                if (bean != null && bean.getStatus() == 0) {
                    String countyName = bean.getResult().getAddressComponent().getDistrict();
                    if (DEBUG)
                        Log.d(TAG, "setCountyByCoordinate.onResponse: countyName: " + countyName);

                    configContact.applyCountyName(countyName);
                    configContact.applyCountyNameLastUpdateTime(System.currentTimeMillis());

                    presenter.setCounty(countyName);
                } else {
                    presenter.toastMessage("getCountyByCoordinate 根据坐标定位失败！");
                }

            }
        });
    }


    /**
     * 选择地址进行定位
     */
    private void getCoordinateByChoosePosition(String countyName) {
//        if (TextUtils.isEmpty(countyName)) { //若无保存的地址，则打开 ChoosePositionActivity
//            Log.e(TAG, "GetCoordinateByChoosePosition: choosed countyName == null");
//            Toast.makeText(WeatherActivity.this, getResources().getString(R.string.choose_your_position),
//                    Toast.LENGTH_LONG).show();
//            Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
//            startActivityForResult(intent, 1);
//        } else {
//            final Message message = handler.obtainMessage();
//            message.what = HANDLE_POSITION;
//            message.obj = countyName;
//            handler.sendMessage(message);

        String url = "http://api.map.baidu.com/geocoder/v2/?output=json&address=%" + countyName + "&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                if (DEBUG)
                    Log.e(TAG, "GetCoordinateByChoosePosition: failed");
                presenter.stopSwipe();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                BaiDuChoosePositionBean bean = JSON.parseObject(responseText, BaiDuChoosePositionBean.class);
                if (bean != null && bean.getStatus() == 0) {

                    locationCoordinates = bean.getResult().getLocation().getLng() + "," + bean.getResult().getLocation().getLat();

                    configContact.applyCoordinate(locationCoordinates);
                    configContact.applyCoordinateLastUpdateTime(System.currentTimeMillis());

                    afterGetCoordinate();
                } else {
                    presenter.toastMessage("根据位置定位失败");
                }
            }
        });
    }

    /**
     * 当位置请求失败时，通过 ip 地址判别位置
     */
    private void getCoordinateByIp() {
//        百度 web api
        String url = "http://api.map.baidu.com/location/ip?ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo&coor=bd09ll";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (DEBUG)
                    Log.e(TAG, "onFailure: fetch locationCoordinates by IP failed");
                presenter.toastMessage("onFailure: fetch locationCoordinates by IP failed");
                presenter.stopSwipe();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                BaiDuIPLocationBean bean = JSON.parseObject(responseText, BaiDuIPLocationBean.class);
                if (bean != null && bean.getStatus() == 0) {
                    locationCoordinates = bean.getContent().getPoint().getX() + "," + bean.getContent().getPoint().getY();
                    String countyName = bean.getContent().getAddress_detail().getCity() + " " + bean.getContent().getAddress_detail().getDistrict();

                    long time = System.currentTimeMillis();
                    configContact.applyCoordinate(locationCoordinates);
                    configContact.applyCoordinateLastUpdateTime(time);
                    configContact.applyCountyName(countyName);
                    configContact.applyCountyNameLastUpdateTime(time);
                    configContact.applyIp(Utility.getIP(context));
                    if (DEBUG)
                        Log.d(TAG, "GetCoordinateByIp: locationCoordinates = " + locationCoordinates);
                    afterGetCoordinate();
                } else {
                    presenter.toastMessage("根据IP定位失败！");
                }
            }
        });
    }

    /**
     * 初始化请求天气的url
     */
    private void initRequireUrl() {
        if (!TextUtils.isEmpty(locationCoordinates)) {
//            即 current weather Url， 获取当前天气的url
            String cUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/realtime.json";
//            即 full weather Url， 获取未来天气的url
            String fUrl = "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/" + locationCoordinates + "/forecast.json";

            configContact.applyCurl(cUrl);
            configContact.applyFurl(fUrl);

            presenter.startSwipe();

//            requestCurrentWeather(cUrl);
            requestFullWeather(fUrl);
        } else {
            presenter.toastMessage("locationCoordinates = null");
            if (DEBUG)
                Log.e(TAG, "initRequireUrl: locationCoordinates = null");
        }
    }


    private void afterGetCoordinate() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                initRequireUrl();
            }
        };
        if (singleThreadPool == null) {
            singleThreadPool = Executors.newSingleThreadExecutor();
        }
        singleThreadPool.execute(runnable);
    }

    @Override
    public void destroy() {
        context = null;
        presenter = null;
        singleThreadPool.shutdownNow();
    }
}
