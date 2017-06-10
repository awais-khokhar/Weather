package top.maweihao.weather.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.alibaba.fastjson.JSON;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import top.maweihao.weather.R;
import top.maweihao.weather.activity.SettingActivity;
import top.maweihao.weather.activity.WeatherActivity;
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Constants.DEBUG;

/**
 * 后台刷新service， 每晚提示第二天温差
 * 还有问题
 */

public class SyncService extends Service {

    static final String TAG = "SyncService";

    Boolean isChinese = false;

    public static boolean isStarSendNotification; //标记

    private static String GET_HOUR;

    private static String GET_MINUTE;


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: SyncService created");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            isChinese = getResources().getConfiguration().getLocales().get(0).getDisplayLanguage().equals("中文");
        } else {
            isChinese = getResources().getConfiguration().locale.getDisplayLanguage().equals("zh-CN");
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        if (isStarSendNotification) //标记是否发送通知
            fetchData();
        startAgain();
        return START_NOT_STICKY;
    }

    private void fetchData() {
        final OkHttpClient client = new OkHttpClient();
        new Thread(new Runnable() {
            @Override
            public void run() {

                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String fUrl = prefs.getString("furl", null);
                if (fUrl != null) {
                    try {
                        Request request = new Request.Builder()
                                .url(fUrl).build();
                        Response response = client.newCall(request).execute();
                        String responseData = response.body().string();
                        //fastJson解析数据
                        ForecastBean forecastBean = JSON.parseObject(responseData, ForecastBean.class);
                        ForecastBean.ResultBean.DailyBean.TemperatureBeanX temp = forecastBean.getResult().getDaily().getTemperature().get(0);
                        ForecastBean.ResultBean.DailyBean.TemperatureBeanX temp2 = forecastBean.getResult().getDaily().getTemperature().get(1);
//                        parseJSON(responseData);
                        calTemDiff(Utility.intRoundFloat(temp.getMax()), Utility.intRoundFloat(temp.getMin()),
                                Utility.intRoundFloat(temp2.getMax()), Utility.intRoundFloat(temp2.getMin()));
                    } catch (IOException e) {
                        e.printStackTrace();
                        Log.e(TAG, "onStartCommand: okhttp error");
                    }
                } else {
                    Log.e(TAG, "onStartCommand: furl == null");
                }
            }
        }).start();
    }

    private void startAgain() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//                                Log.d(TAG, "SettingActivity::notification_time " + sp.getString("notification_time",null));
        String time = sp.getString("notification_time", null);
        String[] splitTime = null;
        if (time != null) {
            splitTime = time.split(SettingActivity.TIME_SPLIT);
            GET_HOUR = splitTime[0];
            GET_MINUTE = splitTime[1];
        } else {
            GET_HOUR = "18";
            GET_MINUTE = "0";
        }
        Log.d(TAG, "startAgain: SharedPreferences == " + time);

        //     每天18:00启动
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Calendar calendar = new GregorianCalendar();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(GET_HOUR));
        calendar.set(Calendar.MINUTE, Integer.parseInt(GET_MINUTE));
        calendar.set(Calendar.SECOND, 0); //加上秒和毫秒，以精确时间，保证设置的时间为绝对时间。
        calendar.set(Calendar.MILLISECOND, 0);

        if (System.currentTimeMillis() > calendar.getTimeInMillis()) //使用系统时间进行判断，保证绝对性
        {
            calendar.set(Calendar.DAY_OF_MONTH, day + 1);
            if (DEBUG)
                Log.d(TAG, "startAgain: day+1    now is:" + calendar.get(Calendar.DAY_OF_MONTH));
        }

        if (DEBUG) {
            Log.d(TAG, "startAgain: calendar == " + calendar.getTime());
            Log.d(TAG, "startAgain: calendar getTimeInMillis== " + calendar.getTimeInMillis());
            Log.d(TAG, "startAgain: System.currentTimeMillis== " + System.currentTimeMillis());
        }


        Intent intent = new Intent(this, SyncService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        isStarSendNotification = true;
    }

    private void sendNotification(String title, String text) {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_cloud_queue_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.weather_few_clouds))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        manager.notify(1, notification);
    }



    /**
     * 计算温差，发送通知
     */
    private void calTemDiff(int todayMax, int todayMin, int tomMax, int tomMin) {
        int maxDiff = tomMax - todayMax;
        int minDiff = tomMin - todayMin;
        if (maxDiff * minDiff >= 0) {
            int a = Math.abs(maxDiff);
            int b = Math.abs(minDiff);
            if (Math.max(a, b) >= 3) {
                Calendar calendar = new GregorianCalendar();
                int nextDay = calendar.get(Calendar.DAY_OF_WEEK) + 1;
                String dayOfWeek = getResources().getStringArray(R.array.weekend)[nextDay == 7 ? 0 : nextDay];
                String tem = (maxDiff > 0 || minDiff > 0) ? getResources().getString(R.string.warmer) : getResources().getString(R.string.colder);
                if (isChinese) {
                    sendNotification(dayOfWeek + "将" + tem + ' ' + Math.max(a, b) + "° ",
                            todayMin + "°/" + todayMax + "°  ···>  " + tomMin + "°/" + tomMax + "° ");
                } else {
                    sendNotification(Math.max(a, b) + "° " + tem + " than " + dayOfWeek,
                            todayMin + "°/" + todayMax + "°  ···>  " + tomMin + "°/" + tomMax + "° ");
                }
            }
        } else {
            Log.d(TAG, "calTemDiff: diff: " + maxDiff * minDiff);
            sendNotification("Temperature", todayMin + "° - " + todayMax + "° -> " + tomMin + "° - " + tomMax + "° ");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: SyncService destroyed");
    }
}
