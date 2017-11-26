package top.maweihao.weather.util;

import android.location.Location;

import com.baidu.location.BDLocation;

import org.greenrobot.greendao.annotation.NotNull;

import top.maweihao.weather.refactor.MLocation;

/**
 * some methods related to location
 * Created by ma on 17-5-30.
 */

public class LocationUtil {

    public static final int TypeNone = 0;
    public static final int TypeGpsLocation = 61;  //GPS 定位
    public static final int TypeCriteriaException = 62;
    public static final int TypeNetWorkException = 63;
    public static final int TypeOffLineLocation = 66;
    public static final int TypeOffLineLocationFail = 67;
    public static final int TypeOffLineLocationNetworkFail = 68;
    public static final int TypeNetWorkLocation = 161;  //网络定位
    public static final int TypeCacheLocation = 65;
    public static final int TypeServerError = 167;
    public static final int TypeServerDecryptError = 162;
    public static final int TypeServerCheckKeyError = 505;

    public static MLocation convertType(BDLocation bdLocation) {
        MLocation location = new MLocation(bdLocation.getLatitude(), bdLocation.getLongitude());
        location.setProvince(bdLocation.getProvince());
        location.setCity(bdLocation.getCity());
        location.setCounty(bdLocation.getDistrict());
        location.setStreet(bdLocation.getStreet());
        switch (bdLocation.getLocType()) {
            case TypeGpsLocation:
                location.setLocateType(MLocation.TYPE_BAIDU_GPS);
                break;
            case TypeOffLineLocation:
            case TypeNetWorkLocation:
            case  TypeCacheLocation:
                location.setLocateType(MLocation.TYPE_BAIDU_NETWORK);
                break;
            default:
                location.setLocateType(MLocation.TYPE_BAIDU_UNKNOWN);
                location.setRawBaiduLocateCode(bdLocation.getLocType());
        }
        return location;
    }

    public static MLocation convertType(@NotNull Location location) {
        MLocation loc = new MLocation(MLocation.TYPE_LOCATION_MANAGER,
                location.getLatitude(), location.getLongitude());
        return loc;

    }

    public static boolean isBaiduLocateSuccess(int type) {
        return type == TypeGpsLocation || type == TypeNetWorkLocation || type == TypeOffLineLocation
                || type == TypeCacheLocation;
    }
}
