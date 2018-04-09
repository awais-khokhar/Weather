package top.maweihao.weather.view;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceCategory;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import top.maweihao.weather.R;

public class AboutActivity extends AppCompatActivity {

    public static final String TAG = AboutActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.current_version_tv)
    TextView versionTv;

    private String versionName;
    private int versionCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tmp_layout_about);
        ButterKnife.bind(this);
        toolbar.setTitle(R.string.about_setting);
        setSupportActionBar(toolbar);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
        initView();

    }

    private void initView() {
        try {
            PackageManager packageManager = this.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(this.getPackageName(), 0);
            versionName = packageInfo.versionName;
            versionCode = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(versionName)) {
            Log.d(TAG, "onCreate: versionName = null");
        } else {
            String version = getResources().getString(R.string.current_version_, versionName + ':' + versionCode);
            versionTv.setText(version);
        }

//        getFragmentManager().beginTransaction()
//                .replace(R.id.about_frameLayout, new AboutPrefFragment())
//                .commit();

    }

    public static class AboutPrefFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.about_preference);
            Preference yahoo = findPreference("yahoo");
            ((PreferenceCategory)findPreference("data_res")).removePreference(yahoo);
            findPreference("mixiaoxiao_weather").setOnPreferenceClickListener(this);
            findPreference("bumptech_glide").setOnPreferenceClickListener(this);
            findPreference("JakeWharton_butterknife").setOnPreferenceClickListener(this);
            findPreference("google_gson").setOnPreferenceClickListener(this);
            findPreference("netflix_rxjava").setOnPreferenceClickListener(this);

        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            Log.d(TAG, "onPreferenceStartFragment: " + preference.getKey());
            switch (preference.getKey()) {
                case "mixiaoxiao_weather":
                    openUrlInBrowser("https://github.com/Mixiaoxiao/Weather", getActivity());
                    return true;
                case "bumptech_glide":
                    openUrlInBrowser("https://github.com/bumptech/glide", getActivity());
                    return true;
                case "JakeWharton_butterknife":
                    openUrlInBrowser("https://github.com/JakeWharton/butterknife", getActivity());
                    return true;
                case "google_gson":
                    openUrlInBrowser("https://github.com/google/gson", getActivity());
                    return true;
                case "netflix_rxjava":
                    openUrlInBrowser("https://github.com/ReactiveX/RxJava", getActivity());
                    return true;
            }
            return false;
        }

        private void openUrlInBrowser(String uri, Context context) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(uri));
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Log.e(TAG, "can't open uri " + uri);
            }

        }


    }
}
