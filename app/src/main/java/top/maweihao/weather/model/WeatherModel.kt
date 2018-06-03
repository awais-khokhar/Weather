package top.maweihao.weather.model

import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import io.reactivex.Flowable
import top.maweihao.weather.entity.dao.*
import top.maweihao.weather.util.Constants
import top.maweihao.weather.util.ServiceUtil
import top.maweihao.weather.util.http.ApiUtils
import top.maweihao.weather.util.http.DataResult
import top.maweihao.weather.util.http.NetworkBoundResource
import top.maweihao.weather.util.remoteView.WidgetUtils




object WeatherModel {
    private val weatherDao: NewWeatherDao
    private var heWeatherNowDao: NewHeWeatherNowDao
//    private val locationDao: MLocationDao

    init {
        val devOpenHelper = DaoMaster.DevOpenHelper(Utils.getApp().applicationContext, "weather.db", null)
        val daoMaster = DaoMaster(devOpenHelper.writableDb)
        val daoSession = daoMaster.newSession()
        weatherDao = daoSession.newWeatherDao
//        weatherRealtimeDao = daoSession.getNewWeatherRealtimeDao();
        heWeatherNowDao = daoSession.newHeWeatherNowDao
//        locationDao = daoSession.mLocationDao
    }

    /**
     * 获取天气数据
     * @param location String
     * @param isLoadCache Boolean
     * @return LiveData<DataResult<NewWeather>>
     */
    fun getWeather(location: String, isLoadCache: Boolean): Flowable<DataResult<NewWeather>> {
        return object : NetworkBoundResource<NewWeather, NewWeather>() {
            override fun saveCallResultOrConvert(item: NewWeather): NewWeather {
                saveWeather(item)

                return item
            }

            override fun shouldFetch(data: NewWeather?): Boolean {
                //判断是否需要网络加载数据。超时5分钟则网络加载
                if (data == null) return true
                val interval = (System.currentTimeMillis() - data.server_time * 1000) / (60 * 1000)
                return if (interval > 5) {  //hardcode the minimum refresh interval temporary
                    true
                } else {
                    LogUtils.d("fetchData: no need to refresh, last $interval ago")
                    if (WidgetUtils.hasAnyWidget(Utils.getApp())) {
                        updateWidget(data, location)
                    }
                    false
                }
            }

            override fun shouldLoad4Cache(): Boolean {
                return isLoadCache
            }

            override fun load4Cache(): NewWeather? {
                return getWeatherCache()
            }

            override fun createCall(): Flowable<NewWeather> {
                return ApiUtils.getWeather(location, null, null, Constants.timeShift, Constants.lang)
            }
        }.asFlowable()

    }

    fun getWeatherCache(): NewWeather? {
        val weatherList = weatherDao.loadAll()
        if (weatherList != null && weatherList.size > 0) {
            weatherList.sort()
            LogUtils.d("-------------------> weatherCache not null")
            return DaoUtils.unpackWeather(weatherList[0])
        }
        return null
    }

    /**
     * 刷新插件
     * @param weatherView NewWeather?
     * @param location String?
     */
    fun updateWidget(weatherView: NewWeather?, location: String?) {
        if (weatherView == null || location == null) {
            LogUtils.e("updateWidget: null!$weatherView $location")
        } else {
            if (WidgetUtils.hasAnyWidget(Utils.getApp())) {
                //                Log.d(TAG, "updateWidget: here has widget");
                ServiceUtil.startWidgetSyncService(Utils.getApp(), false, false)
                WidgetUtils.refreshWidget(Utils.getApp(), weatherView, location)
            }
        }
    }


    fun getHeWeatherNow(location: String): Flowable<DataResult<NewHeWeatherNow>> {
        return object : NetworkBoundResource<NewHeWeatherNow, NewHeWeatherNow>() {
            override fun saveCallResultOrConvert(item: NewHeWeatherNow): NewHeWeatherNow {
                saveWeather(item)
                return item
            }

            override fun shouldFetch(data: NewHeWeatherNow?): Boolean = true

            override fun shouldLoad4Cache(): Boolean = false

            override fun load4Cache(): NewHeWeatherNow? = null

            override fun createCall(): Flowable<NewHeWeatherNow> {
                return ApiUtils.getHeWeatherNow(location)
            }
        }.asFlowable()
    }

    fun saveWeather(weather: NewWeather) {
        if (weather.status == "ok") {
            weatherDao.deleteAll()
            weatherDao.insertOrReplace(DaoUtils.packWeather(weather))
        }
    }

    fun saveWeather(weather: NewHeWeatherNow) {
        if (weather.heWeather5[0].status == "ok") {
            heWeatherNowDao.deleteAll()
            heWeatherNowDao.insertOrReplace(DaoUtils.packWeather(weather, DaoUtils.getHeWeatherUpdateTime(weather)))
        }
    }

    fun getLastUpdateTime(): Long {
        val weatherList = weatherDao.loadAll()
        return if (weatherList != null && weatherList.size > 0) {
            weatherList[0].server_time * 1000
        } else {
            0
        }
    }

    fun getLastHeNowUpdateTime(): Long {
        val weatherList = heWeatherNowDao.loadAll()
        return if (weatherList != null && weatherList.size > 0) {
            weatherList[0].currentTimeInMills
        } else {
            0
        }
    }

    fun getHeWeatherNowCached(): NewHeWeatherNow? {
        val weatherList = heWeatherNowDao.loadAll()
        return if (weatherList != null && weatherList.size > 0) {
            DaoUtils.unpackWeather(weatherList[0])

        } else {
            null
        }
    }
}