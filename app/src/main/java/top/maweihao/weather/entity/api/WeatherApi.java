package top.maweihao.weather.entity.api;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.entity.dao.NewWeatherRealtime;
import top.maweihao.weather.util.Constants;

/**
 * interface for retrofit
 * Created by maweihao on 2017/10/24.
 */

public interface WeatherApi {

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    Observable<NewWeather> getWeather(@Path("location") String cityId, @Query("alert") boolean alert,
                                      @Query("dailysteps") int days);

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    Observable<NewWeather> getWeather(@Path("location") String cityId, @Query("alert") boolean alert,
                                      @Query("dailysteps") int days, @Query("tzshift") int shift,
                                      @Query("lang") String lang);

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    Observable<NewWeatherRealtime> getWeatherNow(@Path("location") String cityId);
}
