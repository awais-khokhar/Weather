package top.maweihao.weather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.preference.SwitchPreference;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

public class SettingActivity extends PreferenceActivity {

    public static final String TAG = "SettingActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new PrefsFragment())
                .commit();
    }

    public static class PrefsFragment extends PreferenceFragment {

        private ListPreference temLp;

        private Preference aboutPreference;

        private Preference choosePositionPreference;

        private Preference feedBack;

        private SwitchPreference autoUpdateSP;

        String countyName;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settingpreference);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            countyName = sharedPreferences.getString("countyName", null);
            initViews();
        }

        private void initViews() {
            temLp = (ListPreference) findPreference("isCelsius");
            aboutPreference = findPreference("about");
            choosePositionPreference = findPreference("choose_position");
            feedBack = findPreference("feedback");
            feedBack.setOnPreferenceClickListener(enterActivityListener);
            choosePositionPreference.setOnPreferenceClickListener(enterActivityListener);
            autoUpdateSP = (SwitchPreference) findPreference("auto_locate");
            autoUpdateSP.setOnPreferenceChangeListener(changeListener);
            aboutPreference.setOnPreferenceClickListener(enterActivityListener);
            temLp.setSummary(temLp.getEntry());
            temLp.setOnPreferenceChangeListener(changeListener);

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
                        choosePositionPreference.setEnabled(false);
                        choosePositionPreference.setSummary(null);
                    } else {
                        choosePositionPreference.setEnabled(true);
                        if (!TextUtils.isEmpty(countyName)) {
                            choosePositionPreference.setSummary(getResources().getString(R.string.selected_county)
                                    + countyName);
                        }
                    }
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
                    return true;
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
                    if (resultCode == RESULT_OK) {
                        getActivity().setResult(RESULT_OK, data);
                        getActivity().finish();
                    }
            }

        }
    }
}
