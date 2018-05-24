package top.maweihao.weather.util.http.api

import android.arch.lifecycle.LiveData
import retrofit2.http.GET
import retrofit2.http.Query
import top.maweihao.weather.entity.dao.NewHeWeatherNow
import top.maweihao.weather.util.http.ApiResponse


interface HeWeatherApi {

    @GET("now/")
    fun getHeWeatherNow(@Query("city") city: String, @Query("key") key: String): LiveData<ApiResponse<NewHeWeatherNow>>
}