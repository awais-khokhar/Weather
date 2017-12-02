package top.maweihao.weather.widget;

import android.app.Activity;
import android.app.WallpaperManager;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import top.maweihao.weather.R;

/**
 * The configuration screen for the {@link BigWeatherWidget BigWeatherWidget} AppWidget.
 * Created by maweihao on 31/10/2017.
 */

public class BigWidgetConfigureActivity extends Activity implements View.OnClickListener {

    private ImageView widgetCard;
    private TextView lunar;
    private ImageView refreshButton;

    private Switch lunarSwitch;
    private Switch darkCardSwitch;
    private Switch refreshSwitch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_configure_big);
        initView();
    }

    private void initView() {
        View widgetView = LayoutInflater.from(this).inflate(R.layout.widget_big_weather, null);
        ((ViewGroup) findViewById(R.id.frameLayout)).addView(widgetView);

        this.widgetCard = widgetView.findViewById(R.id.widget_big_card);
        widgetCard.setVisibility(View.GONE);
        this.lunar = widgetView.findViewById(R.id.big_widget_lunar);
        this.refreshButton = widgetView.findViewById(R.id.iv_refresh);

        ImageView wallpaper = findViewById(R.id.iv_wallpaper);
        wallpaper.setImageDrawable(WallpaperManager.getInstance(this).getDrawable());

//        this.container = findViewById(R.id.activity_create_widget_clock_day_horizontal_container);

        this.darkCardSwitch = findViewById(R.id.dark_card_switch);
//        darkCardSwitch.setOnCheckedChangeListener(new ShowCardSwitchCheckListener());

        this.lunarSwitch = findViewById(R.id.big_lunar_switch);
//        lunarSwitch.setOnCheckedChangeListener(new BlackTextSwitchCheckListener());

        this.refreshSwitch = findViewById(R.id.refresh_button_switch);
//        refreshSwitch.setOnClickListener(new RefreshSwitchLishener());

        Button doneButton = findViewById(R.id.add_button);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_button:


                Intent intent = getIntent();
                Bundle extras = intent.getExtras();
                int appWidgetId = 0;
                if (extras != null) {
                    appWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,
                            AppWidgetManager.INVALID_APPWIDGET_ID);
                }

                Intent resultValue = new Intent();
                resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, resultValue);

//                ServiceHelper.resetNormalService(this, false, true);
                finish();
                break;
        }
    }
}
