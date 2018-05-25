package top.maweihao.weather.adapter

import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.style.StyleSpan
import android.widget.ImageView
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import top.maweihao.weather.R
import top.maweihao.weather.entity.SingleWeather

class DailyWeatherAdapter(weatherList: List<SingleWeather>) : BaseQuickAdapter<SingleWeather, BaseViewHolder>(R.layout.item_per_day_list, weatherList) {

    override fun convert(helper: BaseViewHolder, item: SingleWeather) {
        helper.setText(R.id.per_day_recycler_time_tv, item.time)
        helper.setText(R.id.per_day_recycler_skycon_tv, item.skyconDesc)

        val skyconImage = helper.getView<ImageView>(R.id.per_day_recycler_skycon_iv)
        with(skyconImage) {
            setImageResource(item.skyconId)
            contentDescription = item.skyconDesc
        }

        val temperature = item.temperature
        val index = temperature.indexOf('/')
        if (index != -1) {
            val ss = SpannableString(temperature)
            ss.setSpan(StyleSpan(Typeface.BOLD), 0, index, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
            helper.setText(R.id.per_day_recycler_temperature_tv, ss)
        } else {
            helper.setText(R.id.per_day_recycler_temperature_tv, item.temperature)
            LogUtils.e("onBindViewHolder: temperature string has no '/' $temperature")
        }
    }
}