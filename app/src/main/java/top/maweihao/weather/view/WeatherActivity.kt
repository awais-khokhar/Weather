package top.maweihao.weather.view

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.provider.Settings
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_weather.*
import kotlinx.android.synthetic.main.card_air_condition.*
import kotlinx.android.synthetic.main.card_more_info.*
import kotlinx.android.synthetic.main.card_now.*
import kotlinx.android.synthetic.main.card_refresh_time.*
import kotlinx.android.synthetic.main.card_today.*
import kotlinx.android.synthetic.main.card_wind_view.*
import org.jetbrains.anko.design.longSnackbar
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.startService
import org.jetbrains.anko.toast
import pub.devrel.easypermissions.EasyPermissions
import top.maweihao.weather.R
import top.maweihao.weather.adapter.DailyWeatherAdapter
import top.maweihao.weather.adapter.HourlyWeatherAdapter
import top.maweihao.weather.contract.NewWeatherActivityContract
import top.maweihao.weather.contract.PreferenceConfigContact
import top.maweihao.weather.entity.Alert
import top.maweihao.weather.entity.SingleWeather
import top.maweihao.weather.entity.dao.MLocation
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.presenter.NewWeatherPresenter
import top.maweihao.weather.service.PushService
import top.maweihao.weather.util.Constants.*
import top.maweihao.weather.util.Utility
import top.maweihao.weather.util.Utility.*
import java.lang.ref.WeakReference
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class WeatherActivity : BaseActivity(), View.OnClickListener, NewWeatherActivityContract.NewView<NewWeatherActivityContract.NewPresenter>, EasyPermissions.PermissionCallbacks {

    var locationDetail: String = ""

    private val handler: MessageHandler = MessageHandler(this)
    private val newWeatherPresenter by lazy { NewWeatherPresenter(this@WeatherActivity, this) }
    private val configContact by lazy { Utility.createSimpleConfig(this).create(PreferenceConfigContact::class.java) }

    //24h hourlyRecyclerView adapter
    private val hourWeatherAdapter by lazy { HourlyWeatherAdapter(ArrayList()) }
    //5day hourlyRecyclerView adapter
    private val dailyWeatherAdapter by lazy { DailyWeatherAdapter(ArrayList()) }
    private val alertArrayList: ArrayList<Alert> = ArrayList()

    override fun setContentView(): Int = R.layout.activity_weather


    override fun initView(savedInstanceState: Bundle?) {
        initView()
        doDebugThings()

        swipe_refresh.setColorSchemeResources(R.color.colorPrimary)
        swipe_refresh.setDistanceToTriggerSync(200)
        swipe_refresh.setOnRefreshListener { newWeatherPresenter.initNewLocate() }

        weather_alert_icon.setOnClickListener(this)
        more_days_weather.setOnClickListener(this)
    }


    private fun doDebugThings() {
//        more_days_weather.postDelayed({ CrashReport.testJavaCrash() }, (1000 * 10).toLong())
    }

    private fun initView() {
        val hourlyManager = LinearLayoutManager(this@WeatherActivity)
        hourlyManager.orientation = LinearLayoutManager.HORIZONTAL
        hourly_weather_rv.layoutManager = hourlyManager

        val dailyManager = LinearLayoutManager(this@WeatherActivity)
        dailyManager.orientation = LinearLayoutManager.VERTICAL
        daily_weather_rv.layoutManager = dailyManager

        hourly_weather_rv.adapter = hourWeatherAdapter
        daily_weather_rv.adapter = dailyWeatherAdapter

        setSupportActionBar(toolbar)
        toolbar.title = resources.getString(R.string.app_name)
    }

    override fun initData() {
        newWeatherPresenter.subscribe()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        super.onResume()

        newWeatherPresenter.onResume()

        handler.postDelayed({ dynamicWeatherView.onResume() }, 150)
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()

        newWeatherPresenter.onPause()

        dynamicWeatherView.onPause()
    }

    override fun onStart() {
        Log.d(TAG, "onStart")
        super.onStart()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()

        newWeatherPresenter.onStop()
    }

    public override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
        dynamicWeatherView.onDestroy()

        newWeatherPresenter.onDestroy()
        newWeatherPresenter.unSubscribe()
    }

    override fun onClick(v: View) {
        when (v.id) {
            -1 -> finish()
            R.id.weather_alert_icon -> if (alertArrayList.isNotEmpty()) {
                val intent = Intent(this@WeatherActivity, AlertActivity::class.java)
                intent.putParcelableArrayListExtra(
                        AlertActivity.KEY_ALERT_ACTIVITY_ALERT_LIST, alertArrayList)
                startActivity(intent)
//                startActivity(intentFor<AlertActivity>(AlertActivity.KEY_ALERT_ACTIVITY_ALERT_LIST to alertArrayList))
            }
            R.id.more_days_weather -> {
                startActivity<DetailActivity>()
            }
        }
    }

    private fun setRainInfo(now: String, today: String) {
        handler.post {
            rain_info_tv.text = now
            now_card_desc.text = today
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.nav_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.change_position -> {
                startActivityForResult<ChoosePositionActivity>(ChoosePositionActivityRequestCode)
            }
            R.id.start_service -> {
                // debug only
                startService<PushService>()
            }
            R.id.setting -> {
                startActivityForResult<SettingActivity>(SettingActivityRequestCode)
            }
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            SettingActivityRequestCode -> if (resultCode == SettingCode) {
                val autoLocate = data?.getBooleanExtra("autoLocate", false)
                Log.d(TAG, "onActivityResult: SettingActivity autoLocate=$autoLocate")
                if (autoLocate != null && autoLocate) {
                    newWeatherPresenter.initNewLocate()
                }

            } else if (resultCode == ChooseCode) {
                onActivityResult(ChoosePositionActivityRequestCode, ChooseCode, data)
            }
            ChoosePositionActivityRequestCode -> if (resultCode == ChooseCode) {
                configContact.applyAutoLocate(false)

                val bundle = data?.extras
                if (bundle != null) {
                    val location = bundle.getParcelable<MLocation>("location")
                    val desc = if (TextUtils.isEmpty(location.city))
                        location.county
                    else
                        location.city + "" + location.county
                    Log.d(TAG, "onActivityResult: desc = $desc")
                    newWeatherPresenter.refreshChosenWeather(desc)
                } else {
                    Log.d(TAG, "onActivityResult: cannot get parcelable location")
                }
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>,
                                            grantResults: IntArray) {
        // 将结果转发到EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun getLocationPermission() {
        if (EasyPermissions.hasPermissions(this, locationPermission)) {
            newWeatherPresenter.initBaiduLocate()
        } else {
            EasyPermissions.requestPermissions(this, "", PERMISSION_REQUEST_CODE, locationPermission)
        }
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        //获取权限失败回调
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                newWeatherPresenter.initIpLocate()
                longSnackbar(view_root, R.string.permission_denied)
            }
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        //获取权限成功回调
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> newWeatherPresenter.initBaiduLocate()
        }
    }


    override fun showWeather(weather: NewWeather) {
        val dailyBean = weather.result.daily
        val hourlyBean = weather.result.hourly
        val minutelyBean = weather.result.minutely
        Log.d(TAG, "showWeather: days: " + dailyBean.aqi.size)

        showDailyWeather(dailyBean)
        showHourlyWeather(hourlyBean)
        setRainInfo(minutelyBean.description, hourlyBean.description)
        showCurrentWeather(dailyBean, hourlyBean, weather.result.alert)
        setUpdateTime(weather.server_time * 1000)
    }

    private fun showCurrentWeather(dailyBean: NewWeather.ResultBean.DailyBean,
                                   hourlyBean: NewWeather.ResultBean.HourlyBean,
                                   alertBean: NewWeather.ResultBean.AlertBean) {
        val temperature = Utility.stringRoundDouble(hourlyBean.temperature[0].value)
        val skycon = hourlyBean.skycon[0].value
        val humidity = hourlyBean.humidity[0].value
        val PM25 = hourlyBean.pm25[0].value
        val intensity = hourlyBean.precipitation[0].value
        val aqi = hourlyBean.aqi[0].value
        // 是否显示天气预警图标
        alertArrayList.clear()
        val shouldShowAlert = if (alertBean.content.size > 0) {
            Log.d(TAG, "showCurrentWeather: alert size=" + alertBean.content.size)
            for (contentBean in alertBean.content) {
                alertArrayList.add(Alert(contentBean.status,
                        Integer.parseInt(contentBean.code),
                        contentBean.description, contentBean.alertId,
                        contentBean.city + contentBean.county,
                        contentBean.title))
            }
            true
        } else {
            false
        }

        val windDirection = Utility.getWindDirection(this,
                hourlyBean.wind[0].direction)
        val windLevel = Utility.getWindLevel(this, hourlyBean.wind[0].speed)
        val sunRise = dailyBean.astro[0].sunrise.time
        val sunSet = dailyBean.astro[0].sunset.time
        val hum = humidity * 100
        val weatherString = chooseWeatherSkycon(applicationContext, skycon, intensity, MINUTELY_MODE)

        handler.post {
            if (shouldShowAlert) {
                weather_alert_icon.visibility = View.VISIBLE
            } else {
                weather_alert_icon.visibility = View.GONE
            }
            wind_direction_tv.text = windDirection
            wind_level_tv.text = windLevel
            PM_Circle.setValue(PM25.toInt())
            temperature_text.text = temperature
            AQI_Circle.setValue(aqi.toInt())
            humidity_info.text = "${hum.toString().substring(0, 2)}%"
            skycon_text.text = weatherString
            dynamicWeatherView.setDrawerType(Utility.chooseBgImage(skycon))
            sunrise.text = sunRise
            sunset.text = sunSet
            sunTimeTv.setTime(sunRise, sunSet)
            uv.text = dailyBean.ultraviolet[0].desc
            carWash.text = dailyBean.carWashing[0].desc
            dressing.text = dailyBean.dressing[0].desc
            Log.d(TAG, "showWeather: current weather shown")
        }
    }

    private fun showDailyWeather(dailyBean: NewWeather.ResultBean.DailyBean) {
        var length = dailyBean.skycon.size
        length = if (length >= 5) 5 else length
        Log.d(TAG, "showDailyWeather: $length")
        val singleWeathers = ArrayList<SingleWeather>(length)
        val dayOfWeek = Utility.getDayOfWeek()
        val oldSDF = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        val newSDF = SimpleDateFormat("MM/dd", Locale.getDefault())
        for (i in 0 until length) {
            var time = ""
            try {
                val date = oldSDF.parse(dailyBean.skycon[i].date)
                time = if (i != 0) {
                    newSDF.format(date) + " " +
                            resources.getStringArray(R.array.week)[(dayOfWeek + i - 1) % 7]
                } else {
                    newSDF.format(date) + " " +
                            resources.getString(R.string.today)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                Log.d(TAG, "showDailyWeather: parse time format failed")
            }

            val icon = Utility.chooseWeatherIcon(dailyBean.skycon[i].value,
                    dailyBean.precipitation[i].avg, HOURLY_MODE, false)
            val skyconDesc: String = if (dailyBean.desc == null || TextUtils.isEmpty(dailyBean.desc[i].value)) {
                Utility.chooseWeatherSkycon(this,
                        dailyBean.skycon[i].value,
                        dailyBean.precipitation[i].avg, HOURLY_MODE)
            } else {
                dailyBean.desc[i].value
            }
            // 在有 desc 时优先显示 desc 的内容
            val temperature = (Utility.stringRoundDouble(dailyBean.temperature[i].max)
                    + "°/"
                    + Utility.stringRoundDouble(dailyBean.temperature[i].min) + '°'.toString())
            singleWeathers.add(SingleWeather(time, icon, skyconDesc, temperature))
        }
        handler.post { refreshDailyList(singleWeathers) }
    }

    private fun showHourlyWeather(hourlyBean: NewWeather.ResultBean.HourlyBean) {
        var length = hourlyBean.skycon.size
        length = if (length >= 24) 24 else length
        //        Log.d("setHourlyWeatherChart: total " + length + " hour");
        val singleWeathers = ArrayList<SingleWeather>(length)
        for (i in 0 until length) {
            val time = hourlyBean.skycon[i].datetime.substring(11, 16)
            val icon = Utility.chooseWeatherIcon(hourlyBean.skycon[i].value,
                    hourlyBean.precipitation[i].value, HOURLY_MODE, true)
            val skyconDesc = Utility.chooseWeatherSkycon(this, hourlyBean.skycon[i].value,
                    hourlyBean.precipitation[i].value, HOURLY_MODE)
            val temperature = stringRoundDouble(hourlyBean.temperature[i].value) + '°'
            singleWeathers.add(SingleWeather(time, icon, skyconDesc, temperature))
        }
        singleWeathers[0].time = resources.getString(R.string.now)
        handler.post { refreshHourlyList(singleWeathers) }
    }

    private fun refreshHourlyList(list: List<SingleWeather>) {
        hourWeatherAdapter.setWeatherList(list)
    }

    private fun refreshDailyList(list: List<SingleWeather>) {
        dailyWeatherAdapter.setWeatherList(list)
    }

    override fun showLocation(location: MLocation?) {
        if (location == null) {
            Log.e(TAG, "showLocation: location = null")
            return
        }
        val coarseLocation = location.coarseLocation
        val detailLocation = location.fineLocation
        Log.d(TAG, "showLocation: $coarseLocation$detailLocation")
        when (location.locateType) {
            MLocation.TYPE_CHOOSE -> setLoc(coarseLocation, coarseLocation, false)
            MLocation.TYPE_IP -> setLoc(coarseLocation, Utility.getIP(this), false)
            MLocation.TYPE_BAIDU_GPS, MLocation.TYPE_BAIDU_NETWORK, MLocation.TYPE_BAIDU_UNKNOWN, MLocation.TYPE_LOCATION_MANAGER -> setLoc(coarseLocation, detailLocation, true)
            else -> {
            }
        }
    }

    override fun setUpdateTime(timeInMills: Long) {
        handler.post {
            if (timeInMills == 0L) {
                last_update_time.text = Utility.getTime(this@WeatherActivity)
            } else {
                val time = resources.getString(R.string.updated_on,
                        Utility.getTime(this@WeatherActivity, timeInMills))
                last_update_time.text = time
            }
        }
    }

    override fun showNetworkError() {
        longSnackbar(view_root, R.string.access_network_failed, R.string.go_to_settings) {
            startActivity(Intent().setAction(Settings.ACTION_SETTINGS))
        }
    }

    override fun showError(error: String, showOkButton: Boolean) {
        var errorStr = error
        if (TextUtils.isEmpty(error)) {
            errorStr = resources.getString(R.string.error_happens)
        }
        val snackBar = Snackbar.make(view_root, errorStr, Snackbar.LENGTH_LONG)
        if (showOkButton) {
            snackBar.setAction(R.string.ok) { }
        }
        snackBar.show()
    }

    override fun showIpLocateMessage() {
        longSnackbar(view_root, R.string.ip_method_locate, R.string.donnot_show) {
            // TODO: 02/12/2017
        }
    }

    override fun showLocateError() {
        setRefreshingState(false)
        //        view_root.postDelayed(new Runnable() {
        //            @Override
        //            public void run() {
        //                Snackbar.make(view_root, R.string.locate_failed, Snackbar.LENGTH_LONG)
        //                        .setAction(R.string.go_to_settings, new View.OnClickListener() {
        //                            @Override
        //                            public void onClick(View v) {
        //                                startActivity(new Intent().setAction(Settings.ACTION_SETTINGS));
        //                            }
        //                        });
        //            }
        //        }, 2000);
        longSnackbar(view_root, R.string.locate_failed, R.string.go_to_settings) {
            startActivity(Intent().setAction(Settings.ACTION_SETTINGS))
        }


    }

    override fun setRefreshingState(refresh: Boolean) {
        handler.post {
            swipe_refresh.isRefreshing = refresh
        }
    }

    override fun askForChoosePosition() {
        val intent = Intent(this@WeatherActivity, ChoosePositionActivity::class.java)
        startActivityForResult(intent, ChoosePositionActivityRequestCode)
    }

    private fun setLoc(coarse: String, detail: String?, loc: Boolean) {
        handler.post {
            locate_mode_image.visibility = View.VISIBLE
            if (loc) {
                locate_mode_image.setImageResource(R.drawable.ic_location_on_black_24dp)
            } else {
                locate_mode_image.setImageResource(R.drawable.ic_location_off_black_24dp)
            }
            if (!TextUtils.isEmpty(coarse)) {
                toolbar.title = coarse
            }
            if (!TextUtils.isEmpty(detail)) {
                location_tv.text = detail
            }
        }
    }

    private class MessageHandler(activity: WeatherActivity) : Handler() {
        internal var activityWeakReference: WeakReference<WeatherActivity> = WeakReference(activity)

        override fun handleMessage(msg: Message) {
            val activity = activityWeakReference.get()
            if (activity != null) {
                when (msg.what) {
                    HANDLE_TOAST -> activity.toast(msg.obj as String)
                    HANDLE_EXACT_LOCATION -> {
                        activity.locationDetail = msg.obj as String
                        activity.location_tv.text = msg.obj as String
                    }
                }
            }
        }
    }

    companion object {

        internal const val HANDLE_TOAST = 1
        internal const val HANDLE_EXACT_LOCATION = 2


        private const val locationPermission = Manifest.permission.ACCESS_COARSE_LOCATION

        private const val PERMISSION_REQUEST_CODE = 102
    }


}
