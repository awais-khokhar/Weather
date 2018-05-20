package top.maweihao.weather.contract;

import android.content.Context;

import io.reactivex.Observable;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.entity.dao.NewWeatherRealtime;
import top.maweihao.weather.entity.dao.MLocation;

/**
 * weather repository
 * Created by maweihao on 2017/10/25.
 */

public interface WeatherData {

    Context getContext();

    Observable<NewWeather> getWeather(String location);

    Observable<NewWeather> getWeatherCached();

    Observable<NewWeather> getLocalWeather();

    Observable<NewWeatherRealtime> getWeatherNow(String location);

    Observable<NewWeatherRealtime> getWeatherNowCached();

    Observable<NewHeWeatherNow> getHeWeatherNow(String location);

    Observable<NewHeWeatherNow> getHeWeatherNowCached();

    void saveWeather(NewWeather newWeather);

    void saveWeather(NewWeatherRealtime weatherRealtime);

    void saveWeather(NewHeWeatherNow heWeatherNow);

    MLocation getLocationCached();

    Observable<MLocation> getLocation();

    Observable<MLocation> getCurrentLocation();

    void saveLocation(MLocation location);

    long getLastUpdateTime();

    long getLastHeNowUpdateTime();
}
