package top.maweihao.weather;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.Utils;

import org.litepal.LitePalApplication;

/**
 * Created by limuyang on 2017/12/13.
 */

public class WeatherApplication extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();

        Utils.init(this);
        LogUtils.getConfig().setLogSwitch(BuildConfig.APP_DEBUG);
    }
}
