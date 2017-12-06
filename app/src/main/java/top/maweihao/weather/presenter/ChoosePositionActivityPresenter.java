package top.maweihao.weather.presenter;

import java.util.List;

import top.maweihao.weather.contract.ChoosePositionActivityContract;
import top.maweihao.weather.model.ChoosePositionActivityModel;

/**
 * Created by limuyang on 2017/6/2.
 */

public class ChoosePositionActivityPresenter implements ChoosePositionActivityContract.Presenter {
    private ChoosePositionActivityContract.Model model;
    private ChoosePositionActivityContract.View view;

    public ChoosePositionActivityPresenter(ChoosePositionActivityContract.View view) {
        this.view = view;
        model = new ChoosePositionActivityModel(this);
    }

    @Override
    public void closeProgressDialog() {
        view.closeProgressDialog();
    }

    @Override
    public void showToastMsg(String msg) {
        view.showToastMsg(msg);
    }

    @Override
    public void showProgressDialog() {
        view.showProgressDialog();
    }

    @Override
    public void setRecyclerViewData(List<String> dataList) {
        if (dataList != null) {
            view.setRecyclerViewData(dataList);
        }
    }

    @Override
    public void setToolBarTitle(String str) {
        view.setToolBarTitle(str);
    }

    @Override
    public void queryProvinces() {
         model.queryProvinces();
    }

    @Override
    public void queryCounties() {
        model.queryCounties();
    }

    @Override
    public void queryCities()
    {
         model.queryCities();
    }

    @Override
    public void filterListData(List<String> list, String filterStr) {
       view.setRecyclerViewFilterData(model.filterData(list,filterStr));
    }
}
