package top.maweihao.weather.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.service.SyncService;

/**
 * 开机启动 SyncService
 */
public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getBoolean(PreferenceConfigContact.NOTIFICATION, false)
                || prefs.getBoolean(PreferenceConfigContact.NOTIFICATION_ALARM, false)) {
            SyncService.scheduleSyncService(context.getApplicationContext(), false);
        }
    }
}
