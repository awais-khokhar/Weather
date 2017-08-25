package top.maweihao.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

import top.maweihao.weather.service.NotifyService;

import static top.maweihao.weather.util.Constants.DEBUG;

/**
 * 开机启动 SyncService
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
//        因为service还有问题，默认不开
        if (prefs.getBoolean("notification", false)) {
            Intent startIntent = new Intent(context, NotifyService.class);
            context.startService(startIntent);
            if (DEBUG) {
                Toast.makeText(context, "Weather: SyncService started", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
