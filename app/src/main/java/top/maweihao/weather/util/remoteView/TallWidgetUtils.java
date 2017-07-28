package top.maweihao.weather.util.remoteView;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.widget.RemoteViews;

import top.maweihao.weather.R;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.HeWeather.HeNowWeather;
import top.maweihao.weather.bean.RealTimeBean;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.widget.TallWeatherWidget;

/**
 * Util class for TallWeatherWidget
 * Created by ma on 17-7-27.
 */

public class TallWidgetUtils {

    public static boolean isEnable(Context context) {
        int[] widgetIds = AppWidgetManager.getInstance(context)
                .getAppWidgetIds(new ComponentName(context, TallWeatherWidget.class));
        return widgetIds != null && widgetIds.length > 0;
    }

    public static void refreshWidgetView(Context context, ForecastBean forecastBean) {
        String countyName = getCounty(context);

        ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();
        int tem = Utility.intRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        String skycon = hourlyBean.getSkycon().get(0).getValue();
        Double precipitation = hourlyBean.getPrecipitation().get(0).getValue();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, precipitation, WeatherActivity.HOURLY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, precipitation, WeatherActivity.HOURLY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    public static void refreshWidgetView(Context context, RealTimeBean realTimeBean) {
        String countyName = getCounty(context);

        int tem = Utility.intRoundDouble(realTimeBean.getResult().getTemperature());
        String skycon = realTimeBean.getResult().getSkycon();
        float intensity = realTimeBean.getResult().getPrecipitation().getLocal().getIntensity();
        String skyconString = Utility.chooseWeatherSkycon(context, skycon, intensity, WeatherActivity.MINUTELY_MODE);
        int icon = Utility.chooseWeatherIcon(skycon, intensity, WeatherActivity.MINUTELY_MODE, false);

        updateWidgetView(context, icon, countyName, skyconString, tem);
    }

    public static void refreshWidgetView(Context context, HeNowWeather heNowWeather) {
        // TODO: 17-7-28
    }

    private static void updateWidgetView(Context context, int icon, String countyName, String skyconString, int tem) {
        RemoteViews tallViews = new RemoteViews(context.getPackageName(), R.layout.tall_weather_widget);
        tallViews.setImageViewResource(R.id.tall_widget_skycon, icon);
        tallViews.setTextViewText(R.id.tall_widget_info, countyName + " | " + skyconString + ' ' + tem + '°');
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
        appWidgetManager.updateAppWidget(new ComponentName(context, TallWeatherWidget.class), tallViews);

    }

    private static String getCounty(Context context) {
        PreferenceConfigContact configContact = Utility.createSimpleConfig(context).create(PreferenceConfigContact.class);
        return configContact.getCountyName() == null ? "error" : configContact.getCountyName();
    }
}
