package top.maweihao.weather.util;

import android.content.Context;
import android.content.Intent;

import top.maweihao.weather.service.WidgetSyncService;

/**
 * helper class for all services
 * Created by ma on 17-8-24.
 */

public class ServiceUtil {
    public static void startWidgetSyncService(Context context, boolean forceRefresh) {
        Intent intent = new Intent(context, WidgetSyncService.class);
        intent.putExtra(WidgetSyncService.force_refresh, forceRefresh);
        context.startService(intent);
    }

    public static void stopWidgetSyncService(Context context) {
        Intent intent = new Intent(context, WidgetSyncService.class);
        context.stopService(intent);
    }
}
