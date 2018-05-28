package top.maweihao.weather.util;


import android.text.TextUtils;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean;
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.entity.api.BDLocateApi;
import top.maweihao.weather.entity.api.HeWeatherApi;
import top.maweihao.weather.entity.api.WeatherApi;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.entity.dao.NewWeatherRealtime;

/**
 * Http Utility
 * Created by ma on 17-3-5.
 */

public class HttpUtil {

    private static WeatherApi weatherApi;
    private static HeWeatherApi heWeatherApi;
    private static BDLocateApi locateApi;

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    private static WeatherApi getWeatherApi() {
        if (weatherApi == null) {
            String baseUrl = "https://api.caiyunapp.com/v2/";
            weatherApi = getService(baseUrl).create(WeatherApi.class);
        }
        return weatherApi;
    }

    private static HeWeatherApi getHeWeatherApi() {
        if (heWeatherApi == null) {
            String baseUrl = "https://free-api.heweather.com/v5/";
            heWeatherApi = getService(baseUrl).create(HeWeatherApi.class);
        }
        return heWeatherApi;
    }

    private static BDLocateApi getBDLocateApi() {
        if (locateApi == null) {
            String baseUrl = "http://api.map.baidu.com/";
            locateApi = getService(baseUrl).create(BDLocateApi.class);
        }
        return locateApi;
    }

    private static Retrofit getService(String baseUrl) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        OkHttpClient client = new OkHttpClient.Builder()
//                .cache(cache)
                .addInterceptor(interceptor)
//                .connectTimeout(mTimeOut, TimeUnit.SECONDS)
//                .readTimeout(mTimeOut, TimeUnit.SECONDS)
//                .writeTimeout(mTimeOut, TimeUnit.SECONDS)
                .build();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    /**
     *
     * @param location 坐标
     * @param alert 需要预警？
     * @param days 需要多少天的 forecast
     * @param shift 时间偏移，eg. 北京 = +8 * 60 * 60 = 28800
     * @param lang 语言
     * @return Observable
     */
    public static Observable<NewWeather> getWeather(@NonNull String location, Boolean alert,
                                                    Integer days, Integer shift, String lang) {
        alert = (alert == null) || alert;
        days = (days == null) ? 15 : days;
        shift = (shift == null) ? 28800 : shift;
        lang = (TextUtils.isEmpty(lang)) ? "zh_CN" :lang;
        return getWeatherApi().getWeather(location, alert, days, shift, lang)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<NewWeatherRealtime> getWeatherNow(@NonNull String location) {
        return getWeatherApi().getWeatherNow(location)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<NewHeWeatherNow> getHeWeatherNow(@NonNull String location) {
        return getHeWeatherApi().getHeWeatherNow(location, Constants.HeWeatherKey)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<BaiDuIPLocationBean> getIpLocation() {
        return getBDLocateApi().getIpLocation(Constants.BaiduKey, "gcj02", Constants.mBaiduCode)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<BaiDuCoordinateBean> getAddressDetail(String location) {
        return getBDLocateApi().getAddressDetail(location, "json", 0,
                Constants.BaiduKey, Constants.mBaiduCode)
                .subscribeOn(Schedulers.io());
    }

    public static Observable<BaiDuChoosePositionBean> getCoordinateByDesc(String desc) {
        return getBDLocateApi().getCoordinateByDesc(desc, "json",
                Constants.BaiduKey_Web)
                .subscribeOn(Schedulers.io());
    }
}
