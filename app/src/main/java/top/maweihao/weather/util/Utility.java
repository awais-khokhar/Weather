package top.maweihao.weather.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import top.maweihao.weather.R;
import top.maweihao.weather.WeatherData;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;

import static top.maweihao.weather.WeatherActivity.HOURLY_MODE;
import static top.maweihao.weather.WeatherActivity.MINUTELY_MODE;


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
            Gson gson = new Gson();
            wd = gson.fromJson(result, WeatherData.class);
            String intensity = localJSON.getString("intensity");
            wd.setIntensity(intensity);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.e(TAG, "handleCurrentWeatherResponse: parse weather json error");
        }
        return wd;
    }

    public static ArrayList<JSONArray> handleDailyWeatherResponse(String url) {
        ArrayList<JSONArray> jsonArrays = new ArrayList<>();
        try {
            JSONObject all = new JSONObject(url);
            JSONObject result = all.getJSONObject("result");
            JSONObject daily = result.getJSONObject("daily");
            JSONObject hourly = result.getJSONObject("hourly");
            JSONArray skycon = daily.getJSONArray("skycon");
            JSONArray humidity = daily.getJSONArray("humidity");
            JSONArray temperature = daily.getJSONArray("temperature");
            JSONArray precipitation = daily.getJSONArray("precipitation");
            JSONArray astro = daily.getJSONArray("astro");
            JSONArray uv = daily.getJSONArray("ultraviolet");
            JSONArray dressing = daily.getJSONArray("dressing");
            JSONArray carWashing = daily.getJSONArray("carWashing");

            JSONArray hourly_skycon = hourly.getJSONArray("skycon");
            JSONArray hourly_temperature = hourly.getJSONArray("temperature");
            JSONArray hourly_precipitation = hourly.getJSONArray("precipitation");
            jsonArrays.add(0, skycon);
            jsonArrays.add(1, humidity);
            jsonArrays.add(2, temperature);
            jsonArrays.add(3, precipitation);
            jsonArrays.add(4, astro);
            jsonArrays.add(5, hourly_skycon);
            jsonArrays.add(6, hourly_temperature);
            jsonArrays.add(7, hourly_precipitation);
            jsonArrays.add(8, uv);
            jsonArrays.add(9, dressing);
            jsonArrays.add(10, carWashing);

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

    public static String roundString(String s) {
        return String.valueOf(Math.round(Float.parseFloat(s)));
    }

    public static int intRoundString(String s) {
        return Math.round(Float.parseFloat(s));
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
}

