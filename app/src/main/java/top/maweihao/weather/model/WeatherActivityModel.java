package top.maweihao.weather.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
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
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.activity.WeatherActivity.locationCoordinates;

/**
 * Created by limuyang on 2017/5/31.
 */


public class WeatherActivityModel implements WeatherActivityContract.Model {
    private static final String TAG = "WeatherActivityModel";
    private WeatherActivityContract.Presenter presenter;
    private Context context;
    private static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();

    public WeatherActivityModel(Context context, WeatherActivityContract.Presenter presenter) {
        this.context = context;
        this.presenter = presenter;
    }

    /**
     * 解析获得的未来天气json
     *
     * @param responseText json
     */
    @Override
    @SuppressWarnings("unchecked")
    public void jsonFullWeatherData(String responseText) {
        //fastJson解析数据
        ForecastBean forecastBean = JSON.parseObject(responseText, ForecastBean.class);
        if (forecastBean.getStatus().equals(Constants.STATUS_OK)) {
            List list;
            //返回的json是48小时的预报，截取前24小时
            if ((list = forecastBean.getResult().getHourly().getSkycon()) != null)
                forecastBean.getResult().getHourly().setSkycon(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getCloudrate()) != null)
                forecastBean.getResult().getHourly().setCloudrate(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getAqi()) != null)
                forecastBean.getResult().getHourly().setAqi(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getHumidity()) != null)
                forecastBean.getResult().getHourly().setHumidity(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getPm25()) != null)
                forecastBean.getResult().getHourly().setPm25(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getPrecipitation()) != null)
                forecastBean.getResult().getHourly().setPrecipitation(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getWind()) != null)
                forecastBean.getResult().getHourly().setWind(list.subList(0, 23));
            if ((list = forecastBean.getResult().getHourly().getTemperature()) != null)
                forecastBean.getResult().getHourly().setTemperature(list.subList(0, 23));
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
            if ((list = forecastBean.getResult().getDaily().getDesc()) != null)
                forecastBean.getResult().getDaily().setDesc(list.subList(0, 5));


            ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();
            ForecastBean.ResultBean.DailyBean dailyBean = forecastBean.getResult().getDaily();

            presenter.setDailyWeatherInfo(dailyBean);
            presenter.setHourlyWeatherInfo(hourlyBean);
            presenter.rainInfo(forecastBean.getResult().getMinutely().getDescription());
        }
    }

    /**
     * 网络请求未来天气数据
     *
     * @param url 网址
     */
    @Override
    public void requestFullWeather(String url) {
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
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(context).edit();
                editor.putString("weather_full", responseText);
                editor.putLong("weather_full_last_update_time", System.currentTimeMillis());

                editor.apply();

                presenter.isUpdate(true);
                jsonFullWeatherData(responseText);
            }
        });
    }


    /**
     * 网络请求现在的天气
     */
    @Override
    public void requestCurrentWeather(String url) {

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

                if (bean != null) {
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(context).edit();
                    editor.putString("weather_now", responseText);
                    editor.putLong("weather_now_last_update_time", System.currentTimeMillis());

                    editor.apply();
                    presenter.setCurrentWeatherInfo(bean);
                    presenter.isUpdate(true);
                } else {
                    presenter.toastMessage("weatherDate = null");
                }
            }
        });
    }


    /**
     * 通过获取的坐标获得位置描述， 使用 baidu web api
     *
     * @param coordinate 坐标
     */
    @Override
    public void getCountyByCoordinate(String coordinate) {
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
                String countyName = bean.getResult().getAddressComponent().getDistrict();
//                try {
//                    JSONObject text = new JSONObject(responseText);
//                    JSONObject result = text.getJSONObject("result");
//                    JSONObject addressComponent = result.getJSONObject("addressComponent");
//                    String countyName = addressComponent.getString("district");
                if (DEBUG)
                    Log.d(TAG, "setCountyByCoordinate.onResponse: countyName: " + countyName);
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(context).edit();
                editor.putString("countyName", countyName);
                editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                editor.apply();
                presenter.setCounty(countyName);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                } catch (NullPointerException ee) {
//                    if (DEBUG)
//                        Log.e(TAG, "onResponse: toolBar not found");
//                    ee.printStackTrace();
//                }
            }
        });
    }


    /**
     * 选择地址进行定位
     */
    @Override
    public void getCoordinateByChoosePosition(String countyName) {
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
//                try {
                BaiDuChoosePositionBean bean = JSON.parseObject(responseText, BaiDuChoosePositionBean.class);
//                    JSONObject res = new JSONObject(responseText);
//                    JSONObject JSONResult = res.getJSONObject("result");
//                    JSONObject location = JSONResult.getJSONObject("location");
//                    locationCoordinates = location.getString("lng") + ',' + location.getString("lat");
                locationCoordinates = bean.getResult().getLocation().getLng() + "," + bean.getResult().getLocation().getLat();
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(context).edit();
                editor.putString("coordinate", locationCoordinates);
                editor.putLong("coordinate_last_update", System.currentTimeMillis());
                editor.apply();
                afterGetCoordinate();
//                } catch (JSONException e) {
//                    if (DEBUG) {
//                        Log.e(TAG, "GetCoordinateByChoosePosition: parse json error");
//                        Log.d(TAG, "GetCoordinateByChoosePosition: json result: " + responseText);
//                    }
//                    e.printStackTrace();
//                }
            }
        });
//        }
    }

    /**
     * 当位置请求失败时，通过 ip 地址判别位置
     */
    @Override
    public void getCoordinateByIp() {
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
                locationCoordinates = bean.getContent().getPoint().getX() + "," + bean.getContent().getPoint().getY();
                String countyName = bean.getContent().getAddress_detail().getCity() + " " + bean.getContent().getAddress_detail().getDistrict();
//                try {
//                    JSONObject allAttributes = new JSONObject(responseText);
//                    JSONObject content = allAttributes.getJSONObject("content");
//                    JSONObject address_detail = content.getJSONObject("address_detail");
//                    String countyName = address_detail.getString("city") + " " + address_detail.getString("district");
//                    JSONObject point = content.getJSONObject("point");
//                    String x = point.getString("x");
//                    String y = point.getString("y");
//                    locationCoordinates = x + ',' + y;
                SharedPreferences.Editor editor = PreferenceManager
                        .getDefaultSharedPreferences(context).edit();
                editor.putString("coordinate", locationCoordinates);
                editor.putLong("coordinate_last_update", System.currentTimeMillis());
                editor.putString("countyName", countyName);
                editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                editor.putString("IP", Utility.getIP(context));
                editor.apply();
                if (DEBUG)
                    Log.d(TAG, "GetCoordinateByIp: locationCoordinates = " + locationCoordinates);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    if (DEBUG) {
//                        Log.e(TAG, "GetCoordinateByIp: parse IP address json error");
//                        Log.d(TAG, "response: " + responseText);
//                    }
//                }
                afterGetCoordinate();
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
            SharedPreferences.Editor editor = PreferenceManager
                    .getDefaultSharedPreferences(context).edit();
            editor.putString("curl", cUrl);
            editor.putString("furl", fUrl);
            editor.apply();

            presenter.startSwipe();

            requestCurrentWeather(cUrl);
            requestFullWeather(fUrl);
        } else {
//            Toast.makeText(WeatherActivity.this, "locationCoordinates = null", Toast.LENGTH_SHORT).show();
            presenter.toastMessage("locationCoordinates = null");
            if (DEBUG)
                Log.e(TAG, "initRequireUrl: locationCoordinates = null");
        }
    }


    @Override
    public void afterGetCoordinate() {
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
//        singleThreadPool.shutdownNow();
    }
}
