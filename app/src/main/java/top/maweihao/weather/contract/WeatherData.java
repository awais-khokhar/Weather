package top.maweihao.weather.contract;

import io.reactivex.Observable;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.entity.NewWeatherRealtime;
import top.maweihao.weather.refactor.MLocation;

/**
 * weather repository
 * Created by maweihao on 2017/10/25.
 */

public interface WeatherData {

    Observable<NewWeather> getWeather(String location);

    Observable<NewWeather> getWeatherCached();

    Observable<NewWeatherRealtime> getWeatherNow(String location);

    Observable<NewWeatherRealtime> getWeatherNowCached();

    Observable<NewHeWeatherNow> getHeWeatherNow(String location);

    Observable<NewHeWeatherNow> getHeWeatherNowCached();

    void saveWeather(NewWeather newWeather);

    void saveWeather(NewWeatherRealtime weatherRealtime);

    void saveWeather(NewHeWeatherNow heWeatherNow);

    MLocation getLocationCached();

    Observable<MLocation> getLocation();

    void saveLocation(MLocation location);
}
