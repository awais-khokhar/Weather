package top.maweihao.weather;


import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import android.util.Log;

import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePalApplication;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.LogUtils;
import top.maweihao.weather.util.Utility;

/**
 * Created by limuyang on 2017/12/13.
 */

public class WeatherApplication extends LitePalApplication {

    private static final String TAG = WeatherApplication.class.getSimpleName();
    public static boolean isDebug;

    @Override
    public void onCreate() {
        super.onCreate();
        isDebug = (getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;

        String pn = getProcessName(android.os.Process.myPid());
        if (pn == null || pn.equals(getPackageName())) {
            //tencent bugly
            CrashReport.initCrashReport(getApplicationContext(), "2af8412ed0", isDebug, getStrategy());

            Constants.timeShift = Utility.getTimeShift();
            Constants.lang = Utility.getCurrentLanguage(this);
            Log.d("Application", "time shift and lang is " + Constants.timeShift + Constants.lang);
            LogUtils.init(this);
            LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG);
        }
    }

    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

    private CrashReport.UserStrategy getStrategy() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());
        // TODO: 2018/5/27  
        return strategy;
    }
}
