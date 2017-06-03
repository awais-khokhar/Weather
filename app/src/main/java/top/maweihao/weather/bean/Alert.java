package top.maweihao.weather.bean;

/**
 * gson object for alert
 * Created by ma on 17-5-30.
 */

public class Alert {

    private String status;
    private String code;
    private String description;
    private String pubdate;
    private String location;
    private float[] bound_coord;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public float[] getBound_coord() {
        return bound_coord;
    }

    public void setBound_coord(float[] bound_coord) {
        this.bound_coord = bound_coord;
    }
}
