package top.maweihao.weather.widget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import top.maweihao.weather.R;
import top.maweihao.weather.service.SimpleWidgetUpdateService;

/**
 * The configuration screen for the {@link TallWeatherWidget TallWeatherWidget} AppWidget.
 */
public class TallWeatherWidgetConfigureActivity extends Activity {

    public static final String TAG = "TWConfigureActivity";

    int mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
    View.OnClickListener mOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.add_button:
                    final Context context = TallWeatherWidgetConfigureActivity.this;

                    AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

                    TallWeatherWidget.updateAppWidget(context, appWidgetManager, mAppWidgetId);

                    Intent resultValue = new Intent();
                    resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
                    setResult(RESULT_OK, resultValue);
                    context.startService(new Intent(context, SimpleWidgetUpdateService.class));
                    finish();
                    break;
                case R.id.tall_lunar_switch:
                    if (((Switch)v).isChecked()) {
                        Log.d(TAG, "onClick: is checked");
                    } else {
                        Log.d(TAG, "onClick: not checked");
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
}

