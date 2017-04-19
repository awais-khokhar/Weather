package top.maweihao.weather;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootCompleteReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent startIntent = new Intent(context, SyncService.class);
        context.startService(startIntent);
        Toast.makeText(context, "weather started", Toast.LENGTH_SHORT).show();
    }
}
