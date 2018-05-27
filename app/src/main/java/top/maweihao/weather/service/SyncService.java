package top.maweihao.weather.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.maweihao.weather.R;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.entity.Alert;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.NotificationUtil;
import top.maweihao.weather.util.PrefsUtil;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.util.WeatherUtil;
import top.maweihao.weather.util.remoteView.WidgetUtils;
import top.maweihao.weather.view.WeatherActivity;

public class SyncService extends JobService {

    private static final String TAG = SyncService.class.getSimpleName();
    private WeatherRepository mRepository;

    public static boolean working = false;
    private boolean hasWidget;
    private boolean isBigWidgetOn;
    private PreferenceConfigContact configContact;
    private boolean hasPushedToday;
    private CompositeDisposable mDisposable;
    private boolean isNotificationOn;
    private boolean isAlertNotificationOn;
    private MLocation location;

    private static final long INTERVAL_DEBUG = 1000 * 60 * 60; // 测试用，每隔1小时刷新
    private static final long INTERVAL_ALERT = 1000 * 60 * 60 * 6; // 只检查预警，6小时一次即可
    private static final long INTERVAL_PUSH = 1000 * 60 * 60 * 4;  //开启了天气通知，4小时一次
    private static final long INTERVAL_WIDGET_NORMAL = 1000 * 60 * 60 * 3;  // 有桌面插件，3小时一次
    private static final long INTERVAL_WIDGET_PRECISE = 1000 * 60 * 60 * 2;  // 桌面插件有BigWidget, 2小时

    // 通知类别
//    private static final int TYPE_WEATHER = 0;
//    private static final int TYPE_ALERT = 1;

    @Override
    public void onCreate() {
        super.onCreate();
        configContact = Utility.createSimpleConfig(getApplicationContext()).create(PreferenceConfigContact.class);
        hasWidget = WidgetUtils.hasAnyWidget(this);
        mRepository = WeatherRepository.getInstance(this);
        isBigWidgetOn = WidgetUtils.hasBigWidget(this);
        long pushTime = configContact.getLastPushTime(0);
        hasPushedToday = pushTime != 0 && Utility.isToday(pushTime);
        mDisposable = new CompositeDisposable();
        isNotificationOn = configContact.getNotification(false);
        isAlertNotificationOn = configContact.getAlarmNotification(false);
    }

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "onStartJob: ");
        long now = System.currentTimeMillis();
        long interval = now - configContact.getLastScheduleTime(0);
        if (interval <= 1000 * 3) {
            return false;
        }
        //距离上次不足5分钟时，只使用本地缓存刷新插件
        if (interval <= 5 * 60 * 1000) {
            updateWidget(null, null);
            return false;
        }
        configContact.applyLastJobScheduleTime(now);
        location = mRepository.getLocationCached();
        if (location == null) {
            Log.e(TAG, "onStartJob: NO CACHED LOCATION!");
            return false;
        }
        // 夜里无需检查天气
        if (isSyncTime(now)) {
            // 只有通知的情况
            if (!hasWidget) {
                if (isAlertNotificationOn) {
                    return refreshData(location, params);
                } else if (!hasPushedToday && isNotificationOn && isPushTime(now)){
                    //只开启了天气通知，只需要在指定时间检查
                    return refreshData(location, params);
                }
            } else {
                return refreshData(location, params);
            }
        }
        return false;
    }

    /**
     * 刷新天气
     * @param location
     * @param parameters
     * @return true: 任务未完成  false: 任务已完成
     */
    private boolean refreshData(final MLocation location, final JobParameters parameters) {
        long now = System.currentTimeMillis();
        long lastCacheTime = configContact.getLastSyncTime(0);
        if (now - lastCacheTime <= 5 * 60 * 1000) {
            afterGettingData(mRepository.getWeatherCachedSync(), location);
            return false;
        }
        Disposable disposable = mRepository.getWeather(location.getLocationStringReversed())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        afterGettingData(weather, location);
                        jobFinished(parameters, false);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        jobFinished(parameters, false);
                    }
                });
        mDisposable.add(disposable);
        return true;
    }

    private void afterGettingData(NewWeather weather, MLocation location) {
        updateWidget(weather, location);
        if (isNotificationOn && isPushTime(System.currentTimeMillis())) {

        }
    }

    private void updateWidget(NewWeather weather, MLocation location) {
        if (!hasWidget) {
            return;
        }
        if (weather == null && isBigWidgetOn) {
            //只刷新桌面插件显示的上次更新时间
            WidgetUtils.refreshBigWidgetTime(this);
            return;
        }
        WidgetUtils.refreshWidget(this, weather, location.getCoarseLocation());
    }

    // 在指定时间就可以发送通知
    private boolean isPushTime(long timeInMills) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeInMills);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 16 && hour <= 22;
    }

    private boolean isSyncTime(long timeInMills) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(timeInMills);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour >= 5;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    @Override
    public void onDestroy() {
        mDisposable.dispose();
        super.onDestroy();
    }

    public static int scheduleSyncService(Context context, boolean forceSyncWidget) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        JobInfo.Builder builder = new JobInfo.Builder(Constants.SYNC_SERVICE_CODE,
                new ComponentName(context.getPackageName(), SyncService.class.getName()));
//        long interval = getSuggestedInterval(context);
        long interval = INTERVAL_DEBUG;
        if (interval == 0) {
            Log.d(TAG, "No need to run in background");
            scheduler.cancel(Constants.SYNC_SERVICE_CODE);
            return -1;
        }
        builder.setPeriodic(interval);  //1 hour
        builder.setPersisted(true);
        builder.setRequiresDeviceIdle(false);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        return scheduler.schedule(builder.build());
    }

    private static long getSuggestedInterval(Context context) {
        if (WidgetUtils.hasBigWidget(context)) {
            return INTERVAL_WIDGET_PRECISE;
        }
        if (WidgetUtils.hasAnyWidget(context)) {
            return INTERVAL_WIDGET_NORMAL;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.getBoolean(PreferenceConfigContact.NOTIFICATION, false)) {
            return INTERVAL_PUSH;
        }
        if (preferences.getBoolean(PreferenceConfigContact.NOTIFICATION_ALARM, false)) {
            return INTERVAL_ALERT;
        }
        return 0;
    }

    private void analysisWeather(Context context, NewWeather weather) {
        // 发送预警
        ArrayList<Alert> alerts = WeatherUtil.getAlertList(weather);
        if (alerts != null && alerts.size() > 0) {
            boolean shouldSend = false;
            ArrayList<Alert> sent = new ArrayList<>();
            for (Alert alert : alerts) {
                String id = alert.alertId;
                if (!PrefsUtil.isAlertPushed(context, id)) {
                    shouldSend = true;
                    PrefsUtil.putPushedAlert(context, id);
                } else {
                    alerts.remove(alert);
                    sent.add(alert);
                }
                if (shouldSend) {
                    alerts.addAll(sent);
                    NotificationUtil.sendAlarmNotification(context, alerts);
                }
            }
        }
        // 通知优先级：中雨大雨, 中雪大雪 > 温差 >
        NewWeather.ResultBean.DailyBean tomorrow = weather.getResult().getDaily();
        int tomMax = Utility.intRoundDouble(tomorrow.getTemperature().get(1).getMax());
        int tomMin = Utility.intRoundDouble(tomorrow.getTemperature().get(1).getMin());
        String skycon = Utility.chooseWeatherSkycon(context, tomorrow.getSkycon().get(1).getValue(),
                tomorrow.getPrecipitation().get(1).getAvg(), WeatherActivity.HOURLY_MODE);

        int diff = calTemperatureDiff(weather);
        if (Math.abs(diff) >= 3) {
            sendTemperatureDifference(context, diff);
        }
    }

    private int calTemperatureDiff(NewWeather weather) {
        NewWeather.ResultBean.DailyBean.TemperatureBeanX today =
                weather.getResult().getDaily().getTemperature().get(0);
        NewWeather.ResultBean.DailyBean.TemperatureBeanX tomorrow =
                weather.getResult().getDaily().getTemperature().get(1);
        int todayMax = Utility.intRoundDouble(today.getMax());
        int todayMin = Utility.intRoundDouble(today.getMin());
        int tomMax = Utility.intRoundDouble(tomorrow.getMax());
        int tomMin = Utility.intRoundDouble(tomorrow.getMin());
        int maxDiff = tomMax - todayMax;  //最高温的温差
        int minDiff = tomMin - todayMin;  //最低温的温差
        if (maxDiff * minDiff >= 0) {  // 最高温和最低温都同时变高/低，直接选变化大的那个数值
            return Math.abs(maxDiff) >= Math.abs(minDiff) ? maxDiff : minDiff;
        } else {
            int todayAve = Utility.intRoundDouble(today.getAvg());
            int tomAve = Utility.intRoundDouble(tomorrow.getAvg());
            int diff = tomAve - todayAve;
            return Math.abs(diff) >= 5 ? diff : 0;
        }
    }

    private String getWeatherDesc(Context context, String location, int minTem, int maxTem, String skycon,
                                  @Nullable String wind, int aqi, boolean rain) {
        boolean isChinese = Utility.isChinese(context);
        Resources res = context.getResources();
        String comma = res.getString(R.string.comma);
        String period = res.getString(R.string.period);
        StringBuilder sb = new StringBuilder();
        String desc = res.getString(R.string.weather_desc, skycon, location, maxTem, minTem);
        sb.append(desc);
        if (isChinese && wind != null) {
            sb.append(comma).append("有").append(wind);
        }
        if (aqi >= 150) {
            sb.append(comma).append(res.getString(R.string.air_not_good));
        }
        if (rain) {
            sb.append(period).append(res.getString(R.string.dont_forget_your_raincoat));
        }
        return sb.append(period).toString();
    }

    // 示例："5° warmer in Wednesday"  "周三将升温 5°"
    private void sendTemperatureDifference(Context context, int diff) {
        Calendar calendar = new GregorianCalendar();
        int nextDay = calendar.get(Calendar.DAY_OF_WEEK) + 1;
        String dayOfWeek = getResources().getStringArray(R.array.weekend)[nextDay % 7];
        int res = diff > 0 ? R.string.notification_warmer : R.string.notification_colder;
        String title = getResources().getString(res, diff, dayOfWeek);
        NotificationUtil.sendWeatherNotification(context, title, null);
    }

    private void sendWeatherChangeNotification(Context context, int code) {

    }

}
