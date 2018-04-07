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
import top.maweihao.weather.entity.MLocation;
import top.maweihao.weather.entity.MLocationDao;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

/**
 * weather data repository
 * Created by maweihao on 2017/10/25.
 */

public class WeatherRepository implements WeatherData {

    private static WeatherRepository instance;

    DaoSession daoSession;

    private NewWeather weather;
    private NewWeatherRealtime weatherRealtime;
    private NewHeWeatherNow heWeatherNow;

    private NewWeatherDao weatherDao;
    private NewWeatherRealtimeDao weatherRealtimeDao;
    private NewHeWeatherNowDao heWeatherNowDao;
    private MLocationDao locationDao;
//    private Context context;

    private WeatherRepository(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper =
                new DaoMaster.DevOpenHelper(context.getApplicationContext(), "weather.db", null);
        DaoMaster daoMaster = new DaoMaster(devOpenHelper.getWritableDb());
        daoSession = daoMaster.newSession();
        weatherDao = daoSession.getNewWeatherDao();
//        weatherRealtimeDao = daoSession.getNewWeatherRealtimeDao();
//        heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
        locationDao = daoSession.getMLocationDao();
    }

    public static WeatherRepository getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherRepository(context);
        }
        return instance;
    }

    @Override
    public Observable<NewWeather> getWeather(String location) {
        return HttpUtil.getWeather(location, null, null, Constants.timeShift, Constants.lang)
                .doOnNext(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        if (weather.getStatus().equals("ok")) {
                            saveWeather(weather);
                        }
                    }
                });
    }

    @Override
    public Observable<NewWeather> getLocalWeather() {
        if (getLocationCached() != null) {
            return getWeather(getLocationCached().getLocationStringReversed());
        } else {
            return null;
        }
    }

    @Override
    public Observable<NewWeather> getWeatherCached() {
        return Observable.create(new ObservableOnSubscribe<NewWeather>() {
            @Override
            public void subscribe(ObservableEmitter<NewWeather> e) throws Exception {
                List<NewWeather> weatherList = weatherDao.loadAll();
                if (weatherList != null && weatherList.size() > 0) {
                    Collections.sort(weatherList);
                    e.onNext(Utility.unpackWeather(weatherList.get(0)));
                    e.onComplete();
                } else {
                    e.onError(new Throwable("no cached weather"));
                }
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
                if (weatherList != null && weatherList.size() > 0) {
                    Collections.sort(weatherList);
                    e.onNext(Utility.unpackWeather(weatherList.get(0)));
                    e.onComplete();
                } else {
                    e.onError(new Throwable("no cached weatherNow"));
                }
            }
        });
    }

    @Override
    public Observable<NewHeWeatherNow> getHeWeatherNow(String location) {
        if (heWeatherNowDao == null) {
            heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
        }
        return HttpUtil.getHeWeatherNow(location)
                .doOnNext(new Consumer<NewHeWeatherNow>() {
                    @Override
                    public void accept(NewHeWeatherNow newHeWeatherNow) throws Exception {
                        saveWeather(newHeWeatherNow);
                    }
                });
    }

    @Override
    public Observable<NewHeWeatherNow> getHeWeatherNowCached() {
        if (heWeatherNowDao == null) {
            heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
        }
        return Observable.create(new ObservableOnSubscribe<NewHeWeatherNow>() {
            @Override
            public void subscribe(ObservableEmitter<NewHeWeatherNow> e) throws Exception {
                List<NewHeWeatherNow> weatherList = heWeatherNowDao.loadAll();
                if (weatherList != null && weatherList.size() > 0) {
                    e.onNext(Utility.unpackWeather(weatherList.get(0)));
                    e.onComplete();
                } else {
                    e.onError(new Throwable("no cached heWeatherNow"));
                }
            }
        });
    }

    @Override
    public long getLastUpdateTime() {
        List<NewWeather> weatherList = weatherDao.loadAll();
        if (weatherList != null && weatherList.size() > 0) {
            return weatherList.get(0).getServer_time() * 1000;
        } else {
            return 0;
        }
    }

    @Override
    public long getLastHeNowUpdateTime() {
        if (heWeatherNowDao == null) {
            heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
        }
        List<NewHeWeatherNow> weatherList = heWeatherNowDao.loadAll();
        if (weatherList != null && weatherList.size() > 0) {
            return weatherList.get(0).getCurrentTimeInMills();
        } else {
            return 0;
        }
    }

    @Override
    public void saveWeather(NewWeather weather) {
        if (weather.getStatus().equals("ok")) {
            weatherDao.deleteAll();
            weatherDao.insertOrReplace(Utility.packWeather(weather));
        }
    }

    @Override
    public void saveWeather(NewWeatherRealtime weather) {
        if (weather.getStatus().equals("ok")) {
            weatherRealtimeDao.deleteAll();
            weatherRealtimeDao.insertOrReplace(Utility.packWeather(weather));
        }
    }

    @Override
    public void saveWeather(NewHeWeatherNow weather) {
        if (weather.getHeWeather5().get(0).getStatus().equals("ok")) {
            heWeatherNowDao.deleteAll();
            heWeatherNowDao.insertOrReplace(Utility.packWeather(weather, Utility.getHeWeatherUpdateTime(weather)));
        }
    }

    @Override
    public MLocation getLocationCached() {
        List<MLocation> locationList = locationDao.loadAll();
        if (locationList.size() > 0) {
            return locationList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Observable<MLocation> getLocation() {
        return null;
    }

    @Override
    public void saveLocation(MLocation location) {
        locationDao.deleteAll();
        locationDao.insertOrReplace(location);
    }

}
