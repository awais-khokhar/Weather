package top.maweihao.weather.view;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tencent.bugly.crashreport.CrashReport;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import top.maweihao.weather.R;
import top.maweihao.weather.adapter.DailyWeatherAdapter;
import top.maweihao.weather.adapter.HourlyWeatherAdapter;
import top.maweihao.weather.android_view.SemiCircleView;
import top.maweihao.weather.android_view.SunTimeView;
import top.maweihao.weather.android_view.dynamicweather.DynamicWeatherView;
import top.maweihao.weather.contract.NewWeatherActivityContract;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.entity.Alert;
import top.maweihao.weather.entity.SingleWeather;
import top.maweihao.weather.entity.dao.MLocation;
import top.maweihao.weather.entity.dao.NewWeather;
import top.maweihao.weather.presenter.NewWeatherPresenter;
import top.maweihao.weather.service.PushService;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.R.id.skycon_text;
import static top.maweihao.weather.R.id.temperature_text;
import static top.maweihao.weather.util.Constants.ChooseCode;
import static top.maweihao.weather.util.Constants.ChoosePositionActivityRequestCode;
import static top.maweihao.weather.util.Constants.SettingActivityRequestCode;
import static top.maweihao.weather.util.Constants.SettingCode;
import static top.maweihao.weather.util.Utility.chooseWeatherSkycon;
import static top.maweihao.weather.util.Utility.stringRoundDouble;

public class WeatherActivity extends BaseActivity implements
        View.OnClickListener, NewWeatherActivityContract.NewView<NewWeatherActivityContract.NewPresenter> {

    private static final String TAG = WeatherActivity.class.getSimpleName();

    static final int HANDLE_POSITION       = 0;
    static final int HANDLE_TOAST          = 1;
    static final int HANDLE_EXACT_LOCATION = 2;

    public static final int MINUTELY_MODE = 4;
    public static final int HOURLY_MODE   = 5;

    @BindView(R.id.toolbar)
    Toolbar            toolbar;
    @BindView(temperature_text)
    TextView           temperatureText;
    @BindView(skycon_text)
    TextView           skyconText;
    @BindView(R.id.rain_info_tv)
    TextView           rainInfoTv;
    @BindView(R.id.locate_mode_image)
    ImageView          locateModeImage;
    @BindView(R.id.location_tv)
    TextView           locateMode;
    @BindView(R.id.last_update_time)
    TextView           lastUpdateTime;
    @BindView(R.id.now_card_desc)
    TextView           todayDesc;
    @BindView(R.id.dynamicWeatherView)
    DynamicWeatherView dynamicWeatherView;
    @BindView(R.id.aqi_image)
    ImageView          aqiImage;
    @BindView(R.id.uv_name)
    TextView           uvName;
    @BindView(R.id.uv)
    TextView           uv_text;
    @BindView(R.id.carwash)
    TextView           carWashing_text;
    @BindView(R.id.humidity)
    TextView           hum_text;
    @BindView(R.id.dressing)
    TextView           dressing_text;
    @BindView(R.id.wind_direction_tv)
    TextView           windDirectionTv;
    @BindView(R.id.wind_level_tv)
    TextView           windLevelTv;
    @BindView(R.id.AQI_Circle)
    SemiCircleView     AQICircle;
    @BindView(R.id.PM_Circle)
    SemiCircleView     PMCircle;
    @BindView(R.id.stv)
    SunTimeView        sunTimeView;
    @BindView(R.id.sunrise)
    TextView           sunrise_text;
    @BindView(R.id.sunset)
    TextView           sunset_text;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.more_days_weather)
    ImageButton        imageButton;
    @BindView(R.id.hourly_weather_rv)
    RecyclerView       hourlyRecyclerView;
    @BindView(R.id.daily_weather_rv)
    RecyclerView       dailyRecyclerView;
    @BindView(R.id.weather_alert_icon)
    ImageView          alertImage;
    @BindView(R.id.view_root)
    CoordinatorLayout  viewRoot;

    public String countyName = null;
    public String locationDetail;

    private MessageHandler                          handler;
    private NewWeatherActivityContract.NewPresenter NewPresenter;
    private PreferenceConfigContact                 configContact;

    //24h hourlyRecyclerView adapter
    private HourlyWeatherAdapter hourWeatherAdapter;
    //5day hourlyRecyclerView adapter
    private DailyWeatherAdapter  dailyWeatherAdapter;
    private ArrayList<Alert>     alertArrayList;

    @Override
    protected int setContentView() {
        return R.layout.activity_weather;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        initView();
        doDebugThings();
        configContact = Utility.createSimpleConfig(this).create(PreferenceConfigContact.class);

        handler = new MessageHandler(this);
        NewPresenter = new NewWeatherPresenter(WeatherActivity.this, this);
        NewPresenter.subscribe();

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                NewPresenter.locate();
            }
        }));

        alertImage.setOnClickListener(this);
        imageButton.setOnClickListener(this);
    }


    private void doDebugThings() {
        imageButton.postDelayed(new Runnable() {
            @Override
            public void run() {
                CrashReport.testJavaCrash();
            }
        }, 1000 * 10);
    }

    private void initView() {
        LinearLayoutManager hourlyManager = new LinearLayoutManager(WeatherActivity.this);
        hourlyManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        hourlyRecyclerView.setLayoutManager(hourlyManager);

        LinearLayoutManager dailyManager = new LinearLayoutManager(WeatherActivity.this);
        dailyManager.setOrientation(LinearLayoutManager.VERTICAL);
        dailyRecyclerView.setLayoutManager(dailyManager);

        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "onResume");
        super.onResume();
        if (NewPresenter != null) {
            NewPresenter.onResume();
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dynamicWeatherView.onResume();
            }
        }, 150);
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "onPause");
        super.onPause();
        if (NewPresenter != null) {
            NewPresenter.onPause();
        }
        dynamicWeatherView.onPause();
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "onStart");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "onStop");
        super.onStop();
        if (NewPresenter != null) {
            NewPresenter.onStop();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
        dynamicWeatherView.onDestroy();
        if (NewPresenter != null) {
            NewPresenter.onDestroy();
            NewPresenter.unSubscribe();
            NewPresenter = null;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case -1:
                finish();
                break;
            case R.id.weather_alert_icon:
                if (alertArrayList != null && alertArrayList.size() > 0) {
                    Intent intent = new Intent(WeatherActivity.this, AlertActivity.class);
                    intent.putParcelableArrayListExtra(
                            AlertActivity.KEY_ALERT_ACTIVITY_ALERT_LIST, alertArrayList);
                    startActivity(intent);
                }
                break;
            case R.id.more_days_weather:
                Intent intent = new Intent(WeatherActivity.this, DetailActivity.class);
                startActivity(intent);
        }
    }

    private void setRainInfo(final String now, final String today) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                rainInfoTv.setText(now);
                todayDesc.setText(today);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_position:
                Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                startActivityForResult(intent, ChoosePositionActivityRequestCode);
                break;
            case R.id.start_service:
                // debug only
                Intent startIntent = new Intent(WeatherActivity.this, PushService.class);
                startService(startIntent);
                break;
            case R.id.setting:
                Intent intent1 = new Intent(WeatherActivity.this, SettingActivity.class);
                startActivityForResult(intent1, SettingActivityRequestCode);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SettingActivityRequestCode:
                if (resultCode == SettingCode) {
                    boolean autoLocate = data.getBooleanExtra("autoLocate", false);
                    Log.d(TAG, "onActivityResult: SettingActivity autoLocate=" + autoLocate);
                    if (autoLocate) {
                        NewPresenter.locate();
                    }

                } else if (resultCode == ChooseCode) {
                    onActivityResult(ChoosePositionActivityRequestCode, ChooseCode, data);
                }
                break;
            case ChoosePositionActivityRequestCode:
                if (resultCode == ChooseCode) {
                    configContact.applyAutoLocate(false);

                    Bundle bundle = data.getExtras();
                    if (bundle != null) {
                        MLocation location = bundle.getParcelable("location");
                        String desc = TextUtils.isEmpty(location.getCity()) ? location.getCounty()
                                : location.getCity() + "" + location.getCounty();
                        Log.d(TAG, "onActivityResult: desc = " + desc);
                        NewPresenter.refreshChosenWeather(desc);
                    } else {
                        Log.d(TAG, "onActivityResult: cannot get parcelable location");
                    }
                }
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case Constants.newRequestLocationCode:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    NewPresenter.locate();
                } else {
                    NewPresenter.onPermissionDenied();
                }
                break;
            default:
                Log.e(TAG, "onRequestPermissionsResult: undefined request code" + requestCode);
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void setPresenter(NewWeatherActivityContract.NewPresenter presenter) {
        this.NewPresenter = presenter;
    }

    @Override
    public void showWeather(NewWeather weather) {
        NewWeather.ResultBean.DailyBean dailyBean = weather.getResult().getDaily();
        NewWeather.ResultBean.HourlyBean hourlyBean = weather.getResult().getHourly();
        NewWeather.ResultBean.MinutelyBean minutelyBean = weather.getResult().getMinutely();
        Log.d(TAG, "showWeather: days: " + dailyBean.getAqi().size());

        showDailyWeather(dailyBean);
        showHourlyWeather(hourlyBean);
        setRainInfo(minutelyBean.getDescription(), hourlyBean.getDescription());
        showCurrentWeather(dailyBean, hourlyBean, weather.getResult().getAlert());
        setUpdateTime(weather.getServer_time() * 1000);
    }

    private void showCurrentWeather(final NewWeather.ResultBean.DailyBean dailyBean,
                                    NewWeather.ResultBean.HourlyBean hourlyBean,
                                    NewWeather.ResultBean.AlertBean alertBean) {
        final String temperature =
                Utility.stringRoundDouble(hourlyBean.getTemperature().get(0).getValue());
        final String skycon = hourlyBean.getSkycon().get(0).getValue();
        double humidity = hourlyBean.getHumidity().get(0).getValue();
        final double PM25 = hourlyBean.getPm25().get(0).getValue();
        double intensity = hourlyBean.getPrecipitation().get(0).getValue();
        final double aqi = hourlyBean.getAqi().get(0).getValue();
        final boolean shouldShowAlert;
        // 是否显示天气预警图标
        if (alertBean.getContent().size() > 0) {
            Log.d(TAG, "showCurrentWeather: alert size=" + alertBean.getContent().size());
            alertArrayList = new ArrayList<>();
            for (NewWeather.ResultBean.AlertBean.ContentBean contentBean : alertBean.getContent()) {
                alertArrayList.add(new Alert(contentBean.getStatus(),
                        Integer.parseInt(contentBean.getCode()),
                        contentBean.getDescription(), contentBean.getAlertId(),
                        contentBean.getCity() + contentBean.getCounty(),
                        contentBean.getTitle()));
            }
            shouldShowAlert = true;
        } else {
            alertArrayList = null;
            shouldShowAlert = false;
        }

        final String windDirection = Utility.getWindDirection(this,
                hourlyBean.getWind().get(0).getDirection());
        final String windLevel = Utility.getWindLevel(this, hourlyBean.getWind().get(0).getSpeed());
        final String sunRise = dailyBean.getAstro().get(0).getSunrise().getTime();
        final String sunSet = dailyBean.getAstro().get(0).getSunset().getTime();
        final double hum = humidity * 100;
        final String weatherString =
                chooseWeatherSkycon(getApplicationContext(), skycon, intensity, MINUTELY_MODE);

        handler.post(new Runnable() {
            @Override
            public void run() {
                if (shouldShowAlert) {
                    alertImage.setVisibility(View.VISIBLE);
                } else {
                    alertImage.setVisibility(View.GONE);
                }
                windDirectionTv.setText(windDirection);
                windLevelTv.setText(windLevel);
                PMCircle.setValue((int) PM25);
                temperatureText.setText(temperature);
                AQICircle.setValue((int) aqi);
                hum_text.setText(String.valueOf(hum).substring(0, 2) + "%");
                skyconText.setText(weatherString);
                dynamicWeatherView.setDrawerType(Utility.chooseBgImage(skycon));
                sunrise_text.setText(sunRise);
                sunset_text.setText(sunSet);
                sunTimeView.setTime(sunRise, sunSet);
                uv_text.setText(dailyBean.getUltraviolet().get(0).getDesc());
                carWashing_text.setText(dailyBean.getCarWashing().get(0).getDesc());
                dressing_text.setText(dailyBean.getDressing().get(0).getDesc());
                Log.d(TAG, "showWeather: current weather shown");
            }
        });
    }

    private void showDailyWeather(NewWeather.ResultBean.DailyBean dailyBean) {
        int length = dailyBean.getSkycon().size();
        length = (length >= 5) ? 5 : length;
        Log.d(TAG, "showDailyWeather: " + length);
        final ArrayList<SingleWeather> singleWeathers = new ArrayList<>(length);
        int dayOfWeek = Utility.getDayOfWeek();
        SimpleDateFormat oldSDF = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        SimpleDateFormat newSDF = new SimpleDateFormat("MM/dd", Locale.getDefault());
        for (int i = 0; i < length; i++) {
            String time = "";
            try {
                Date date = oldSDF.parse(dailyBean.getSkycon().get(i).getDate());
                if (i != 0) {
                    time = newSDF.format(date) + " " +
                            getResources().getStringArray(R.array.week)[(dayOfWeek + i - 1) % 7];
                } else {
                    time = newSDF.format(date) + " " +
                            getResources().getString(R.string.today);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                Log.d(TAG, "showDailyWeather: parse time format failed");
            }
            int icon = Utility.chooseWeatherIcon(dailyBean.getSkycon().get(i).getValue(),
                    dailyBean.getPrecipitation().get(i).getAvg(), HOURLY_MODE, false);
            String skyconDesc;
            // 在有 desc 时优先显示 desc 的内容
            if (dailyBean.getDesc() == null || TextUtils.isEmpty(dailyBean.getDesc().get(i).getValue())) {
                skyconDesc = Utility.chooseWeatherSkycon(this,
                        dailyBean.getSkycon().get(i).getValue(),
                        dailyBean.getPrecipitation().get(i).getAvg(), HOURLY_MODE);
            } else {
                skyconDesc = dailyBean.getDesc().get(i).getValue();
            }
            String temperature = Utility.stringRoundDouble(dailyBean.getTemperature().get(i).getMax())
                    + "°/"
                    + Utility.stringRoundDouble(dailyBean.getTemperature().get(i).getMin()) + '°';
            singleWeathers.add(new SingleWeather(time, icon, skyconDesc, temperature));
        }
        handler.post(new Runnable() {
            @Override
            public void run() {
                refreshDailyList(singleWeathers);
            }
        });
    }

    private void showHourlyWeather(NewWeather.ResultBean.HourlyBean hourlyBean) {
        int length = hourlyBean.getSkycon().size();
        length = (length >= 24) ? 24 : length;
//        Log.d("setHourlyWeatherChart: total " + length + " hour");
        final ArrayList<SingleWeather> singleWeathers = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            String time = hourlyBean.getSkycon().get(i).getDatetime().substring(11, 16);
            int icon = Utility.chooseWeatherIcon(hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE, true);
            String skyconDesc = Utility.chooseWeatherSkycon(this, hourlyBean.getSkycon().get(i).getValue(),
                    hourlyBean.getPrecipitation().get(i).getValue(), HOURLY_MODE);
            String temperature = stringRoundDouble(hourlyBean.getTemperature().get(i).getValue()) + '°';
            singleWeathers.add(new SingleWeather(time, icon, skyconDesc, temperature));
        }
        singleWeathers.get(0).setTime(getResources().getString(R.string.now));
        handler.post(new Runnable() {
            @Override
            public void run() {
                refreshHourlyList(singleWeathers);
            }
        });
    }

    private void refreshHourlyList(List<SingleWeather> list) {
        if (hourWeatherAdapter == null) {
            hourWeatherAdapter = new HourlyWeatherAdapter(list);
            hourlyRecyclerView.setAdapter(hourWeatherAdapter);
        } else {
            hourWeatherAdapter.setWeatherList(list);
        }
    }

    private void refreshDailyList(List<SingleWeather> list) {
        if (dailyWeatherAdapter == null) {
            dailyWeatherAdapter = new DailyWeatherAdapter(list);
            dailyRecyclerView.setAdapter(dailyWeatherAdapter);
        } else {
            dailyWeatherAdapter.setWeatherList(list);
        }
    }

    @Override
    public void showLocation(MLocation location) {
        if (location == null) {
            Log.e(TAG, "showLocation: location = null");
            return;
        }
        String coarseLocation = location.getCoarseLocation();
        String detailLocation = location.getFineLocation();
        Log.d(TAG, "showLocation: " + coarseLocation + detailLocation);
        switch (location.getLocateType()) {
            case MLocation.TYPE_CHOOSE:
                setLoc(coarseLocation, coarseLocation, false);
                break;
            case MLocation.TYPE_IP:
                setLoc(coarseLocation, Utility.getIP(this), false);
                break;
            case MLocation.TYPE_BAIDU_GPS:
            case MLocation.TYPE_BAIDU_NETWORK:
            case MLocation.TYPE_BAIDU_UNKNOWN:
            case MLocation.TYPE_LOCATION_MANAGER:
                setLoc(coarseLocation, detailLocation, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void setUpdateTime(final long timeInMills) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (timeInMills == 0) {
                    lastUpdateTime.setText(Utility.getTime(WeatherActivity.this));
                } else {
                    String time = getResources().getString(R.string.updated_on,
                            Utility.getTime(WeatherActivity.this, timeInMills));
                    lastUpdateTime.setText(time);
                }
            }
        });
    }

    @Override
    public void showNetworkError() {
        Snackbar.make(viewRoot, R.string.access_network_failed, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_to_settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent().setAction(Settings.ACTION_SETTINGS));
                    }
                }).show();
    }

    @Override
    public void showError(String error, boolean showOkButton) {
        if (TextUtils.isEmpty(error)) {
            error = getResources().getString(R.string.error_happens);
        }
        Snackbar snackbar = Snackbar.make(viewRoot, error, Snackbar.LENGTH_LONG);
        if (showOkButton) {
            snackbar.setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });
        }
        snackbar.show();
    }

    @Override
    public void showPermissionError() {
        // TODO: 02/12/2017 deal with the not show
        Snackbar.make(viewRoot, R.string.permission_denied, Snackbar.LENGTH_LONG)
                .setAction(R.string.grant_permission, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

    @Override
    public void showIpLocateMessage() {
        Snackbar.make(viewRoot, R.string.ip_method_locate, Snackbar.LENGTH_LONG)
                .setAction(R.string.donnot_show, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO: 02/12/2017
                    }
                });
    }

    @Override
    public void showLocateError() {
        setRefreshingState(false);
//        viewRoot.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Snackbar.make(viewRoot, R.string.locate_failed, Snackbar.LENGTH_LONG)
//                        .setAction(R.string.go_to_settings, new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                startActivity(new Intent().setAction(Settings.ACTION_SETTINGS));
//                            }
//                        });
//            }
//        }, 2000);
        Snackbar.make(viewRoot, R.string.locate_failed, Snackbar.LENGTH_LONG)
                .setAction(R.string.go_to_settings, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent().setAction(Settings.ACTION_SETTINGS));
                    }
                });
    }

    @Override
    public void setRefreshingState(final boolean refresh) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (refresh) {
                    if (!swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(true);
                    }
                } else {
                    if (swipeRefreshLayout.isRefreshing()) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }
            }
        });
    }

    @Override
    public void askForChoosePosition() {
        Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
        startActivityForResult(intent, ChoosePositionActivityRequestCode);
    }

    private void setLoc(final String coarse, final String detail, final boolean loc) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                locateModeImage.setVisibility(View.VISIBLE);
                if (loc) {
                    locateModeImage.setImageResource(R.drawable.ic_location_on_black_24dp);
                } else {
                    locateModeImage.setImageResource(R.drawable.ic_location_off_black_24dp);
                }
                if (!TextUtils.isEmpty(coarse)) {
                    toolbar.setTitle(coarse);
                }
                if (!TextUtils.isEmpty(detail)) {
                    locateMode.setText(detail);
                }
            }
        });
    }

    private static class MessageHandler extends Handler {
        WeakReference<WeatherActivity> activityWeakReference;

        MessageHandler(WeatherActivity activity) {
            activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            WeatherActivity activity = activityWeakReference.get();
            if (activity != null) {
                switch (msg.what) {
                    case HANDLE_TOAST:
                        Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_SHORT).show();
                        break;
                    case HANDLE_EXACT_LOCATION:
                        activity.locationDetail = (String) msg.obj;
                        activity.locateMode.setText((String) msg.obj);
                        break;
                }
            }
        }
    }

}
