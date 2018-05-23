package top.maweihao.weather.model;

import android.text.TextUtils;
import android.util.Log;

import com.blankj.utilcode.util.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.contract.ChoosePositionActivityContract;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.view.ChoosePositionActivity;

import static top.maweihao.weather.view.ChoosePositionActivity.LEVEL_CITY;
import static top.maweihao.weather.view.ChoosePositionActivity.LEVEL_COUNTY;
import static top.maweihao.weather.view.ChoosePositionActivity.LEVEL_PROVINCE;
import static top.maweihao.weather.view.ChoosePositionActivity.cityList;
import static top.maweihao.weather.view.ChoosePositionActivity.countyList;
import static top.maweihao.weather.view.ChoosePositionActivity.provinceList;
import static top.maweihao.weather.view.ChoosePositionActivity.selectedCity;
import static top.maweihao.weather.view.ChoosePositionActivity.selectedProvince;

/**
 * Created by limuyang on 2017/6/2.
 */

public class ChoosePositionActivityModel implements ChoosePositionActivityContract.Model {
    public static final String TAG = "ChoosePositionModel";
    private ChoosePositionActivityContract.Presenter presenter;

    private List<String> dataList = new ArrayList<>();

    public ChoosePositionActivityModel(ChoosePositionActivityContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void queryProvinces() {
        presenter.setToolBarTitle("中国");

        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getProvinceName());
                Log.i(TAG, "dataList add " + province.getProvinceName());
            }
            presenter.setRecyclerViewData(dataList);
            LogUtils.d("queryProvinces: adapter has been notified");
            ChoosePositionActivity.currentLevel = LEVEL_PROVINCE;
        } else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address, "province");
        }
    }

    @Override
    public void queryCities() {
        presenter.setToolBarTitle(selectedProvince.getProvinceName());

        cityList = DataSupport.where("provinceid = ?",
                String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
                Log.i(TAG, "dataList add " + city.getCityName());
            }
            presenter.setRecyclerViewData(dataList);
            ChoosePositionActivity.currentLevel = LEVEL_CITY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            String address = "http://guolin.tech/api/china/" + provinceCode;
            queryFromServer(address, "city");
        }
    }

    @Override
    public void queryCounties() {
//        Log.d(TAG, "queryCounties: on");
        presenter.setToolBarTitle(selectedCity.getCityName());
        countyList = DataSupport.where("cityid = ?", String.valueOf(selectedCity.getId())).find(County.class);
//        Log.d(TAG, "queryCounties: countyList.size" + cityList.size());
        if (countyList.size() > 0) {
            dataList.clear();
            for (County county : countyList) {
                dataList.add(county.getCountyName());
            }
            presenter.setRecyclerViewData(dataList);
            ChoosePositionActivity.currentLevel = LEVEL_COUNTY;
        } else {
            int provinceCode = selectedProvince.getProvinceCode();
            int cityCode = selectedCity.getCityCode();
            String address = "http://guolin.tech/api/china/" + provinceCode + "/" + cityCode;
            queryFromServer(address, "county");
        }
    }

    private void queryFromServer(String address, final String type) {
        presenter.showProgressDialog();
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                presenter.closeProgressDialog();
                presenter.showToastMsg("从服务器加载失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseText = response.body().string();
                boolean result = false;
                if ("province".equals(type)) {
                    result = handleProvinceResponse(responseText);
                } else if ("city".equals(type)) {
                    result = handleCityResponse(responseText, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = handleCountyResponse(responseText, selectedCity.getId());
                    LogUtils.d("onResponse: result: " + result);
                }
                if (result) {
                    presenter.closeProgressDialog();
                    if ("province".equals(type)) {
                        queryProvinces();
                    } else if ("city".equals(type)) {
                        queryCities();
                    } else if ("county".equals(type)) {
                        queryCounties();
                    }
                }
            }
        });
    }

    @Override
    public List<String> filterData(List<String> list, String filterStr) {
        List<String> filterList = new ArrayList<>();
        for (String str : list) {
            if (str.contains(filterStr)) {
                filterList.add(str);
            }
        }
        return filterList;
    }

    private boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                    LogUtils.v("saved province: " + provinceObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                LogUtils.e("handleProvinceResponse: JSONObeject error");
            }
        }
        return false;
    }

    private boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);
                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                    LogUtils.v("saved city: " + cityObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                LogUtils.e("handleCityResponse: JSONObject error");
            }
        }
        return false;
    }

    private boolean handleCountyResponse(String response, int cityId) {
        LogUtils.d("handleCountyResponse: ");
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response);
                for (int i = 0; i < allCounties.length(); i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyObject.getString("name"));
                    county.setCityId(cityId);
                    county.save();
                    LogUtils.v("saved county: " + countyObject.getString("name"));
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
                LogUtils.e("handleCountyResponse: JSONObject error");
            }
        }
        return false;
    }
}
