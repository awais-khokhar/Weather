package top.maweihao.weather.activity;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import top.maweihao.weather.R;

public class AboutActivity extends AppCompatActivity {

    public static final String TAG = "AboutActivity";

    private TextView versionTv;
    private String versionName;
    private int versionCode = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        versionTv = (TextView) findViewById(R.id.current_version_tv);
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
            String version = getResources().getString(R.string.current_version)
                    + ": " + versionName + ':' + versionCode;
            versionTv.setText(version);
        }

    }
}
