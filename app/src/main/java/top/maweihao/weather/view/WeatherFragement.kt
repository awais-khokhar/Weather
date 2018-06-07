package top.maweihao.weather.view

import android.os.Bundle
import android.view.View
import top.maweihao.weather.R

class WeatherFragement : BaseLazyFragment(){

    override fun getLayoutId(): Int = R.layout.fragment_weather

    override fun initViewProperty(view: View, savedInstanceState: Bundle?) {

    }

    override fun lazyLoad() {

    }
}