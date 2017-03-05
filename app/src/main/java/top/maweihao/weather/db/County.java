package top.maweihao.weather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by ma on 17-3-5.
 */

public class County extends DataSupport {

    private String countyName;

    private int cityId;

    private String latitude;

    private String longitude;

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public int getCityId() {
        return cityId;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
