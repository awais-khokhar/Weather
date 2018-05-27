package top.maweihao.weather.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Switch;

import top.maweihao.weather.R;
import top.maweihao.weather.service.SyncService;

import static top.maweihao.weather.R.id.tall_widget_preview;

/**
 * The configuration screen for the {@link TallWeatherWidget TallWeatherWidget} AppWidget.
 */
public class TallWeatherWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "top.maweihao.weather.TallWeatherWidget";
    private static final String PREF_LUNAR_PREFIX_KEY = "appwidget_lunar";
    private static final String PREF_CARD_PREFIX_KEY = "appwidget_card";

    public static final String TAG = "TWConfigureActivity";


    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = TallWeatherWidgetConfigureActivity.this.getApplicationContext();

            switch (v.getId()) {
                case R.id.add_button:
                    Intent resultValue = new Intent();
                    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                    setResult(RESULT_OK, resultValue);
//                    ServiceUtil.startWidgetSyncService(context, true, true);
                    SyncService.scheduleSyncService(context, true);
                    finish();
                    break;
                case R.id.tall_lunar_switch:
                    if (((Switch)v).isChecked()) {
                        ((ImageView)findViewById(tall_widget_preview)).setImageResource(R.drawable.tall_widget_lunar_on);
                        saveLunarPref(context, true);
                    } else {
                        ((ImageView)findViewById(tall_widget_preview)).setImageResource(R.drawable.tall_widget_lunar_off);
                        saveLunarPref(context, false);
                    }
                    break;
                case R.id.tall_card_switch:
                    if (((Switch) v).isChecked()) {
                        saveCardPref(context, true);
                    } else {
                        saveCardPref(context, false);
                    }
            }
        }
    };

    public TallWeatherWidgetConfigureActivity() {
        super();
    }

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setResult(RESULT_CANCELED);

        setContentView(R.layout.widget_configure_tall);
        findViewById(R.id.tall_lunar_switch).setOnClickListener(mOnClickListener);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);
        findViewById(R.id.tall_card_switch).setOnClickListener(mOnClickListener);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            mAppWidgetId = extras.getInt(
                    AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        // If this activity was started with an intent without an app widget ID, finish with an error.
        if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }
    }

    // Write the prefix to the SharedPreferences object for this widget
    public static void saveLunarPref(Context context, Boolean visible) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putBoolean(PREF_LUNAR_PREFIX_KEY, visible);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
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

