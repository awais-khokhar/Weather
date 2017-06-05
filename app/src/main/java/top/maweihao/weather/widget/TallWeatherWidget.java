package top.maweihao.weather.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.provider.AlarmClock;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

import top.maweihao.weather.R;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.service.TallWidgetUpdateService;
import top.maweihao.weather.util.LunarCalendar;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link TallWeatherWidgetConfigureActivity TallWeatherWidgetConfigureActivity}
 */
public class TallWeatherWidget extends AppWidgetProvider {

    public static final String TAG = "TallWeatherWidget";
    private static final int WEATHER_PENDING_INTENT_CODE = 122;
    private static final int CLOCK_PENDING_INTENT_CODE = 222;

    static LunarCalendar lunarCalendar = new LunarCalendar();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        PendingIntent weatherPendingIntent = PendingIntent.getActivity(context, WEATHER_PENDING_INTENT_CODE,
                new Intent(context, WeatherActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        Intent mClockIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
        mClockIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent clockPendingIntent = PendingIntent.getActivity(context, CLOCK_PENDING_INTENT_CODE, mClockIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Boolean visible = TallWeatherWidgetConfigureActivity.loadTitlePref(context, appWidgetId);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.tall_weather_widget);
        if (visible) {
            Log.d(TAG, "updateAppWidget: visible");
            views.setViewVisibility(R.id.tall_widget_lunar, View.VISIBLE);
        } else {
            views.setViewVisibility(R.id.tall_widget_lunar, View.GONE);
        }
        views.setTextViewText(R.id.tall_widget_lunar, lunarCalendar.getLunarDateFromTimeMills());
        views.setOnClickPendingIntent(R.id.tall_widget_clock, clockPendingIntent);
        views.setOnClickPendingIntent(R.id.tall_widget_weather, weatherPendingIntent);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {

        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
        context.startService(new Intent(context, TallWidgetUpdateService.class));
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

