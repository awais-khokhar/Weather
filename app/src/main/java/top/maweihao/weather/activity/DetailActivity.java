package top.maweihao.weather.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.fastjson.JSON;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.DailyWeatherAdapter;
import top.maweihao.weather.entity.ForecastBean;
import top.maweihao.weather.entity.SingleWeather;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.activity.WeatherActivity.HOURLY_MODE;
import static top.maweihao.weather.util.Utility.stringRoundDouble;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";
    private List<SingleWeather> weatherList;
    private PreferenceConfigContact configContact;

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
        try {
            init();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
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

    private void init() throws ParseException {
        this.weatherList = getIntent().getParcelableArrayListExtra(KEY_DETAIL_ACTIVITY_WEATHER_LIST);
        if (weatherList == null) {
            weatherList = new ArrayList<>();
            configContact = Utility.createSimpleConfig(this).create(PreferenceConfigContact.class);
            String weather = configContact.getWeatherFull();
            ForecastBean forecastBean = JSON.parseObject(weather, ForecastBean.class);
            ForecastBean.ResultBean.DailyBean dailyBean = forecastBean.getResult().getDaily();

            List<ForecastBean.ResultBean.DailyBean.TemperatureBeanX> temperatureBeanXList = dailyBean.getTemperature();
            List<ForecastBean.ResultBean.DailyBean.SkyconBeanX> skyconBeanXList = dailyBean.getSkycon();
            List<ForecastBean.ResultBean.DailyBean.PrecipitationBeanX> precipitationBeanXList = dailyBean.getPrecipitation();

            SimpleDateFormat oldsdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
            SimpleDateFormat newsdf = new SimpleDateFormat("MM/dd", Locale.CHINA);
            for (int i = 0; i < temperatureBeanXList.size(); i++) {
                Date date = oldsdf.parse(temperatureBeanXList.get(i).getDate());
                String time = newsdf.format(date);
                int icon = Utility.chooseWeatherIcon(skyconBeanXList.get(i).getValue(),
                        precipitationBeanXList.get(i).getAvg(), HOURLY_MODE, false);
                String skyconDesc;
                // 在有 desc 时优先显示 desc 的内容
                if (dailyBean.getDesc() == null) {
                    skyconDesc = Utility.chooseWeatherSkycon(this, skyconBeanXList.get(i).getValue(),
                            precipitationBeanXList.get(i).getAvg(), HOURLY_MODE);
                } else {
                    skyconDesc = dailyBean.getDesc().get(i).getValue();
                }
                String temperature = stringRoundDouble(temperatureBeanXList.get(i).getMax()) + "° / "
                        + stringRoundDouble(temperatureBeanXList.get(i).getMin()) + '°';
                weatherList.add(new SingleWeather(time, icon, skyconDesc, temperature));
            }
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        DailyWeatherAdapter dailyWeatherAdapter = new DailyWeatherAdapter(weatherList);
        recyclerView.setAdapter(dailyWeatherAdapter);
    }
}
