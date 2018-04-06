package top.maweihao.weather.util;

import android.location.Location;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.baidu.location.BDLocation;

import org.greenrobot.greendao.annotation.NotNull;

import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean;
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean;
import top.maweihao.weather.entity.BaiDu.BaiDuIPLocationBean;
import top.maweihao.weather.entity.MLocation;

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

    public static MLocation convertType(@NonNull BaiDuIPLocationBean bean) {
        BaiDuIPLocationBean.ContentBean.AddressDetailBean address_detail =
                bean.getContent().getAddress_detail();
        MLocation location = new MLocation(MLocation.TYPE_IP,
                bean.getContent().getPoint().getY(), bean.getContent().getPoint().getX());
        if (!TextUtils.isEmpty(address_detail.getProvince())) {
            location.setProvince(address_detail.getProvince());
        }
        if (!TextUtils.isEmpty(address_detail.getCity())) {
            location.setCity(address_detail.getCity());
        }
        if (!TextUtils.isEmpty(address_detail.getDistrict())) {
            location.setCounty(address_detail.getDistrict());
        }
        return location;
    }

    public static MLocation convertType(@NonNull BaiDuChoosePositionBean bean, String county) {
        BaiDuChoosePositionBean.ResultBean.LocationBean locationBean =
                bean.getResult().getLocation();
        MLocation location = new MLocation(MLocation.TYPE_CHOOSE,
                locationBean.getLat(), locationBean.getLng());
        location.setCounty(county);
        return location;
    }

    public static void fillLocation(MLocation location, BaiDuCoordinateBean bean) {
        BaiDuCoordinateBean.ResultBean.AddressComponentBean addressComponentBean
                 = bean.getResult().getAddressComponent();
        if (!TextUtils.isEmpty(addressComponentBean.getProvince())) {
            location.setProvince(addressComponentBean.getProvince());
        }
        if (!TextUtils.isEmpty(addressComponentBean.getCity())) {
            location.setCity(addressComponentBean.getCity());
        }
        if (!TextUtils.isEmpty(addressComponentBean.getDistrict())) {
            location.setCounty(addressComponentBean.getDistrict());
        }
        // "sematic_description" is more accurate than "street"
        if (!TextUtils.isEmpty(bean.getResult().getSematic_description())) {
            location.setStreet(bean.getResult().getSematic_description());
        } else if (!TextUtils.isEmpty(addressComponentBean.getStreet())){
            location.setStreet(addressComponentBean.getStreet());
        }
        location.setNeedGeocode(false);
    }

    public static boolean isBaiduLocateSuccess(int type) {
        return type == TypeGpsLocation || type == TypeNetWorkLocation || type == TypeOffLineLocation
                || type == TypeCacheLocation;
    }
}
