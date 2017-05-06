package top.maweihao.weather;

import android.content.Intent;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.util.Log;

import static org.litepal.LitePalApplication.getContext;

public class SettingActivity extends PreferenceActivity {

    public static final String TAG = "SettingActivity";

    private static Preference.OnPreferenceChangeListener changeSummaryListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            String stringValue = newValue.toString();
            Log.d(TAG, "onPreferenceChange: " + stringValue);

            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int index = listPreference.findIndexOfValue(stringValue);

                // Set the summary to reflect the new value.
                preference.setSummary(
                        index >= 0
                                ? listPreference.getEntries()[index]
                                : null);
            }
            return true;
        }
    };

    private static Preference.OnPreferenceClickListener enterActivityListener = new Preference.OnPreferenceClickListener() {
        @Override
        public boolean onPreferenceClick(Preference preference) {
            if (preference.getKey().equals("about")) {
                Intent intent = new Intent(getContext(), AboutActivity.class);
                getContext().startActivity(intent);
            }
            return true;
        }
    };

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

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.settingpreference);
            initViews();
        }

        private void initViews() {
            temLp = (ListPreference) findPreference("isCelsius");
            aboutPreference = findPreference("about");
            aboutPreference.setOnPreferenceClickListener(enterActivityListener);
            temLp.setSummary(temLp.getEntry());
            temLp.setOnPreferenceChangeListener(changeSummaryListener);

        }

    }
}
