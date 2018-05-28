package top.maweihao.weather.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import top.maweihao.weather.entity.dao.NewWeather;

public class CaiyunAnalyst implements WeatherAnalyst{

    private NewWeather weather;

    public CaiyunAnalyst(NewWeather weather) {
        this.weather = weather;
    }

    @Override
    public String getTomorrowDesc() {
        return null;
    }

    @Override
    public String getTomorrowBriefInfo() {
        return null;
    }

    public static String getWeekTomorrow() {
        SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.getDefault());
        return format.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
    }
}
