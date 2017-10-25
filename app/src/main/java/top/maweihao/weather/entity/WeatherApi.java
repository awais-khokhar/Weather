package top.maweihao.weather.entity;


import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * interface for retrofit
 * Created by maweihao on 2017/10/24.
 */

public interface WeatherApi {

    @GET("Ahu2IBCHsYWm1--o/{location}/forecast.json")
    Observable<NewWeather> getWeather(@Path("location") String cityId, @Query("alert") boolean alert, @Query("dailysteps") int days);

    @GET("Ahu2IBCHsYWm1--o/{location}/forecast.json")
    Observable<NewWeatherRealtime> getWeatherNow(@Path("location") String cityId);
}
