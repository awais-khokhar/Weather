package top.maweihao.weather.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.SingleWeather;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.model.WeatherActivityModel;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.activity.WeatherActivity.HOURLY_MODE;
import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Utility.stringRoundFloat;

/**
 * 接口对接，数据传递
 * Created by limuyang on 2017/5/31.
 */

public class WeatherActivityPresenter implements WeatherActivityContract.Presenter {

    private static final String TAG = "WeatherPresenter";

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

//    @Override
//    public void setHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean) {
//        weatherView.showHourlyWeatherInfo(hourlyBean);
//    }

    @Override
    public void setHourlyWeatherChart(ForecastBean.ResultBean.HourlyBean hourlyBean) {
        int length = hourlyBean.getSkycon().size();
        if (DEBUG)
            Log.d(TAG, "setHourlyWeatherChart: total " + length + " hour");
        ArrayList<SingleWeather> singleWeatherArrayList = weatherModel.getHourWeatherList();
        singleWeatherArrayList.clear();
        for (int i = 0; i < length; i++) {
            String time = hourlyBean.getSkycon().get(i).getDatetime().substring(11, 16);
            int icon = Utility.chooseWeatherIcon(hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE, true);
            String skyconDesc = Utility.chooseWeatherSkycon((Activity)weatherView, hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE);
            String temperature = stringRoundFloat(hourlyBean.getTemperature().get(i).getValue()) + '°';
            singleWeatherArrayList.add(new SingleWeather(time, icon, skyconDesc, temperature));
        }
        singleWeatherArrayList.get(0).setTime("现在");
        weatherView.updateRecyclerView();
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

    @Override
    public void initHourlyView() {
        weatherView.initRecyclerView(weatherModel.getHourWeatherList());
    }
}
