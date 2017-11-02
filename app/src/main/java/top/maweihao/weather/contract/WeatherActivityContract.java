package top.maweihao.weather.contract;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.SingleWeather;

/**
 * Created by limuyang on 2017/5/31.
 */

public interface WeatherActivityContract {
    interface View {

        boolean isRefreshDone();

        void setRefreshDone(boolean refreshDone);

        void setRainInfo(String now, String today);

        void showCurrentWeatherInfo(ForecastBean forecastBean);

        void setLastUpdateTime(long time);

        void showToastMessage(String msg);

        void setCounty(String countyStr);

        void setLocationDetail(String locationDetail);

        void startSwipe();

        void stopSwipe();

        void setLocateModeImage(boolean isLocation);

        void initHourlyRecyclerView(List<SingleWeather> singleWeatherList);

        void updateHourlyRecyclerView();

        void initDailyRecyclerView(List<SingleWeather> singleWeatherList);

        void updateDailyRecyclerView();
    }

    interface Presenter {
        void rainInfo(String now, String today);

        void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean);

        void setHourlyWeatherChart(final ForecastBean.ResultBean.HourlyBean hourlyBean);

        void setLastUpdateTime(long time);

        void toastMessage(String msg);

        void setLocateModeImage(boolean isLocation);

        void setCurrentWeatherInfo(ForecastBean forecastBean);

        void setCounty(String countyStr);

        void setLocationDetail(String locationDetail);

        void startSwipe();

        void stopSwipe();

        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void stopBdLocation();

        void destroy();

        void initHourlyView();

        void initDailyView();

        void updateWidget(ForecastBean forecastBean);
    }

    interface Model {

        ArrayList<SingleWeather> getHourWeatherList();

        ArrayList<SingleWeather> getDailyWeatherList();

        void refreshWeather(boolean forceRefresh, @Nullable String countyName);

        void stopBdLocation();

        void destroy();
    }
}
