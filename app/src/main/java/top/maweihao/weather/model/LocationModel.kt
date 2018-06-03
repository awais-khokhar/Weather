package top.maweihao.weather.model

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.baidu.location.BDLocation
import com.baidu.location.BDLocationListener
import com.baidu.location.LocationClient
import com.baidu.location.LocationClientOption
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import io.reactivex.FlowableOnSubscribe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.operators.flowable.FlowableCreate
import io.reactivex.schedulers.Schedulers
import top.maweihao.weather.entity.BaiDu.BDIPLocationBean
import top.maweihao.weather.entity.BaiDu.BaiDuChoosePositionBean
import top.maweihao.weather.entity.BaiDu.BaiDuCoordinateBean
import top.maweihao.weather.entity.dao.DaoMaster
import top.maweihao.weather.entity.dao.MLocation
import top.maweihao.weather.entity.dao.MLocationDao
import top.maweihao.weather.util.LocationUtil
import top.maweihao.weather.util.Utility.isGPSEnabled
import top.maweihao.weather.util.http.ApiUtils
import top.maweihao.weather.util.http.DataResult
import top.maweihao.weather.util.http.NetworkBoundResource

object LocationModel {

    private val mLocationClient by lazy { LocationClient(Utils.getApp().applicationContext) }

    //百度定位参数
    private val option: LocationClientOption by lazy {
        val o = LocationClientOption()
        o.setScanSpan(1000)
        o.locationMode = LocationClientOption.LocationMode.Hight_Accuracy
        o.setIsNeedAddress(true)
        o
    }

    private val locationDao: MLocationDao

    init {
        val devOpenHelper = DaoMaster.DevOpenHelper(Utils.getApp().applicationContext, "weather.db", null)
        val daoMaster = DaoMaster(devOpenHelper.writableDb)
        val daoSession = daoMaster.newSession()
        locationDao = daoSession.mLocationDao
//        daoSession.startAsyncSession().runInTx {
//
//        }

    }

    /**
     * 百度定位
     * @param success Function1<[@kotlin.ParameterName] MLocation, Unit>
     * @param failed Function0<Unit>
     */
    fun initBaiduLocation(): Flowable<MLocation> {
        val locateTime = System.currentTimeMillis()

        return FlowableCreate<MLocation>(FlowableOnSubscribe {
            with(mLocationClient) {
                locOption = option
                registerLocationListener(object : BDLocationListener {
                    override fun onReceiveLocation(bdLocation: BDLocation?) {
                        LogUtils.d("BAIDU: received" + (System.currentTimeMillis() - locateTime))
                        if (bdLocation == null) return
                        if (System.currentTimeMillis() - locateTime < 3 * 1000) {  //at most x second
                            if (bdLocation.locType != BDLocation.TypeGpsLocation) {
                                LogUtils.d("BAIDU onReceiveLocation: Locate type == " + bdLocation.locType)
                                if (LocationUtil.isBaiduLocateSuccess(bdLocation.locType)) {
                                    if (!isGPSEnabled(Utils.getApp())) {
                                        LogUtils.d("BAIDU onReceiveLocation: system GPS is off")
                                        mLocationClient.stop()
//                                showAndSaveWeather(bdLocation)
//                                locationData.value = DataResult.success(LocationUtil.convertType(bdLocation))
//                                    success(LocationUtil.convertType(bdLocation))
                                        it.onNext(LocationUtil.convertType(bdLocation))
                                    }
                                }
                            } else {
                                mLocationClient.stop()
                                LogUtils.d("BAIDU onReceiveLocation: Locate type == GPS")
//                        showAndSaveWeather(bdLocation)
//                        locationData.value = DataResult.success(LocationUtil.convertType(bdLocation))
//                            success(LocationUtil.convertType(bdLocation))
                                it.onNext(LocationUtil.convertType(bdLocation))
                            }
                        } else {
                            mLocationClient.stop()
                            LogUtils.d("BAIDU onReceiveLocation: baidu locate time out, locType == " + bdLocation.locType)

                            if (LocationUtil.isBaiduLocateSuccess(bdLocation.locType)) {
//                        showAndSaveWeather(bdLocation)
//                        locationData.value = DataResult.success(LocationUtil.convertType(bdLocation))
//                            success(LocationUtil.convertType(bdLocation))
                                it.onNext(LocationUtil.convertType(bdLocation))
                            } else {
                                LogUtils.d("BAIDU onReceiveLocation: baidu locate failed, " + "switch to LocationManager method")

//                            initSystemLocate(success, failed)
                                initSystemLocate(it)
//                        locationData.addSource(initSystemLocate()) {
//                            locationData.value = it
//                        }
                            }
                        }
                    }

                    override fun onConnectHotSpotMessage(p0: String?, p1: Int) {

                    }

                })
                start()
            }
        }, BackpressureStrategy.LATEST)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    @SuppressLint("MissingPermission")
    private fun initSystemLocate(emitter: FlowableEmitter<MLocation>) {
        val mLocationManager = Utils.getApp().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val location: Location?
        LogUtils.d("initSystemLocate: " + mLocationManager.getProviders(true))
        if (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER) || mLocationManager.getProviders(true).contains(LocationManager.PASSIVE_PROVIDER)) {
            location = if (mLocationManager.getProviders(true).contains(LocationManager.NETWORK_PROVIDER)) {
                mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER)
            } else {
                mLocationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER)
            }

            if (location != null) {
                LogUtils.d("initSystemLocate: " + location.latitude + " " + location.longitude)
                val loc = LocationUtil.convertType(location)
                loc.needGeocode = true
                LogUtils.d("LocationManager: locationCoordinates = " + loc.locationString!!)

                emitter.onNext(loc)
            } else {

                emitter.onError(Throwable("Location null"))
            }
        } else {

            emitter.onError(Throwable("Location Providers error"))
        }
    }

    /**
     * IP地址定位
     * @return LiveData<DataResult<BDIPLocationBean>>
     */
    fun initIPLocate(): Flowable<DataResult<BDIPLocationBean>> {
        return object : NetworkBoundResource<BDIPLocationBean, BDIPLocationBean>() {
            override fun saveCallResultOrConvert(item: BDIPLocationBean): BDIPLocationBean {
                return item
            }

            override fun shouldFetch(data: BDIPLocationBean?): Boolean = true

            override fun shouldLoad4Cache(): Boolean = false

            override fun load4Cache(): BDIPLocationBean? = null

            override fun createCall(): Flowable<BDIPLocationBean> {
                return ApiUtils.getIpLocation()
            }
        }.asFlowable()

    }

    //地理编码
    fun geocodeLocation(location: MLocation): Flowable<DataResult<MLocation>> {
        return object : NetworkBoundResource<MLocation, BaiDuCoordinateBean>() {
            override fun saveCallResultOrConvert(item: BaiDuCoordinateBean): MLocation {
                if (item.status == 0) {
                    LocationUtil.fillLocation(location, item)
                    saveLocationCache(location)
                } else {
                    LogUtils.d("geocodeLocationAndSave: bean status error. " + item.status)
                }
                return location
            }

            override fun shouldFetch(data: MLocation?): Boolean = true

            override fun shouldLoad4Cache(): Boolean = false

            override fun load4Cache(): MLocation? = null

            override fun createCall(): Flowable<BaiDuCoordinateBean> {
                return ApiUtils.getAddressDetail(location.locationString)
            }

            override fun onFetchFailed(): MLocation? = location
        }.asFlowable()
    }


    fun getCoordinateByDesc(desc: String): Flowable<DataResult<MLocation>> {
        return object : NetworkBoundResource<MLocation, BaiDuChoosePositionBean>() {
            override fun saveCallResultOrConvert(item: BaiDuChoosePositionBean): MLocation {
                val location = LocationUtil.convertType(item, desc)
                saveLocationCache(location)
                return location
            }

            override fun shouldFetch(data: MLocation?): Boolean = true

            override fun shouldLoad4Cache(): Boolean = false

            override fun load4Cache(): MLocation? = null

            override fun createCall(): Flowable<BaiDuChoosePositionBean> {
                return ApiUtils.getCoordinateByDesc(desc)
            }
        }.asFlowable()

    }

    //保存定位信息
    fun saveLocationCache(location: MLocation) {
        locationDao.deleteAll()
        locationDao.insertOrReplace(location)
    }

    /**
     * 获取定位缓存
     */
    fun getLocationCached(): MLocation? {
        val locationList = locationDao.loadAll()
        return if (locationList.size > 0) {
            locationList[0]
        } else {
            null
        }
    }
}
