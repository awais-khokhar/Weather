package top.maweihao.weather.widget;

import android.app.Activity;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.util.LunarUtil;
import top.maweihao.weather.util.ServiceUtil;

public class SimpleWidgetConfigureActivity extends Activity implements View.OnClickListener{

    private static final String PREFS_NAME = "top.maweihao.weather.SimpleWeatherWidget";
    private static final String PREF_LUNAR_PREFIX_KEY = "appwidget_simple_lunar";
    private static final String PREF_CARD_PREFIX_KEY = "appwidget_simple_card";

    @BindView(R.id.widget_big_card)
    ImageView widgetCard;
    @BindView(R.id.simple_widget_skycon)
    ImageView widgetIcon;
    @BindView(R.id.simple_widget_lunar)
    TextView widgetLunar;
    @BindView(R.id.simple_widget_info)
    TextView widgetInfo;

    @BindView(R.id.iv_wallpaper)
    ImageView wallpaper;
    @BindView(R.id.big_lunar_switch)
    Switch lunarSwitch;
    @BindView(R.id.dark_card_switch)
    Switch darkCardSwitch;
    @BindView(R.id.add_button)
    FloatingActionButton doneButton;

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_configure_big);
        setResult(RESULT_CANCELED);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }
        initView();
    }

    private void initView() {
        final View widgetView = LayoutInflater.from(this).inflate(R.layout.widget_simple_weather, null);
        ((ViewGroup) findViewById(R.id.frameLayout)).addView(widgetView);
        ButterKnife.bind(this);
        wallpaper.setImageDrawable(WallpaperManager.getInstance(this).getDrawable());
        lunarSwitch.setChecked(true);

        initWidgetView();

        darkCardSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    widgetCard.setVisibility(View.VISIBLE);
                } else {
                    widgetCard.setVisibility(View.GONE);
                }
            }
        });

        lunarSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    widgetLunar.setVisibility(View.VISIBLE);
                } else {
                    widgetLunar.setVisibility(View.GONE);
                }
            }
        });

        doneButton.setOnClickListener(this);
    }

    private void initWidgetView() {
        lunarSwitch.setVisibility(View.VISIBLE);
        LunarUtil lunarUtilDate = new LunarUtil(new GregorianCalendar());
        widgetLunar.setText(lunarUtilDate.toString());
        widgetInfo.setText("示例地区" + " | " + "多云" + ' ' + "16" + '°');
        widgetIcon.setImageResource(R.mipmap.weather_clouds);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_button:
                saveCardPref(SimpleWidgetConfigureActivity.this, darkCardSwitch.isChecked());
                saveLunarPref(SimpleWidgetConfigureActivity.this, lunarSwitch.isChecked());
                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                setResult(RESULT_OK, resultValue);
                ServiceUtil.startWidgetSyncService(SimpleWidgetConfigureActivity.this, true);
                finish();
                break;
            default:
                break;
        }
    }

    public static void saveLunarPref(Context context, Boolean visible) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putBoolean(PREF_LUNAR_PREFIX_KEY, visible);
        prefs.apply();
    }

    public static Boolean loadLunarPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(PREF_LUNAR_PREFIX_KEY, false);
    }

    public static void saveCardPref(Context context, Boolean visible) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putBoolean(PREF_CARD_PREFIX_KEY, visible);
        prefs.apply();
    }

    public static Boolean loadCardPref(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(PREF_CARD_PREFIX_KEY, false);
    }

    public static void deleteAllPref(Context context) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_LUNAR_PREFIX_KEY);
        prefs.remove(PREF_CARD_PREFIX_KEY);
        prefs.apply();
    }
}
