package top.maweihao.weather.util.http

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import top.maweihao.weather.BuildConfig
import top.maweihao.weather.util.http.api.BDLocateApi
import top.maweihao.weather.util.http.api.HeWeatherApi
import top.maweihao.weather.util.http.api.WeatherApi
import java.util.concurrent.TimeUnit


object HttpUtils {
    val weatherApi: WeatherApi by lazy {
        val baseUrl = "https://api.caiyunapp.com/v2/"
        getService(baseUrl).create<WeatherApi>(WeatherApi::class.java)
    }

    val baiduLocateApi: BDLocateApi by lazy {
        val baseUrl = "http://api.map.baidu.com/"
        getService(baseUrl).create<BDLocateApi>(BDLocateApi::class.java)
    }

    val heWeatherApi: HeWeatherApi by lazy {
        val baseUrl = "https://free-api.heweather.com/v5/"
        getService(baseUrl).create<HeWeatherApi>(HeWeatherApi::class.java)
    }


    private fun getService(baseUrl: String): Retrofit {

        val client = OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间

                .retryOnConnectionFailure(true)


        val logInterceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            //显示日志
            logInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            logInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        //添加拦截
        if (BuildConfig.DEBUG) {
            client.addInterceptor(logInterceptor)
        }

        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
    }
}