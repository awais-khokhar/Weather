package top.maweihao.weather.android_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.GregorianCalendar;

import top.maweihao.weather.R;

/**
 * View for sun rise and sun set
 * Created by ma on 17-5-17.
 */
@Deprecated
public class SunTimeView extends View {

    public static final String TAG = "SunTimeView";

    private Paint mPaint1;
    private Paint mPaint2;
    private Paint mPaint3;

    private int sunRiseRad;
    private int sunSetRad;
    private int timeRad;

    private int riseHour;
    private int riseMinute;
    private int setHour;
    private int setMinute;

    private int dayColor;
    private int darkColor;
    private int nowColor;

    private RectF rectF;

    public SunTimeView(Context context) {
        super(context);
        initPaint();
    }

    public SunTimeView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SunTimeView);
        riseHour = typedArray.getInt(R.styleable.SunTimeView_riseHour, 0);
        riseMinute = typedArray.getInt(R.styleable.SunTimeView_riseMinute, 0);
        setHour = typedArray.getInt(R.styleable.SunTimeView_setHour, 0);
        setMinute = typedArray.getInt(R.styleable.SunTimeView_setMinute, 0);
        dayColor = typedArray.getColor(R.styleable.SunTimeView_dayColor, Color.parseColor("#FFB300"));
        darkColor = typedArray.getColor(R.styleable.SunTimeView_darkColor, Color.parseColor("#1A237E"));
        nowColor = typedArray.getColor(R.styleable.SunTimeView_nowColor, Color.parseColor("#616161"));
        typedArray.recycle();
        initPaint();
    }

    private void initPaint() {
        Calendar calendar = new GregorianCalendar();
        timeRad = timeToRad(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
        sunRiseRad = timeToRad(riseHour, riseMinute);
        sunSetRad = timeToRad(setHour, setMinute);
        mPaint1 = new Paint();
        mPaint1.setStyle(Paint.Style.STROKE);
        mPaint1.setColor(nowColor);
        mPaint1.setStrokeWidth(15);

        mPaint2 = new Paint();
        mPaint2.setStyle(Paint.Style.STROKE);
        mPaint2.setColor(dayColor);
        mPaint2.setStrokeWidth(20);

        mPaint3 = new Paint();
        mPaint3.setStyle(Paint.Style.STROKE);
        mPaint3.setColor(darkColor);
        mPaint3.setStrokeWidth(20);
        mPaint1.setAntiAlias(true);
        mPaint2.setAntiAlias(true);
        mPaint3.setAntiAlias(true);

        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int width = getMeasuredWidth();
        int height = getMeasuredHeight() - 20;
        int rad = width / 2 - 20;
        int edge = height - rad;
        rectF.set((width / 2 - rad), edge, (width / 2 + rad), height + rad);
//        canvas.drawLine(width / 2, height + rad,
//                (int) (width / 2 + (rad / 2) * Math.cos((double) timeRad)), (int) (height + rad + (rad / 2) * Math.sin((double) timeRad)), mPaint1);
        canvas.drawArc(rectF, timeRad - 180, -1, true, mPaint1);
        canvas.drawArc(rectF, sunSetRad - 180, sunRiseRad - sunSetRad, false, mPaint2);
        canvas.drawArc(rectF, sunRiseRad - 180, -sunRiseRad, false, mPaint3);
        canvas.drawArc(rectF, 0, sunSetRad - 180, false, mPaint3);
        super.onDraw(canvas);
    }

    public void setTime(int riseHour, int riseMinute, int setHour, int setMinute) {
        this.riseHour = riseHour;
        this.riseMinute = riseMinute;
        this.setHour = setHour;
        this.setMinute = setMinute;
        sunRiseRad = timeToRad(riseHour, riseMinute);
        sunSetRad = timeToRad(setHour, setMinute);
        invalidate();
    }

    public void setTime(String rise, String set) {
        String[] riseTimes = rise.split(":");
        String[] setTimes = set.split(":");
        setTime(Integer.parseInt(riseTimes[0]), Integer.parseInt(riseTimes[1]),
                Integer.parseInt(setTimes[0]), Integer.parseInt(setTimes[1]));

    }

    private int timeToRad(int hour, int minute) {
        int minuteOfADay = 24 * 60;
        return 180 * (hour * 60 + minute) / minuteOfADay;
    }
}
