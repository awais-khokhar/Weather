package top.maweihao.weather;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";
    private String mJson;
    private String mUrl;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }
        tv = (TextView) findViewById(R.id.main_text);
        mUrl = initUrl();
        fetchFromInternet();

    }

    private String initUrl() {
        return "https://api.caiyunapp.com/v2/3a9KGv6UhM=btTHY/118.933290,32.111416/realtime.json";
    }

    private void fetchFromInternet() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(mUrl)
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    mJson = response.body().string();
                    showResponse(mJson);
                } catch (IOException e) {
                    Log.e(TAG, "fetchFromInternet: get response error");
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String skycon = null;
                int temperature = 0;
                try {
                    JSONObject allAttributes =  new JSONObject(response);
                    JSONObject weatherResult = allAttributes.getJSONObject("result");
                    skycon = weatherResult.getString("skycon");
                    temperature = weatherResult.getInt("temperature");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "run: parse json error");
                }
                tv.setText(skycon + '\n' + temperature + '℃');
            }
        });
    }
}
