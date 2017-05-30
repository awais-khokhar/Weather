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
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.simple_weather_widget);
        views.setOnClickPendingIntent(R.id.simple_weather_widget, weatherPendingIntent);
//        views.setOnClickPendingIntent(R.id.widget_clock_day_horizontal_clockContainer, clockPendingClock);

        // Instruct the widget manager to update the widget
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

