package top.maweihao.weather.activity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TimePicker;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.service.SyncService;

import static top.maweihao.weather.util.Constants.ChooseCode;
import static top.maweihao.weather.util.Constants.DEBUG;
import static top.maweihao.weather.util.Constants.SettingCode;
import static top.maweihao.weather.util.Constants.isSetResultIntent;

public class SettingActivity extends AppCompatActivity {

    public static final String TAG = "SettingActivity";

    public static final String TIME_SPLIT = " : ";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private static boolean originalAutoLocate, changeAutolocate;//记录原始配置和改变后的配置，判断自动定位是否发生改变；

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        toolbar.setTitle(getResources().getString(R.string.setting));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);

        getFragmentManager().beginTransaction()
                .replace(R.id.setting_frameLayout, new PrefsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            backListener();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        backListener();
    }

    private void backListener()
    {
        if (originalAutoLocate != changeAutolocate) {
            isSetResultIntent=true;
            Intent intent = new Intent();
            intent.putExtra("autoLocate", changeAutolocate);
            setResult(SettingCode, intent);
        }
        finish();
    }

    public static class PrefsFragment extends PreferenceFragment {

        private ListPreference temLp;

        private Preference aboutPreference;

        private Preference choosePositionPreference;

        private Preference feedBack;

        private SwitchPreference autoUpdateSP;
        private SwitchPreference notification;
        private Preference notificationTime;

        String countyName;
        SharedPreferences sharedPreferences;


        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settingpreference);
            sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            countyName = sharedPreferences.getString("countyName", null);
            changeAutolocate = originalAutoLocate = sharedPreferences.getBoolean("auto_locate", false);
            initViews();
        }

        private void initViews() {

            temLp = (ListPreference) findPreference("isCelsius");
            temLp.setEnabled(false);
            temLp.setSummary("当前地区不可用");

            aboutPreference = findPreference("about");
            choosePositionPreference = findPreference("choose_position");
            feedBack = findPreference("feedback");
            feedBack.setOnPreferenceClickListener(enterActivityListener);
            choosePositionPreference.setOnPreferenceClickListener(enterActivityListener);
            autoUpdateSP = (SwitchPreference) findPreference("auto_locate");
            autoUpdateSP.setOnPreferenceChangeListener(changeListener);
            aboutPreference.setOnPreferenceClickListener(enterActivityListener);

            notification = (SwitchPreference) findPreference("notification");
            notification.setOnPreferenceChangeListener(changeListener);

            notificationTime = findPreference("notification_time");
            notificationTime.setOnPreferenceClickListener(new NotificationTimePreferenceClickListener());

//            temLp.setSummary(temLp.getEntry());
//            temLp.setOnPreferenceChangeListener(changeListener);

            if (notification.isChecked()) {
                notificationTime.setEnabled(true);
                notificationTime.setSummary(sharedPreferences.getString("notification_time", "18 : 00"));
            } else
                notificationTime.setEnabled(false);


            if (autoUpdateSP.isChecked()) {
                Log.d(TAG, "SettingActivity::initViews: autoUpdate is checked");
                choosePositionPreference.setEnabled(false);
                choosePositionPreference.setShouldDisableView(true);
            } else {
                if (!TextUtils.isEmpty(countyName)) {
                    choosePositionPreference.setSummary(getResources().getString(R.string.selected_county)
                            + countyName);
                }
            }
        }

        private Preference.OnPreferenceChangeListener changeListener = new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                String stringValue = newValue.toString();
                if (DEBUG)
                    Log.d(TAG, "onPreferenceChange: " + stringValue);
                if (preference instanceof ListPreference) {
                    ListPreference listPreference = (ListPreference) preference;
                    int index = listPreference.findIndexOfValue(stringValue);
                    preference.setSummary(
                            index >= 0
                                    ? listPreference.getEntries()[index]
                                    : null);
                    return true;
                } else if (preference.getKey().equals("auto_locate")) {
                    if (stringValue.equals("true")) {
                        changeAutolocate = true;
                        choosePositionPreference.setEnabled(false);
                        choosePositionPreference.setSummary(null);
                        choosePositionPreference.setSummary(getResources().getString(R.string.select_disabled));
                    } else {
                        changeAutolocate = false;
                        choosePositionPreference.setEnabled(true);
                        if (!TextUtils.isEmpty(countyName)) {
                            choosePositionPreference.setSummary(getResources().getString(R.string.selected_county)
                                    + countyName);
                        }
                    }
                    return true;
                } else if (preference.getKey().equals("notification")) {
                    Intent startIntent = new Intent(getActivity(), SyncService.class);
                    if (stringValue.equals("true")) {
                        notificationTime.setEnabled(true);
                        notificationTime.setSummary(sharedPreferences.getString("notification_time", "18 : 00"));
                        SyncService.isStarSendNotification = false;
                        getActivity().startService(startIntent);
                        if (DEBUG)
                            Log.d(TAG, "onPreferenceChange: start SyncService");
                    } else {
                        notificationTime.setEnabled(false);
                        getActivity().stopService(startIntent);
                        if (DEBUG)
                            Log.d(TAG, "onPreferenceChange: stop SyncService");
                    }
                    return true;
                } else if (preference.getKey().equals("notificatn_time")) {
                    if (DEBUG)
                        Log.d(TAG, "onPreferenceChange: notification_time");
                    notificationTime.setSummary(preference.getKey());
                    return true;
                }
                return false;
            }
        };

        private Preference.OnPreferenceClickListener enterActivityListener = new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                if (preference.getKey().equals("about")) {
                    Intent intent = new Intent(getActivity(), AboutActivity.class);
                    startActivity(intent);
//                    return true;
                } else if (preference.getKey().equals("choose_position")) {
                    Intent intent = new Intent(getActivity(), ChoosePositionActivity.class);
                    startActivityForResult(intent, 1);
                } else if (preference.getKey().equals("feedback")) {
                    Intent email = new Intent(Intent.ACTION_SENDTO);
                    email.setData(Uri.parse("mailto:hellowello1996@outlook.com"));
                    email.putExtra(Intent.EXTRA_SUBJECT, "Feedback: Weather");
                    email.putExtra(Intent.EXTRA_TEXT, "Feedback: ");
                    startActivity(email);
                }
                return true;
            }
        };

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            switch (requestCode) {
                case 1:
                    if (resultCode == ChooseCode) {
                        getActivity().setResult(ChooseCode, data);
                        getActivity().finish();
                    }
            }

        }

        private class NotificationTimePreferenceClickListener implements Preference.OnPreferenceClickListener {
            @Override
            public boolean onPreferenceClick(final Preference preference) {
                Calendar calendar = new GregorianCalendar();
                TimePickerDialog timeDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {
                            //从这个方法中取得获得的时间
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                DecimalFormat df = new DecimalFormat("00");
                                String formatHour = df.format(hourOfDay);
                                String formatMinute = df.format(minute);
                                preference.setSummary(formatHour + ": " + formatMinute);

                                SharedPreferences sp = preference.getSharedPreferences();
//                                Log.d(TAG, "SettingActivity::notification_time " + sp.getString("notification_time",null));
                                sp.edit().putString("notification_time", formatHour + TIME_SPLIT + formatMinute).apply();
                                SyncService.isStarSendNotification = false;
                                Intent startIntent = new Intent(getActivity(), SyncService.class);
                                getActivity().startService(startIntent);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timeDialog.show();
                return false;
            }
        }
    }
}
