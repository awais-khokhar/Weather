package top.maweihao.weather.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.startActivity
import top.maweihao.weather.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false)
        //        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        //        if (prefs.getLong("refresh_time_interval", 0) == 0) {
        //            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        //            editor.putLong("refresh_time_interval", 60*60*1000);  //默认刷新间隔分钟数
        //            editor.apply();
        //        }

        startActivity<WeatherActivity>()

        finish()
    }
}
