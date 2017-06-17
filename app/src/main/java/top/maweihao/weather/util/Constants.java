package top.maweihao.weather.util;

import android.support.annotation.IntDef;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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

    public static final int ChoosePositionActivityRequestCode =367;
    public static final int SettingActivityRequestCode=864;
    public static final int SettingCode = 320;
    public static final int ChooseCode = 239;
    public static boolean isSetResultIntent=false;


    @IntDef({Through.THROUGH_IP, Through.THROUGH_CHOOSE_POSITION,
            Through.THROUGH_LOCATE, Through.THROUGH_COORDINATE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Through {
        int THROUGH_IP = 0;
        int THROUGH_CHOOSE_POSITION = 1;
        int THROUGH_LOCATE = 2;
        int THROUGH_COORDINATE = 3;
    }

    @IntDef({EXPANDED,COLLAPSED,INTERNEDIATE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface CollapsingToolbarLayoutState {
        int EXPANDED = 0;
        int COLLAPSED = 1;
        int INTERNEDIATE = 2;
    }


}
