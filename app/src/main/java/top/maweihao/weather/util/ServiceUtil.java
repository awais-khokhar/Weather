package top.maweihao.weather.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import top.maweihao.weather.service.WidgetSyncService;

import static android.content.Context.ALARM_SERVICE;

/**
 * helper class for all services
 * Created by ma on 17-8-24.
 */

public class ServiceUtil {

    private static final String TAG = ServiceUtil.class.getSimpleName();

    public static void startWidgetSyncService(Context context, boolean forceRefresh, boolean reOpen) {
        Log.d(TAG, "startWidgetSyncService: here");
        if (reOpen || !WidgetSyncService.working) {
            Intent intent = new Intent(context, WidgetSyncService.class);
            intent.putExtra(WidgetSyncService.force_refresh, forceRefresh);
            PendingIntent pendingIntent = PendingIntent.getService(context,
                    Constants.WidgetSyncServiceRequestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            if (alarmManager != null) {
                alarmManager.cancel(pendingIntent);
                alarmManager.set(AlarmManager.ELAPSED_REALTIME,
                        SystemClock.elapsedRealtime() + 5 * 1000, pendingIntent);
                Log.d(TAG, "startWidgetSyncService in 5 second");
//                Toast.makeText(context, "i started!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static void stopWidgetSyncService(Context context) {
        Intent intent = new Intent(context, WidgetSyncService.class);
        context.stopService(intent);
    }
}
