package top.maweihao.weather.refactor;

/**
 * location bean
 * Created by maweihao on 26/11/2017.
 */

public class MLocation {

    public static final int TYPE_CHOOSE = 0;
    public static final int TYPE_IP = 1;
    public static final int TYPE_BAIDU_GPS = 2;
    public static final int TYPE_BAIDU_NETWORK = 3;
    public static final int TYPE_BAIDU_UNKNOWN = 4;
    public static final int TYPE_LOCATION_MANAGER = 5;

    private int locateType;
    private int rawBaiduLocateCode;
    private boolean needGeocode;

    private float latitude;  //经度
    private float longitude;  //纬度

    private String province;
    private String city;
    private String county;
    private String street;

    public MLocation() {
    }

    public MLocation(int locateType) {
        this.locateType = locateType;
    }

    public MLocation(int locateType, float latitude, float longitude) {
        this.locateType = locateType;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MLocation(int locateType, Double latitude, Double longitude) {
        this.locateType = locateType;
        this.latitude = latitude.floatValue();
        this.longitude = longitude.floatValue();
    }

    public MLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MLocation(Double latitude, Double longitude) {
        this.latitude = latitude.floatValue();
        this.longitude = longitude.floatValue();
    }

    public String getLocationStringReversed() {
        if (latitude != 0 && longitude != 0) {
            return latitude + "," + longitude;
        }
        return null;
    }

    public String getLocationString() {
        if (latitude != 0 && longitude != 0) {
            return  longitude + "," + latitude;
        }
        return null;
    }

    public int getLocateType() {
        return locateType;
    }

    public void setLocateType(int locateType) {
        this.locateType = locateType;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getRawBaiduLocateCode() {
        return rawBaiduLocateCode;
    }

    public void setRawBaiduLocateCode(int rawBaiduLocateCode) {
        this.rawBaiduLocateCode = rawBaiduLocateCode;
    }

    public boolean isNeedGeocode() {
        return needGeocode;
    }

    public void setNeedGeocode(boolean needGeocode) {
        this.needGeocode = needGeocode;
    }
}
