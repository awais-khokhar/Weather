package top.maweihao.weather.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.GregorianCalendar;

import top.maweihao.weather.R;
import top.maweihao.weather.util.LogUtils;

/**
 * view for sun trace
 * Created by maweihao on 02/11/2017.
 */

public class SunTraceView extends View {
    private static final String TAG = SunTraceView.class.getSimpleName();

    private int centerX, centerY;
    PointF start, end, controlA, controlB;  //left
    PointF start2, controlA2, controlB2;  //right
    PointF sun;
    PathMeasure pathMeasure;

    private Paint pacePaint;
    private Paint sunPaint;
    private Paint horizonPaint;
    private Path tracePath;
    private Path horizonPath;

    int hour = 16;
    int minute = 21;
    int riseHour = 6;
    int riseMinute = 10;
    int setHour = 18;
    int setMinute = 21;
    int minuteInADay = 24 * 60;

    float length, darkLength;
    float[] point = new float[2];
    float[] tan = new float[2];
    float[] sunFinalPoint = new float[2];

    public SunTraceView(Context context) {
        super(context);
        initPaint();
    }

    public SunTraceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunTraceView);
        // TODO: 12/12/2017
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        LogUtils.d("SunTraceView: init");
        Calendar calendar = new GregorianCalendar();
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);

        pacePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        pacePaint.setColor(Color.BLACK);
        pacePaint.setStrokeWidth(8);
        pacePaint.setStyle(Paint.Style.STROKE);

        sunPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        sunPaint.setColor(Color.BLACK);
        sunPaint.setStyle(Paint.Style.FILL);

        horizonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        horizonPaint.setColor(Color.GRAY);
        horizonPaint.setStrokeWidth(6);

        start = new PointF(0, 0);
        end = new PointF(0, 0);
        controlA = new PointF(0, 0);
        controlB = new PointF(0, 0);
        start2 = new PointF(0, 0);
//        end2 = new PointF(0, 0);
        controlA2 = new PointF(0, 0);
        controlB2 = new PointF(0, 0);

        pathMeasure = new PathMeasure();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        Log.d(TAG, "onSizeChanged: ");
        super.onSizeChanged(w, h, oldw, oldh);
        int small = Math.min(w, h);
        centerX = w / 2;
        centerY = h / 2;

        start.set(centerX - small / 2 + 50, centerY + small / 2 - 50);
        end.set(centerX, centerY - small / 2 + 50);
        start2.set(centerX + (centerX - start.x), start.y);

        float width2 = end.x - start.x;
        float height2 = start.y - end.y;

        controlA.set(start.x + width2 / 2, start.y + 0.00f * height2);
        controlA2.set(centerX + (centerX - controlA.x), controlA.y);

        controlB.set(start.x + 0.6f * width2, 0.06f * height2 + end.y);
        controlB2.set(centerX + (centerX - controlB.x), controlB.y);

        tracePath = new Path();
        tracePath.moveTo(start.x, start.y);
        tracePath.cubicTo(controlA.x, controlA.y, controlB.x, controlB.y, end.x, end.y);
//        tracePath.moveTo(start2.x, start2.y);
        tracePath.cubicTo(controlB2.x, controlB2.y, controlA2.x, controlA2.y, start2.x, start2.y);

        horizonPath = new Path();
        sun = new PointF(start.x, start.y);

        pathMeasure.setPath(tracePath, false);
        length = pathMeasure.getLength();
        darkLength = ((float) (darkTime() / 2)) / minuteInADay * length;
        pathMeasure.getPosTan(nowLength(length, darkLength), sunFinalPoint, tan);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Log.d(TAG, "onDraw: ");
        super.onDraw(canvas);
        canvas.drawRect(0, 0, getWidth(), getHeight(), horizonPaint); //debug only
        canvas.drawPath(tracePath, pacePaint);

        pathMeasure.setPath(tracePath, false);
        length = pathMeasure.getLength();
        darkLength = ((float) (darkTime() / 2)) / minuteInADay * length;

        pathMeasure.getPosTan(darkLength, point, tan);
        canvas.drawLine(point[0] - 50, point[1],
                (centerX - (point[0] - 50)) + centerX, point[1], horizonPaint);

        pathMeasure.getPosTan(nowLength(length, darkLength), sunFinalPoint, tan);
        canvas.drawCircle(sun.x, sun.y, 13, sunPaint);

    }

    /**
     * set sunrise and sunset time
     *
     * @param riseHour   hour of sunrise
     * @param riseMinute minute of minute
     * @param setHour    hour of sunset
     * @param setMinute  minute of sunset
     */
    public void setTime(int riseHour, int riseMinute, int setHour, int setMinute) {
        this.riseHour = riseHour;
        this.riseMinute = riseMinute;
        this.setHour = setHour;
        this.setMinute = setMinute;
        invalidate();
    }

    public void setPoint(PointF point) {
        sun = point;
        invalidate();
    }

    public PointF getPoint() {
        return sun;
    }

    /**
     * return dark time in a day in minutes
     *
     * @return minutes
     */
    private int darkTime() {
        int before = riseHour * 60 + riseMinute;
        int after = (23 - setHour) * 60 + (60 - setMinute);
        return before + after;
    }

    private float nowLength(float length, float dark) {
        float halfLength = length / 2;
        int minutes = hour * 60 + minute;
        int riseMinutes = riseHour * 60 + riseMinute;
        int setMinutes = setHour * 60 + setMinute;
        int noonMinutes = 12 * 60;
        if (minutes < riseMinutes) {
            return (float) (minutes / riseMinutes) * dark;
        } else if (minutes <= noonMinutes) {
            return (((float) (minutes - riseMinutes)) / (noonMinutes - riseMinutes)) * (halfLength - dark) + dark;
        } else if (minutes <= setMinutes) {
            return ((float) (minutes - noonMinutes) / (setMinutes - noonMinutes)) * (halfLength - dark) + halfLength;
        } else {
            return ((float) (minutes - setMinutes) / (riseMinutes)) * dark + (length - dark);
        }
    }

}
