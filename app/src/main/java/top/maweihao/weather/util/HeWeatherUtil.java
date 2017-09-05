package top.maweihao.weather.util;

import android.support.annotation.NonNull;

import top.maweihao.weather.R;

/**
 * 和风天气工具类
 * 参考 https://www.heweather.com/documents/
 * Created by ma on 17-9-5.
 */

public class HeWeatherUtil {

    public static final int MODE_NOW = 0;

    /**
     * choose icon
     *
     * @param code code
     * @return mipmap code
     */
    public static int chooseHeIcon(int code) {
        switch (code) {
            case 100:
                return R.mipmap.weather_clear;
            case 101:
                return R.mipmap.weather_clouds;
            case 102:
                return R.mipmap.weather_few_clouds;
            case 104:
                return R.mipmap.weather_clouds;
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 209:
            case 210:
            case 211:
            case 212:
            case 213:
                return R.mipmap.weather_wind;
            case 300:
                return R.mipmap.weather_drizzle_day;
            case 301:
                return R.mipmap.weather_showers_day;
            case 302:
                return R.mipmap.weather_drizzle_day;
            case 303:
                return R.mipmap.weather_showers_day;
            case 304:
                return R.mipmap.weather_hail;
            case 305:
                return R.mipmap.weather_drizzle_day;
            case 306:
                return R.mipmap.weather_rain_day;
            case 307:
            case 308:
                return R.mipmap.weather_showers_day;
            case 309:
                return R.mipmap.weather_drizzle_day;
            case 310:
            case 311:
            case 312:
                return R.mipmap.weather_showers_day;
            case 313:
                return R.mipmap.weather_hail;
            case 400:
            case 401:
                return R.mipmap.weather_snow;
            case 402:
            case 403:
                return R.mipmap.weather_big_snow;
            case 404:
            case 405:
            case 406:
                return R.mipmap.weather_snow_rain;
            case 407:
                return R.mipmap.weather_snow;
            case 500:
                return R.mipmap.weather_mist;
            case 501:
                return R.mipmap.weather_fog;
            case 503:
            case 504:
            case 507:
            case 508:
                return R.mipmap.ic_warning_sand_storm;
            default:
                return R.mipmap.weather_none_available;
        }
    }

    public static String initRequireUrl(int mode, @NonNull String locationCoordinates) {
        switch (mode) {
            case MODE_NOW:
                return "https://free-api.heweather.com/v5/now?city=" + locationCoordinates + "&key=5dfd7d649560440ca704c077a21af092";
        }
        return null;
    }
}
