package top.maweihao.weather.util;

import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import top.maweihao.weather.entity.Alert;
import top.maweihao.weather.entity.dao.NewWeather;

public class WeatherUtil {

    private static final String TAG = WeatherUtil.class.getSimpleName();

    public static @Nullable ArrayList<Alert> getAlertList(NewWeather weather) {
        ArrayList<Alert> list = null;
        NewWeather.ResultBean.AlertBean alertBean = weather.getResult().getAlert();
        if (alertBean != null && alertBean.getContent().size() > 0) {
            Log.d(TAG, "showCurrentWeather: alert size=" + alertBean.getContent().size());
            list = new ArrayList<>();
            for (NewWeather.ResultBean.AlertBean.ContentBean contentBean : alertBean.getContent()) {
                list.add(new Alert(contentBean.getStatus(),
                        Integer.parseInt(contentBean.getCode()),
                        contentBean.getDescription(), contentBean.getAlertId(),
                        contentBean.getCity() + contentBean.getCounty(),
                        contentBean.getTitle()));
            }
        }
        return list;
    }
}
