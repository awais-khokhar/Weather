package top.maweihao.weather.entity.dao;

import android.util.Log;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DaoUtils {

    private static final String TAG = DaoUtils.class.getSimpleName();

    public static NewWeather packWeather(NewWeather weather) {
        Gson gson = new Gson();
        weather.setJsonString(gson.toJson(weather));
        return weather;
    }

    public static NewWeather unpackWeather(NewWeather weather) {
        Gson gson = new Gson();
        return gson.fromJson(weather.getJsonString(), NewWeather.class);
    }

    public static NewWeatherRealtime packWeather(NewWeatherRealtime weather) {
        Gson gson = new Gson();
        weather.setJsonString(gson.toJson(weather));
        return weather;
    }

    public static NewWeatherRealtime unpackWeather(NewWeatherRealtime weather) {
        Gson gson = new Gson();
        return gson.fromJson(weather.getJsonString(), NewWeatherRealtime.class);
    }

    public static NewHeWeatherNow packWeather(NewHeWeatherNow weather, long time) {
        Gson gson = new Gson();
        weather.setCurrentTimeInMills(time);
        weather.setJsonString(gson.toJson(weather));
        return weather;
    }

    public static NewHeWeatherNow unpackWeather(NewHeWeatherNow weather) {
        Gson gson = new Gson();
        return gson.fromJson(weather.getJsonString(), NewHeWeatherNow.class);
    }

    public static long getHeWeatherUpdateTime(NewHeWeatherNow weather) {
        NewHeWeatherNow.HeWeather5Bean.BasicBean basicBean = weather.getHeWeather5().get(0).getBasic();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        try {
            Date date = simpleDateFormat.parse(basicBean.getUpdate().getLoc());
            return date.getTime();
        } catch (ParseException e) {
            Log.e(TAG, "getHeWeatherUpdateTime: parse failed" + basicBean.getUpdate().getLoc());
            return 0;
        }
    }
}
