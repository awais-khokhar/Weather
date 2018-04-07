package top.maweihao.weather;


import android.util.Log;

import org.litepal.LitePalApplication;

import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.LogUtils;
import top.maweihao.weather.util.Utility;

/**
 * Created by limuyang on 2017/12/13.
 */

public class WeatherApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Constants.timeShift = Utility.getTimeShift();
        Constants.lang = Utility.getCurrentLanguage(this);
        Log.d("HERE", "onCreate: " + Constants.timeShift + Constants.lang);
        LogUtils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG);
    }
}
