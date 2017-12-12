package top.maweihao.weather.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

import top.maweihao.weather.util.ServiceUtil;
import top.maweihao.weather.util.remoteView.BigWidgetUtils;
import top.maweihao.weather.util.remoteView.WidgetUtils;

/**
 * Implementation of BigWeatherWidget functionality.
 */
public class BigWeatherWidget extends AppWidgetProvider {

    public static final String TAG = BigWeatherWidget.class.getSimpleName();

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        BigWidgetUtils.setIntent(context, null);
        ServiceUtil.startWidgetSyncService(context, true, false);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
        BigWidgetConfigureActivity.deleteAllPref(context);
        if (WidgetUtils.hasAnyWidget(context)) {
            ServiceUtil.stopWidgetSyncService(context);
        }
    }
}

