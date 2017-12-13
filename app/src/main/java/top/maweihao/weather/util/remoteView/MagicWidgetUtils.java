package top.maweihao.weather.util.remoteView;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;

/**
 * utility class for MagicWidget
 * Created by maweihao on 12/12/2017.
 */

public class MagicWidgetUtils {

    private static final String TAG = MagicWidgetUtils.class.getSimpleName();

    public static Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();
        Log.d(TAG, "getViewBitmap: " + cacheBitmap);

        return Bitmap.createBitmap(cacheBitmap);
    }
}
