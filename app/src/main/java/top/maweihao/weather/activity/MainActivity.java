package top.maweihao.weather.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;

import top.maweihao.weather.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false);
//        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
//        if (prefs.getLong("refresh_time_interval", 0) == 0) {
//            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
//            editor.putLong("refresh_time_interval", 60*60*1000);  //默认刷新间隔分钟数
//            editor.apply();
//        }

        Intent intent = new Intent(this, WeatherActivity.class);
        startActivity(intent);
        finish();
    }
}
