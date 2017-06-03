package top.maweihao.weather.activity;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import top.maweihao.weather.R;

/**
 * RecyclerView适配器
 * Created by limuyang on 2017/6/2.
 */

public class ChoosePositionRecyclerViewAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public ChoosePositionRecyclerViewAdapter(List<String> data)
    {
        //R.layout.item_activity_choose_position 为RecyclerView中Item的样式
        super(R.layout.item_activity_choose_position, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.name_tv,item);
    }
}
