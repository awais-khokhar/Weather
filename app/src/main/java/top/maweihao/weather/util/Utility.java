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

import top.maweihao.weather.WeatherData;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;


/**
 * Created by ma on 17-3-5.
 */

public class Utility {

    public static final String TAG = "Utility";

    public static WeatherData handleWeatherResponse(String url) {
        WeatherData wd = null;
        try {
            JSONObject allAttributes = new JSONObject(url);
            String result = allAttributes.getString("result");
            Gson gson = new Gson();
            wd = gson.fromJson(result, WeatherData.class);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "handleWeatherResponse: parse weather json error");
        }
        return wd;
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

    public static Activity getCurrentActivity () {
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
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}

