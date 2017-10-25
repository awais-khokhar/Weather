package top.maweihao.weather.util;


import android.util.AndroidException;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.maweihao.weather.entity.HeWeather.HeWeatherApi;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.entity.NewWeatherRealtime;
import top.maweihao.weather.entity.WeatherApi;

/**
 * Http Utility
 * Created by ma on 17-3-5.
 */

public class HttpUtil {

    private static WeatherApi weatherApi;
    private static HeWeatherApi heWeatherApi;

    @Deprecated
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

    private static Retrofit getService(String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public static Observable<NewWeather> getWeather(@NonNull String location, Boolean alert, Integer days) {
        alert = (alert != null) && alert;
        days = (days == null) ? 15 : days;
        return getWeatherApi().getWeather(location, alert, days)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<NewWeatherRealtime> getWeatherNow(@NonNull String location) {
        return getWeatherApi().getWeatherNow(location)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<NewHeWeatherNow> getHeWeatherNow(@NonNull String location) {
        return getHeWeatherApi().getHeWeatherNow(location, Constants.HeWeatherKey)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
