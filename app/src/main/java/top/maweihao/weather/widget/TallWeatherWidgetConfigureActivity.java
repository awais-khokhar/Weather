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
import top.maweihao.weather.service.SimpleWidgetUpdateService;

import static top.maweihao.weather.R.id.tall_widget_preview;

/**
 * The configuration screen for the {@link TallWeatherWidget TallWeatherWidget} AppWidget.
 */
public class TallWeatherWidgetConfigureActivity extends Activity {

    private static final String PREFS_NAME = "top.maweihao.weather.TallWeatherWidget";
    private static final String PREF_PREFIX_KEY = "appwidget_";

    public static final String TAG = "TWConfigureActivity";

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            final Context context = TallWeatherWidgetConfigureActivity.this;
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

            switch (v.getId()) {
                case R.id.add_button:
                    TallWeatherWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);
                    Intent resultValue = new Intent();
                    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                    setResult(RESULT_OK, resultValue);
                    context.startService(new Intent(context, SimpleWidgetUpdateService.class));
                    finish();
                    break;
                case R.id.tall_lunar_switch:
                    if (((Switch)v).isChecked()) {
                        ((ImageView)findViewById(tall_widget_preview)).setImageResource(R.drawable.tall_widget_lunar_on);
                        saveTitlePref(context, mAppWidgetId, true);
                    } else {
                        ((ImageView)findViewById(tall_widget_preview)).setImageResource(R.drawable.tall_widget_lunar_off);
                        saveTitlePref(context, mAppWidgetId, false);
                    }
                    break;
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

        setContentView(R.layout.tall_weather_widget_configure);
        findViewById(R.id.tall_lunar_switch).setOnClickListener(mOnClickListener);
        findViewById(R.id.add_button).setOnClickListener(mOnClickListener);

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
    static void saveTitlePref(Context context, int appWidgetId, Boolean visible) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.putBoolean(PREF_PREFIX_KEY + appWidgetId, visible);
        prefs.apply();
    }

    // Read the prefix from the SharedPreferences object for this widget.
    // If there is no preference saved, get the default from a resource
    static Boolean loadTitlePref(Context context, int appWidgetId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, 0);
        return prefs.getBoolean(PREF_PREFIX_KEY + appWidgetId, false);
    }

    static void deleteTitlePref(Context context, int appWidgetId) {
        SharedPreferences.Editor prefs = context.getSharedPreferences(PREFS_NAME, 0).edit();
        prefs.remove(PREF_PREFIX_KEY + appWidgetId);
        prefs.apply();
    }
}

