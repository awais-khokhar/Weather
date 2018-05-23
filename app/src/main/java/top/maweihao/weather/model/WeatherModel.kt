package top.maweihao.weather.model

import android.arch.lifecycle.LiveData
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import top.maweihao.weather.entity.dao.DaoMaster
import top.maweihao.weather.entity.dao.DaoUtils
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.entity.dao.NewWeatherDao
import top.maweihao.weather.util.Constants
import top.maweihao.weather.util.ServiceUtil
import top.maweihao.weather.util.http.ApiResponse
import top.maweihao.weather.util.http.ApiUtils
import top.maweihao.weather.util.http.DataResult
import top.maweihao.weather.util.http.NetworkBoundResource
import top.maweihao.weather.util.remoteView.WidgetUtils





object WeatherModel {
    private val weatherDao: NewWeatherDao
//    private val heWeatherNowDao: NewHeWeatherNowDao
//    private val locationDao: MLocationDao

    init {
        val devOpenHelper = DaoMaster.DevOpenHelper(Utils.getApp().applicationContext, "weather.db", null)
        val daoMaster = DaoMaster(devOpenHelper.writableDb)
        val daoSession = daoMaster.newSession()
        weatherDao = daoSession.newWeatherDao
//        weatherRealtimeDao = daoSession.getNewWeatherRealtimeDao();
//        heWeatherNowDao = daoSession.getNewHeWeatherNowDao();
//        locationDao = daoSession.mLocationDao
    }


    fun getWeather(location: String, isLoadCache: Boolean): LiveData<DataResult<NewWeather>> {
        return object : NetworkBoundResource<NewWeather, NewWeather>() {
            override fun saveCallResultOrConvert(item: NewWeather): NewWeather {
                if (item.status == "ok") {
                    weatherDao.deleteAll()
                    weatherDao.insertOrReplace(DaoUtils.packWeather(item))
                }

                return item
            }

            override fun shouldFetch(data: NewWeather?): Boolean {
                return true
            }

            override fun shouldLoad4Cache(): Boolean {
                return isLoadCache
            }

            override fun load4Cache(): NewWeather? {
                return getWeatherCache()
            }

            override fun createCall(): LiveData<ApiResponse<NewWeather>> {
                return ApiUtils.getWeather(location, null, null, Constants.timeShift, Constants.lang)
            }
        }.asLiveData()

    }

    fun getWeatherCache(): NewWeather? {
        val weatherList = weatherDao.loadAll()
        if (weatherList != null && weatherList.size > 0) {
            weatherList.sort()

            System.out.println("-------------------> weatherCache not null")
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

    fun getLastUpdateTime(): Long {
        val weatherList = weatherDao.loadAll()
        return if (weatherList != null && weatherList.size > 0) {
            weatherList[0].server_time * 1000
        } else {
            0
        }
    }


}