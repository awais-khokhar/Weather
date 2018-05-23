package top.maweihao.weather


import android.text.TextUtils
import android.util.Log
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
        Utils.init(this)


        val pn = getProcessName(android.os.Process.myPid())
        if (pn == null || pn == packageName) {
            //tencent bugly
            CrashReport.initCrashReport(applicationContext, "2af8412ed0", true)

            Constants.timeShift = Utility.getTimeShift()
            Constants.lang = Utility.getCurrentLanguage(this)
            Log.d("Application", "time shift and lang is " + Constants.timeShift + Constants.lang)

            LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG)
        }
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
}
