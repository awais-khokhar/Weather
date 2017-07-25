package top.maweihao.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.AlertAdapter;
import top.maweihao.weather.bean.Alert;

public class AlertActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "AlertActivity";

    ArrayList<Alert> alarmList;

    @BindView(R.id.alert_recycler_view)
    RecyclerView alertView;
    @BindView(R.id.activity_alert_toolbar)
    Toolbar toolbar;

    public static final String KEY_ALERT_ACTIVITY_ALERT_LIST = "ALERT_ACTIVITY_ALERT_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(this);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case -1:
                finish();
        }
    }

    private void init() {
        this.alarmList = getIntent().getParcelableArrayListExtra(KEY_ALERT_ACTIVITY_ALERT_LIST);
        if (alarmList != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AlertActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            alertView.setLayoutManager(linearLayoutManager);
            alertView.setAdapter(new AlertAdapter(alarmList));
        }

    }

    private int chooseAlertIcon(int code) {
        switch (code / 100) {  //code 前两位
            case 1:
                return R.mipmap.ic_warning_typhoon;
            case 2:
                return R.mipmap.ic_warning_rain_storm;
            case 3:
                return R.mipmap.ic_warning_snow_storm;
            case 4:
                return R.mipmap.ic_warning_cold_wave;
            case 5:
                return R.mipmap.ic_warning_gale;
            case 6:
                return R.mipmap.ic_warning_sand_storm;
            case 7:
                return R.mipmap.ic_warning_heat_wave;
            case 8:
                return R.mipmap.ic_warning_drough;
            case 9:
                return R.mipmap.ic_warning_lighting;
            case 10:
                return R.mipmap.ic_warning_hail;
            case 11:
                return R.mipmap.ic_warning_frost;
            case 12:
                return R.mipmap.ic_warning_heavy_fog;
            case 13:
                return R.mipmap.ic_warning_haze;
            case 14:
                return R.mipmap.ic_warning_road_icing;
            case 15:
                return R.mipmap.ic_warn_forest_fire;
            case 16:
                return R.mipmap.ic_warn_thunderstorm;
            default:
                Log.d(TAG, "chooseAlertIcon: code == " + code);
                return R.mipmap.ic_warning_others;
        }
    }

    private int chooseAlertBg(int code) {
        switch (code % 100) {  //code 后两位
            case 1:
                return R.mipmap.ic_warn_blue_bg;
            case 2:
                return R.mipmap.ic_warn_yellow_bg;
            case 3:
                return R.mipmap.ic_warn_orange_bg;
            case 4:
                return R.mipmap.ic_warn_red_bg;
            default:
                Log.d(TAG, "chooseAlertBg: code == " + code);
                return R.mipmap.ic_warn_grey_bg;
        }
    }
}
