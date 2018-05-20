package top.maweihao.weather.view;

import android.os.Bundle;
import android.support.annotation.UiThread;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.DailyWeatherAdapter;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.entity.SingleWeather;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Utility.HOURLY_MODE;
import static top.maweihao.weather.util.Utility.stringRoundDouble;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_DETAIL_ACTIVITY_WEATHER_LIST = "DETAIL_ACTIVITY_WEATHER_LIST";
    private static final String TAG = DetailActivity.class.getSimpleName();
    private WeatherData model;
    DailyWeatherAdapter adapter;

    @BindView(R.id.activity_detail_toolbar)
    Toolbar toolbar;
    @BindView(R.id.detail_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.view_root)
    CoordinatorLayout viewRoot;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        fetchData();
    }

    private void initView() {
        ButterKnife.bind(this);
        model = WeatherRepository.getInstance(getApplicationContext());
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
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

    private void fetchData() {
        startSwipe();
        List<SingleWeather> weatherList =
                getIntent().getParcelableArrayListExtra(KEY_DETAIL_ACTIVITY_WEATHER_LIST);
        if (weatherList == null) {
            model.getWeatherCached()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<NewWeather>() {
                        @Override
                        public void accept(NewWeather weather) throws Exception {
                            stopSwipe();
                            List<SingleWeather> list = generateList(weather);
                            if (list == null) {
                                showError();
                            } else if (adapter == null) {
                                adapter = new DailyWeatherAdapter(list);
                                recyclerView.setAdapter(adapter);
                            } else {
                                adapter.setWeatherList(list);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            stopSwipe();
                            Log.e(TAG, "fetchData: get weather cached failed" + throwable);
                            showError();
                        }
                    });
        }
    }

    @UiThread
    private void showError() {
        stopSwipe();
        Snackbar.make(viewRoot, R.string.error_happens, Snackbar.LENGTH_LONG)
                .setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {}
                }).show();
    }

    private List<SingleWeather> generateList(NewWeather weather) {
        List<SingleWeather> list = new ArrayList<>();
        NewWeather.ResultBean.DailyBean dailyBean = weather.getResult().getDaily();

        List<NewWeather.ResultBean.DailyBean.TemperatureBeanX> temperatureBeanXList =
                dailyBean.getTemperature();
        List<NewWeather.ResultBean.DailyBean.SkyconBeanX> skyconBeanXList = dailyBean.getSkycon();
        List<NewWeather.ResultBean.DailyBean.PrecipitationBeanX> precipitationBeanXList =
                dailyBean.getPrecipitation();
        int dayOfWeek = Utility.getDayOfWeek();
        SimpleDateFormat oldSdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat newSdf = new SimpleDateFormat("MM/dd", Locale.CHINA);
        for (int i = 0; i < temperatureBeanXList.size(); i++) {
            String time;
            try {
                Date date = oldSdf.parse(temperatureBeanXList.get(i).getDate());
                if (i != 0) {
                    time = newSdf.format(date) + " " +
                            getResources().getStringArray(R.array.week)[(dayOfWeek + i - 1) % 7];
                } else {
                    time = newSdf.format(date) + " " +
                            getResources().getString(R.string.today);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
//            String time = newSdf.format(date);
            int icon = Utility.chooseWeatherIcon(skyconBeanXList.get(i).getValue(),
                    precipitationBeanXList.get(i).getAvg(),HOURLY_MODE, false);
            String skyconDesc;
            // 在有 desc 时优先显示 desc 的内容
            if (dailyBean.getDesc() == null || TextUtils.isEmpty(dailyBean.getDesc().get(i).getValue())) {
                skyconDesc = Utility.chooseWeatherSkycon(this, skyconBeanXList.get(i).getValue(),
                        precipitationBeanXList.get(i).getAvg(), HOURLY_MODE);
            } else {
                skyconDesc = dailyBean.getDesc().get(i).getValue();
            }
            Log.d(TAG, "generateList: " + skyconDesc);
            String temperature = stringRoundDouble(temperatureBeanXList.get(i).getMax()) + "° / "
                    + stringRoundDouble(temperatureBeanXList.get(i).getMin()) + '°';
            list.add(new SingleWeather(time, icon, skyconDesc, temperature));
        }
        return list;
    }

    private void stopSwipe() {
        progressBar.setVisibility(View.GONE);
    }

    private void startSwipe() {
        progressBar.setVisibility(View.VISIBLE);
    }
}
