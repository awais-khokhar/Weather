package top.maweihao.weather.util.remoteView;

import android.content.Context;

import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.HeWeather.HeNowWeather;

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

    // 使用和风天气的数据只能刷新 TallWidget 和 SimpleWidget
    public static void refreshWidget(final Context context, final HeNowWeather heNowWeather) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, heNowWeather);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, heNowWeather);
            }
        }).start();
    }

    // 为 BigWidget 带上下次刷新时间，测试用
    @Deprecated
    public static void refreshWidget(final Context context, final ForecastBean forecastBean, final int minute) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, forecastBean);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, forecastBean);
                if (BigWidgetUtils.isEnable(context))
                    BigWidgetUtils.refreshWidgetView(context, forecastBean, minute);
            }
        }).start();
    }

    public static boolean hasAnyWidget(Context context) {
        return SimpleWidgetUtils.isEnable(context)
                || TallWidgetUtils.isEnable(context)
                || BigWidgetUtils.isEnable(context);
    }

    public static boolean hasBigWidget(Context context) {
        return BigWidgetUtils.isEnable(context);
    }
}
