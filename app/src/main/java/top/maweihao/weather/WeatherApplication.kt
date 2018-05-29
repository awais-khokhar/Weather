package top.maweihao.weather


import android.text.TextUtils
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.tencent.bugly.crashreport.CrashReport
import org.litepal.LitePalApplication
import top.maweihao.weather.util.Constants
import top.maweihao.weather.util.Utility
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * Created by limuyang on 2017/12/13.
 */

class WeatherApplication : LitePalApplication() {
    override fun onCreate() {
        super.onCreate()

        val pn = getProcessName(android.os.Process.myPid())
        if (pn == null || pn == packageName) {
            initBugly()
            initAdmob()
            initTimeAndLang()
            initUtils()
        }
    }

    private fun initUtils() {
        Utils.init(this)
        LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG)
    }

    private fun initTimeAndLang() {
        Constants.timeShift = Utility.getTimeShift()
        Constants.lang = Utility.getCurrentLanguage(this)
        //        Log.d("Application", "time shift and lang is " + Constants.timeShift + Constants.lang);
    }

    private fun initBugly() {
        CrashReport.initCrashReport(applicationContext, "2af8412ed0", BuildConfig.APP_DEBUG, getStrategy())
    }

    private fun initAdmob() {
        //        MobileAds.initialize(this, Constants.AD_MOB_ID);
    }

    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                if (reader != null) {
                    reader.close()
                }
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }

    private fun getStrategy(): CrashReport.UserStrategy {
        // TODO: 2018/5/27
        return CrashReport.UserStrategy(applicationContext)
    }
}
