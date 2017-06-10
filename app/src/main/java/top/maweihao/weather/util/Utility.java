package top.maweihao.weather.util;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
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
     * 解析当前天气json
     *
     * @param url json
     * @return WeatherData 类
     */
//    public static RealTimeBean handleCurrentWeatherResponse(String url) {
////        WeatherData wd = null;
////        try {
////            JSONObject allAttributes = new JSONObject(url);
////            String result = allAttributes.getString("result");
////            JSONObject resultJSON = allAttributes.getJSONObject("result");
////            JSONObject precipitationJSON = resultJSON.getJSONObject("precipitation");
////            JSONObject localJSON = precipitationJSON.getJSONObject("local");
////            Gson gson = new Gson();
////            wd = gson.fromJson(result, WeatherData.class);
////            String intensity = localJSON.getString("intensity");
////            wd.setIntensity(intensity);
////        } catch (JSONException e) {
////            e.printStackTrace();
////            Log.e(TAG, "handleCurrentWeatherResponse: parse weather json error");
////        }
//        return JSON.parseObject(url,RealTimeBean.class);
//    }

//    /**
//     * 大致解析json
//     *
//     * @return <JSONArray> list
//     */
//    public static ArrayList<JSONArray> handleFullWeatherResponse(String url) {
//        ArrayList<JSONArray> jsonArrays = new ArrayList<>();
//        try {
//            JSONObject all = new JSONObject(url);
//            JSONObject result = all.getJSONObject("result");
//            JSONObject daily = result.getJSONObject("daily");
//            JSONObject hourly = result.getJSONObject("hourly");
//            JSONArray skycon = daily.getJSONArray("skycon");
//            JSONArray humidity = daily.getJSONArray("humidity");
//            JSONArray temperature = daily.getJSONArray("temperature");
//            JSONArray precipitation = daily.getJSONArray("precipitation");
//            JSONArray astro = daily.getJSONArray("astro");
//            JSONArray uv = daily.getJSONArray("ultraviolet");
//            JSONArray dressing = daily.getJSONArray("dressing");
//            JSONArray carWashing = daily.getJSONArray("carWashing");
//
//            JSONArray hourly_skycon = hourly.getJSONArray("skycon");
//            JSONArray hourly_temperature = hourly.getJSONArray("temperature");
//            JSONArray hourly_precipitation = hourly.getJSONArray("precipitation");
//            jsonArrays.add(0, skycon);
//            jsonArrays.add(1, humidity);
//            jsonArrays.add(2, temperature);
//            jsonArrays.add(3, precipitation);
//            jsonArrays.add(4, astro);
//            jsonArrays.add(5, hourly_skycon);
//            jsonArrays.add(6, hourly_temperature);
//            jsonArrays.add(7, hourly_precipitation);
//            jsonArrays.add(8, uv);
//            jsonArrays.add(9, dressing);
//            jsonArrays.add(10, carWashing);
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//            Log.e(TAG, "handleFewDaysWeatherResponse: parse weather json error");
//        }
//        return jsonArrays;
//    }

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
    public static BaseDrawer.Type chooseBgImage(String skycon) {
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
     * 选择天气图标
     *
     * @param skycon        天气
     * @param precipitation 雨量
     * @param mode          Mode 代表 precipitation 精度， 以确定雨量
     * @return 图片（int） + ‘and’ + 描述（string）
     */
    public static String chooseWeatherIcon(String skycon, float precipitation, int mode) {
        switch (skycon) {
            case "CLEAR_DAY":
                return R.mipmap.weather_clear + "and" + "晴";
            case "CLEAR_NIGHT":
                return R.mipmap.weather_clear_night + "and" + "晴";
            case "PARTLY_CLOUDY_DAY":
                return R.mipmap.weather_few_clouds + "and" + "多云";
            case "PARTLY_CLOUDY_NIGHT":
                return R.mipmap.weather_few_clouds_night + "and" + "多云";
            case "CLOUDY":
                return R.mipmap.weather_clouds + "and" + "阴";
            case "RAIN":
                switch (mode) {
                    case MINUTELY_MODE:
                        if (precipitation <= 0.15)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (precipitation <= 0.35)
                            return R.mipmap.weather_rain_day + "and" + "中雨";
                        else
                            return R.mipmap.weather_showers_day + "and" + "大雨";
                    case HOURLY_MODE:
                        if (precipitation <= 10)
                            return R.mipmap.weather_drizzle_day + "and" + "小雨";
                        else if (precipitation <= 25)
                            return R.mipmap.weather_rain_day + "and" + "中雨";
                        else
                            return R.mipmap.weather_showers_day + "and" + "大雨";
                }
            case "SNOW":
                return R.mipmap.weather_snow + "and" + "雪";
            case "WIND":
                return R.mipmap.weather_wind + "and" + "多风";
            case "FOG":
                return R.mipmap.weather_fog + "and" + "雾";
            default:
                return null;
        }
    }

    /**
     * 只返回天气图片
     */
    public static int chooseWeatherIconOnly(String skycon, float precipitation, int mode) {
        String response = chooseWeatherIcon(skycon, precipitation, mode);
        assert response != null;
        String[] responses = response.split("and");
        return Integer.parseInt(responses[0]);
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
    public static int getStatusBarHeight(@Nullable Context context) {
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
}

