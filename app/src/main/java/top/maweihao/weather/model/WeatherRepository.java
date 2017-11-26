package top.maweihao.weather.model;

import android.content.Context;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Consumer;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.DaoMaster;
import top.maweihao.weather.entity.DaoSession;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNowDao;
import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.entity.NewWeatherDao;
import top.maweihao.weather.entity.NewWeatherRealtime;
import top.maweihao.weather.entity.NewWeatherRealtimeDao;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

/**
 * weather data repository
 * Created by maweihao on 2017/10/25.
 */

public class WeatherRepository implements WeatherData {

    private static WeatherRepository instance;

    private NewWeather weather;
    private NewWeatherRealtime weatherRealtime;
    private NewHeWeatherNow heWeatherNow;

    private NewWeatherDao weatherDao;
    private NewWeatherRealtimeDao weatherRealtimeDao;
    private NewHeWeatherNowDao heWeatherNowDao;
//    private Context context;

    private WeatherRepository(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context, "weather.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        DaoSession daoSession = daoMaster.newSession();
        weatherDao = daoSession.getNewWeatherDao();
        weatherRealtimeDao = daoSession.getNewWeatherRealtimeDao();
        heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
    }

    public static WeatherRepository getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherRepository(context);
        }
        return instance;
    }

    @Override
    public Observable<NewWeather> getWeather(String location) {
        return HttpUtil.getWeather(location, null, null)
                .doOnNext(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        saveWeather(weather);
                    }
                });
    }

    @Override
    public Observable<NewWeather> getWeatherCached() {
//        final QueryBuilder<NewWeather> qb = weatherDao.queryBuilder();
        return Observable.create(new ObservableOnSubscribe<NewWeather>() {
            @Override
            public void subscribe(ObservableEmitter<NewWeather> e) throws Exception {
                List<NewWeather> newWeatherList = weatherDao.loadAll();
                Collections.sort(newWeatherList);
                e.onNext(Utility.unpackWeather(newWeatherList.get(0)));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<NewWeatherRealtime> getWeatherNow(String location) {
        return HttpUtil.getWeatherNow(location)
                .doOnNext(new Consumer<NewWeatherRealtime>() {
                    @Override
                    public void accept(NewWeatherRealtime weather) throws Exception {
                        saveWeather(weather);
                    }
                });
    }

    @Override
    public Observable<NewWeatherRealtime> getWeatherNowCached() {
        return Observable.create(new ObservableOnSubscribe<NewWeatherRealtime>() {
            @Override
            public void subscribe(ObservableEmitter<NewWeatherRealtime> e) throws Exception {
                List<NewWeatherRealtime> weatherList = weatherRealtimeDao.loadAll();
                Collections.sort(weatherList);
                e.onNext(Utility.unpackWeather(weatherList.get(0)));
                e.onComplete();
            }
        });
    }

    @Override
    public Observable<NewHeWeatherNow> getHeWeatherNow(String location) {
        return HttpUtil.getHeWeatherNow(location)
                .doOnNext(new Consumer<NewHeWeatherNow>() {
                    @Override
                    public void accept(NewHeWeatherNow newHeWeatherNow) throws Exception {
                        saveWeather(weather);
                    }
                });
    }

    @Override
    public Observable<NewHeWeatherNow> getHeWeatherNowCached() {
        return Observable.create(new ObservableOnSubscribe<NewHeWeatherNow>() {
            @Override
            public void subscribe(ObservableEmitter<NewHeWeatherNow> e) throws Exception {
                List<NewHeWeatherNow> weatherList = heWeatherNowDao.loadAll();
                e.onNext(Utility.unpackWeather(weatherList.get(0)));
                e.onComplete();
            }
        });
    }

    @Override
    public void saveWeather(NewWeather Weather) {
        if (Weather.getStatus().equals("ok")) {
            weatherDao.deleteAll();
            weatherDao.insert(Utility.packWeather(Weather));
        }
    }

    @Override
    public void saveWeather(NewWeatherRealtime Weather) {
        if (Weather.getStatus().equals("ok")) {
            weatherRealtimeDao.deleteAll();
            weatherRealtimeDao.insert(Utility.packWeather(Weather));
        }
    }

    @Override
    public void saveWeather(NewHeWeatherNow Weather) {
        if (Weather.getHeWeather5().get(0).getStatus().equals("ok")) {
            heWeatherNowDao.deleteAll();;
            heWeatherNowDao.insert(Utility.packWeather(Weather, System.currentTimeMillis()));
        }
    }

    @Override
    public void getLocationCached() {

    }

    @Override
    public void getLocation() {

    }

}