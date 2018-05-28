package top.maweihao.weather.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import java.util.ArrayList;

import top.maweihao.weather.R;
import top.maweihao.weather.entity.Alert;
import top.maweihao.weather.view.AlertActivity;
import top.maweihao.weather.view.WeatherActivity;

public class NotificationUtil {

    private static final String TAG = NotificationUtil.class.getSimpleName();

    public static void sendWeatherNotification(Context context, String title, String content) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            Log.e(TAG, "sendWeatherNotification: NotificationManager = null");
            return;
        }
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText(content);
        style.setBigContentTitle(title);
        Intent intent = new Intent(context, WeatherActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                Constants.NOTIFICATION_OPEN_WEATHER_ACTIVITY, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_WEATHER,
                    context.getResources().getString(R.string.weather_notifications), NotificationManager.IMPORTANCE_LOW);
            manager.createNotificationChannel(channel);
        }
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_WEATHER);
        Notification notification = builder.setContentTitle(title)
                .setContentText(content)
                .setStyle(style)
                .setSmallIcon(R.drawable.ic_cloud_queue_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        manager.notify(Constants.ID_NOTIFICATION_NORMAL, notification);
    }

    public static void sendAlarmNotification(Context context, ArrayList<Alert> alerts) {
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (manager == null) {
            Log.e(TAG, "sendAlarmNotification: NotificationManager = null");
            return;
        }
        Intent intent = new Intent(context, AlertActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putParcelableArrayListExtra(AlertActivity.KEY_ALERT_ACTIVITY_ALERT_LIST, alerts);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                Constants.NOTIFICATION_OPEN_ALARM_ACTIVITY, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(Constants.CHANNEL_ALERT,
                    context.getResources().getString(R.string.action_alert), NotificationManager.IMPORTANCE_DEFAULT);
            manager.createNotificationChannel(channel);
        }
        Alert alert = alerts.get(0);
        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
        style.bigText(alert.content);
        style.setBigContentTitle(alert.subtitle);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, Constants.CHANNEL_ALERT);
        Notification notification = builder.setContentTitle(alert.subtitle)
                .setContentText(alert.content)
                .setSmallIcon(alert.iconId)
                .setStyle(style)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        manager.notify(Constants.ID_NOTIFICATION_ALERT, notification);
    }

}
