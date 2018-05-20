package top.maweihao.weather.service;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.maweihao.weather.R;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.util.LogUtils;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.view.SettingActivity;
import top.maweihao.weather.view.WeatherActivity;

/**
 * 后台刷新service， 每晚提示第二天温差
 */

public class PushService extends Service {

    private static final String TAG = PushService.class.getSimpleName();

    Boolean isChinese = false;

    public static boolean isStarSendNotification; //标记

    private static String GET_HOUR;

    private static String GET_MINUTE;

    private PreferenceConfigContact configContact;

    private WeatherData model;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtils.d("onCreate: SyncService created");
        isChinese = Utility.isChinese(this);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        LogUtils.d(TAG, "onStartCommand: ");
        configContact = Utility.createSimpleConfig(getApplicationContext()).create(PreferenceConfigContact.class);
        if (model == null) {
            model = WeatherRepository.getInstance(this);
        }
        if (isStarSendNotification) { //标记是否发送通知
            fetchData();
        }
        startAgain();
        return START_NOT_STICKY;
    }

    private void fetchData() {
        io.reactivex.Observable<NewWeather> observable = model.getLocalWeather();
        if (observable == null) {
            stopSelf();
            return;
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        if (weather.getStatus().equals("ok")) {
                            NewWeather.ResultBean.DailyBean.TemperatureBeanX temp =
                                    weather.getResult().getDaily().getTemperature().get(0);
                            NewWeather.ResultBean.DailyBean.TemperatureBeanX temp2 =
                                    weather.getResult().getDaily().getTemperature().get(1);
                            calTemDiff(Utility.intRoundDouble(temp.getMax()),
                                    Utility.intRoundDouble(temp.getMin()),
                                    Utility.intRoundDouble(temp2.getMax()),
                                    Utility.intRoundDouble(temp2.getMin()));
                            stopSelf();
                        } else {
                            LogUtils.e(TAG, "api error");
                            stopSelf();
                        }
                    }
                });
    }

    private void startAgain() {
        String time = configContact.getNotificationTime(null);
        if (!TextUtils.isEmpty(time)) {
            String[] splitTime = time.split(SettingActivity.TIME_SPLIT);
            GET_HOUR = splitTime[0];
            GET_MINUTE = splitTime[1];
        } else {
            GET_HOUR = "18";
            GET_MINUTE = "0";
        }
        LogUtils.d(TAG, "startAgain: SharedPreferences == " + time);

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
            LogUtils.d("startAgain: day+1    now is:" + calendar.get(Calendar.DAY_OF_MONTH));
        }

        Intent intent = new Intent(this, PushService.class);
        PendingIntent pendingIntent = PendingIntent.getService(this,
                0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
        isStarSendNotification = true;
    }

    private void sendNotification(String title, String text) {
        Intent intent = new Intent(getApplicationContext(), WeatherActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "weather";
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "weatherInfo", importance);
//            notificationChannel.enableLights(true);
//            notificationChannel.setLightColor(Color.RED);
//            notificationChannel.enableVibration(true);
            assert manager != null;
            manager.createNotificationChannel(notificationChannel);
        }
        Notification notification = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(text)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_cloud_queue_black_24dp)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.weather_few_clouds))
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();
        if (manager != null) {
            manager.notify(1, notification);
        }
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
            String titleStr;
            if (Math.max(a, b) >= 3) {
                Calendar calendar = new GregorianCalendar();
                int nextDay = calendar.get(Calendar.DAY_OF_WEEK) + 1;
                String dayOfWeek = getResources().getStringArray(R.array.weekend)[nextDay % 7];
                String tem = (maxDiff > 0 || minDiff > 0) ? getResources().getString(R.string.warmer)
                        : getResources().getString(R.string.colder);
                if (isChinese) {
                    LogUtils.i("Chinese");
                    titleStr = dayOfWeek + "将" + tem + ' ' + Math.max(a, b) + "° ";
                } else {
                    LogUtils.i("no Chinese");
                    titleStr = Math.max(a, b) + "° " + tem + " than " + dayOfWeek;
                }
            } else {
                if (isChinese) {
                    LogUtils.i("Chinese");
                    titleStr = "明天温差变化不大哦~";
                } else {
                    LogUtils.i("no Chinese");
                    titleStr = "The temperature doesn't change much tomorrow~";
                }
            }
            sendNotification(titleStr,
                    todayMin + "°/" + todayMax + "°  ···>  " + tomMin + "°/" + tomMax + "° ");
        } else {
            LogUtils.d("calTemDiff: diff: " + maxDiff * minDiff);
            sendNotification("Temperature", todayMin + "° - " +
                    todayMax + "° -> " + tomMin + "° - " + tomMax + "° ");
        }
    }

    @Override
    public void onDestroy() {
        LogUtils.d(TAG, "onDestroy: SyncService destroyed");
        super.onDestroy();
    }
}
