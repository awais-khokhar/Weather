package top.maweihao.weather;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.maweihao.weather.gson.temperature;

public class SyncService extends Service {

    static final String TAG = "SyncService";
    OkHttpClient client;
    Gson gson;
    List<temperature> temList;
    NotificationManager manager;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        client = new OkHttpClient();
        gson = new Gson();
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String cUrl = prefs.getString("cUrl", null);
                String fUrl = prefs.getString("furl", null);
                if (fUrl != null) {
                    try {
                        Request request = new Request.Builder()
                                .url(fUrl).build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        parseJSON(responseData);
                        temperature temp = temList.get(0);
                        sendNotification("Today temperature:", temp.getMin() + " - " + temp.getMax());
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onStartCommand: okhttp error");
                    } catch (JSONException e) {
                        Log.e(TAG, "onStartCommand: Gson error");
                        e.printStackTrace();
                    }
                } else {
                    Log.e(TAG, "onStartCommand: furl == null");
                }
            }
        }).start();
        stopSelf();
        return super.onStartCommand(intent, flags, startId);
    }

    private void sendNotification(String title, String text) {
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_cloud_queue_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.weather_few_clouds))
                .build();
        manager.notify(1, notification);
    }

    private void parseJSON(String responseData) throws JSONException {
        JSONObject all = new JSONObject(responseData);
        JSONObject result = all.getJSONObject("result");
        JSONObject daily = result.getJSONObject("daily");
        JSONArray tem = daily.getJSONArray("temperature");
        temList = gson.fromJson(tem.toString(), new TypeToken<List<temperature>>() {
        }.getType());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
