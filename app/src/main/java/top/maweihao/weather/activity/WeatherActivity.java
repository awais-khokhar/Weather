package top.maweihao.weather.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
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

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.bean.ExtendedWeatherData;
import top.maweihao.weather.bean.HourlyWeather;
import top.maweihao.weather.bean.MyLocation;
import top.maweihao.weather.bean.WeatherData;
import top.maweihao.weather.contract.WeatherActivityContract;
import top.maweihao.weather.presenter.WeatherActivityPresenter;
import top.maweihao.weather.service.SyncService;
import top.maweihao.weather.util.SimplePermissionUtils;
import top.maweihao.weather.util.Utility;
import top.maweihao.weather.view.HScrollView;
import top.maweihao.weather.view.SemiCircleView;
import top.maweihao.weather.view.SunTimeView;
import top.maweihao.weather.view.dynamicweather.DynamicWeatherView;
import top.maweihao.weather.view.hourlyWeatherView;
import top.maweihao.weather.view.perDayWeatherView;

import static top.maweihao.weather.R.id.skycon_text;
import static top.maweihao.weather.R.id.temperature_text;
import static top.maweihao.weather.util.Utility.chooseWeatherIcon;
import static top.maweihao.weather.util.Utility.chooseWeatherIconOnly;
import static top.maweihao.weather.util.Utility.intRoundString;

public class WeatherActivity extends AppCompatActivity implements WeatherActivityContract.View {
    public static boolean DEBUG = true;

    static final String TAG = "WeatherActivity";
    static final int THROUGH_IP = 0;
    static final int THROUGH_CHOOSE_POSITION = 1;
    static final int THROUGH_LOCATE = 2;
    static final int THROUGH_COORDINATE = 3;
    public static final int MINUTELY_MODE = 4;
    public static final int HOURLY_MODE = 5;

    static final int HANDLE_POSITION = 0;
    static final int HANDLE_TOAST = 1;
//    static final int HANDLE_SWIPE_BEGIN = 2;
//    static final int HANDLE_SWIPE_STOP = 3;

    private Long locateTime;
    private LocationClient mLocationClient;

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

    private CollapsingToolbarLayoutState state;

    private enum CollapsingToolbarLayoutState {
        EXPANDED,
        COLLAPSED,
        INTERNEDIATE
    }

    private boolean isDone = false;
    private String countyName = null;


    public static String locationCoordinates;
    private perDayWeatherView[] day = new perDayWeatherView[5];

    private Boolean autoLocate;
    private MessageHandler handler; //消息队列
    private WeatherActivityContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        PreferenceManager.setDefaultValues(this, R.xml.settingpreference, false);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

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
                beforeRequestWeather(autoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
            }
        }));

        appBarLayout.addOnOffsetChangedListener(new AppBarOnOffsetChanged());

        mLocationClient = new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new MainLocationListener());
        initLocation();

    }

    @Override
    protected void onResume() {
        super.onResume();
        dynamicWeatherView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        dynamicWeatherView.onPause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        permission();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mLocationClient.isStarted()) {
            mLocationClient.stop();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dynamicWeatherView.onDestroy();
        presenter.destroy();
        presenter = null;
    }

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
    public void setLastUpdateTime() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lastUpdateTime.setText(Utility.getTime(getApplicationContext()));
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
                if (state != CollapsingToolbarLayoutState.EXPANDED) {
                    state = CollapsingToolbarLayoutState.EXPANDED;//修改状态标记为展开
                    toolbarLayout.setTitle(null);
                    toolBarLinearLayout.setVisibility(View.VISIBLE);

                }
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (state != CollapsingToolbarLayoutState.COLLAPSED) {
                    toolbarLayout.setTitle(countyName);//设置title
                    toolBarLinearLayout.setVisibility(View.INVISIBLE);
                    state = CollapsingToolbarLayoutState.COLLAPSED;//修改状态标记为折叠
                }
            } else {
                if (state != CollapsingToolbarLayoutState.INTERNEDIATE) {
                    if (state == CollapsingToolbarLayoutState.COLLAPSED) {
                        toolbarLayout.setTitle(null);
                        if (toolBarLinearLayout.getVisibility()==View.INVISIBLE)
                            toolBarLinearLayout.setVisibility(View.VISIBLE);
                    }
                    state = CollapsingToolbarLayoutState.INTERNEDIATE;//修改状态标记为中间

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
//                            if (activity.toolbar != null) {
//                                activity.toolbar.setTitle((String) msg.obj);
//                            } else {
//                                Log.e(TAG, "handleMessage: toolBar == null");
//                            }
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_position:
                Intent intent = new Intent(WeatherActivity.this, ChoosePositionActivity.class);
                startActivityForResult(intent, 1);
                break;
            case R.id.start_service:
                Intent startIntent = new Intent(WeatherActivity.this, SyncService.class);
                startService(startIntent);
                break;
            case R.id.setting:
                Intent intent1 = new Intent(WeatherActivity.this, SettingActivity.class);
                startActivityForResult(intent1, 2);
                break;
            default:
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
            case 2:
//                settingActivity 和 choosePositionActivity 返回的数据一样的
                if (resultCode == RESULT_OK) {
                    countyName = data.getStringExtra("countyName");
                    setCounty(countyName);
                    if (DEBUG)
                        Log.d(TAG, "onActivityResult: county_return: " + countyName);
                    SharedPreferences.Editor editor = PreferenceManager
                            .getDefaultSharedPreferences(WeatherActivity.this).edit();
                    editor.putString("countyName", countyName);
                    editor.putLong("countyName_last_update_time", System.currentTimeMillis());
                    editor.putBoolean("auto_locate", false);
                    editor.apply();
                    beforeRequestWeather(THROUGH_CHOOSE_POSITION);
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

    /**
     * 申请权限，可批量授权
     */
    private void permission() {
        SimplePermissionUtils.requestPermissionsResult(this, 1, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION}
                , new SimplePermissionUtils.OnPermissionListener() {
                    @Override
                    public void onPermissionGranted() {
                        //Toast.makeText(BaseActivity.this, "获取权限成功!", Toast.LENGTH_SHORT).show();
                        readPreferencesAndCache();
                    }

                    @Override
                    public void onPermissionDenied() {
                        Toast.makeText(WeatherActivity.this, getResources().getString(R.string.permission_denied), Toast.LENGTH_SHORT).show();
                        if (DEBUG)
                            Log.d(TAG, "onActivityResult: Locate permission denied, switch to ip mode");
//                        SimplePermissionUtils.showTipsDialog(WeatherActivity.this);
                        readPreferencesAndCache();
                    }
                });
    }

    /**
     * 初始化百度定位
     */
    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
//        刷新间隔（ms）
        option.setScanSpan(1000);
//        定位模式：精确
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        // 需要地址信息
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    /**
     * 读取 SharedPreferences 中保存的天气数据json
     */
    private void readPreferencesAndCache() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        //读取配置
        autoLocate = prefs.getBoolean("auto_locate", true);
        countyName = prefs.getString("countyName", null);

        /*minInterval： 最低刷新间隔*/
        int minInterval = prefs.getInt("refresh_interval", 10);
//        现在的天气， 原始json
        String weatherNow = prefs.getString("weather_now", null);
        long weatherNowLastUpdateTime = prefs.getLong("weather_now_last_update_time", 0);
//        未来的天气， 原始json
        String weatherFull = prefs.getString("weather_full", null);
        long weatherFullLastUpdateTime = prefs.getLong("weather_full_last_update_time", 0);
//        若保存的天气刷新时间和现在相差小于 minInterval，则直接使用
        if (weatherNow != null && System.currentTimeMillis() - weatherNowLastUpdateTime < minInterval * 60 * 1000
                && weatherFull != null && System.currentTimeMillis() - weatherFullLastUpdateTime < minInterval * 60 * 1000) {
            if (DEBUG) {
                Log.d(TAG, "readCache: last nowWeather synced: "
                        + (System.currentTimeMillis() - weatherNowLastUpdateTime) / 1000 + "s ago");
                Log.d(TAG, "readCache: last fullWeather synced: "
                        + (System.currentTimeMillis() - weatherFullLastUpdateTime) / 1000 + "s ago");
            }
            lastUpdateTime.setText(Utility.getTime(getApplicationContext(), weatherNowLastUpdateTime));
            WeatherData wd = Utility.handleCurrentWeatherResponse(weatherNow);
            showCurrentWeatherInfo(wd);
            presenter.getFullWeatherDataForJson(weatherFull);

            if (autoLocate)
                setLocateModeImage(true);
            else
                setLocateModeImage(false);
            String ip;
            if (countyName != null)
                locateMode.setText(countyName);
            else if ((ip = prefs.getString("IP", null)) != null) {
                locateMode.setText(ip);
            }
        } else {
//            全量刷新
            beforeRequestWeather(autoLocate ? THROUGH_LOCATE : THROUGH_CHOOSE_POSITION);
        }
    }

    private void beforeRequestWeather(int requestCode) {
        startSwipe();
        locateMode.setVisibility(View.VISIBLE);
        switch (requestCode) {
            case THROUGH_IP:
//                主界面显示当前为 ip 定位
//                locateMode 和 locateModeImage 用来显示当前定位方式
                setLocateModeImage(true);
                locateMode.setText(Utility.getIP(this));
                presenter.getCoordinateByIp();
                break;
            case THROUGH_CHOOSE_POSITION:
                setLocateModeImage(false);
                presenter.getCoordinateByChoosePosition(countyName);
                break;
            case THROUGH_LOCATE:
                setLocateModeImage(true);
                bdLocate();
                break;
            case THROUGH_COORDINATE:
                locateModeImage.setVisibility(View.INVISIBLE);
                locateMode.setVisibility(View.INVISIBLE);
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
                locationCoordinates = prefs.getString("coordinate", null);
//                initRequireUrl();
                presenter.afterGetCoordinate();
                break;
        }
    }

    /**
     * 使用百度定位
     */
    private void bdLocate() {
        locateTime = System.currentTimeMillis();
        //等着回调 MainLocationListener
        mLocationClient.start();
    }

    /**
     * 百度定位成功
     *
     * @param location 自定义的位置类
     */
    private void locateSuccess(MyLocation location) {
        locationCoordinates = location.getCoordinate();
        countyName = location.getDistrict();
        if (DEBUG) {
            Log.d(TAG, "locateSuccess: locationCoordinates == " + locationCoordinates);
            Log.d(TAG, "locateSuccess: location: " + countyName + location.getStreet());
        }
        SharedPreferences.Editor editor = PreferenceManager
                .getDefaultSharedPreferences(WeatherActivity.this).edit();
        editor.putString("coordinate", locationCoordinates);
        editor.putLong("coordinate_last_update", System.currentTimeMillis());
        editor.putString("countyName", countyName);
        editor.putLong("countyName_last_update_time", System.currentTimeMillis());
        editor.apply();
        presenter.setCounty(countyName);
        presenter.afterGetCoordinate();
    }

    /**
     * 当百度定位失败时，使用 locationManager 定位
     */
    private void locationManagerLocate() {
        LocationManager mLocationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Location location = null;
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            location = mLocationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }

        if (location != null) {
            locationCoordinates = String.valueOf(location.getLongitude()) + ',' + String.valueOf(location.getLatitude());
            if (DEBUG)
                Log.d(TAG, "GetCoordinateByLocate: locationCoordinates = " + locationCoordinates);
            SharedPreferences.Editor editor = PreferenceManager
                    .getDefaultSharedPreferences(WeatherActivity.this).edit();
            editor.putString("coordinate", locationCoordinates);
            editor.putLong("coordinate_last_update", System.currentTimeMillis());
            editor.apply();

            presenter.getCountyByCoordinate(locationCoordinates);
            presenter.afterGetCoordinate();
        } else {
            if (DEBUG)
                Log.d(TAG, "requestLocation: location == null, switch to IP method");
            beforeRequestWeather(THROUGH_IP);
        }
    }

    /**
     * 定位图片的显示
     *
     * @param isLocation 是否是定位状态
     */
    private void setLocateModeImage(boolean isLocation) {
        locateModeImage.setVisibility(View.VISIBLE);
        if (isLocation)
            locateModeImage.setImageResource(R.drawable.ic_location_on_black_24dp);
        else
            locateModeImage.setImageResource(R.drawable.ic_location_off_black_24dp);
    }

    /*
     *刷新24小时内的天气的自定义 view
     */
    @Override
    public void showHourlyWeatherInfo(final ArrayList<HourlyWeather> hourlyWeathers) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                HScrollView hScrollView = (HScrollView) findViewById(R.id.HScrollView);
                hourlyWeatherView mLineChartView = (hourlyWeatherView) findViewById(R.id.simpleLineChart);
                ArrayList<String> xItemArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    xItemArray.add(hourlyWeather.getDatetime().substring(11, 16));
                }
                //天气
                ArrayList<String> weatherArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    weatherArray.add(hourlyWeather.getSkyon());
                }
                //温度
                ArrayList<Integer> yItemArray = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    yItemArray.add(intRoundString(hourlyWeather.getTemperature()));
                }

                ArrayList<Float> precipitation = new ArrayList<>();
                for (HourlyWeather hourlyWeather : hourlyWeathers) {
                    precipitation.add(Float.parseFloat(hourlyWeather.getPrecipitation()));
                }

                mLineChartView.setXItem(xItemArray);
                mLineChartView.setYItem(yItemArray);
                mLineChartView.setWeather(weatherArray);
                mLineChartView.setPrecipitation(precipitation);
                mLineChartView.setmHScrollView(hScrollView);
                mLineChartView.applyChanges();
            }
        });

    }

    /**
     * 展示未来5天的天气
     */
    @Override
    public void showDailyWeatherInfo(final ArrayList<ExtendedWeatherData> weatherDatas) {
        if (weatherDatas.size() == 5) {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 5; i++) {
                        String[] simpleDate = weatherDatas.get(i).getDate().split("-");
                        day[i].setDate(simpleDate[1] + '/' + simpleDate[2]);
                        day[i].setTemperature(Utility.roundString(weatherDatas.get(i).getMinTemperature()) + '/'
                                + Utility.roundString(weatherDatas.get(i).getMaxTemperature()) + "ºC");
                        day[i].setIcon(chooseWeatherIconOnly(weatherDatas.get(i).getSkycon(), Float.parseFloat(weatherDatas.get(i).getIntensity()), HOURLY_MODE));
                    }
                    day[0].setDate(getResources().getString(R.string.today));
                    day[1].setDate(getResources().getString(R.string.tomorrow));
                    sunrise_text.setText(weatherDatas.get(0).getSunriseTime());
                    sunset_text.setText(weatherDatas.get(0).getSunsetTime());
                    sunTimeView.setTime(weatherDatas.get(0).getSunriseTime(), weatherDatas.get(0).getSunsetTime());
                    uv_text.setText(weatherDatas.get(0).getUvDesc());
                    carWashing_text.setText(weatherDatas.get(0).getCarWashingDesc());
                    dressing_text.setText(weatherDatas.get(0).getDressingDesc());
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
    public void showCurrentWeatherInfo(final WeatherData weatherData) {
        handler.post(new Runnable() {
            @Override
            public void run() {
//                final RemoteViews remoteViews = new RemoteViews(getApplicationContext().getPackageName(), R.layout.simple_weather_widget);
                String temperature = Utility.roundString(weatherData.getTemperature());
                String skycon = weatherData.getSkycon();
                String humidity = weatherData.getHumidity();
                String PM25 = weatherData.getPm25();
                float intensity = Float.parseFloat(weatherData.getIntensity());
                String aqi = weatherData.getAqi();
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(WeatherActivity.this);
                String countyName = prefs.getString("countyName", null);
                if (weatherData.getWind() != null) {
                    setWindDirection(weatherData.getWind().getDirection());
                    setWindLevel(weatherData.getWind().getSpeed());
                }
                if (!TextUtils.isEmpty(countyName)) {
                    setCounty(countyName);
                } else {
                    if (DEBUG)
                        Log.d(TAG, "showCurrentWeatherInfo: countyName == null");
                }
                PMCircle.setValue(Integer.valueOf(PM25));
                temperatureText.setText(temperature);
                AQICircle.setValue(Integer.valueOf(aqi));
                Float hum = Float.parseFloat(humidity) * 100;
                hum_text.setText(hum.toString().substring(0, 2) + "%");
                String weatherString = chooseWeatherIcon(skycon, intensity, MINUTELY_MODE);
                if (weatherString != null) {
                    String[] ws = weatherString.split("and");
                    skyconText.setText(ws[1]);
//                    remoteViews.setImageViewResource(R.id.simple_widget_skycon, Integer.parseInt(ws[0]));
//                    remoteViews.setTextViewText(R.id.simple_widget_info, countyName + " | " + ws[1] + ' ' + temperature + '°');
                }
//                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
//                appWidgetManager.updateAppWidget(new ComponentName(getApplicationContext(), SimpleWeatherWidget.class),
//                        remoteViews);
                dynamicWeatherView.setDrawerType(Utility.chooseBgImage(skycon));
                if (isDone) {
                    stopSwipe();
                    isDone = false;
                } else {
                    isDone = true;
                }
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

    private class MainLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            Log.d(TAG, "BAIDU: received" + (System.currentTimeMillis() - locateTime));
            if (System.currentTimeMillis() - locateTime < 2 * 1001) {  //at most x second
                if (bdLocation.getLocType() != BDLocation.TypeGpsLocation) {
                    // do nothing
                    if (DEBUG) {
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == " + bdLocation.getLocType());
                    }
                } else {
                    mLocationClient.stop();
                    if (DEBUG) {
                        Log.d(TAG, "BAIDU onReceiveLocation: Locate type == GPS");
                        presenter.toastMessage("GPS!");
                    }
                    locateSuccess(simplifyBDLocation(bdLocation));
                }
            } else {
                mLocationClient.stop();
                if (DEBUG) {
                    Log.d(TAG, "BAIDU onReceiveLocation: baidu locate time out, locType == " + bdLocation.getLocType());
                }
                if (MyLocation.locateSuccess(bdLocation.getLocType())) {
                    locateSuccess(simplifyBDLocation(bdLocation));
                } else {
                    Log.d(TAG, "BAIDU onReceiveLocation: baidu locate failed, switch to LocationManager method");
                    locationManagerLocate();
                }
            }
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {
//      nothing to do
        }

        private MyLocation simplifyBDLocation(BDLocation bdLocation) {
            MyLocation location = new MyLocation(bdLocation.getLongitude(), bdLocation.getLatitude());
            location.setType(bdLocation.getLocType());
            location.setProvince(bdLocation.getProvince());
            location.setCity(bdLocation.getCity());
            location.setDistrict(bdLocation.getDistrict());
            location.setStreet(bdLocation.getStreet());
            return location;
        }
    }

}
