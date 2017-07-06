package top.maweihao.weather.presenter;

import android.content.Context;
import android.support.annotation.Nullable;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.model.WeatherActivityModel;

/**
 * 接口对接，数据传递
 * Created by limuyang on 2017/5/31.
 */

public class WeatherActivityPresenter implements WeatherActivityContract.Presenter {
    private WeatherActivityContract.View weatherView;
    private WeatherActivityContract.Model weatherModel;

    public WeatherActivityPresenter(Context context, WeatherActivityContract.View weatherView) {
        this.weatherView = weatherView;
        this.weatherModel = new WeatherActivityModel(context, this);
    }

    @Override
    public void rainInfo(String str) {
        weatherView.setRainInfo(str);
    }

    /**
     * 展示未来5天的天气
     */
    @Override
    public void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean) {
        weatherView.showDailyWeatherInfo(dailyBean);
    }

    @Override
    public void setHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean) {
        weatherView.showHourlyWeatherInfo(hourlyBean);
    }

    /**
     * 更新成功后设置显示时间
     *
     * @param time 更新时间
     */
    @Override
    public void setLastUpdateTime(long time) {
        weatherView.setLastUpdateTime(time);
    }

    @Override
    public void setLocateModeImage(boolean isLocation) {
        weatherView.setLocateModeImage(isLocation);
    }

    @Override
    public void toastMessage(String msg) {
        weatherView.showToastMessage(msg);
    }


    @Override
    public void refreshWeather(boolean forceRefresh, @Nullable String countyName) {
        weatherModel.refreshWeather(forceRefresh, countyName);
    }

    @Override
    public void setCurrentWeatherInfo(ForecastBean forecastBean) {
        weatherView.showCurrentWeatherInfo(forecastBean);
    }

    @Override
    public void setCounty(String countyStr) {
        weatherView.setCounty(countyStr);
    }

    @Override
    public void stopBdLocation() {
        weatherModel.stopBdLocation();
    }

    @Override
    public void startSwipe() {
        weatherView.startSwipe();
    }

    @Override
    public void stopSwipe() {
        weatherView.stopSwipe();
    }

    @Override
    public void destroy() {
        weatherView = null;
        if (weatherModel != null) {
            weatherModel.destroy();
            weatherModel = null;
        }
    }
}
