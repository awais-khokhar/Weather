package top.maweihao.weather.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.bean.ForecastBean;
import top.maweihao.weather.bean.SingleWeather;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.presenter.WeatherActivityPresenter;
import top.maweihao.weather.service.SyncService;
import top.maweihao.weather.util.Constants;
import top.maweihao.weather.util.SimplePermissionUtils;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.view.SemiCircleView;
import top.maweihao.weather.view.SunTimeView;
import top.maweihao.weather.view.dynamicweather.DynamicWeatherView;
import top.maweihao.weather.view.perDayWeatherView;

import static top.maweihao.weather.R.id.skycon_text;
import static top.maweihao.weather.R.id.temperature_text;
import static top.maweihao.weather.util.Constants.ChooseCode;
import static top.maweihao.weather.util.Constants.ChoosePositionActivityRequestCode;
import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Constants.SettingActivityRequestCode;
import static top.maweihao.weather.util.Constants.SettingCode;
import static top.maweihao.weather.util.Constants.isSetResultIntent;
import static top.maweihao.weather.util.Utility.chooseWeatherIcon;
import static top.maweihao.weather.util.Utility.chooseWeatherSkycon;

public class WeatherActivity extends AppCompatActivity implements WeatherActivityContract.View {

    static final String TAG = "WeatherActivity";

    public static final int MINUTELY_MODE = 4;
    public static final int HOURLY_MODE = 5;

    static final int HANDLE_POSITION = 0;
    static final int HANDLE_TOAST = 1;
//    static final int HANDLE_SWIPE_BEGIN = 2;
//    static final int HANDLE_SWIPE_STOP = 3;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(temperature_text)
    TextView temperatureText;
    @BindView(skycon_text)
    TextView skyconText;
    @BindView(R.id.rain_info_tv)
    TextView rainInfoTv;
    @BindView(R.id.lacate_mode_image)
    ImageView locateModeImage;
    @BindView(R.id.locate_mode)
    TextView locateMode;
    @BindView(R.id.last_update_time)
    TextView lastUpdateTime;
    @BindView(R.id.toolbar_LinearLayout)
    LinearLayout toolBarLinearLayout;
    @BindView(R.id.dynamicWeatherView)
    DynamicWeatherView dynamicWeatherView;
    @BindView(R.id.daily_weather_0)
    perDayWeatherView dailyWeather0;
    @BindView(R.id.daily_weather_1)
    perDayWeatherView dailyWeather1;
    @BindView(R.id.daily_weather_2)
    perDayWeatherView dailyWeather2;
    @BindView(R.id.daily_weather_3)
    perDayWeatherView dailyWeather3;
    @BindView(R.id.daily_weather_4)
    perDayWeatherView dailyWeather4;
    @BindView(R.id.aqi_image)
    ImageView aqiImage;
    @BindView(R.id.uv_name)
    TextView uvName;
    @BindView(R.id.uv)
    TextView uv_text;
    @BindView(R.id.carwash)
    TextView carWashing_text;
    @BindView(R.id.humidity)
    TextView hum_text;
    @BindView(R.id.dressing)
    TextView dressing_text;
    @BindView(R.id.wind_direction_tv)
    TextView windDirectionTv;
    @BindView(R.id.wind_level_tv)
    TextView windLevelTv;
    @BindView(R.id.AQI_Circle)
    SemiCircleView AQICircle;
    @BindView(R.id.PM_Circle)
    SemiCircleView PMCircle;
    @BindView(R.id.stv)
    SunTimeView sunTimeView;
    @BindView(R.id.sunrise)
    TextView sunrise_text;
    @BindView(R.id.sunset)
    TextView sunset_text;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.head_layout)
    ConstraintLayout headLayout;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @BindView(R.id.navigation_bar_view)
    View navigationBarView;
    @BindView(R.id.hourly_weather_rv)
    RecyclerView recyclerView;

    @Constants.CollapsingToolbarLayoutState
    private int state;

    private boolean isDone = false;
    public String countyName = null;

    private perDayWeatherView[] day = new perDayWeatherView[5];

    private MessageHandler handler; //消息队列
    private WeatherActivityContract.Presenter presenter;

    private PreferenceConfigContact configContact;

    //24h recyclerView adapter
    HourWeatherAdapter hourWeatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        configContact = Utility.createSimpleConfig(this).create(PreferenceConfigContact.class);

        int statusHeight = Utility.getStatusBarHeight(this);
        int navigationBarHeight = Utility.getNavigationBarHeight(this);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {   //not necessary
        CollapsingToolbarLayout.LayoutParams lp = (CollapsingToolbarLayout.LayoutParams) toolbar.getLayoutParams();
        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) headLayout.getLayoutParams();
        LinearLayout.LayoutParams lp3 = (LinearLayout.LayoutParams) navigationBarView.getLayoutParams();
        lp.topMargin = lp2.topMargin = statusHeight;
        lp3.topMargin = navigationBarHeight;
        toolbar.setLayoutParams(lp);
        headLayout.setLayoutParams(lp2);
        navigationBarView.setLayoutParams(lp3);
//        }
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        handler = new MessageHandler(this);
        presenter = new WeatherActivityPresenter(this, this);

//        未来五天的天气
        day[0] = dailyWeather0;
        day[1] = dailyWeather1;
        day[2] = dailyWeather2;
        day[3] = dailyWeather3;
        day[4] = dailyWeather4;

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setDistanceToTriggerSync(200);
        swipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                presenter.refreshWeather(true, countyName);
            }
        }));

        appBarLayout.addOnOffsetChangedListener(new AppBarOnOffsetChanged());
    }


    @Override
    protected void onResume() {
        super.onResume();
        isItemSelected = false;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dynamicWeatherView.onResume();
            }
        }, 150);

    }

    @Override
    protected void onPause() {
        super.onPause();
        dynamicWeatherView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isSetResultIntent)
            permission();
        else
            isSetResultIntent = false;

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.stopBdLocation();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dynamicWeatherView.onDestroy();
        presenter.destroy();
        presenter = null;
    }

//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.i(TAG, "dispatchTouchEvent");
//        int action = ev.getAction();
////        switch (action) {
//////            case MotionEvent.ACTION_DOWN://按下
//////                Log.i(TAG, "ACTION_DOWN");
//////                dynamicWeatherView.onPause();
//////                break;
////            case MotionEvent.ACTION_MOVE://移动
////                Log.i(TAG, "ACTION_MOVE");
////                dynamicWeatherView.onPause();
////                break;
////            case MotionEvent.ACTION_UP://松开
////                Log.i(TAG, "ACTION_UP");
////
////                dynamicWeatherView.onResume();
////                break;
////        }
//        return super.dispatchTouchEvent(ev);
////        return true;
//    }


    @Override
    public void setRainInfo(final String str) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                rainInfoTv.setText(str);
            }
        });
    }

    /**
     * 设置显示更新时间
     */
    @Override
    public void setLastUpdateTime(final long time) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (time == 0)
                    lastUpdateTime.setText(Utility.getTime(getApplicationContext()));
                else
                    lastUpdateTime.setText(Utility.getTime(WeatherActivity.this, time));
            }
        });
    }

    /**
     * Toast消息
     *
     * @param msg 信息
     */
    @Override
    public void showToastMessage(String msg) {
        Message message = new Message();
        message.what = HANDLE_TOAST;
        message.obj = msg;
        handler.sendMessage(message);
    }

    /**
     * 设置城市
     *
     * @param countyStr 城市名
     */
    @Override
    public void setCounty(String countyStr) {
        Message message = handler.obtainMessage();
        message.what = HANDLE_POSITION;
        message.obj = countyStr;
        handler.sendMessage(message);
    }

    /**
     * CollapsingToolbarLayout滑动改变监听
     */
    private class AppBarOnOffsetChanged implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (verticalOffset == 0) {
                if (state != Constants.CollapsingToolbarLayoutState.EXPANDED) {
                    state = Constants.CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    toolbarLayout.setTitle(null);
                    toolBarLinearLayout.setVisibility(View.VISIBLE);

                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (state != Constants.CollapsingToolbarLayoutState.COLLAPSED) {
                    toolbarLayout.setTitle(countyName);//设置title
                    toolBarLinearLayout.setVisibility(View.INVISIBLE);
                    state = Constants.CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                }
            } else {
                if (state != Constants.CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (state == Constants.CollapsingToolbarLayoutState.COLLAPSED) {
                        toolbarLayout.setTitle(null);
                        if (toolBarLinearLayout.getVisibility() == View.INVISIBLE)
                            toolBarLinearLayout.setVisibility(View.VISIBLE);
                    }
                    state = Constants.CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间

                }
            }
        }
    }

    /**
     * 创建"弱引用"的Handler,而不是强引用
     * 避免内存泄漏
     */
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
                    case HANDLE_POSITION:
                        if (msg.obj instanceof String) {
                            activity.countyName = (String) msg.obj;
                            activity.locateMode.setText((String) msg.obj);

                        } else {
                            Log.e(TAG, "handleMessage: HANDLE_POSITION obj == " + msg.obj.getClass());
                        }
                        break;
                    case HANDLE_TOAST:
                        if (msg.obj instanceof String) {
                            Toast.makeText(activity, (String) msg.obj, Toast.LENGTH_SHORT).show();
                        } else {
                            Log.e(TAG, "handleMessage: HANDLE_TOAST obj == " + msg.obj.getClass());
                        }
                        break;
                }
            }
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        Log.i(TAG, "onOptionsMenuClosed");
        super.onOptionsMenuClosed(menu);
    }

    //菜单打开时，暂停天气view绘制
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dynamicWeatherView.onPause();
            }
        }, 400);
        return super.onMenuOpened(featureId, menu);
    }

    boolean isItemSelected = false;

    //菜单关闭时，重启天气view绘制
    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        if (!isItemSelected) {
//            Log.i(TAG,"onPanelClosed" + featureId);
//            if (isMenuClose)
            dynamicWeatherView.onResume();
        }
        super.onPanelClosed(featureId, menu);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        isItemSelected = true;
        switch (item.getItemId()) {
            case R.id.change_position:
                Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                startActivityForResult(intent, ChoosePositionActivityRequestCode);
                break;
            case R.id.start_service:
                Intent startIntent = new Intent(WeatherActivity.this, SyncService.class);
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
//        SharedPreferences.Editor editor = PreferenceManager
//                .getDefaultSharedPreferences(WeatherActivity.this).edit();
//
//        SharedPreferences per = PreferenceManager
//                .getDefaultSharedPreferences(WeatherActivity.this);
        switch (requestCode) {
            case SettingActivityRequestCode:
                if (resultCode == SettingCode) {
//                    editor.putBoolean("auto_locate", data.getBooleanExtra("autoLocate", false));
                    configContact.applyAutoLocate(data.getBooleanExtra("autoLocate", false));

                    if (DEBUG)
                        Log.d(TAG, "onActivityResult: SettingActivity");
//                    String perCountyName = per.getString("countyName", null);
                    String perCountyName = configContact.getCountyName();
                    if (TextUtils.isEmpty(perCountyName)) {
                        Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                        startActivityForResult(intent, ChoosePositionActivityRequestCode);
                    } else {
                        presenter.refreshWeather(true, perCountyName);
                    }
                } else if (resultCode == ChooseCode) {
                    onActivityResult(ChoosePositionActivityRequestCode, ChooseCode, data);
                }
                break;
            case ChoosePositionActivityRequestCode:
                if (resultCode == ChooseCode) {
                    countyName = data.getStringExtra("countyName");
                    setCounty(countyName);
                    if (DEBUG)
                        Log.d(TAG, "onActivityResult: county_return: " + countyName);
//                    editor.putString("countyName", countyName);
//                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
//                    editor.putBoolean("auto_locate", false);
//                    editor.apply();
                    configContact.applyCountyName(countyName);
                    configContact.applyCountyNameLastUpdateTime(System.currentTimeMillis());
                    configContact.applyAutoLocate(false);
                    presenter.refreshWeather(true, countyName);
                }
                break;
            default:
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        SimplePermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    String[] per = {Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,};

    /**
     * 申请权限，可批量授权
     */
    private void permission() {
        SimplePermissionUtils.requestPermissionsResult(this, 1, per
                , new SimplePermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //Toast.makeText(BaseActivity.this, "获取权限成功!", Toast.LENGTH_SHORT).show();
//                        readPreferencesAndCache();
                        presenter.refreshWeather(false, null);
                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(WeatherActivity.this, getResources().getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                        if (DEBUG)
                            Log.d(TAG, "onActivityResult: Locate permission denied, switch to ip mode");
//                        SimplePermissionUtils.showTipsDialog(WeatherActivity.this);
//                        readPreferencesAndCache();
                        presenter.refreshWeather(false, null);
                    }
                });
    }


    /**
     * 定位图片的显示
     *
     * @param isLocation 是否是定位状态
     */
    public void setLocateModeImage(boolean isLocation) {
        locateModeImage.setVisibility(View.VISIBLE);
        if (isLocation)
            locateModeImage.setImageResource(R.drawable.ic_location_on_black_24dp);
        else
            locateModeImage.setImageResource(R.drawable.ic_location_off_black_24dp);
    }

//    /*
//     *刷新24小时内的天气的自定义 view
//     */
//    @Override
//    public void showHourlyWeatherInfo(final ForecastBean.ResultBean.HourlyBean hourlyBean) {
//
//        handler.post(new Runnable() {
//            @Override
//            public void run() {
//                HScrollView hScrollView = (HScrollView) findViewById(R.id.HScrollView);
//                hourlyWeatherView mLineChartView = (hourlyWeatherView) findViewById(R.id.simpleLineChart);
//                ArrayList<String> xItemArray = new ArrayList<>();
//
//                //天气
//                ArrayList<String> weatherArray = new ArrayList<>();
//                for (ForecastBean.ResultBean.HourlyBean.SkyconBean hourlyWeather : hourlyBean.getSkycon()) {
//                    weatherArray.add(hourlyWeather.getValue());
//                    xItemArray.add(Utility.ampm(hourlyWeather.getDatetime().substring(11, 13)));
//                }
//                //温度
//                ArrayList<Integer> yItemArray = new ArrayList<>();
//                for (ForecastBean.ResultBean.HourlyBean.TemperatureBean hourlyWeather : hourlyBean.getTemperature()) {
//                    yItemArray.add(intRoundFloat(hourlyWeather.getValue()));
//                }
//                //降水强度
//                ArrayList<Float> precipitation = new ArrayList<>();
//                for (ForecastBean.ResultBean.HourlyBean.PrecipitationBean hourlyWeather : hourlyBean.getPrecipitation()) {
//                    precipitation.add(hourlyWeather.getValue());
//                }
//
//                mLineChartView.setXItem(xItemArray);
//                mLineChartView.setYItem(yItemArray);
//                mLineChartView.setWeather(weatherArray);
//                mLineChartView.setPrecipitation(precipitation);
//                mLineChartView.setmHScrollView(hScrollView);
//                mLineChartView.applyChanges();
//            }
//        });
//
//    }

    /**
     * 展示未来5天的天气
     */
    @Override
    public void showDailyWeatherInfo(final ForecastBean.ResultBean.DailyBean dailyBean) {
        if (dailyBean.getStatus().equals("ok")) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        SimpleDateFormat oldsdf = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
                        SimpleDateFormat newsdf = new SimpleDateFormat("MM/dd", Locale.CHINA);
                        try {
                            Date date = oldsdf.parse(dailyBean.getSkycon().get(i).getDate());
                            day[i].setDate(newsdf.format(date));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
//                        String[] simpleDate = weatherDatas.get(i).getDate().split("-");
//                        day[i].setDate(simpleDate[1] + '/' + simpleDate[2]);
                        day[i].setTemperature(Utility.stringRoundFloat(dailyBean.getTemperature().get(i).getMin()) + '/'
                                + Utility.stringRoundFloat(dailyBean.getTemperature().get(i).getMax()) + "ºC");
                        day[i].setIcon(chooseWeatherIcon(dailyBean.getSkycon().get(i).getValue(), dailyBean.getPrecipitation().get(i).getMax(), HOURLY_MODE, false));
                        day[i].setSkycon(chooseWeatherSkycon(WeatherActivity.this, dailyBean.getSkycon().get(i).getValue(), dailyBean.getPrecipitation().get(i).getMax(), HOURLY_MODE));
                    }
                    Calendar calendar = Calendar.getInstance();
                    int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                    day[0].setDate(getResources().getString(R.string.today));
                    day[1].setDate(getResources().getString(R.string.tomorrow));
                    day[2].setDate(getResources().getStringArray(R.array.week)[(dayOfWeek + 1) % 7]);
                    day[3].setDate(getResources().getStringArray(R.array.week)[(dayOfWeek + 2) % 7]);
                    day[4].setDate(getResources().getStringArray(R.array.week)[(dayOfWeek + 3) % 7]);
                    String sunRise = dailyBean.getAstro().get(0).getSunrise().getTime();
                    String sunSet = dailyBean.getAstro().get(0).getSunset().getTime();
                    sunrise_text.setText(sunRise);
                    sunset_text.setText(sunSet);
                    sunTimeView.setTime(sunRise, sunSet);
                    uv_text.setText(dailyBean.getUltraviolet().get(0).getDesc());
                    carWashing_text.setText(dailyBean.getCarWashing().get(0).getDesc());
                    dressing_text.setText(dailyBean.getDressing().get(0).getDesc());
                    if (isDone) {
                        stopSwipe();
                        isDone = false;
                    } else {
                        isDone = true;
                    }
                }
            });
        }
    }

    /**
     * 展示现在的天气
     */
    @Override
    public void showCurrentWeatherInfo(final ForecastBean forecastBean) {
        handler.post(new Runnable() {
            @Override
            public void run() {
//                final RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);
                ForecastBean.ResultBean.HourlyBean hourlyBean = forecastBean.getResult().getHourly();

                String temperature = Utility.stringRoundFloat(hourlyBean.getTemperature().get(0).getValue());
                String skycon = hourlyBean.getSkycon().get(0).getValue();
                float humidity = hourlyBean.getHumidity().get(0).getValue();
                float PM25 = hourlyBean.getPm25().get(0).getValue();
                float intensity = hourlyBean.getPrecipitation().get(0).getValue();
                float aqi = hourlyBean.getAqi().get(0).getValue();

                String countyName =  configContact.getCountyName();
                if (hourlyBean.getWind() != null) {
                    setWindDirection(hourlyBean.getWind().get(0).getDirection());
                    setWindLevel(hourlyBean.getWind().get(0).getSpeed());
                }
                if (!TextUtils.isEmpty(countyName)) {
                    setCounty(countyName);
                } else {
                    if (DEBUG)
                        Log.d(TAG, "showCurrentWeatherInfo: countyName == null");
                }
                PMCircle.setValue((int) PM25);
                temperatureText.setText(temperature);
                AQICircle.setValue((int) aqi);
                Float hum = humidity * 100;
                hum_text.setText(hum.toString().substring(0, 2) + "%");
                String weatherString = chooseWeatherSkycon(getApplicationContext(), skycon, intensity, MINUTELY_MODE);
                skyconText.setText(weatherString);

//                    remoteViews.setImageViewResource(R.id.simple_widget_skycon, Integer.parseInt(ws[0]));
//                    remoteViews.setTextViewText(R.id.simple_widget_info, countyName + " | " + weatherString + ' ' + temperature + '°');

//                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
//                appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class),
//                        remoteViews);
                dynamicWeatherView.setDrawerType(Utility.chooseBgImage(skycon));
//                dynamicWeatherView.setDrawerType(BaseDrawer.Type.RAIN_D);
                if (isDone) {
                    stopSwipe();
                    isDone = false;
                } else {
                    isDone = true;
                }
            }
        });
    }

    /**
     * 初始化 RecyclerView()
     */
    @Override
    public void initRecyclerView(final List<SingleWeather> singleWeatherList) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeatherActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                hourWeatherAdapter = new HourWeatherAdapter(singleWeatherList);
                recyclerView.setAdapter(hourWeatherAdapter);
            }
        });
    }

    @Override
    public void updateRecyclerView() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                hourWeatherAdapter.notifyDataSetChanged();
            }
        });
    }

    /*
             *刷新环开始刷新
             */
    @Override
    public void startSwipe() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(true);
                }
            }
        });
    }

    /*
     *刷新环停止刷新
     */
    @Override
    public void stopSwipe() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (swipeRefreshLayout.isRefreshing()) {
                    swipeRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 显示风向
     */
    private void setWindDirection(float direction) {
        String dir;
        if (direction <= 22.5 || direction >= 337.5) {
            dir = getResources().getString(R.string.north);
        } else if (direction <= 67.5) {
            dir = getResources().getString(R.string.northeast);
        } else if (direction <= 112.5) {
            dir = getResources().getString(R.string.east);
        } else if (direction <= 157.5) {
            dir = getResources().getString(R.string.southeast);
        } else if (direction <= 202.5) {
            dir = getResources().getString(R.string.south);
        } else if (direction <= 247.5) {
            dir = getResources().getString(R.string.southwest);
        } else if (direction <= 292.5) {
            dir = getResources().getString(R.string.west);
        } else {
            dir = getResources().getString(R.string.northwest);
        }
        windDirectionTv.setText(dir);
    }

    /**
     * 显示风力
     */
    private void setWindLevel(float speed) {
        int level;
        String info;
        if (speed <= 0.72) {
            level = 0;
            info = "无风";
        } else if (speed <= 5.4) {
            level = 1;
            info = "软风";
        } else if (speed <= 11.88) {
            level = 2;
            info = "轻风";
        } else if (speed <= 19.44) {
            level = 3;
            info = "微风";
        } else if (speed <= 28.44) {
            level = 4;
            info = "和风";
        } else if (speed <= 38.52) {
            level = 5;
            info = "劲风";
        } else if (speed <= 49.68) {
            level = 6;
            info = "强风";
        } else if (speed <= 61.56) {
            level = 7;
            info = "疾风";
        } else if (speed <= 74.52) {
            level = 8;
            info = "大风";
        } else if (speed <= 87.84) {
            level = 9;
            info = "烈风";
        } else if (speed <= 102.24) {
            level = 10;
            info = "狂风";
        } else if (speed <= 117.36) {
            level = 11;
            info = "暴风";
        } else {
            level = 12;
            info = "飓风";
        }
        if (Utility.isChinese(getApplicationContext())) {
            windLevelTv.setText(level + " 级" + info);
        } else {
            windLevelTv.setText("LEVEL " + level);
        }
    }

}
