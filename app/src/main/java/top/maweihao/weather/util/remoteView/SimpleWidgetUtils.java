package top.maweihao.weather.util.remoteView;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.support.annotation.NonNull;
import android.widget.RemoteViews;

import java.util.GregorianCalendar;

import top.maweihao.weather.R;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.HeWeather.HeNowWeather;
import top.maweihao.weather.entity.RealTimeBean;
import top.maweihao.weather.util.HeWeatherUtil;
import top.maweihao.weather.util.LunarUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.SimpleWeatherWidget;

/**
 * Util class for SimpleWeatherWidget
 * Created by ma on 17-7-27.
 */

public class SimpleWidgetUtils {

    public static final int WEATHER_PENDING_INTENT_CODE = 121;
    public static final int CLOCK_PENDING_INTENT_CODE = 221;

    public static boolean isEnable(Context context) {
        int[] widgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, SimpleWeatherWidget.class));
        return widgetIds != null && widgetIds.length > 0;
    }

    public static void refreshWidgetView(Context context, @NonNull ForecastBean forecastBean) {
        String countyName = getCounty(context);

        ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();
        int tem = Utility.intRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        String skycon = hourlyBean.getSkycon().get(0).getValue();
        Double precipitation = hourlyBean.getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, precipitation, WeatherActivity.HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, precipitation, WeatherActivity.HOURLY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    public static void refreshWidgetView(Context context, @NonNull RealTimeBean realTimeBean) {
        String countyName = getCounty(context);

        int tem = Utility.intRoundDouble(realTimeBean.getResult().getTemperature());
        String skycon = realTimeBean.getResult().getSkycon();
        float intensity = realTimeBean.getResult().getPrecipitation().getLocal().getIntensity();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, intensity, WeatherActivity.MINUTELY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, intensity, WeatherActivity.MINUTELY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    public static void refreshWidgetView(Context context, @NonNull HeNowWeather heNowWeather) {
        String countyName = getCounty(context);
        if (heNowWeather.getHeWeather5().get(0).getStatus().equals("ok")) {
            HeNowWeather.HeWeather5Bean.NowBean now = heNowWeather.getHeWeather5().get(0).getNow();
            int tem = Integer.parseInt(now.getTmp());
            String skyconString = now.getCond().getTxt();
            int icon = HeWeatherUtil.chooseHeIcon(now.getCond().getCode());

            updateWidgetView(context, icon, countyName, skyconString, tem);
        }
    }

    private static String getCounty(Context context) {
        PreferenceConfigContact configContact = Utility.createSimpleConfig(context).create(PreferenceConfigContact.class);
        return configContact.getCountyName() == null ? "error" : configContact.getCountyName();
    }

    private static void updateWidgetView(Context context, int icon, String countyName, String skyconString, int tem) {
        RemoteViews simpleViews = new RemoteViews(context.getPackageName(), R.layout.widget_simple_weather);
        simpleViews.setImageViewResource(R.id.simple_widget_skycon, icon);
        simpleViews.setTextViewText(R.id.simple_widget_info, countyName + " | " + skyconString + ' ' + tem + '°');

        LunarUtil lunarUtilDate = new LunarUtil(new GregorianCalendar());
        simpleViews.setTextViewText(R.id.simple_widget_lunar, lunarUtilDate.toString());

        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context, CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        simpleViews.setOnClickPendingIntent(R.id.simple_widget_left, clockPendingIntent);
        simpleViews.setOnClickPendingIntent(R.id.simple_widget_right, weatherPendingIntent);

        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, SimpleWeatherWidget.class), simpleViews);
    }
}
