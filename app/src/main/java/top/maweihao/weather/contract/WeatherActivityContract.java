package top.maweihao.weather.contract;

import java.util.ArrayList;

import top.maweihao.weather.ExtendedWeatherData;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.bean.HourlyWeather;

/**
 * Created by limuyang on 2017/5/31.
 */

public interface WeatherActivityContract {
    public interface View {

        void setRainInfo(String str);

        void showDailyWeatherInfo(final ArrayList<ExtendedWeatherData> weatherDatas);

        void showHourlyWeatherInfo(final ArrayList<HourlyWeather> hourlyWeathers);

        void showCurrentWeatherInfo(WeatherData weatherData);

        void setLastUpdateTime();

        void showToastMessage(String msg);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();
    }

    public interface Presenter {
        void rainInfo(String str);

        void setDailyWeatherInfo(final ArrayList<ExtendedWeatherData> weatherDatas);

        void setHourlyWeatherInfo(final ArrayList<HourlyWeather> hourlyWeathers);

        void isUpdate(boolean bool);

        void toastMessage(String msg);

        void getFullWeatherDataForJson(String responseText);

        void setCurrentWeatherInfo(WeatherData weatherData);

        void setCounty(String countyStr);

        void getCountyByCoordinate(String coordinate);

        void startSwipe();

        void stopSwipe();

        void afterGetCoordinate();

        void getCoordinateByIp();

        void getCoordinateByChoosePosition(String countyName);

        void destroy();
    }

    public interface Model {
        void requestFullWeather(String url);

        void jsonFullWeatherData(String responseText);

        void requestCurrentWeather(String url);

        void getCountyByCoordinate(String coordinate);

        void afterGetCoordinate();

        void getCoordinateByIp();

        void getCoordinateByChoosePosition(String countyName);

        void destroy();
    }
}
