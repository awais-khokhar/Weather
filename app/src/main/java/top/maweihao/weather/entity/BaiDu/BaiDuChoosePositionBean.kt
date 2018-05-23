package top.maweihao.weather.entity.BaiDu
import com.google.gson.annotations.SerializedName

/**
 * 根据城市位知名，百度定位返回的json bean
 * 根据json数据，自动生成
 * Created by limuyang on 2017/6/10.
 * example: http://api.map.baidu.com/geocoder/v2/?address=%E5%8D%97%E4%BA%AC&output=json&ak=RUUaavVwpwqYdz8QGHLXSFBfwhs2ba6j&mcode=B6:F3:EA:31:1E:7F:BB:C5:D2:6C:5A:A7:CA:D8:4F:DF:79:46:C9:C2;top.maweihao.weather
 */

data class BaiDuChoosePositionBean(
    @SerializedName("status") val status: Int = 0, //0
    @SerializedName("result") val result: Result = Result()
){
    data class Result(
            @SerializedName("location") val location: Location = Location(),
            @SerializedName("precise") val precise: Int = 0, //0
            @SerializedName("confidence") val confidence: Int = 0, //12
            @SerializedName("level") val level: String = "" //城市
    ){
        data class Location(
                @SerializedName("lng") val lng: Double = 0.0, //118.77807440802562
                @SerializedName("lat") val lat: Double = 0.0 //32.05723550180587
        )
    }
}



