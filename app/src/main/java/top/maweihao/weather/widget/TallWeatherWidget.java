package top.maweihao.weather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import top.maweihao.weather.service.SyncService;
import top.maweihao.weather.util.remoteView.TallWidgetUtils;

/**
 * Implementation of App Widget functionality.
 * App Widget Configuration implemented in {@link TallWidgetConfigureActivity TallWidgetConfigureActivity}
 */
public class TallWeatherWidget extends AppWidgetProvider {

    public static final String TAG = TallWeatherWidget.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        TallWidgetUtils.setIntent(context, null);
//        ServiceUtil.startWidgetSyncService(context, true, false);
        SyncService.scheduleSyncService(context, true, false);
    }

    @Override
    public void onDeleted(Context context, int[] appWidgetIds) {
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        TallWidgetConfigureActivity.deleteAllPref(context);
        SyncService.stopSyncService(context);
    }
}

