package top.maweihao.weather.util.remoteView;

import android.content.Context;

import top.maweihao.weather.bean.ForecastBean;

/**
 * Utility class for updating all widgets
 * Created by ma on 17-7-27.
 */

public class WidgetUtils {
    public static void refreshWidget(final Context context, final ForecastBean forecastBean) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, forecastBean);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, forecastBean);
                if (BigWidgetUtils.isEnable(context))
                    BigWidgetUtils.refreshWidgetView(context, forecastBean);
            }
        }).start();
    }
}
