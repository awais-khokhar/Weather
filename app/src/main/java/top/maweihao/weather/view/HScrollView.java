package top.maweihao.weather.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

/**
 * view for Horizontal ScrollView
 * Created by ma on 17-5-20.
 */

public class HScrollView extends HorizontalScrollView {

    private OnMyHScrollView mOnMyHScrollView;

    // 定义接口
    public interface OnMyHScrollView {
        void onMyScrollChanged(int l, int t, int oldl, int oldt);
    }

    //接口设置监听器
    public void setmOnMyHScrollView(OnMyHScrollView mOnMyHScrollView) {
        this.mOnMyHScrollView = mOnMyHScrollView;
    }


    public HScrollView(Context context) {
        super(context);
    }

    public HScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public HScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
//        super(context, attrs, defStyleAttr, defStyleRes);
//    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mOnMyHScrollView != null) {
            mOnMyHScrollView.onMyScrollChanged(l, t, oldl, oldt);
        }
    }

}
