package top.maweihao.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.DailyWeatherAdapter;
import top.maweihao.weather.bean.SingleWeather;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private List<SingleWeather> weatherList;

    @BindView(R.id.activity_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_recycler_view)
    RecyclerView recyclerView;

    public static final String KEY_DETAIL_ACTIVITY_WEATHER_LIST = "DETAIL_ACTIVITY_WEATHER_LIST";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
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
        this.weatherList = getIntent().getParcelableArrayListExtra(KEY_DETAIL_ACTIVITY_WEATHER_LIST);
        if (weatherList != null) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(weatherList);
            recyclerView.setAdapter(dailyWeatherAdapter);
        }
    }
}
