package top.maweihao.weather.viewModel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.Handler
import android.os.Looper
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import top.maweihao.weather.R
import top.maweihao.weather.contract.PreferenceConfigContact
import top.maweihao.weather.entity.dao.MLocation
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.model.LocationModel
import top.maweihao.weather.model.WeatherModel
import top.maweihao.weather.service.SyncService
import top.maweihao.weather.util.LocationUtil
import top.maweihao.weather.util.Utility
import top.maweihao.weather.util.http.DataResult
import top.maweihao.weather.util.http.Status

class WeatherViewModel : ViewModel() {

    var isLoadCache: Boolean = false

    val weatherLiveData by lazy {
        LogUtils.d("weatherLiveData null")
        MediatorLiveData<DataResult<NewWeather>>()
    }

    val locationResult by lazy { MediatorLiveData<MLocation>() }

    val tipsData by lazy { MutableLiveData<TipsData>() }

    private var workingFlag = false

    private var weather4widget: NewWeather? = null  //for widget refresh
    private var countyName4widget: String? = null //for widget refresh

    private val configContact by lazy { Utility.createSimpleConfig(Utils.getApp()).create(PreferenceConfigContact::class.java) }

    private val resource by lazy { Utils.getApp().resources }

    fun getWeatherCache() {
        weatherLiveData.value = DataResult.cache(WeatherModel.getWeatherCache())
    }

    fun getWeather(isHavePermission: Boolean) {
        if (configContact.getAutoLocate(true)) {
            LogUtils.d("AutoLocate: true")
            if (isHavePermission) {
                LocationModel.initBaiduLocation(success = { location ->
                    //定位成功
                    LogUtils.d(location.locationStringReversed)
                    Handler(Looper.getMainLooper()).post {
                        refreshWeather(location)
                    }

                }, failed = {
                    //定位失败
                    tipsData.value = TipsData(TipsType.LOCATION_FAIL)
                })

            } else {
                //ip定位
                LogUtils.d("start initIPLocate")
                locationResult.addSource(LocationModel.initIPLocate()) {
                    if (it == null) return@addSource
                    if (it.status == Status.SUCCESS) {
                        it.data?.let {
                            if (it.status == 0) {
                                val location = LocationUtil.convertType(it)
                                LogUtils.d("ip locate success: " + location.locationStringReversed)
                                refreshWeather(location)
                                tipsData.value = TipsData(type = TipsType.NORMAL, message = resource.getString(R.string.ip_method_locate))
                            } else {
                                LogUtils.d("initIpLocate: baidu ip address error. " + it.status)
                                tipsData.value = TipsData(type = TipsType.NORMAL, message = it.message)
                            }
                        }
                    }
                }

            }
        } else {
            //不是自动定位
            val location = LocationModel.getLocationCached()
            if (location != null) {
                refreshWeather(location)
            } else {
                tipsData.value = TipsData(TipsType.CHOOSE_POSITION)
            }
        }
    }

    private fun refreshWeather(location: MLocation) {
        if (location.isNeedGeocode) {
            workingFlag = true
            val locationData = LocationModel.geocodeLocation(location)
            locationResult.addSource(locationData) {
                if (it == null) return@addSource
                it.data?.let {
                    countyName4widget = location.coarseLocation
                    if (workingFlag) {
                        workingFlag = false
                    } else {
                        WeatherModel.updateWidget(weather4widget, countyName4widget)
                    }
                }
                locationResult.value = location
            }
        } else {
            locationResult.value = location
        }

        val tempWeatherData = WeatherModel.getWeather(location.locationStringReversed, isLoadCache)
        weatherLiveData.addSource(tempWeatherData) {
            LogUtils.d("weatherLiveData")
            weatherLiveData.value = it
            it?.let {
                if (it.status != Status.CACHE) {
                    weather4widget = it.data
                    if (workingFlag) {
                        workingFlag = false
                    } else {
                        WeatherModel.updateWidget(weather4widget, countyName4widget)
                        SyncService.scheduleSyncService(Utils.getApp().applicationContext, false, false)

                    }

                }
            }
        }
    }


    fun refreshChosenWeather(desc: String) {
        val data = LocationModel.getCoordinateByDesc(desc)
        locationResult.addSource(data) {
            it?.let {
                when (it.status) {
                    Status.SUCCESS -> {
                        it.data?.let {
                            if (it.latitude == 0.0f && it.longitude == 0.0f) {
                                tipsData.value = TipsData(TipsType.NORMAL, "Get weather failed, try to enable \"Auto Locate\"")
                            } else {
                                refreshWeather(it)
                            }
                        }
                    }
                    Status.ERROR   -> {
                        tipsData.value = TipsData(TipsType.NORMAL, "NetWork Error : ${it.message}")
                    }
                    else           -> {
                    }
                }

            }
        }
    }

}

enum class TipsType {
    NORMAL, LOCATION_FAIL, CHOOSE_POSITION,
}

data class TipsData(val type: TipsType, val message: String = "")