package top.maweihao.weather.presenter;

import android.content.Context;

import java.util.ArrayList;

import top.maweihao.weather.ExtendedWeatherData;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.bean.HourlyWeather;
import top.maweihao.weather.model.WeatherActivityModel;

/**
 * 接口对接，数据传递
 * Created by limuyang on 2017/5/31.
 */

public class WeatherActivityPresenter implements WeatherActivityContract.Presenter {
    private WeatherActivityContract.View weatherView;
    private WeatherActivityContract.Model weatherModel;

    public WeatherActivityPresenter(Context context, WeatherActivityContract.View weatherView)
    {
        this.weatherView=weatherView;
        this.weatherModel=new WeatherActivityModel(context,this);
    }

    @Override
    public void rainInfo(String str) {
        weatherView.setRainInfo(str);
    }

    /**
     * 展示未来5天的天气
     */
    @Override
    public void setDailyWeatherInfo(final ArrayList<ExtendedWeatherData> weatherDatas) {
        weatherView.showDailyWeatherInfo(weatherDatas);
    }

    @Override
    public void setHourlyWeatherInfo(final ArrayList<HourlyWeather> hourlyWeathers) {
        weatherView.showHourlyWeatherInfo(hourlyWeathers);
    }

    /**
     * 更新成功后设置显示时间
     * @param bool
     */
    @Override
    public void isUpdate(boolean bool) {
        if (bool)
            weatherView.setLastUpdateTime();
    }

    @Override
    public void toastMessage(String msg) {
        weatherView.showToastMessage(msg);
    }

    /**
     * 通过json获取未来天气数据
     * @param responseText json数据
     */
    @Override
    public void getFullWeatherDataForJson(String responseText) {
        weatherModel.jsonFullWeatherData(responseText);
    }


    @Override
    public void setCurrentWeatherInfo(WeatherData weatherData) {
        weatherView.showCurrentWeatherInfo(weatherData);
    }

    @Override
    public void setCounty(String countyStr) {
        weatherView.setCounty(countyStr);
    }

    @Override
    public void getCountyByCoordinate(String coordinate)
    {
        weatherModel.getCountyByCoordinate(coordinate);
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
    public void afterGetCoordinate() {
        weatherModel.afterGetCoordinate();
    }

    @Override
    public void getCoordinateByIp() {
        weatherModel.getCoordinateByIp();
    }

    @Override
    public void getCoordinateByChoosePosition(String countyName) {
        weatherModel.getCoordinateByChoosePosition(countyName);
    }

    @Override
    public void destroy() {
        weatherView = null;
        if(weatherModel != null) {
            weatherModel.destroy();
            weatherModel = null;
        }
    }
}
