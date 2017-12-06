package top.maweihao.weather.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;
import static top.maweihao.weather.util.Constants.CollapsingToolbarLayoutState.COLLAPSED;
import static top.maweihao.weather.util.Constants.CollapsingToolbarLayoutState.EXPANDED;
import static top.maweihao.weather.util.Constants.CollapsingToolbarLayoutState.INTERNEDIATE;

/**
 * 放置常量
 * Created by limuyang on 2017/6/11.
 */

public class Constants {
    public static final boolean DEBUG = true;
    public static final String STATUS_OK = "ok";

    public static final long SYSTEM_NOW_TIME = 0;

    public static final int ChoosePositionActivityRequestCode = 367;
    public static final int SettingActivityRequestCode = 864;
    public static final int SettingCode = 320;
    public static final int ChooseCode = 239;
    public static final int WidgetSyncServiceRequestCode = 66;
    public static boolean isSetResultIntent = false;

//    public static final int requestLocationCode = 3;
    public static final int newRequestLocationCode = 4;

    public static final String HeWeatherKey = "5dfd7d649560440ca704c077a21af092";

    public static final String BaiduKey = "RUUaavVwpwqYdz8QGHLXSFBfwhs2ba6j";

    public static final String mBaiduCode =
            "B6:F3:EA:31:1E:7F:BB:C5:D2:6C:5A:A7:CA:D8:4F:DF:79:46:C9:C2;top.maweihao.weather";


    @IntDef({Through.THROUGH_IP, Through.THROUGH_CHOOSE_POSITION,
            Through.THROUGH_LOCATE, Through.THROUGH_COORDINATE})
    @Documented
    @Retention(SOURCE)
    public @interface Through {
        int THROUGH_IP = 0;
        int THROUGH_CHOOSE_POSITION = 1;
        int THROUGH_LOCATE = 2;
        int THROUGH_COORDINATE = 3;
    }

    @IntDef({EXPANDED, COLLAPSED, INTERNEDIATE})
    @Documented
    @Retention(SOURCE)
    public @interface CollapsingToolbarLayoutState {
        int EXPANDED = 0;
        int COLLAPSED = 1;
        int INTERNEDIATE = 2;
    }

}
