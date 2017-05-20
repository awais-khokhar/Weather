package top.maweihao.weather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import top.maweihao.weather.R;

/**
 * view for pm2.5 and aqi circle
 * Created by ma on 17-5-16.
 */

public class SemiCircleView extends View {

    private int negativeColor;

    private int paintColor;

    private int smallTextSize;

    private int bigTextSize;

    private int value = 0;

    private Paint paint1;
    private Paint paint2;
    private Paint paint3;
    private Paint paint4;
    private RectF rectF;

    private boolean isAQI;
    private int maxValue;
    private String info;

    public SemiCircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SemiCircleView);
        negativeColor = typedArray.getColor(R.styleable.SemiCircleView_negativeColor, Color.parseColor("#BDBDBD"));
        isAQI = typedArray.getBoolean(R.styleable.SemiCircleView_aqi, true);
        maxValue = isAQI ? 500 : 150;
        info = isAQI ? "AQI" : "PM 2.5";
        typedArray.recycle();
        initPaint();
    }

    public SemiCircleView(Context context) {
        super(context);
        initPaint();
    }

    public void initPaint() {
        if (isAQI) {
            paintColor = chooseAQIColor(value);
        } else {
            paintColor = choosePM25Color(value);
        }
        rectF = new RectF();

        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
        paint4 = new Paint();
        paint1.setAntiAlias(true);
        paint2.setAntiAlias(true);
        paint3.setAntiAlias(true);
        paint4.setAntiAlias(true);

        paint1.setColor(negativeColor);
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(7);

        paint2.setColor(paintColor);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(10);

        paint3.setColor(paintColor);
        paint3.setTextAlign(Paint.Align.CENTER);
        bigTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 36, getResources().getDisplayMetrics());
        paint3.setTextSize(bigTextSize);

        paint4.setColor(negativeColor);
        paint4.setTextAlign(Paint.Align.CENTER);
        smallTextSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12, getResources().getDisplayMetrics());
        paint4.setTextSize(smallTextSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int edge = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics());
        rectF.set(edge, edge, width - edge, height - edge);
        canvas.drawArc(rectF, 120, 300 * value / maxValue, false, paint2);
        int ed = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics());
        if (value < maxValue - ed) {
            canvas.drawArc(rectF, 60, -300 * (maxValue - value) / maxValue + ed, false, paint1);
        }
        Paint.FontMetricsInt fontMetrics = paint3.getFontMetricsInt();
        canvas.drawText(String.valueOf(value), width / 2, (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top, paint3);
        canvas.drawText(info, width / 2, height / 2 + smallTextSize + bigTextSize / 2, paint4);
        super.onDraw(canvas);
    }

    private int chooseAQIColor(int value) {
        if (value <= 50) {
            return Color.parseColor("#8BC34A"); //Light Green
        } else if (value <= 100) {
            return Color.parseColor("#FDD835"); //Yellow
        } else if (value <= 150) {
            return Color.parseColor("#FF9800"); //Orange
        } else if (value <= 200) {
            return Color.parseColor("#FF5722"); //Deep Orange
        } else if (value <= 300) {
            return Color.parseColor("#9C27B0"); //Purple
        } else {
            return Color.parseColor("#B71C1C"); //Deep Red
        }
    }

    private int choosePM25Color(int value) {
        if (value <= 11) {
            return Color.parseColor("#9CCC65"); //Lighter Green
        } else if (value <= 23) {
            return Color.parseColor("#8BC34A"); //Light Green
        } else if (value <= 35) {
            return Color.parseColor("#4CAF50"); //Green
        } else if (value <= 41) {
            return Color.parseColor("#FDD835"); //Yellow
        } else if (value <= 47) {
            return Color.parseColor("#FFC107"); //Amber
        } else if (value <= 53) {
            return Color.parseColor("#FF9800"); //Orange
        } else if (value <= 58) {
            return Color.parseColor("#E91E63"); //Pink
        } else if (value <= 64) {
            return Color.parseColor("#F44336"); //Red
        } else if (value <= 70) {
            return Color.parseColor("#B71C1C"); //Deep Red
        } else {
            return Color.parseColor("#311B92"); //Deep Purple
        }
    }

    public void setValue(int value) {
        this.value = value;
        paintColor = isAQI ? chooseAQIColor(value) : choosePM25Color(value);
        paint2.setColor(paintColor);
        paint3.setColor(paintColor);
        invalidate();
    }
}
