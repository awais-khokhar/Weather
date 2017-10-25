package top.maweihao.weather.entity.HeWeather;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * interface for retrofit
 * Created by maweihao on 2017/10/25.
 */

public interface HeWeatherApi {

    @GET("now/")
    Observable<NewHeWeatherNow> getHeWeatherNow(@Query("city") String city, @Query("key") String key);
}
