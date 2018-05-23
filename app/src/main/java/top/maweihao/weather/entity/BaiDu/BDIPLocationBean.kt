package top.maweihao.weather.entity.BaiDu

import com.google.gson.annotations.SerializedName

/**
 * 百度IP定位
 * Created by limuyang on 2017/6/10.
 * example: http://api.map.baidu.com/location/ip?ak=RUUaavVwpwqYdz8QGHLXSFBfwhs2ba6j&coor=bd09&mcode=B6:F3:EA:31:1E:7F:BB:C5:D2:6C:5A:A7:CA:D8:4F:DF:79:46:C9:C2;top.maweihao.weather
 */


data class BDIPLocationBean(
        @SerializedName("address") val address: String = "", //CN|四川|成都|None|CHINANET|0|0
        @SerializedName("content") val content: Content = Content(),
        @SerializedName("status") val status: Int = 0, //0
        @SerializedName("message") val message: String = "" //AK参数不存在
) {
    data class Content(
            @SerializedName("address_detail") val addressDetail: AddressDetail = AddressDetail(),
            @SerializedName("address") val address: String = "", //四川省成都市
            @SerializedName("point") val point: Point = Point()
    ) {
        data class AddressDetail(
                @SerializedName("province") val province: String = "", //四川省
                @SerializedName("city") val city: String = "", //成都市
                @SerializedName("district") val district: String = "",
                @SerializedName("street") val street: String = "",
                @SerializedName("street_number") val streetNumber: String = "",
                @SerializedName("city_code") val cityCode: Int = 0 //75
        )

        data class Point(
                @SerializedName("y") val y: String = "", //3569251.03
                @SerializedName("x") val x: String = "" //11584914.3
        )
    }
}



