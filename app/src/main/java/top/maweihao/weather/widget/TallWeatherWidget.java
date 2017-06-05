package top.maweihao.weather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import top.maweihao.weather.R;
import top.maweihao.weather.util.LunarCalendar;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link TallWeatherWidgetConfigureActivity TallWeatherWidgetConfigureActivity}
 */
public class TallWeatherWidget extends AppWidgetProvider {

    public static final String TAG = "TallWeatherWidget";

    static LunarCalendar lunarCalendar = new LunarCalendar();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Boolean visible = TallWeatherWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tall_weather_widget);
        if (visible) {
            Log.d(TAG, "updateAppWidget: visible");
            views.setViewVisibility(R.id.tall_widget_lunar, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.tall_widget_lunar, View.GONE);
        }
        views.setTextViewText(R.id.tall_widget_lunar, lunarCalendar.getLunarDateFromTimeMills());
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        Log.d(TAG, "onUpdate: tall widget update");

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
        for (int appWidgetId : appWidgetIds) {
            TallWeatherWidgetConfigureActivity.deleteTitlePref(context, appWidgetId);
        }
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

