package top.maweihao.weather.contract;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.SingleWeather;

/**
 * Created by limuyang on 2017/5/31.
 */

public interface WeatherActivityContract {
    interface View {

        void setRainInfo(String str);

        void showDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

//        void showHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void showCurrentWeatherInfo(ForecastBean forecastBean);

        void setLastUpdateTime(long time);

        void showToastMessage(String msg);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();

        void setLocateModeImage(boolean isLocation);

        void initRecyclerView(List<SingleWeather> singleWeatherList);

        void updateRecyclerView();
    }

    interface Presenter {
        void rainInfo(String str);

        void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

//        void setHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void setHourlyWeatherChart(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void setLastUpdateTime(long time);

        void toastMessage(String msg);

        void setLocateModeImage(boolean isLocation);

        void setCurrentWeatherInfo(ForecastBean forecastBean);

        void setCounty(String countyStr);

        void startSwipe();

        void stopSwipe();

        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void stopBdLocation();

        void destroy();

        void initHourlyView();
    }

    interface Model {

        ArrayList<SingleWeather> getHourWeatherList();

        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void stopBdLocation();

        void destroy();
    }
}
