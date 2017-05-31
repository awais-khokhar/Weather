package top.maweihao.weather;

/**
 * 扩充的 WeatherData
 * Created by ma on 17-4-2.
 */

public class ExtendedWeatherData extends WeatherData {

    private String date;
    private String sunriseTime;
    private String sunsetTime;
    private String minTemperature;
    private String maxTemperature;
    private int uvIndex;
    private String uvDesc;
    private int derssingIndex;
    private String dressingDesc;
    private int carWashingIndex;
    private String carWashingDesc;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSunriseTime() {
        return sunriseTime;
    }

    public void setSunriseTime(String sunriseTime) {
        this.sunriseTime = sunriseTime;
    }

    public String getSunsetTime() {
        return sunsetTime;
    }

    public void setSunsetTime(String sunsetTime) {
        this.sunsetTime = sunsetTime;
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

    public int getDerssingIndex() {
        return derssingIndex;
    }

    public String getDressingDesc() {
        return dressingDesc;
    }

    public int getCarWashingIndex() {
        return carWashingIndex;
    }

    public int getUvIndex() {
        return uvIndex;
    }

    public String getUvDesc() {
        return uvDesc;
    }

    public String getCarWashingDesc() {
        return carWashingDesc;
    }

    public void setDerssingIndex(int derssingIndex) {
        this.derssingIndex = derssingIndex;
    }

    public void setUvIndex(int uvIndex) {
        this.uvIndex = uvIndex;
    }

    public void setUvDesc(String uvDesc) {
        this.uvDesc = uvDesc;
    }

    public void setCarWashingDesc(String carWashingDesc) {
        this.carWashingDesc = carWashingDesc;
    }

    public void setCarWashingIndex(int carWashingIndex) {
        this.carWashingIndex = carWashingIndex;
    }

    public void setDressingDesc(String dressingDesc) {
        this.dressingDesc = dressingDesc;
    }
}
