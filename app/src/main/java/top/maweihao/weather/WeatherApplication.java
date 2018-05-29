package top.maweihao.weather;


import android.text.TextUtils;

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

    @Override
    public void onCreate() {
        super.onCreate();

        String pn = getProcessName(android.os.Process.myPid());
        if (pn == null || pn.equals(getPackageName())) {
            initBugly();
            initAdmob();
            initTimeAndLang();
            initLog();
        }
    }

    private void initLog() {
        LogUtils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG);
    }

    private void initTimeAndLang() {
        Constants.timeShift = Utility.getTimeShift();
        Constants.lang = Utility.getCurrentLanguage(this);
//        Log.d("Application", "time shift and lang is " + Constants.timeShift + Constants.lang);
    }

    private void initBugly() {
        CrashReport.initCrashReport(getApplicationContext(), "2af8412ed0", BuildConfig.APP_DEBUG, getStrategy());
    }

    private void initAdmob() {
//        MobileAds.initialize(this, Constants.AD_MOB_ID);
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
