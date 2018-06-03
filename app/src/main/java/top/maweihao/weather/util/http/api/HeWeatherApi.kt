package top.maweihao.weather.util.http.api

import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query
import top.maweihao.weather.entity.dao.NewHeWeatherNow


interface HeWeatherApi {

    @GET("now/")
    fun getHeWeatherNow(@Query("city") city: String, @Query("key") key: String): Flowable<NewHeWeatherNow>
}