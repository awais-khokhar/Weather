package top.maweihao.weather.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import top.maweihao.weather.R
import top.maweihao.weather.entity.SingleWeather

class HourlyWeatherAdapter(weatherList: List<SingleWeather>) : BaseQuickAdapter<SingleWeather, BaseViewHolder>(R.layout.item_few_hour_list, weatherList) {
    override fun convert(helper: BaseViewHolder, item: SingleWeather) {
        helper.setText(R.id.few_hour_time_tv, item.time)
        helper.setImageResource(R.id.few_hour_skycon_iv, item.skyconId)
        helper.setText(R.id.few_hour_skycon_tv, item.skyconDesc)
        helper.setText(R.id.few_hour_tem_tv, item.temperature)
    }
}