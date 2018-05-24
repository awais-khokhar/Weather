package top.maweihao.weather.service;

import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;

import top.maweihao.weather.util.Constants;

public class SyncService extends JobService {

    private static final String TAG = SyncService.class.getSimpleName();

    @Override
    public boolean onStartJob(JobParameters params) {

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }

    public static int scheduleSyncService(Context context) {
        JobScheduler scheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(Constants.SYNC_SERVICE_CODE,
                new ComponentName(context.getPackageName(), SyncService.class.getName()));
        builder.setPeriodic(1000 * 60 * 60 * 2);  //4 hour
        builder.setPersisted(true);
//        builder.setRequiresCharging(true);
        builder.setRequiresDeviceIdle(false);
        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        return scheduler.schedule(builder.build());
    }
}
