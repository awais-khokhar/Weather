package top.maweihao.weather.util;

import android.content.Context;
import android.content.Intent;

import top.maweihao.weather.service.WidgetSyncService;

/**
 * helper class for service
 * Created by ma on 17-8-24.
 */

public class ServiceUtil {
    public static void startWidgetSyncService(Context context, boolean forceRefresh) {
        if (forceRefresh) {
            Intent intent = new Intent(context, WidgetSyncService.class);
            intent.putExtra(WidgetSyncService.force_refresh, true);
            context.startService(intent);
        } else if (!WidgetSyncService.working) {
            Intent intent = new Intent(context, WidgetSyncService.class);
            context.startService(intent);
        }
    }

    public static void stopWidgetSyncService(Context context) {
        Intent intent = new Intent(context, WidgetSyncService.class);
        context.stopService(intent);
    }
}
