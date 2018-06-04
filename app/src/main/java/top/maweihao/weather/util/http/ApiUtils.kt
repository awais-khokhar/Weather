package top.maweihao.weather.util.http

import android.text.TextUtils
import io.reactivex.Flowable
import io.reactivex.annotations.NonNull
import top.maweihao.weather.entity.BaiDu.BDIPLocationBean
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean
import top.maweihao.weather.entity.dao.NewHeWeatherNow
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.util.Constants
import top.maweihao.weather.util.http.HttpUtils.baiduLocateApi
import top.maweihao.weather.util.http.HttpUtils.heWeatherApi
import top.maweihao.weather.util.http.HttpUtils.weatherApi


object ApiUtils {

    /**
     *
     * @param location 坐标
     * @param alert 需要预警？
     * @param days 需要多少天的 forecast
     * @param shift 时间偏移，eg. 北京 = +8 * 60 * 60 = 28800
     * @param lang 语言
     * @return Observable
     */
    fun getWeather(@NonNull location: String, alert: Boolean?,
                   days: Int?, shift: Int?, lang: String): Flowable<NewWeather> {
        var alert = alert
        var days = days
        var shift = shift
        var lang = lang
        alert = alert == null || alert
        days = if (days == null) 15 else days
        shift = if (shift == null) 28800 else shift
        lang = if (TextUtils.isEmpty(lang)) "zh_CN" else lang

        return weatherApi.getWeather(location, alert, days, shift, lang)
    }

    fun getHeWeatherNow(@NonNull location: String): Flowable<NewHeWeatherNow> {
        return heWeatherApi.getHeWeatherNow(location, Constants.HeWeatherKey)
    }

    fun getIpLocation(): Flowable<BDIPLocationBean> {
        return baiduLocateApi.getIpLocation(Constants.BaiduKey, "gcj02", Constants.mBaiduCode)
    }

    fun getAddressDetail(location: String): Flowable<BaiDuCoordinateBean> {
        return baiduLocateApi.getAddressDetail(location,
                "json",
                0,
                Constants.BaiduKey,
                Constants.mBaiduCode)
    }

    fun getCoordinateByDesc(desc: String): Flowable<BaiDuChoosePositionBean> {
        return baiduLocateApi.getCoordinateByDesc(desc, "json",
                Constants.BaiduKey, Constants.mBaiduCode)
    }
}