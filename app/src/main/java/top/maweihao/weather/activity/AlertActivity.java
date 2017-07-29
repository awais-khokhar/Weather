package top.maweihao.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.AlertAdapter;
import top.maweihao.weather.bean.Alert;

public class AlertActivity extends AppCompatActivity {

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
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        init();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    private void init() {
        this.alarmList = getIntent().getParcelableArrayListExtra(KEY_ALERT_ACTIVITY_ALERT_LIST);
        if (alarmList != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(AlertActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            alertView.setLayoutManager(linearLayoutManager);
            AlertAdapter alertAdapter = new AlertAdapter(alarmList);
            alertView.setAdapter(alertAdapter);
        }
    }

}
