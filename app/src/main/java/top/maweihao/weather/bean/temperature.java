package top.maweihao.weather.bean;

/**
 * Created by ma on 17-4-20.
 * Gson object for fullWeatherJson -> result -> daily -> temperature
 */

public class temperature {

    private String date;
    private String max;
    private String min;
    private String avg;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }
}
