package top.maweihao.weather.view;

import android.app.TimePickerDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;
import top.maweihao.weather.contract.PreferenceConfigContact;
import top.maweihao.weather.contract.WeatherData;
import top.maweihao.weather.model.WeatherRepository;
import top.maweihao.weather.service.PushService;
import top.maweihao.weather.util.LogUtils;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.util.Constants.ChooseCode;
import static top.maweihao.weather.util.Constants.SettingCode;
import static top.maweihao.weather.util.Constants.isSetResultIntent;

public class SettingActivity extends AppCompatActivity {

    public static final String TIME_SPLIT = " : ";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //记录原始配置和改变后的配置，判断自动定位是否发生改变；
    private static boolean originalAutoLocate;
    private static boolean changeAutoLocate;
    private WeatherData model;
    private static String countyName;
    private static final String alipayUrl =
            "alipayqr://platformapi/startapp?saId=10000007&qrcode=https://qr.alipay.com/a6x07374sqhbxyur624d77e";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        ButterKnife.bind(this);
        toolbar.setTitle(getResources().getString(R.string.setting));
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        setSupportActionBar(toolbar);
        model = WeatherRepository.getInstance(this);
        countyName = (model.getLocationCached() == null ? "" : model.getLocationCached().getCoarseLocation());
        getFragmentManager().beginTransaction()
                .replace(R.id.setting_frameLayout, new PrefsFragment())
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (originalAutoLocate != changeAutoLocate) {
            isSetResultIntent = true;
            Intent intent = new Intent();
            intent.putExtra("autoLocate", changeAutoLocate);
            setResult(SettingCode, intent);
        }
        finish();
    }

    public static class PrefsFragment extends PreferenceFragment {

        private ListPreference temLp;
        private Preference aboutPreference;
        private Preference choosePositionPreference;
        private Preference feedBack;
        private Preference donate;

        private SwitchPreference autoUpdateSP;
        private SwitchPreference notification;
        private Preference notificationTime;

        private PreferenceConfigContact configContact;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settingpreference);
            configContact = Utility.createSimpleConfig(getActivity()).create(PreferenceConfigContact.class);
            changeAutoLocate = originalAutoLocate = configContact.getAutoLocate(false);
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

            donate = findPreference("donate");
            donate.setOnPreferenceClickListener(enterActivityListener);

            choosePositionPreference.setOnPreferenceClickListener(enterActivityListener);
            autoUpdateSP = (SwitchPreference) findPreference("auto_locate");
            autoUpdateSP.setOnPreferenceChangeListener(changeListener);
            aboutPreference.setOnPreferenceClickListener(enterActivityListener);

            notification = (SwitchPreference) findPreference("notification");
            notification.setOnPreferenceChangeListener(changeListener);

            notificationTime = findPreference("notification_time");
            notificationTime.setOnPreferenceClickListener(new NotificationTimePreferenceClickListener());

            if (notification.isChecked()) {
                notificationTime.setEnabled(true);
                notificationTime.setSummary(configContact.getNotificationTime("18 : 00"));
            } else
                notificationTime.setEnabled(false);

            if (autoUpdateSP.isChecked()) {
                LogUtils.d( "initViews: autoUpdate is checked");
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
                LogUtils.d( "onPreferenceChange: " + stringValue);
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
                        changeAutoLocate = true;
                        choosePositionPreference.setEnabled(false);
                        choosePositionPreference.setSummary(null);
                        choosePositionPreference.setSummary(getResources().getString(R.string.select_disabled));
                    } else {
                        changeAutoLocate = false;
                        choosePositionPreference.setEnabled(true);
                        if (!TextUtils.isEmpty(countyName)) {
                            choosePositionPreference.setSummary(getResources().getString(R.string.selected_county) + countyName);
                        }
                    }
                    return true;
                } else if (preference.getKey().equals("notification")) {
                    Intent startIntent = new Intent(getActivity(), PushService.class);
                    if (stringValue.equals("true")) {
                        notificationTime.setEnabled(true);
                        notificationTime.setSummary(configContact.getNotificationTime("18 : 00"));
                        PushService.isStarSendNotification = false;
                        getActivity().startService(startIntent);
                        LogUtils.d("onPreferenceChange: start SyncService");
                    } else {
                        notificationTime.setEnabled(false);
                        getActivity().stopService(startIntent);
                        LogUtils.d("onPreferenceChange: stop SyncService");
                    }
                    return true;
                } else if (preference.getKey().equals("notification_time")) {
                    LogUtils.d("onPreferenceChange: notification_time");
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
                } else if (preference.getKey().equals("choose_position")) {
                    Intent intent = new Intent(getActivity(), ChoosePositionActivity.class);
                    startActivityForResult(intent, 1);
                } else if (preference.getKey().equals("feedback")) {
                    try {
                        Intent email = new Intent(Intent.ACTION_SENDTO);
                        email.setData(Uri.parse("mailto:hellowello1996@outlook.com"));
                        email.putExtra(Intent.EXTRA_SUBJECT, "反馈: 速知天气");
//                        email.putExtra(Intent.EXTRA_TEXT, "Feedback: ");
                        startActivity(email);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getActivity(), getResources().getString(R.string.email_needed),
                                Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                } else if (preference.getKey().equals("donate")) {
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageResource(R.drawable.wechat_donate_qrcode);

                    final AlertDialog.Builder weChatDialog = new AlertDialog.Builder(getActivity());
                    weChatDialog.setTitle(R.string.donate_with_wechat)
                            .setMessage(R.string.info_wechat_donate)
                            .setView(imageView)
                            .setNegativeButton(R.string.cancel, null);

                    final AlertDialog.Builder chooseMethod = new AlertDialog.Builder(getActivity());
                    chooseMethod.setTitle(R.string.choose_donate_method)
                            .setItems(new String[]{"Alipay", "WeChat"}, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    switch (which) {
                                        case 0:
                                            try {
                                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                                intent.setData(Uri.parse(alipayUrl));
                                                startActivity(intent);
                                            } catch (ActivityNotFoundException e) {
                                                Toast.makeText(getActivity(),
                                                        getResources().getString(R.string.alipay_needed),
                                                        Toast.LENGTH_LONG).show();
                                                e.printStackTrace();
                                            }
                                            break;
                                        case 1:
                                            AlertDialog alertDialog = weChatDialog.create();
                                            alertDialog.show();
                                    }
                                }
                            })
                            .setNegativeButton(R.string.cancel, null);

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                    alertDialogBuilder.setTitle(getResources().getString(R.string.donate))
                            .setMessage(getResources().getString(R.string.donate_info))
                            .setNegativeButton(R.string.cancel, null)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    AlertDialog alertDialog = chooseMethod.create();
                                    alertDialog.show();
                                }
                            });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
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
                                configContact.applyNotificationTime(formatHour + TIME_SPLIT + formatMinute);
                                PushService.isStarSendNotification = false;
                                Intent startIntent = new Intent(getActivity(), PushService.class);
                                getActivity().startService(startIntent);
                            }
                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timeDialog.show();
                return false;
            }
        }
    }
}
