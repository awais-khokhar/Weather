package top.maweihao.weather.entity.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;

/**
 * interface for retrofit
 * Created by maweihao on 2017/10/25.
 */

public interface HeWeatherApi {

    @GET("now/")
    Observable<NewHeWeatherNow> getHeWeatherNow(@Query("city") String city, @Query("key") String key);
}
