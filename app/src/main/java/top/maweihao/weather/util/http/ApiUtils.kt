package top.maweihao.weather.util.http

import android.arch.lifecycle.LiveData
import android.text.TextUtils
import io.reactivex.Observable
import io.reactivex.annotations.NonNull
import retrofit2.http.*
import top.maweihao.weather.entity.BaiDu.BDIPLocationBean
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.entity.dao.NewWeatherRealtime
import top.maweihao.weather.util.Constants
import top.maweihao.weather.util.http.HttpUtils.baiduLocateApi
import top.maweihao.weather.util.http.HttpUtils.weatherApi

object ApiUtils {

    /**
     *
     * @param location 坐标
     * @param alert 需要预警？
     * @param days 需要多少天的 forecast
     * @param shift 时间偏移，eg. 北京 = +8 * 60 * 60 = 28800
     * @param lang 语言
     * @return Observable
     */
    fun getWeather(@NonNull location: String, alert: Boolean?,
                   days: Int?, shift: Int?, lang: String): LiveData<ApiResponse<NewWeather>> {
        var alert = alert
        var days = days
        var shift = shift
        var lang = lang
        alert = alert == null || alert
        days = if (days == null) 15 else days
        shift = if (shift == null) 28800 else shift
        lang = if (TextUtils.isEmpty(lang)) "zh_CN" else lang

        return weatherApi.getWeather(location, alert, days, shift, lang)
    }

    fun getIpLocation(): LiveData<ApiResponse<BDIPLocationBean>> {
        return baiduLocateApi.getIpLocation(Constants.BaiduKey, "gcj02", Constants.mBaiduCode)
    }

    fun getAddressDetail(location: String): LiveData<ApiResponse<BaiDuCoordinateBean>> {
        return baiduLocateApi.getAddressDetail(location,
                                               "json",
                                               0,
                                               Constants.BaiduKey,
                                               Constants.mBaiduCode)
    }

    fun getCoordinateByDesc(desc: String): LiveData<ApiResponse<BaiDuChoosePositionBean>> {
        return baiduLocateApi.getCoordinateByDesc(desc, "json",
                                                  Constants.BaiduKey, Constants.mBaiduCode)
    }
}


interface WeatherApi {

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeather(@Path("location") cityId: String,
                   @Query("alert") alert: Boolean,
                   @Query("dailysteps") days: Int): LiveData<ApiResponse<NewWeather>>

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeather(@Path("location") cityId: String,
                   @Query("alert") alert: Boolean,
                   @Query("dailysteps") days: Int,
                   @Query("tzshift") shift: Int,
                   @Query("lang") lang: String): LiveData<ApiResponse<NewWeather>>

    @GET(Constants.CaiyunWeatherKey + "/{location}/forecast.json")
    fun getWeatherNow(@Path("location") cityId: String): Observable<NewWeatherRealtime>
}

//百度定位API
interface BDLocateApi {

    @FormUrlEncoded
    @POST("location/ip")
    fun getIpLocation(@Field("ak") ak: String,
                      @Field("coor") coor: String,
                      @Field("mcode", encoded = false) mcode: String): LiveData<ApiResponse<BDIPLocationBean>>

    @FormUrlEncoded
    @POST("geocoder/v2/")
    fun getAddressDetail(@Field("location") location: String,
                         @Field("output") output: String,
                         @Field("pois") pois: Int,
                         @Field("ak") ak: String,
                         @Field("mcode") mcode: String): LiveData<ApiResponse<BaiDuCoordinateBean>>

    @FormUrlEncoded
    @POST("geocoder/v2/")
    fun getCoordinateByDesc(@Field("address") location: String,
                            @Field("output") output: String,
                            @Field("ak") ak: String,
                            @Field("mcode") mcode: String): LiveData<ApiResponse<BaiDuChoosePositionBean>>
}