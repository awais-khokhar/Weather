package top.maweihao.weather.entity;

import io.reactivex.Observable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean;
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;

/**
 * interface for retrofit
 * Created by maweihao on 26/11/2017.
 */

public interface BDLocateApi {

    @GET("location/ip")
    Observable<BaiDuIPLocationBean> getIpLocation(@Query("ak") String ak,
                                                  @Query("coor") String coor,
                                                  @Query("mcode") String mcode);

    @GET("geocoder/v2/")
    Observable<BaiDuCoordinateBean> getAddressDetail(@Query("location") String location,
                                                     @Query("output") String output,
                                                     @Query("pois") int pois,
                                                     @Query("ak") String ak,
                                                     @Query("mcode") String mcode);

    @GET("geocoder/v2/")
    Observable<BaiDuChoosePositionBean> getCoordinateByDesc(@Query("address") String location,
                                                            @Query("output") String output,
                                                            @Query("ak") String ak,
                                                            @Query("mcode") String mcode);
}
