package top.maweihao.weather.contract;

import top.maweihao.weather.entity.NewWeather;
import top.maweihao.weather.refactor.MLocation;

/**
 * mvp contract for WeatherActivity
 * Created by maweihao on 2017/10/25.
 */

public interface NewWeatherActivityContract {

    interface newView extends BaseView<newPresenter> {

        void showWeather(NewWeather weather);
        void showLocation(MLocation location);
        void setUpdateTime(long timeInMills);
        void showNetworkError();
        void showError(String error);
        void showPermissionError();
        void showIpLocateMessage();
        void showRefreshingState(boolean refresh);
        void askForChoosePosition();
    }

    interface newPresenter extends BasePresenter {
        void fetchData();
        void locate();
        void refreshWeather(MLocation location);
    }
}
