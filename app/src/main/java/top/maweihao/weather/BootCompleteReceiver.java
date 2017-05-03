package top.maweihao.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        if (prefs.getBoolean("notification", true)) {
            Intent startIntent = new Intent(context, SyncService.class);
            context.startService(startIntent);
            Toast.makeText(context, "SyncService started", Toast.LENGTH_SHORT).show();
        }
    }
}
