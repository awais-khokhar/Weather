package top.maweihao.weather.presenter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import top.maweihao.weather.R;
import top.maweihao.weather.contract.BasePresenter;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.SingleWeather;
import top.maweihao.weather.model.WeatherActivityModel;
import top.maweihao.weather.util.ServiceUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.remoteView.WidgetUtils;

import static top.maweihao.weather.activity.WeatherActivity.HOURLY_MODE;
import static top.maweihao.weather.util.Utility.stringRoundDouble;

/**
 * 接口对接，数据传递
 * Created by limuyang on 2017/5/31.
 */

public class WeatherActivityPresenter implements WeatherActivityContract.Presenter, BasePresenter {

    private static final String TAG = WeatherActivityPresenter.class.getSimpleName();

    private WeatherActivityContract.View weatherView;
    private WeatherActivityContract.Model weatherModel;

    public WeatherActivityPresenter(Context context, WeatherActivityContract.View weatherView) {
        this.weatherView = weatherView;
        this.weatherModel = new WeatherActivityModel(context, this);
    }

    @Override
    public void rainInfo(String now, String today) {
        weatherView.setRainInfo(now, today);
    }

    /**
     * 展示未来5天的天气
     */
    @Override
    public void setDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean) {
        int length = dailyBean.getSkycon().size();
        ArrayList<SingleWeather> singleWeatherArrayList = weatherModel.getDailyWeatherList();
        singleWeatherArrayList.clear();
        int dayOfWeek = Utility.getDayOfWeek();
        if (dailyBean.getStatus().equals("ok")) {
            SimpleDateFormat oldsdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat newsdf = new SimpleDateFormat("MM/dd", Locale.CHINA);
            for (int i = 0; i < length; i++) {
                String time = "";
                try {
                    Date date = oldsdf.parse(dailyBean.getSkycon().get(i).getDate());
                    time = newsdf.format(date) + " " + ((Activity) weatherView).getResources().getStringArray(R.array.week)[(dayOfWeek + i - 1) % 7];
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int icon = Utility.chooseWeatherIcon(dailyBean.getSkycon().get(i).getValue(),
                        dailyBean.getPrecipitation().get(i).getAvg(), HOURLY_MODE, false);
                String skyconDesc;
                // 在有 desc 时优先显示 desc 的内容
                if (dailyBean.getDesc() == null) {
                    skyconDesc = Utility.chooseWeatherSkycon((Activity) weatherView, dailyBean.getSkycon().get(i).getValue(),
                            dailyBean.getPrecipitation().get(i).getAvg(), HOURLY_MODE);
                } else {
                    skyconDesc = dailyBean.getDesc().get(i).getValue();
                }
                String temperature = stringRoundDouble(dailyBean.getTemperature().get(i).getMax()) + "° / "
                        + stringRoundDouble(dailyBean.getTemperature().get(i).getMin()) + '°';
                singleWeatherArrayList.add(new SingleWeather(time, icon, skyconDesc, temperature));
            }
            weatherView.updateDailyRecyclerView();
        }
        if (weatherView.isRefreshDone()) {
            stopSwipe();
            weatherView.setRefreshDone(false);
        } else {
            weatherView.setRefreshDone(true);
        }
    }

    @Override
    public void setHourlyWeatherChart(ForecastBean.ResultBean.HourlyBean hourlyBean) {
        int length = hourlyBean.getSkycon().size();
        Log.d(TAG, "setHourlyWeatherChart: total " + length + " hour");
        ArrayList<SingleWeather> singleWeatherArrayList = weatherModel.getHourWeatherList();
        singleWeatherArrayList.clear();
        for (int i = 0; i < length; i++) {
            String time = hourlyBean.getSkycon().get(i).getDatetime().substring(11, 16);
            int icon = Utility.chooseWeatherIcon(hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE, true);
            String skyconDesc = Utility.chooseWeatherSkycon((Activity)weatherView, hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE);
            String temperature = stringRoundDouble(hourlyBean.getTemperature().get(i).getValue()) + '°';
            singleWeatherArrayList.add(new SingleWeather(time, icon, skyconDesc, temperature));
        }
        singleWeatherArrayList.get(0).setTime("现在");
        weatherView.updateHourlyRecyclerView();
    }

    /**
     * 更新成功后设置显示时间
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
    public void setLocationDetail(String locationDetail) {
        weatherView.setLocationDetail(locationDetail);
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
        weatherView.initHourlyRecyclerView(weatherModel.getHourWeatherList());
    }

    @Override
    public void initDailyView() {
        weatherView.initDailyRecyclerView(weatherModel.getDailyWeatherList());
    }

    @Override
    public void updateWidget(ForecastBean forecastBean) {
        if (WidgetUtils.hasAnyWidget((Activity) weatherView)) {
            ServiceUtil.startWidgetSyncService((Activity) weatherView, true);
        }
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {

    }
}
