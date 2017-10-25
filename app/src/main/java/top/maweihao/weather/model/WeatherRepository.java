package top.maweihao.weather.model;

import io.reactivex.Observable;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.entity.NewWeatherRealtime;

/**
 * weather data repository
 * Created by maweihao on 2017/10/25.
 */

public class WeatherRepository implements WeatherData {

    private NewWeather weather;
    private NewWeatherRealtime weatherRealtime;
    private NewHeWeatherNow heWeatherNow;

    private WeatherRepository() {

    }

    public static WeatherRepository getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static final WeatherRepository instance = new WeatherRepository();
    }

    @Override
    public Observable<NewWeather> getWeather(String location) {
        return null;
    }

    @Override
    public Observable<NewWeather> getWeatherCached() {
        return null;
    }

    @Override
    public NewWeatherRealtime getWeatherNow(String location) {
        return null;
    }

    @Override
    public NewWeatherRealtime getWeatherNowCached() {
        return null;
    }

    @Override
    public NewHeWeatherNow getHeWeatherNow(String location) {
        return null;
    }

    @Override
    public NewHeWeatherNow getHeWeatherNowCached() {
        return null;
    }
}
