package top.maweihao.weather.presenter;

import android.support.annotation.NonNull;

import io.reactivex.disposables.CompositeDisposable;
import top.maweihao.weather.contract.NewWeatherActivityContract;
import top.maweihao.weather.contract.WeatherData;

/**
 * Created by maweihao on 28/10/2017.
 */

public class NewWeatherPresenter implements NewWeatherActivityContract.newPresenter {

    private final WeatherData model;

    private final NewWeatherActivityContract.newView view;

    private final CompositeDisposable compositeDisposable;

    public NewWeatherPresenter(@NonNull NewWeatherActivityContract.newView view, WeatherData model) {
        this.model = model;
        this.view = view;
        compositeDisposable = new CompositeDisposable();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }
}
