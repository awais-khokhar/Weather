package top.maweihao.weather.util.remoteView;

import android.graphics.Bitmap;
import android.view.View;

/**
 * utility class for MagicWidget
 * Created by maweihao on 12/12/2017.
 */

public class MagicWidgetUtils {

    private static Bitmap getViewBitmap(View addViewContent) {

        addViewContent.setDrawingCacheEnabled(true);
        addViewContent.measure(
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        addViewContent.layout(0, 0,
                addViewContent.getMeasuredWidth(),
                addViewContent.getMeasuredHeight());
        addViewContent.buildDrawingCache();
        Bitmap cacheBitmap = addViewContent.getDrawingCache();

        return Bitmap.createBitmap(cacheBitmap);
    }
}
