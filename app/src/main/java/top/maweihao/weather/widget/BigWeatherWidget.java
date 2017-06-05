package top.maweihao.weather.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.widget.RemoteViews;

import top.maweihao.weather.R;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.service.BigWidgetUpdateService;

/**
 * Implementation of BigWeatherWidget functionality.
 */
public class BigWeatherWidget extends AppWidgetProvider {

    public static final String TAG = "BigWeatherWidget";
    private static final int WEATHER_PENDING_INTENT_CODE = 123;
    private static final int CLOCK_PENDING_INTENT_CODE = 223;
    private static final int TALL_WIDGET_REFRESH_CODE = 323;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context, CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        PendingIntent refreashPendingIntent = PendingIntent.getService(context, TALL_WIDGET_REFRESH_CODE, new Intent(context, BigWidgetUpdateService.class), PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.big_weather_widget);
        views.setOnClickPendingIntent(R.id.big_widget_clock, clockPendingIntent);
        views.setOnClickPendingIntent(R.id.big_widget_skycon, weatherPendingIntent);
        views.setOnClickPendingIntent(R.id.big_weather_refresh, refreashPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        context.startService(new Intent(context, BigWidgetUpdateService.class));
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

