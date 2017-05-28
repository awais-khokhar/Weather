package top.maweihao.weather;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Implementation of App Widget functionality.
 */
public class SimpleWeatherWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, WeatherActivity.class), 0);
//        Intent clockIntent = new Intent();
//        clockIntent.setClassName("com.google.android.deskclock", "com.google.android.deskclock.clock");
//        PendingIntent clockPendingClock = PendingIntent.getActivity(context, 0, clockIntent, 0);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_weather_widget);
        views.setOnClickPendingIntent(R.id.weather_widget, weatherPendingIntent);
//        views.setOnClickPendingIntent(R.id.widget_clock_day_horizontal_clockContainer, clockPendingClock);
//        views.setTextViewText(R.id.appwidget_text, widgetText);

//        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
//        context.startService(new Intent(context, SimpleWidgetUpdateService.class));
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

