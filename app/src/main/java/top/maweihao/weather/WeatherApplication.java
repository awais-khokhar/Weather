package top.maweihao.weather;


import org.litepal.LitePalApplication;

import top.maweihao.weather.util.LogUtils;

/**
 * Created by limuyang on 2017/12/13.
 */

public class WeatherApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        LogUtils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG);
    }
}
