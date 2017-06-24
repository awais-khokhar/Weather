package top.maweihao.weather.bean;

/**
 * simplified class of BDLocation
 * Created by ma on 17-6-3.
 */

public class MyLocation {

    // 各种定位方式码
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

    private double longitude;
    private double latitude;
    private String province;
    private String city;
    private String district;
    private String street;
    private int type;

    public static boolean locateSuccess(int type) {
        return type == TypeGpsLocation || type == TypeNetWorkLocation || type == TypeOffLineLocation
                || type == TypeCacheLocation;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public MyLocation() {

    }

    public MyLocation(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public String getCoordinate() {
        return longitude + "," + latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
