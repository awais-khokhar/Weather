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
import top.maweihao.weather.service.SimpleWidgetUpdateService;

/**
 * Implementation of App Widget functionality.
 */
public class SimpleWeatherWidget extends AppWidgetProvider {

    private static final int WEATHER_PENDING_INTENT_CODE = 121;
    private static final int CLOCK_PENDING_INTENT_CODE = 221;

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);


        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context, CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_weather_widget);
        views.setOnClickPendingIntent(R.id.simple_widget_left, clockPendingIntent);
        views.setOnClickPendingIntent(R.id.simple_widget_right, weatherPendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        context.startService(new Intent(context, SimpleWidgetUpdateService.class));
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

