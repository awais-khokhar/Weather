package top.maweihao.weather;

/**
 * Created by ma on 17-3-5.
 */

public class WeatherData {

    private String date;
    private String minTemperature;
    private String maxTemperature;
    private String temperature;
    private String skycon;
    private String pm25;
    private String cloudrate;
    private String humidity;
    private String aqi;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMaxTemperature() {
        return maxTemperature;
    }

    public String getMinTemperature() {
        return minTemperature;
    }

    public void setMaxTemperature(String maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public void setMinTemperature(String minTemperature) {
        this.minTemperature = minTemperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setSkycon(String skycon) {
        this.skycon = skycon;
    }

    public String getSkycon() {
        return skycon;
    }

    public String getPm25() {
        return pm25;
    }

    public void setPm25(String pm25) {
        this.pm25 = pm25;
    }

    public void setCloudrate(String cloudrate) {
        this.cloudrate = cloudrate;
    }

    public String getCloudrate() {
        return cloudrate;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setAqi(String aqi) {
        this.aqi = aqi;
    }

    public String getAqi() {
        return aqi;
    }
}
