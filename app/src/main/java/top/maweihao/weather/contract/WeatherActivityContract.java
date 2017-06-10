package top.maweihao.weather.contract;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.RealTimeBean;

/**
 * Created by limuyang on 2017/5/31.
 */

public interface WeatherActivityContract {
    public interface View {

        void setRainInfo(String str);

        void showDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

        void showHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void showCurrentWeatherInfo(RealTimeBean realTimeBean);

        void setLastUpdateTime();

        void showToastMessage(String msg);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();
    }

    public interface Presenter {
        void rainInfo(String str);

        void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

        void setHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void isUpdate(boolean bool);

        void toastMessage(String msg);

        void getFullWeatherDataForJson(String responseText);

        void setCurrentWeatherInfo(RealTimeBean realTimeBean);

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
