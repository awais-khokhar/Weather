package top.maweihao.weather.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

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
import top.maweihao.weather.util.CaiyunAnalyst;
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
    private static final long INTERVAL_WIDGET_NORMAL = 1000 * 60 * 60 * 4;  // 有桌面插件，4小时一次
    private static final long INTERVAL_WIDGET_PRECISE = 1000 * 60 * 60 * 3;  // 桌面插件有BigWidget, 3小时

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
        writeLog();

        long now = System.currentTimeMillis();
        long interval = now - configContact.getLastScheduleTime(0);
        configContact.applyLastJobScheduleTime(now);
        if (interval <= 1000) {
            return false;
        }
        location = mRepository.getLocationCached();
        if (location == null) {
            Log.e(TAG, "HERE onStartJob: NO CACHED LOCATION!");
            return false;
        }
        //距离上次不足5分钟时，只使用本地缓存刷新插件
        if (interval <= 5 * 60 * 1000) {
            updateWidget(null, location);
            return false;
        }
        // 夜里无需检查天气
        if (isSyncTime(now)) {
            if (!hasWidget) {  // 只有通知的情况
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

    private void writeLog() {
        File file = new File(getApplicationContext().getFilesDir().getAbsolutePath()
                + File.separator + "weather_job_scheduler.log");
        FileWriter writer = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Date date = new Date();
            String s = "JobScheduler startJob: " + date.toString() + '\n';
            writer = new FileWriter(file, true);
            writer.write(s);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Utility.closeIO(writer);
        }
    }

    /**
     * 刷新天气
     * @param location
     * @param parameters
     * @return true: 任务未完成  false: 任务已完成
     */
    private boolean refreshData(final MLocation location, final JobParameters parameters) {
        Disposable disposable = mRepository.getLocalWeatherAllowCached()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<NewWeather>() {
                    @Override
                    public void accept(NewWeather weather) throws Exception {
                        if (weather.getStatus().equals("ok")) {
                            afterGettingData(weather, location);
                        }
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
            analysisWeather(getApplicationContext(), weather);
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

    public static int scheduleSyncService(Context context, boolean forceSyncWidget, boolean configChanged) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        if (!configChanged && !forceSyncWidget && scheduler.getAllPendingJobs().size() > 0) {
            Log.d(TAG, "scheduleSyncService: No Need To Run Again");
            return 10;
        }
        JobInfo.Builder builder = new JobInfo.Builder(Constants.SYNC_SERVICE_CODE,
                new ComponentName(context.getPackageName(), SyncService.class.getName()));
        long interval = getSuggestedInterval(context);
//        long interval = INTERVAL_DEBUG;
        if (interval == 0) {
            Log.d(TAG, "No need to run in background");
            scheduler.cancel(Constants.SYNC_SERVICE_CODE);
            return -1;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setPeriodic(interval, 1000 * 60 * 30);
        } else {
            builder.setPeriodic(interval);
        }
        builder.setPersisted(true);
        builder.setRequiresDeviceIdle(false);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        return scheduler.schedule(builder.build());
    }

    public static void stopSyncService(Context context) {
        if (WidgetUtils.hasAnyWidget(context)) {
            return;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.getBoolean(PreferenceConfigContact.NOTIFICATION, false)
                || preferences.getBoolean(PreferenceConfigContact.NOTIFICATION_ALARM, false)) {
            return;
        }
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        assert scheduler != null;
        scheduler.cancel(Constants.SYNC_SERVICE_CODE);
    }

    private static long getSuggestedInterval(Context context) {
        long interval = 0;
        if (WidgetUtils.hasBigWidget(context)) {
            interval = INTERVAL_WIDGET_PRECISE;
        }
        if (WidgetUtils.hasAnyWidget(context)) {
            interval = INTERVAL_WIDGET_NORMAL;
        }
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (preferences.getBoolean(PreferenceConfigContact.NOTIFICATION, false)) {
            interval = INTERVAL_PUSH;
        }
        if (preferences.getBoolean(PreferenceConfigContact.NOTIFICATION_ALARM, false)) {
            interval = INTERVAL_ALERT;
        }
        Log.d(TAG, "getSuggestedInterval: " + interval);
        return interval;
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
            }
            if (shouldSend) {
                alerts.addAll(sent);
                NotificationUtil.sendAlarmNotification(context, alerts);
            }
        }
        if (hasPushedToday) {
            return;
        }
        hasPushedToday = true;
        configContact.applyLastPushTime(System.currentTimeMillis());
        String tomorrowWeek = CaiyunAnalyst.getWeekTomorrow();
        NewWeather.ResultBean.DailyBean daily = weather.getResult().getDaily();
        int tomMax = Utility.intRoundDouble(daily.getTemperature().get(1).getMax());
        int tomMin = Utility.intRoundDouble(daily.getTemperature().get(1).getMin());

        String skycon = Utility.chooseWeatherSkycon(context, daily.getSkycon().get(1).getValue(),
                daily.getPrecipitation().get(1).getAvg(), WeatherActivity.HOURLY_MODE);

        double windSpeed = daily.getWind().get(1).getMax().getSpeed();
        int aqi = (int) daily.getAqi().get(1).getAvg();
        String windLevel = Utility.getWindLevel(this, windSpeed);
        int diff = calTemperatureDiff(weather);
        boolean hasRainOrSnow = WeatherUtil.hasRainOrSnow(context.getResources(), skycon);
        String desc = getWeatherDesc(context, location.getCoarseLocation(), tomMin, tomMax,
                skycon, windSpeed > 49.68 ? windLevel : null, aqi, hasRainOrSnow);  //6级以上的风
        String title;
        if (Math.abs(diff) >= 4) {
            title = getTemperatureDifferenceString(context, diff);
        } else if (hasRainOrSnow) {
            title = context.getResources().getString(R.string.dont_forget_your_raincoat_on, tomorrowWeek);
        } else if (windSpeed >= 61.56) {  //7级以上的风
            title = context.getResources().getString(R.string.big_wind_on, tomorrowWeek);
        } else {
            title = null;
        }
        NotificationUtil.sendWeatherNotification(context, title, desc);
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
            //否则按均温计算，相差超过5度才记录
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
    private String getTemperatureDifferenceString(Context context, int diff) {
        SimpleDateFormat format = new SimpleDateFormat("EEEE", Locale.getDefault());
        String dayOfWeek = format.format(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000));
        int res = diff > 0 ? R.string.notification_warmer : R.string.notification_colder;
        return getResources().getString(res, diff, dayOfWeek);
    }

}
