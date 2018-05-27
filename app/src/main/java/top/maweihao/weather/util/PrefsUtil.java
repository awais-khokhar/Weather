package top.maweihao.weather.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

public class PrefsUtil {

    private static final String PREF_ALERT_NAME = "alert_history";

    public static boolean isAlertPushed(Context context, String id) {
        if (TextUtils.isEmpty(id)) {
            return false;
        }
        SharedPreferences sp = context.getSharedPreferences(PREF_ALERT_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(id, false);
    }

    public static void putPushedAlert(Context context, String id) {
        if (!TextUtils.isEmpty(id)) {
            SharedPreferences sp = context.getSharedPreferences(PREF_ALERT_NAME, Context.MODE_PRIVATE);
            sp.edit().putBoolean(id, true).apply();
        }
    }
}
