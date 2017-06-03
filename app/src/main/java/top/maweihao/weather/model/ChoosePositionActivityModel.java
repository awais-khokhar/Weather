package top.maweihao.weather.model;

import android.util.Log;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import top.maweihao.weather.activity.ChoosePositionActivity;
import top.maweihao.weather.contract.ChoosePositionActivityContract;
import top.maweihao.weather.db.City;
import top.maweihao.weather.db.County;
import top.maweihao.weather.db.Province;
import top.maweihao.weather.util.HttpUtil;
import top.maweihao.weather.util.Utility;

import static top.maweihao.weather.activity.ChoosePositionActivity.LEVEL_CITY;
import static top.maweihao.weather.activity.ChoosePositionActivity.LEVEL_COUNTY;
import static top.maweihao.weather.activity.ChoosePositionActivity.LEVEL_PROVINCE;
import static top.maweihao.weather.activity.ChoosePositionActivity.cityList;
import static top.maweihao.weather.activity.ChoosePositionActivity.countyList;
import static top.maweihao.weather.activity.ChoosePositionActivity.provinceList;
import static top.maweihao.weather.activity.ChoosePositionActivity.selectedCity;
import static top.maweihao.weather.activity.ChoosePositionActivity.selectedProvince;

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
            Log.d(TAG, "queryProvinces: adapter has been notified");
            ChoosePositionActivity.currentLevel = LEVEL_PROVINCE;
        } else {
            String address = "http://guolin.tech/api/china";
            queryFromServer(address, "province");
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
                    result = Utility.handleProvinceResponse(responseText);
                } else if ("city".equals(type)) {
                    result = Utility.handleCityResponse(responseText, selectedProvince.getId());
                } else if ("county".equals(type)) {
                    result = Utility.handleCountyResponse(responseText, selectedCity.getId());
                    Log.d(TAG, "onResponse: result: " + result);
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
    public void queryCounties() {
        Log.d(TAG, "queryCounties: on");

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

    @Override
    public void queryCities() {
        presenter.setToolBarTitle(selectedProvince.getProvinceName());

        cityList = DataSupport.where("provinceid = ?", String.valueOf(selectedProvince.getId())).find(City.class);
        if (cityList.size() > 0) {
            dataList.clear();
            for (City city : cityList) {
                dataList.add(city.getCityName());
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
    public List<String> filterData(List<String> list, String filterStr) {
        List<String> filterList = new ArrayList<>();
        for (String str : list) {
            if (str.contains(filterStr)) {
                filterList.add(str);
            }
        }
        return filterList;
    }
}
