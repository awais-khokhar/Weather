package top.maweihao.weather.util;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;

import github.hellocsl.simpleconfig.Config;
import github.hellocsl.simpleconfig.SimpleConfig;
import top.maweihao.weather.R;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;
import top.maweihao.weather.view.dynamicweather.BaseDrawer;

import static top.maweihao.weather.activity.WeatherActivity.HOURLY_MODE;
import static top.maweihao.weather.activity.WeatherActivity.MINUTELY_MODE;
import static top.maweihao.weather.util.Constants.DEBUG;


/**
 * Created by ma on 17-3-5.
 * this is a unity class
 */

public class Utility {

    private static final String TAG = "Utility";

    /**
     * 创建自定义的配置文件读取方法
     * @param context Context
     * @return SimpleConfig
     */
    public static SimpleConfig createSimpleConfig(Context context)
    {
        return new SimpleConfig.Builder(context).configFactory(new Config.Factory() {
            @Override
            public Config newConfig(Context context, String name, int mode) {
                return new PreferenceConfig(context);
            }
        }).build();
    }


    /**
     * choosePositionActivity 里用的方法，应该不用改
     */
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {

                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                    Log.v(TAG, "saved province: " + provinceObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "handleProvinceResponse: JSONObeject error");
            }
        }
        return false;
    }

    /**
     * choosePositionActivity 里用的方法，应该不用改
     */
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                    Log.v(TAG, "saved city: " + cityObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "handleCityResponse: JSONObject error");
            }
        }
        return false;
    }

    /**
     * choosePositionActivity 里用的方法，应该不用改
     */
    public static boolean handleCountyResponse(String response, int cityId) {
        Log.d(TAG, "handleCountyResponse: ");
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.save();
                    Log.v(TAG, "saved county: " + countyObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e(TAG, "handleCountyResponse: JSONObject error");
            }
        }
        return false;
    }

    /**
     * 把 float  四舍五入再返回 String，用来处理温度的
     */
    public static String stringRoundFloat(float f) {
        return String.valueOf(Math.round(f));
    }


    /**
     * 把  float 四舍五入再返回 int，用来处理温度的
     */
    public static int intRoundFloat(float s) {
        return Math.round(s);
    }

    /**
     * 摄氏度华氏度转换的 还没用
     */
    public static int Cel2Fah(int cel) {
        double fah = cel * 1.8 + 32;
        return (int) Math.round(fah);
    }

    /**
     * 摄氏度华氏度转换的 还没用
     */
    public static int Cel2Fah(float cel) {
        double fah = cel * 1.8 + 32;
        return (int) Math.round(fah);
    }

    /**
     * 通过天气获取背景图
     */
    public static @BaseDrawer.Type int chooseBgImage(String skycon) {
        switch (skycon) {
            case "CLEAR_DAY":
                return BaseDrawer.Type.CLEAR_D;
            case "CLEAR_NIGHT":
                return BaseDrawer.Type.CLEAR_N;
            case "PARTLY_CLOUDY_DAY":
                return BaseDrawer.Type.OVERCAST_D;
            case "PARTLY_CLOUDY_NIGHT":
                return BaseDrawer.Type.OVERCAST_N;
            case "CLOUDY":
                return BaseDrawer.Type.CLOUDY_D;
            case "RAIN":
                return BaseDrawer.Type.RAIN_D;
            case "SNOW":
                return BaseDrawer.Type.SNOW_D;
            case "WIND":
                return BaseDrawer.Type.WIND_D;
            case "FOG":
                return BaseDrawer.Type.FOG_D;
            default:
                return BaseDrawer.Type.UNKNOWN_D;
        }
    }

    /**
     * 获得天气 String
     * @param context  context
     * @param skycon        天气标识
     * @param precipitation 雨量
     * @param mode          Mode 代表 precipitation 的精度，分为小时级和分钟级， 以确定雨量
     * @return 描述（string）
     */
    public static String chooseWeatherSkycon(Context context, String skycon, float precipitation, int mode) {
        if (context != null) {
            switch (skycon) {
                case "CLEAR_DAY":
                    return context.getResources().getString(R.string.CLEAR_DAY);
                case "CLEAR_NIGHT":
                    return context.getResources().getString(R.string.CLEAR_NIGHT);
                case "PARTLY_CLOUDY_DAY":
                    return context.getResources().getString(R.string.PARTLY_CLOUDY_DAY);
                case "PARTLY_CLOUDY_NIGHT":
                    return context.getResources().getString(R.string.PARTLY_CLOUDY_NIGHT);
                case "CLOUDY":
                    return context.getResources().getString(R.string.CLOUDY);
                case "RAIN":
                    switch (mode) {
                        case MINUTELY_MODE:
                            if (precipitation <= 0.25)
                                return context.getResources().getString(R.string.LIGHT_RAIN);
                            else if (precipitation <= 0.35)
                                return context.getResources().getString(R.string.MODERATE_RAIN);
                            else
                                return context.getResources().getString(R.string.HEAVY_RAIN);
                        case HOURLY_MODE:
                            if (precipitation <= 0.9)
                                return context.getResources().getString(R.string.LIGHT_RAIN);
                            else if (precipitation <= 2.87)
                                return context.getResources().getString(R.string.MODERATE_RAIN);
                            else
                                return context.getResources().getString(R.string.HEAVY_RAIN);
                    }
                case "SNOW":
                    return context.getResources().getString(R.string.SNOW);
                case "WIND":
                    return context.getResources().getString(R.string.WIND);
                case "FOG":
                    return context.getResources().getString(R.string.FOG);
                default:
                    return null;
            }
        } else {
            return null;
        }
    }

    /**
     * 获得天气图标
     * @param skycon    天气标识
     * @param precipitation   雨量
     * @param mode   Mode 代表 precipitation 的精度，分为小时级和分钟级， 以确定雨量
     * @param simpleIcon 是否返回简单黑白风格的 icon
     * @return 天气图片 id
     */
    public static int chooseWeatherIcon(String skycon, float precipitation, int mode, boolean simpleIcon) {
        switch (skycon) {
            case "CLEAR_DAY":
                return simpleIcon ? R.mipmap.simple_clear_day_gray : R.mipmap.weather_clear;
            case "CLEAR_NIGHT":
                return simpleIcon ? R.mipmap.simple_clear_night_gray : R.mipmap.weather_clear_night;
            case "PARTLY_CLOUDY_DAY":
                return simpleIcon ? R.mipmap.simple_cloudy_day_gray : R.mipmap.weather_few_clouds;
            case "PARTLY_CLOUDY_NIGHT":
                return simpleIcon ? R.mipmap.simple_cloudy_night_gray : R.mipmap.weather_few_clouds_night;
            case "CLOUDY":
                return simpleIcon ? R.mipmap.simple_cloudy_2_gray : R.mipmap.weather_clouds;
            case "RAIN":
                switch (mode) {
                    case MINUTELY_MODE:
                        if (precipitation <= 0.25)
                            return simpleIcon ? R.mipmap.simple_light_rain_gray : R.mipmap.weather_drizzle_day;
                        else if (precipitation <= 0.35)
                            return simpleIcon ? R.mipmap.simple_medium_rain_gray : R.mipmap.weather_rain_day;
                        else
                            return simpleIcon ? R.mipmap.simple_heavy_rain_gray : R.mipmap.weather_showers_day;
                    case HOURLY_MODE:
                        if (precipitation <= 0.9)
                            return simpleIcon ? R.mipmap.simple_light_rain_gray : R.mipmap.weather_drizzle_day;
                        else if (precipitation <= 2.87)
                            return simpleIcon ? R.mipmap.simple_medium_rain_gray : R.mipmap.weather_rain_day;
                        else
                            return simpleIcon ? R.mipmap.simple_heavy_rain_gray : R.mipmap.weather_showers_day;
                }
            case "SNOW":
                return simpleIcon ? R.mipmap.simple_snow_gray : R.mipmap.weather_snow;
            case "WIND":
                return simpleIcon ? R.mipmap.simple_wind_gray : R.mipmap.weather_wind;
            case "FOG":
                return simpleIcon ? R.mipmap.simple_wind_gray : R.mipmap.weather_fog;
            default:
                return -1;
        }
    }

    public static boolean isChinese(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return context.getResources().getConfiguration().getLocales().get(0).getDisplayLanguage().equals("中文");
        } else {
            return context.getResources().getConfiguration().locale.getDisplayLanguage().equals("zh-CN");
        }
    }

    /**
     * 判断时间和现在的时间相差
     *
     * @param mills 时间
     */
    public static String getTime(Context context, long mills) {
        long nowTime = System.currentTimeMillis();
        long interval = nowTime - mills;
        if (interval < 60 * 1000) {
            return context.getResources().getString(R.string.just_now);
        } else if (interval < 60 * 60 * 1000) {
            return (interval / 1000 / 60) + context.getResources().getString(R.string.minute_age);
        } else {
            return (interval / 1000 / 60 / 60) + context.getResources().getString(R.string.hour_ago);
        }
    }

    /**
     * 直接返回 now
     */
    public static String getTime(Context context) {
        return context.getResources().getString(R.string.just_now);
    }

    /**
     * getIP 获取网络IP地址(优先获取wifi地址)
     *
     * @param ctx
     * @return ip地址字符串
     */
    public static String getIP(Context ctx) {
        WifiManager wifiManager = (WifiManager) ctx.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        return wifiManager.isWifiEnabled() ? getWifiIP(wifiManager) : getGPRSIP();
    }

    private static String getWifiIP(WifiManager wifiManager) {
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
        String ip = intToIp(wifiInfo.getIpAddress());
        return ip != null ? ip : "";
    }

    private static String getGPRSIP() {
        String ip = null;
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                for (Enumeration<InetAddress> enumIpAddr = en.nextElement().getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        ip = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
            ip = null;
        }
        return ip;
    }

    private static String intToIp(int i) {
        return (i & 0xFF) + "." + ((i >> 8) & 0xFF) + "." + ((i >> 16) & 0xFF) + "." + (i >> 24 & 0xFF);
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return 高度值
     */
    public static int getStatusBarHeight(@NonNull Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            if (DEBUG)
                Log.d(TAG, "get status bar height fail");
            e1.printStackTrace();
            return 75;
        }
    }

    /**
     * 获取导航栏高度
     * @param context
     * @return 高度值
     */
    public static int getNavigationBarHeight(@Nullable Context context) {
        int result = 0;
        int resourceId=0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid!=0){
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        }else
            return 0;
    }

    /**
     * 关闭软键盘
     * @param context
     */
    public static void closeSoftInput(@Nullable Context context) {
        assert context != null;
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null && ((Activity) context).getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(((Activity) context).getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    public static String parseTime() {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
        return simpleDateFormat.format(date);
    }

    public static String ampm(String time) {
        int hour = Integer.parseInt(time);
        return (hour < 12) ? (hour + "am") : ((hour - 12) + "pm");
    }

    public static boolean GPSEnabled(Context context) {
        LocationManager alm =
                (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return alm.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
}

