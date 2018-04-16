package top.maweihao.weather.util.remoteView;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.RemoteViews;

import top.maweihao.weather.entity.dao.NewWeather;

/**
 * interface for utility class of widgets
 * Created by maweihao on 12/12/2017.
 */

public interface BaseWidgetUtil {

    void setIntent(Context context, @Nullable RemoteViews remoteViews);

    void refreshWidgetView(Context context, NewWeather weather, String countyName);
}
