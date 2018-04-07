package top.maweihao.weather.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * location bean
 * Created by maweihao on 26/11/2017.
 */
@Entity
public class MLocation implements Parcelable {

    public static final int TYPE_CHOOSE = 0;
    public static final int TYPE_IP = 1;
    public static final int TYPE_BAIDU_GPS = 2;
    public static final int TYPE_BAIDU_NETWORK = 3;
    public static final int TYPE_BAIDU_UNKNOWN = 4;
    public static final int TYPE_LOCATION_MANAGER = 5;

    @Property
    private int locateType;
    @Property
    private int rawBaiduLocateCode;  //百度定位 BDLocation 原始结果码
    @Property
    private boolean needGeocode;  //是否需要根据解析地址

    @Property
    private float latitude;  //经度
    @Property
    private float longitude;  //纬度

    @Property
    private String province;
    @Property
    private String city;
    @Property
    private String county;
    @Property
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

    public MLocation(int locateType, String latitude, String longitude) {
        this.locateType = locateType;
        this.latitude = Float.parseFloat(latitude);
        this.longitude = Float.parseFloat(longitude);
    }

    public MLocation(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MLocation(Double latitude, Double longitude) {
        this.latitude = latitude.floatValue();
        this.longitude = longitude.floatValue();
    }

    public MLocation(String latitude, String longitude) {
        this.latitude = Float.parseFloat(latitude);
        this.longitude = Float.parseFloat(longitude);
    }

    @Generated(hash = 1684770172)
    public MLocation(int locateType, int rawBaiduLocateCode, boolean needGeocode,
            float latitude, float longitude, String province, String city,
            String county, String street) {
        this.locateType = locateType;
        this.rawBaiduLocateCode = rawBaiduLocateCode;
        this.needGeocode = needGeocode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.province = province;
        this.city = city;
        this.county = county;
        this.street = street;
    }

    public String getCoarseLocation() {
        return TextUtils.isEmpty(county) ? city : county;
    }

    public String getFineLocation() {
        switch (locateType) {
            case TYPE_CHOOSE:
                return TextUtils.isEmpty(county) ? city : county;
            case TYPE_IP:
                return city;
            case TYPE_BAIDU_GPS:
                return TextUtils.isEmpty(street) ? county : street;
            case TYPE_BAIDU_NETWORK:
                return TextUtils.isEmpty(street) ? county : street;
            case TYPE_BAIDU_UNKNOWN:
                return TextUtils.isEmpty(street) ? county : street;
            case TYPE_LOCATION_MANAGER:
                return TextUtils.isEmpty(street) ? county : street;
            default:
                return null;
        }
    }

    public String getLocationStringReversed() {
        if (latitude != 0 && longitude != 0) {
            return  longitude + "," + latitude;
        }
        return null;
    }

    public String getLocationString() {
        if (latitude != 0 && longitude != 0) {
            return latitude + "," + longitude;
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

    public boolean getNeedGeocode() {
        return this.needGeocode;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.locateType);
        dest.writeInt(this.rawBaiduLocateCode);
        dest.writeByte(this.needGeocode ? (byte) 1 : (byte) 0);
        dest.writeFloat(this.latitude);
        dest.writeFloat(this.longitude);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.county);
        dest.writeString(this.street);
    }

    protected MLocation(Parcel in) {
        this.locateType = in.readInt();
        this.rawBaiduLocateCode = in.readInt();
        this.needGeocode = in.readByte() != 0;
        this.latitude = in.readFloat();
        this.longitude = in.readFloat();
        this.province = in.readString();
        this.city = in.readString();
        this.county = in.readString();
        this.street = in.readString();
    }

    public static final Parcelable.Creator<MLocation> CREATOR = new Parcelable.Creator<MLocation>() {
        @Override
        public MLocation createFromParcel(Parcel source) {
            return new MLocation(source);
        }

        @Override
        public MLocation[] newArray(int size) {
            return new MLocation[size];
        }
    };
}
