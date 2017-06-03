package top.maweihao.weather.bean;

/**
 * JavaBeans for HourlyWeather
 * Created by ma on 17-5-20.
 */

public class HourlyWeather {

    private String datetime;

    private String skyon;

    private String temperature;

    private String precipitation;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public void setSkyon(String skyon) {
        this.skyon = skyon;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getSkyon() {
        return skyon;
    }
}
