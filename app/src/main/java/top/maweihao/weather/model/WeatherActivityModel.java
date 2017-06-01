package top.maweihao.weather.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.ExtendedWeatherData;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.gson.HourlyWeather;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.activity.WeatherActivity.DEBUG;
import static top.maweihao.weather.activity.WeatherActivity.locationCoordinates;
import static top.maweihao.weather.util.Utility.handleCurrentWeatherResponse;

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
    public void jsonFullWeatherData(String responseText) {
        ArrayList<ExtendedWeatherData> weatherDatas = new ArrayList<>(5);
        ArrayList<HourlyWeather> hourlyWeathers = new ArrayList<>(24);
        ArrayList<JSONArray> jsonArrays = moreHandleDailyWeatherResponse(responseText);
        if (jsonArrays.size() == 11) {
            for (int i = 0; i < 5; i++) {
                try {
                    ExtendedWeatherData wd = new ExtendedWeatherData();
                    JSONObject skycon = jsonArrays.get(0).getJSONObject(i);
                    JSONObject humidity = jsonArrays.get(1).getJSONObject(i);
                    JSONObject temperatures = jsonArrays.get(2).getJSONObject(i);
                    JSONObject precipitation = jsonArrays.get(3).getJSONObject(i);
                    JSONObject astro = jsonArrays.get(4).getJSONObject(i);
                    JSONObject uv = jsonArrays.get(8).getJSONObject(i);
                    JSONObject dressing = jsonArrays.get(9).getJSONObject(i);
                    JSONObject carWashing = jsonArrays.get(10).getJSONObject(i);
                    wd.setDate(temperatures.getString("date"));
                    wd.setMaxTemperature(temperatures.getString("max"));
                    wd.setMinTemperature(temperatures.getString("min"));
                    wd.setSkycon(skycon.getString("value"));
                    wd.setHumidity(humidity.getString("max"));
                    wd.setIntensity(precipitation.getString("max"));
                    wd.setSunriseTime(astro.getJSONObject("sunrise").getString("time"));
                    wd.setSunsetTime(astro.getJSONObject("sunset").getString("time"));
                    wd.setUvIndex(uv.getInt("index"));
                    wd.setUvDesc(uv.getString("desc"));
                    wd.setDerssingIndex(dressing.getInt("index"));
                    wd.setDressingDesc(dressing.getString("desc"));
                    wd.setCarWashingIndex(carWashing.getInt("index"));
                    wd.setCarWashingDesc(carWashing.getString("desc"));
                    weatherDatas.add(i, wd);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (DEBUG)
                        Log.e(TAG, "handleFullWeatherData: parse jsonArrays error");
                }
            }
            for (int i = 0; i < 24; i++) {
                try {
                    HourlyWeather hw = new HourlyWeather();
                    JSONObject skyon = jsonArrays.get(5).getJSONObject(i);
                    JSONObject temperatures = jsonArrays.get(6).getJSONObject(i);
                    JSONObject precipitation = jsonArrays.get(7).getJSONObject(i);
                    hw.setDatetime(skyon.getString("datetime"));
                    hw.setSkyon(skyon.getString("value"));
                    hw.setPrecipitation(precipitation.getString("value"));
                    hw.setTemperature(temperatures.getString("value"));
                    hourlyWeathers.add(i, hw);
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (DEBUG)
                        Log.e(TAG, "handleFullWeatherData: parse jsonArrays error(hourly)");
                }
            }
//            showDailyWeatherInfo(weatherDatas);
//            showHourlyWeatherInfo(hourlyWeathers);

            presenter.setDailyWeatherInfo(weatherDatas);
            presenter.setHourlyWeatherInfo(hourlyWeathers);
        }
    }


    /**
     * 就是在 Utility.handleDailyWeatherResponse() 前先获得2小时内天气描述并展示
     *
     * @param url
     * @return 直接返回 Utility.handleDailyWeatherResponse(url)
     */
    private ArrayList<JSONArray> moreHandleDailyWeatherResponse(String url) {
        try {
            JSONObject all = new JSONObject(url);
            JSONObject result = all.getJSONObject("result");
            final JSONObject minutely = result.getJSONObject("minutely");
            final String des = minutely.getString("description");
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    rainInfo.setText(des);
//                }
//            });
            presenter.rainInfo(des);
        } catch (JSONException e) {
            if (DEBUG)
                Log.e(TAG, "moreHandleDailyWeatherResponse: parse weather json error");
            e.printStackTrace();
        }

        return Utility.handleFullWeatherResponse(url);
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
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(WeatherActivity.this, "load full weather failed", Toast.LENGTH_SHORT).show();
//                    }
//                });
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
                final WeatherData weatherData = handleCurrentWeatherResponse(responseText);
//                handler.post(new Runnable() {
//                    @Override
//                    public void run() {
                if (weatherData != null) {
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(context).edit();
                    editor.putString("weather_now", responseText);
                    editor.putLong("weather_now_last_update_time", System.currentTimeMillis());

                    editor.apply();
//                            showCurrentWeatherInfo(weatherData);
                    presenter.setCurrentWeatherInfo(weatherData);
                    presenter.isUpdate(true);
                } else {
                    presenter.toastMessage("weatherDate = null");
                }
//                    }
//                });
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
                try {
                    JSONObject text = new JSONObject(responseText);
                    JSONObject result = text.getJSONObject("result");
                    JSONObject addressComponent = result.getJSONObject("addressComponent");
                    String countyName = addressComponent.getString("district");
                    Log.d(TAG, "setCountyByCoordinate.onResponse: countyName: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(context).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.apply();
//                    Message message = handler.obtainMessage();
//                    message.what = HANDLE_POSITION;
//                    message.obj = countyName;
//                    handler.sendMessage(message);
                    presenter.setCounty(countyName);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (NullPointerException ee) {
                    if (DEBUG)
                        Log.e(TAG, "onResponse: toolBar not found");
                    ee.printStackTrace();
                }
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
                try {
                    JSONObject res = new JSONObject(responseText);
                    JSONObject JSONResult = res.getJSONObject("result");
                    JSONObject location = JSONResult.getJSONObject("location");
                    locationCoordinates = location.getString("lng") + ',' + location.getString("lat");
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(context).edit();
                    editor.putString("coordinate", locationCoordinates);
                    editor.putLong("coordinate_last_update", System.currentTimeMillis());
                    editor.apply();
                    afterGetCoordinate();
                } catch (JSONException e) {
                    if (DEBUG) {
                        Log.e(TAG, "GetCoordinateByChoosePosition: parse json error");
                        Log.d(TAG, "GetCoordinateByChoosePosition: json result: " + responseText);
                    }
                    e.printStackTrace();
                }
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
                try {
                    JSONObject allAttributes = new JSONObject(responseText);
                    JSONObject content = allAttributes.getJSONObject("content");
                    JSONObject address_detail = content.getJSONObject("address_detail");
                    String countyName = address_detail.getString("city") + " " + address_detail.getString("district");
                    JSONObject point = content.getJSONObject("point");
                    String x = point.getString("x");
                    String y = point.getString("y");
                    locationCoordinates = x + ',' + y;
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
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (DEBUG) {
                        Log.e(TAG, "GetCoordinateByIp: parse IP address json error");
                        Log.d(TAG, "response: " + responseText);
                    }
                }
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
//            startSwipe();
//            requestCurrentWeather(cUrl);
//            requestFullWeather(fUrl);
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
