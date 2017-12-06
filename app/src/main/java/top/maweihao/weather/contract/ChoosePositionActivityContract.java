package top.maweihao.weather.contract;

import java.util.List;

/**
 * Created by limuyang on 2017/6/2.
 */

public interface ChoosePositionActivityContract {
    interface View {
        void showProgressDialog();

        void closeProgressDialog();

        void showToastMsg(String msg);

        void setRecyclerViewData(List<String> dataList);

        void setToolBarTitle(String str);

        void setRecyclerViewFilterData(List<String> dataList);
    }

    interface Presenter {

        void closeProgressDialog();

        void showProgressDialog();

        void showToastMsg(String msg);

        void setRecyclerViewData(List<String> dataList);

        void setToolBarTitle(String str);

        void queryProvinces();

        void queryCounties();

        void queryCities();

        void filterListData(List<String> list, String filterStr);
    }

    interface Model {
        void queryProvinces();

        void queryCounties();

        void queryCities();

        List<String> filterData(List<String> list, String filterStr);
    }
}
