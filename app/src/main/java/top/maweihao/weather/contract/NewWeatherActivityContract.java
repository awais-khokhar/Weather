package top.maweihao.weather.contract;

import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.entity.dao.MLocation;

/**
 * mvp contract for WeatherActivity
 * Created by maweihao on 2017/10/25.
 */

public interface NewWeatherActivityContract {

    interface NewView<N extends BasePresenter> extends BaseView<NewPresenter> {
        void showWeather(NewWeather weather);
        void showLocation(MLocation location);
        void setUpdateTime(long timeInMills);
        void showNetworkError();
        void showError(String error, boolean showOkButton);
        void showLocateError();
        void showPermissionError();
        void showIpLocateMessage();
        void setRefreshingState(boolean refresh);
        void askForChoosePosition();
    }

    interface NewPresenter extends BasePresenter {
        void fetchCachedData(boolean ignoreInterval);
        void refreshLocalWeather();
        void onPermissionDenied();
        void refreshChosenWeather(String desc);
        void locate();
        void refreshWeather(MLocation location);
        void onResume();
        void onPause();
        void onStop();
        void onDestroy();
    }
}
