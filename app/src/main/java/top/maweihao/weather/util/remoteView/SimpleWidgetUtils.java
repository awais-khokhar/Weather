package top.maweihao.weather.util.remoteView;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RemoteViews;

import java.util.GregorianCalendar;

import top.maweihao.weather.R;
import top.maweihao.weather.view.WeatherActivity;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.util.HeWeatherUtil;
import top.maweihao.weather.util.LunarUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.SimpleWeatherWidget;
import top.maweihao.weather.widget.SimpleWidgetConfigureActivity;

import static top.maweihao.weather.util.Utility.HOURLY_MODE;

/**
 * Util class for SimpleWeatherWidget
 * Created by ma on 17-7-27.
 */

public class SimpleWidgetUtils {

    private static final String TAG = SimpleWidgetUtils.class.getSimpleName();
    private static final int WEATHER_PENDING_INTENT_CODE = 121;
    private static final int CLOCK_PENDING_INTENT_CODE = 221;

    public static boolean isEnable(Context context) {
        int[] widgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, SimpleWeatherWidget.class));
        return widgetIds != null && widgetIds.length > 0;
    }

    public static void refreshWidgetView(Context context, @NonNull NewWeather weather, String countyName) {

        NewWeather.ResultBean.HourlyBean hourlyBean = weather.getResult().getHourly();
        int tem = Utility.intRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        String skycon = hourlyBean.getSkycon().get(0).getValue();
        Double precipitation = hourlyBean.getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, precipitation, HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, precipitation, HOURLY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    public static void refreshWidgetView(Context context, @NonNull NewHeWeatherNow weather, String countyName) {
        if (weather.getHeWeather5().get(0).getStatus().equals("ok")) {
            NewHeWeatherNow.HeWeather5Bean.NowBean now = weather.getHeWeather5().get(0).getNow();
            int tem = Integer.parseInt(now.getTmp());
            String skyconString = now.getCond().getTxt();
            int icon = HeWeatherUtil.chooseHeIcon(Integer.parseInt(now.getCond().getCode()));

            updateWidgetView(context, icon, countyName, skyconString, tem);
        }
    }

    private static void updateWidgetView(Context context, int icon, String countyName, String skyconString, int tem) {
        RemoteViews simpleViews = new RemoteViews(context.getPackageName(), R.layout.widget_simple_weather);
        simpleViews.setImageViewResource(R.id.simple_widget_skycon, icon);
        simpleViews.setTextViewText(R.id.simple_widget_info, countyName + " | " + skyconString + ' ' + tem + '°');

        boolean lunar = SimpleWidgetConfigureActivity.loadLunarPref(context);
        boolean card = SimpleWidgetConfigureActivity.loadCardPref(context);

        if (lunar) {
            simpleViews.setViewVisibility(R.id.simple_widget_lunar, View.VISIBLE);
            LunarUtil lunarUtilDate = new LunarUtil(new GregorianCalendar());
            simpleViews.setTextViewText(R.id.simple_widget_lunar, lunarUtilDate.toString());
        } else {
            simpleViews.setViewVisibility(R.id.simple_widget_lunar, View.GONE);
        }

        if (card) {
            simpleViews.setViewVisibility(R.id.widget_big_card, View.VISIBLE);
        } else {
            simpleViews.setViewVisibility(R.id.widget_big_card, View.GONE);
        }

        setIntent(context, simpleViews);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, SimpleWeatherWidget.class), simpleViews);
    }

    public static void setIntent(Context context, @Nullable RemoteViews simpleViews) {
        boolean refresh = false;
        if (simpleViews == null) {
            simpleViews = new RemoteViews(context.getPackageName(), R.layout.widget_simple_weather);
            refresh = true;
        }

        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context,
                CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        simpleViews.setOnClickPendingIntent(R.id.simple_widget_left, clockPendingIntent);
        simpleViews.setOnClickPendingIntent(R.id.simple_widget_right, weatherPendingIntent);

        if (refresh) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, SimpleWeatherWidget.class), simpleViews);
        }
    }
}
