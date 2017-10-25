package top.maweihao.weather.contract;

import io.reactivex.Observable;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.entity.NewWeatherRealtime;

/**
 * Created by maweihao on 2017/10/25.
 */

public interface WeatherData {

    Observable<NewWeather> getWeather(String location);

    Observable<NewWeather> getWeatherCached();

    Observable<NewHeWeatherNow> getWeatherNow(String location);

    Observable<NewHeWeatherNow> getWeatherNowCached();

    Observable<NewHeWeatherNow> getHeWeatherNow(String location);

    Observable<NewHeWeatherNow> getHeWeatherNowCached();
}
