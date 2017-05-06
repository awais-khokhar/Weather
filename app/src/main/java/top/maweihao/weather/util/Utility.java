package top.maweihao.weather.util;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import top.maweihao.weather.R;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;


/**
 * Created by ma on 17-3-5.
 * this is a unity class
 */

public class Utility {

    private static final String TAG = "Utility";

    public static WeatherData handleCurrentWeatherResponse(String url) {
        WeatherData wd = null;
        try {
            JSONObject allAttributes = new JSONObject(url);
            String result = allAttributes.getString("result");
            JSONObject resultJSON = allAttributes.getJSONObject("result");
            JSONObject precipitationJSON = resultJSON.getJSONObject("precipitation");
            JSONObject localJSON = precipitationJSON.getJSONObject("local");
            String intensity = localJSON.getString("intensity");
            Gson gson = new Gson();
            wd = gson.fromJson(result, WeatherData.class);
            wd.setIntensity(intensity);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "handleCurrentWeatherResponse: parse weather json error");
        }
        return wd;
    }

    public static JSONArray[] handleDailyWeatherResponse(String url) {
        JSONArray[] jsonArrays = new JSONArray[5];
        try {
            JSONObject all = new JSONObject(url);
            JSONObject result = all.getJSONObject("result");
            JSONObject daily = result.getJSONObject("daily");
            JSONArray skycon = daily.getJSONArray("skycon");
            JSONArray humidity = daily.getJSONArray("humidity");
            JSONArray temperature = daily.getJSONArray("temperature");
            JSONArray precipitation = daily.getJSONArray("precipitation");
            JSONArray astro = daily.getJSONArray("astro");
            jsonArrays[0] = skycon;
            jsonArrays[1] = humidity;
            jsonArrays[2] = temperature;
            jsonArrays[3] = precipitation;
            jsonArrays[4] = astro;
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "handleFewDaysWeatherResponse: parse weather json error");
        }
        return jsonArrays;
    }

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

    static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    return (Activity) activityField.get(activityRecord);
                }
            }
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String roundString(String s) {
        return String.valueOf(Math.round(Float.parseFloat(s)));
    }

    public static int Cel2Fah(int cel) {
        double fah = cel * 1.8 + 32;
        return (int) Math.round(fah);
    }

    public static int Cel2Fah(float cel) {
        double fah = cel * 1.8 + 32;
        return (int) Math.round(fah);
    }

    public static int chooseBgImage(String skycon) {
        switch (skycon) {
            case "CLEAR_DAY":
                return R.mipmap.weatherbg_sunshine;
            case "CLEAR_NIGHT":
                return R.mipmap.weatherbg_sunshine_night;
            case "PARTLY_CLOUDY_DAY":
                return R.mipmap.weatherbg_pcloud;
            case "PARTLY_CLOUDY_NIGHT":
                return R.mipmap.weatherbg_pcloud_night;
            case "CLOUDY":
                return R.mipmap.weatherbg_cloud;
            case "RAIN":
                return R.mipmap.weatherbg_rain;
            case "SNOW":
                return R.mipmap.weatherbg_snow;
            case "WIND":
                return R.mipmap.weatherbg_wind;
            case "FOG":
                return R.mipmap.weatherbg_haze;
            default:
                return 0;
        }
    }
}

