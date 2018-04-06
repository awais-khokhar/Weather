package top.maweihao.weather.android_view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import top.maweihao.weather.util.Utility;

/**
 * blank FrameLayout which height equals StatusBarHeight
 * Created by ma on 17-8-25.
 */

public class StatusBarView extends FrameLayout {

    public StatusBarView(Context context) {
        super(context);
    }

    public StatusBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(
                getResources().getDisplayMetrics().widthPixels,
                Utility.getStatusBarHeight(getResources()));
    }
}
