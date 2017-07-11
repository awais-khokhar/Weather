package top.maweihao.weather.bean;

/**
 * 未来 24h 天气 RecyclerView 类
 * 未来 5day 天气 RecyclerView 类
 * Created by ma on 17-7-7.
 */

public class SingleWeather {
    private String time;
    private int skyconId;
    private String skyconDesc;
    private String temperature;

    public SingleWeather(String time, int skyconId, String skyconDesc, String temperature) {
        this.time = time;
        this.skyconId = skyconId;
        this.skyconDesc = skyconDesc;
        this.temperature = temperature;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getSkyconId() {
        return skyconId;
    }

    public void setSkyconId(int skyconId) {
        this.skyconId = skyconId;
    }

    public String getSkyconDesc() {
        return skyconDesc;
    }

    public void setSkyconDesc(String skyconDesc) {
        this.skyconDesc = skyconDesc;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
