package top.maweihao.weather.util.http.api

import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.entity.dao.NewWeatherRealtime
import top.maweihao.weather.util.Constants

interface WeatherApi {

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeather(@Path("location") cityId: String,
                   @Query("alert") alert: Boolean,
                   @Query("dailysteps") days: Int): Flowable<NewWeather>

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeather(@Path("location") cityId: String,
                   @Query("alert") alert: Boolean,
                   @Query("dailysteps") days: Int,
                   @Query("tzshift") shift: Int,
                   @Query("lang") lang: String): Flowable<NewWeather>

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeatherNow(@Path("location") cityId: String): Observable<NewWeatherRealtime>
}