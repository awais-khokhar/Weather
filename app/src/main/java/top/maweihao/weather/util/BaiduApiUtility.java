package top.maweihao.weather.util;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.R;

/**
 * Created by ma on 17-3-24.
 * some methods about Baidu web api
 */

public class BaiduApiUtility {

    private static String TAG = "BaiduApiUtility";


//    通过坐标查询城市名称，并设置 WeatherActivity 中的 “location” 值
//    停用！
    public static void setCountyByCoordinate(String coordinate) {
        final String[] county = new String[1];
        String url;
        if (!TextUtils.isEmpty(coordinate)) {
            String[] part = coordinate.split(",");
            String reverseCoordinate = part[1] + ',' + part[0];
            Log.d(TAG, "setCountyByCoordinate: reverseCoordinate: " + reverseCoordinate);
            url = "http://api.map.baidu.com/geocoder/v2/?location=" + reverseCoordinate + "&output=json&pois=1&ak=eTTiuvV4YisaBbLwvj4p8drl7BGfl1eo";
        } else {
            Log.e(TAG, "setCountyByCoordinate: coordinate == null");
            return;
        }
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                try {
                    JSONObject text = new JSONObject(responseText);
                    JSONObject result = text.getJSONObject("result");
                    JSONObject addressComponent = result.getJSONObject("addressComponent");
                    county[0] = addressComponent.getString("district");
                    Log.d(TAG, "onResponse: countyName: " + county[0]);
                    final Activity currentActivity = Utility.getCurrentActivity();
                    if (currentActivity != null && currentActivity.getLocalClassName().equals("WeatherActivity")) {
                        currentActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView position_text = (TextView) currentActivity.findViewById(R.id.position_text);
                                position_text.setText(county[0]);
                            }
                        });
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
