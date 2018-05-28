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
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.dao.NewHeWeatherNow;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.util.HeWeatherUtil;
import top.maweihao.weather.util.LunarUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.TallWeatherWidget;
import top.maweihao.weather.widget.TallWidgetConfigureActivity;

/**
 * Util class for TallWeatherWidget
 * Created by ma on 17-7-27.
 */

public class TallWidgetUtils {

    private static final String TAG = TallWidgetUtils.class.getSimpleName();
    private static final int WEATHER_PENDING_INTENT_CODE = 122;
    private static final int CLOCK_PENDING_INTENT_CODE = 222;

    public static boolean isEnable(Context context) {
        int[] widgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, TallWeatherWidget.class));
        return widgetIds != null && widgetIds.length > 0;
    }

    public static void refreshWidgetView(Context context, @NonNull NewWeather weather, String countyName) {
        NewWeather.ResultBean.HourlyBean hourlyBean = weather.getResult().getHourly();
        int tem = Utility.intRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        String skycon = hourlyBean.getSkycon().get(0).getValue();
        Double precipitation = hourlyBean.getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, precipitation, WeatherActivity.HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, precipitation, WeatherActivity.HOURLY_MODE, false);

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

    @Deprecated
    static void refreshWidgetView(Context context, ForecastBean forecastBean) {
//        setPendingIntent();
        String countyName = getCounty(context);

        ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();
        int tem = Utility.intRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        String skycon = hourlyBean.getSkycon().get(0).getValue();
        Double precipitation = hourlyBean.getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, precipitation, WeatherActivity.HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, precipitation, WeatherActivity.HOURLY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    private static void updateWidgetView(Context context, int icon, String countyName, String skyconString, int tem) {
        RemoteViews tallViews = new RemoteViews(context.getPackageName(), R.layout.widget_tall_weather);
        tallViews.setImageViewResource(R.id.tall_widget_skycon, icon);
        tallViews.setTextViewText(R.id.tall_widget_info, countyName + " | " + skyconString + ' ' + tem + '°');

        Boolean lunar = TallWidgetConfigureActivity.loadLunarPref(context);
        Boolean card = TallWidgetConfigureActivity.loadCardPref(context);
        if (lunar) {
            LunarUtil lunarUtilDate = new LunarUtil(new GregorianCalendar());
            tallViews.setViewVisibility(R.id.tall_widget_lunar, View.VISIBLE);

            tallViews.setTextViewText(R.id.tall_widget_lunar, lunarUtilDate.toString());
        } else {
            tallViews.setViewVisibility(R.id.tall_widget_lunar, View.GONE);
        }
        if (card) {
            tallViews.setViewVisibility(R.id.widget_tall_card, View.VISIBLE);
        } else {
            tallViews.setViewVisibility(R.id.widget_tall_card, View.GONE);
        }

        setIntent(context, tallViews);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, TallWeatherWidget.class), tallViews);

    }

    @Deprecated
    private static String getCounty(Context context) {
        PreferenceConfigContact configContact = Utility.createSimpleConfig(context).create(PreferenceConfigContact.class);
        return configContact.getCountyName() == null ? "error" : configContact.getCountyName();
    }

    public static void setIntent(Context context, @Nullable RemoteViews tallViews) {
        boolean refresh = false;
        if (tallViews == null) {
            refresh = true;
            tallViews = new RemoteViews(context.getPackageName(), R.layout.widget_tall_weather);
        }
        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context,
                CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        tallViews.setOnClickPendingIntent(R.id.tall_widget_clock, clockPendingIntent);
        tallViews.setOnClickPendingIntent(R.id.tall_widget_weather, weatherPendingIntent);

        if (refresh) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            appWidgetManager.updateAppWidget(new ComponentName(context, TallWeatherWidget.class), tallViews);
        }
    }

}
