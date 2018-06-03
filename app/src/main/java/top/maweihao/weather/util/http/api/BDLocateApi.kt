package top.maweihao.weather.util.http.api

import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import top.maweihao.weather.entity.BaiDu.BDIPLocationBean
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean

//百度定位API
interface BDLocateApi {

    @FormUrlEncoded
    @POST("location/ip")
    fun getIpLocation(@Field("ak") ak: String,
                      @Field("coor") coor: String,
                      @Field("mcode", encoded = false) mcode: String): Flowable<BDIPLocationBean>

    @FormUrlEncoded
    @POST("geocoder/v2/")
    fun getAddressDetail(@Field("location") location: String,
                         @Field("output") output: String,
                         @Field("pois") pois: Int,
                         @Field("ak") ak: String,
                         @Field("mcode") mcode: String): Flowable<BaiDuCoordinateBean>

    @FormUrlEncoded
    @POST("geocoder/v2/")
    fun getCoordinateByDesc(@Field("address") location: String,
                            @Field("output") output: String,
                            @Field("ak") ak: String,
                            @Field("mcode") mcode: String): Flowable<BaiDuChoosePositionBean>
}