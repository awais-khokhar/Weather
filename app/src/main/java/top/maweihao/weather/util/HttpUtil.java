package top.maweihao.weather.util;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.maweihao.weather.entity.api.HeWeatherApi;
import top.maweihao.weather.entity.api.WeatherApi;

/**
 * Http Utility
 * Created by ma on 17-3-5.
 */

public class HttpUtil {

    private static WeatherApi weatherApi;
    private static HeWeatherApi heWeatherApi;

    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
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


}
