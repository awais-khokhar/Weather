package top.maweihao.weather.util;

import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import top.maweihao.weather.WeatherData;


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
}

