package top.maweihao.weather.view

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.util.Log
import android.view.MenuItem
import android.view.View
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.design.longSnackbar
import top.maweihao.weather.R
import top.maweihao.weather.adapter.DailyWeatherAdapter
import top.maweihao.weather.entity.SingleWeather
import top.maweihao.weather.entity.dao.NewWeather
import top.maweihao.weather.model.WeatherModel
import top.maweihao.weather.util.Utility
import top.maweihao.weather.util.Utility.HOURLY_MODE
import top.maweihao.weather.util.Utility.stringRoundDouble
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.concurrent.thread

class DetailActivity : BaseActivity() {
    //    private WeatherData model;
    internal var adapter: DailyWeatherAdapter = DailyWeatherAdapter(ArrayList())

    override fun setContentView(): Int = R.layout.activity_detail

    override fun initView(savedInstanceState: Bundle?) {
        setSupportActionBar(activity_detail_toolbar)
        activity_detail_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp)
        val linearLayoutManager = LinearLayoutManager(this@DetailActivity)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        detail_recycler_view.layoutManager = linearLayoutManager
        detail_recycler_view.adapter = adapter
    }

    override fun initData() {
        fetchData()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchData() {
        startSwipe()
        val weatherList = intent.getParcelableArrayListExtra<SingleWeather>(KEY_DETAIL_ACTIVITY_WEATHER_LIST)
        if (weatherList == null) {

            thread {
                val weather = WeatherModel.getWeatherCache()
                runOnUiThread {
                    if (weather != null) {
                        stopSwipe()
                        val list = generateList(weather)
                        if (list == null) {
                            showError()
                        } else {
                            adapter.setNewData(list)
                        }
                    } else {
                        stopSwipe()
                        Log.e(TAG, "fetchData: get weather cached failed" + " -- > null")
                        showError()
                    }
                }
            }

            //            model.getWeatherCached()
            //                    .subscribeOn(Schedulers.io())
            //                    .observeOn(AndroidSchedulers.mainThread())
            //                    .subscribe(new Consumer<NewWeather>() {
            //                        @Override
            //                        public void accept(NewWeather weather) throws Exception {
            //                            stopSwipe();
            //                            List<SingleWeather> list = generateList(weather);
            //                            if (list == null) {
            //                                showError();
            //                            } else if (adapter == null) {
            //                                adapter = new DailyWeatherAdapter(list);
            //                                recyclerView.setAdapter(adapter);
            //                            } else {
            //                                adapter.setWeatherList(list);
            //                            }
            //                        }
            //                    }, new Consumer<Throwable>() {
            //                        @Override
            //                        public void accept(Throwable throwable) throws Exception {
            //                            stopSwipe();
            //                            Log.e(TAG, "fetchData: get weather cached failed" + throwable);
            //                            showError();
            //                        }
            //                    });
        }
    }

    @UiThread
    private fun showError() {
        stopSwipe()
        longSnackbar(view_root, R.string.error_happens, R.string.ok) {}
    }

    private fun generateList(weather: NewWeather): List<SingleWeather>? {
        val list = ArrayList<SingleWeather>()
        val dailyBean = weather.result.daily

        val temperatureBeanXList = dailyBean.temperature
        val skyconBeanXList = dailyBean.skycon
        val precipitationBeanXList = dailyBean.precipitation
        val dayOfWeek = Utility.getDayOfWeek()
        val oldSdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
        val newSdf = SimpleDateFormat("MM/dd", Locale.CHINA)
        for (i in temperatureBeanXList.indices) {
            val time = try {
                val date = oldSdf.parse(temperatureBeanXList[i].date)
                if (i != 0) {
                    newSdf.format(date) + " " +
                    resources.getStringArray(R.array.week)[(dayOfWeek + i - 1) % 7]
                } else {
                    newSdf.format(date) + " " +
                    resources.getString(R.string.today)
                }
            } catch (e: ParseException) {
                e.printStackTrace()
                return null
            }

            //            String time = newSdf.format(date);
            val icon = Utility.chooseWeatherIcon(skyconBeanXList[i].value,
                                                 precipitationBeanXList[i].avg, HOURLY_MODE, false)
            // 在有 desc 时优先显示 desc 的内容
            val skyconDesc = if (dailyBean.desc == null || TextUtils.isEmpty(dailyBean.desc[i].value)) {
                Utility.chooseWeatherSkycon(this, skyconBeanXList[i].value,
                                            precipitationBeanXList[i].avg, HOURLY_MODE)
            } else {
                dailyBean.desc[i].value
            }
            Log.d(TAG, "generateList: $skyconDesc")
            val temperature = (stringRoundDouble(temperatureBeanXList[i].max) + "° / "
                               + stringRoundDouble(temperatureBeanXList[i].min) + '°'.toString())
            list.add(SingleWeather(time, icon, skyconDesc, temperature))
        }
        return list
    }

    private fun stopSwipe() {
        progressBar.visibility = View.GONE
    }

    private fun startSwipe() {
        progressBar.visibility = View.VISIBLE
    }

    companion object {
        val KEY_DETAIL_ACTIVITY_WEATHER_LIST = "DETAIL_ACTIVITY_WEATHER_LIST"
    }
}