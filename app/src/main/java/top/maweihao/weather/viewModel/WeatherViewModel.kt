package top.maweihao.weather.viewModel

import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import io.reactivex.subscribers.DefaultSubscriber
import top.maweihao.weather.R
import top.maweihao.weather.contract.PreferenceConfigContact
import top.maweihao.weather.entity.BaiDu.BDIPLocationBean
import top.maweihao.weather.entity.dao.MLocation
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.model.LocationModel
import top.maweihao.weather.model.WeatherModel
import top.maweihao.weather.service.SyncService
import top.maweihao.weather.util.LocationUtil
import top.maweihao.weather.util.Utility
import top.maweihao.weather.util.http.NetworkSubscriber

class WeatherViewModel : ViewModel() {

    var isLoadCache: Boolean = false

    val weatherLiveData by lazy { MutableLiveData<NewWeather>() }

    val locationResult by lazy { MediatorLiveData<MLocation>() }

    val statusInfoData by lazy { MutableLiveData<StatusInfoData>() }

    val refreshViewData by lazy { MutableLiveData<Boolean>() }

    private var workingFlag = false

    private var weather4widget: NewWeather? = null  //for widget refresh
    private var countyName4widget: String? = null //for widget refresh

    private val configContact by lazy { Utility.createSimpleConfig(Utils.getApp()).create(PreferenceConfigContact::class.java) }

    private val resource by lazy { Utils.getApp().resources }


    fun getWeatherCache() {
//        weatherLiveData.value = DataResult.cache(WeatherModel.getWeatherCache())
//        weatherLiveData.value = WeatherModel.getWeatherCache()
    }

    fun getWeather(isHavePermission: Boolean) {
        if (configContact.getAutoLocate(true)) {
            LogUtils.d("AutoLocate: true")
            if (isHavePermission) {

                LocationModel.initBaiduLocation()
                        .subscribe(object : DefaultSubscriber<MLocation>() {
                            override fun onComplete() {
                            }

                            override fun onStart() {
                                super.onStart()
                                refreshViewData.value = true
                            }

                            override fun onNext(t: MLocation?) {
                                t?.let { refreshWeather(it) }
                            }

                            override fun onError(t: Throwable?) {
                                //定位失败
                                statusInfoData.value = StatusInfoData(StatusInfo.LOCATION_FAIL, t?.message
                                                                                                ?: "")
                            }
                        })

            } else {
                //ip定位
                LogUtils.d("start initIPLocate")
                LocationModel.initIPLocate()
                        .subscribe(object : NetworkSubscriber<BDIPLocationBean>() {
                            override fun onSuccess(data: BDIPLocationBean, isDbCache: Boolean) {
                                if (data.status == 0) {
                                    val location = LocationUtil.convertType(data)
                                    LogUtils.d("ip locate success: " + location.locationStringReversed)
                                    refreshWeather(location)
                                    statusInfoData.value = StatusInfoData(type = StatusInfo.NORMAL, message = resource.getString(R.string.ip_method_locate))
                                } else {
                                    LogUtils.d("initIpLocate: baidu ip address error. " + data.status)
                                    statusInfoData.value = StatusInfoData(type = StatusInfo.NORMAL, message = data.message)
                                }
                            }

                            override fun onNetError(msg: String?) {
                                statusInfoData.value = StatusInfoData(StatusInfo.NET_ERR, "NetWork Error : $msg")
                            }
                        })
            }
        } else {
            //不是自动定位
            val location = LocationModel.getLocationCached()
            if (location != null) {
                refreshWeather(location)
            } else {
                statusInfoData.value = StatusInfoData(StatusInfo.CHOOSE_POSITION)
            }
        }
    }

    private fun refreshWeather(location: MLocation) {

        if (location.isNeedGeocode) {
            workingFlag = true
            LocationModel.geocodeLocation(location)
                    .subscribe(object : NetworkSubscriber<MLocation>() {
                        override fun onSuccess(data: MLocation, isDbCache: Boolean) {
                            locationResult.value = data
                            countyName4widget = data.coarseLocation
                            if (workingFlag) {
                                workingFlag = false
                            } else {
                                WeatherModel.updateWidget(weather4widget, countyName4widget)
                            }
                        }

                        override fun onNetError(msg: String?) {
                            statusInfoData.value = StatusInfoData(StatusInfo.NET_ERR, "NetWork Error : $msg")
                        }
                    })
        } else {
            locationResult.value = location
        }

        LogUtils.d("getWeather")
        WeatherModel.getWeather(location.locationStringReversed, isLoadCache)
                .subscribe(object : NetworkSubscriber<NewWeather>() {
                    override fun onSuccess(data: NewWeather, isDbCache: Boolean) {
                        weatherLiveData.value = data

                        LogUtils.d("set Value")

                        refreshViewData.value = false

                        weather4widget = data
                        if (workingFlag) {
                            workingFlag = false
                        } else {
                            WeatherModel.updateWidget(weather4widget, countyName4widget)
                            SyncService.scheduleSyncService(Utils.getApp().applicationContext, false, false)
                        }
                    }

                    override fun onNetError(msg: String?) {
                        LogUtils.e(msg)
                        refreshViewData.value = false
                        statusInfoData.value = StatusInfoData(StatusInfo.NET_ERR, "NetWork Error : $msg")
                    }

                    override fun onLoading() {
                        refreshViewData.value = true
                    }

                    override fun onComplete() {
                        LogUtils.d("set onComplete")

                    }
                })
    }


    fun refreshChosenWeather(desc: String) {
        LocationModel.getCoordinateByDesc(desc)
                .subscribe(object : NetworkSubscriber<MLocation>() {
                    override fun onSuccess(data: MLocation, isDbCache: Boolean) {
                        if (data.latitude == 0.0f && data.longitude == 0.0f) {
                            statusInfoData.value = StatusInfoData(StatusInfo.NORMAL, "Get weather failed, try to enable \"Auto Locate\"")
                        } else {
                            refreshWeather(data)
                        }
                    }

                    override fun onNetError(msg: String?) {
                        statusInfoData.value = StatusInfoData(StatusInfo.NORMAL, "NetWork Error : $msg")
                    }
                })
    }

}

enum class StatusInfo {
    NORMAL, LOCATION_FAIL, CHOOSE_POSITION, NET_ERR
}

data class StatusInfoData(val type: StatusInfo, val message: String = "")