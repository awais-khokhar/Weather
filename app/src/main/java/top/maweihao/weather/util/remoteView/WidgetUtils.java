package top.maweihao.weather.util.remoteView;

import android.content.Context;
import android.util.Log;

import top.maweihao.weather.entity.HeWeather.HeNowWeather;
import top.maweihao.weather.entity.HeWeather.NewHeWeatherNow;
import top.maweihao.weather.entity.NewWeather;

/**
 * Utility class for updating all widgets
 * Created by ma on 17-7-27.
 */

public class WidgetUtils {

    private static final String TAG = WidgetUtils.class.getSimpleName();

    // 使用和风天气的数据只能刷新 TallWidget 和 SimpleWidget
    @Deprecated
    public static void refreshWidget(final Context context, final HeNowWeather heNowWeather,
                                     final String countyName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, heNowWeather, countyName);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, heNowWeather, countyName);
            }
        }).start();
    }

    public static void refreshWidget(final Context context, final NewHeWeatherNow weather,
                                     final String countyName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, weather, countyName);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, weather, countyName);
            }
        }).start();
    }

    public static void refreshWidget(final Context context, final NewWeather weather,
                                     final String countyName) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (SimpleWidgetUtils.isEnable(context))
                    SimpleWidgetUtils.refreshWidgetView(context, weather, countyName);
                if (TallWidgetUtils.isEnable(context))
                    TallWidgetUtils.refreshWidgetView(context, weather, countyName);
                if (BigWidgetUtils.isEnable(context))
                    BigWidgetUtils.refreshWidgetView(context, weather, countyName);
            }
        }).start();
    }

    public static void refreshBigWidgetTime(Context context) {
        Log.d(TAG, "refreshBigWidgetTime: updateTime2Now");
        if (hasBigWidget(context)) {
            BigWidgetUtils.updateTime2Now(context);
        }
    }

    // 为 BigWidget 带上下次刷新时间，测试用
//    @Deprecated
//    public static void refreshWidget(final Context context, final ForecastBean forecastBean, final int minute) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                if (SimpleWidgetUtils.isEnable(context))
//                    SimpleWidgetUtils.refreshWidgetView(context, forecastBean);
//                if (TallWidgetUtils.isEnable(context))
//                    TallWidgetUtils.refreshWidgetView(context, forecastBean);
//                if (BigWidgetUtils.isEnable(context))
//                    BigWidgetUtils.refreshWidgetView(context, forecastBean, minute);
//            }
//        }).start();
//    }

    /**
     * 是否添加了任何 widget
     * @param context context
     * @return bool
     */
    public static boolean hasAnyWidget(Context context) {
        return SimpleWidgetUtils.isEnable(context)
                || TallWidgetUtils.isEnable(context)
                || BigWidgetUtils.isEnable(context);
    }

    /**
     * 是否有添加了 BigWidget(不能用和风源刷新)
     * @param context context
     * @return bool
     */
    public static boolean hasBigWidget(Context context) {
        return BigWidgetUtils.isEnable(context);
    }
}
