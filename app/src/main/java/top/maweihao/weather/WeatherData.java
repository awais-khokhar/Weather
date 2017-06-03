package top.maweihao.weather;

import top.maweihao.weather.bean.Wind;

/**
 * gson object
 * Created by ma on 17-3-5.
 */

public class WeatherData {

    private String temperature;
    private String skycon;
    private String pm25;
    private String cloudrate;
    private String humidity;
    private String aqi;
    private String intensity;
    private Wind wind;

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
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

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Wind getWind() {
        return wind;
    }
}
