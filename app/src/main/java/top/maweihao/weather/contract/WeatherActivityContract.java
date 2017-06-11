package top.maweihao.weather.contract;

import android.support.annotation.Nullable;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.util.Constants;

/**
 * Created by limuyang on 2017/5/31.
 */

public interface WeatherActivityContract {
    interface View {

        void setRainInfo(String str);

        void showDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

        void showHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void showCurrentWeatherInfo(RealTimeBean realTimeBean);

        void setLastUpdateTime(long time);

        void showToastMessage(String msg);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();

        void setLocateModeImage(boolean isLocation);
    }

    interface Presenter {
        void rainInfo(String str);

        void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

        void setHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void setLastUpdateTime(long time);

        void toastMessage(String msg);

        void setLocateModeImage(boolean isLocation);

        void setCurrentWeatherInfo(RealTimeBean realTimeBean);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();

        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void stopBdLocation();

        void destroy();
    }

    interface Model {
        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void beforeRequestWeather(@Constants.Through int requestCode);

        void stopBdLocation();

        void destroy();
    }
}
