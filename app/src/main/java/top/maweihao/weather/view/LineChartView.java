package top.maweihao.weather.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import java.util.ArrayList;

import top.maweihao.weather.R;
import top.maweihao.weather.WeatherActivity;

/**
 * View for LineChartView
 * Created by ma on 17-5-20.
 */

public class LineChartView extends View implements HScrollView.OnMyHScrollView {

    private static final String TAG = "LineChartView";

    //X轴  每个刻度的间距间距
    private int mxInterval = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40, getResources().getDisplayMetrics());
    //Y轴距离view长度
    private int mLeftInterval = 50;
    //天气与X轴距离
//    private int mWeatherToXaxis = 20;
    private int mHeight;
    //线的颜色
    private int mLineColor = Color.parseColor("#24c2f0");

    //X轴的文字 (时间)
    private ArrayList mXAxis;
    //点 (温度)
    private ArrayList<Integer> mYAxis;
    // 天气
    private ArrayList<String> mWeather;

    private ArrayList<Float> precipitation;
    //滑动的距离
    private float mScrollLeft;

    private RectF rectF = new RectF();

    private Paint axisPaint;
    private Paint xlinePaint;
    private Paint pointPaint;
    private Paint linePaint;

    public LineChartView(Context context) {
        this(context, null);
        initPaint();
    }

    public LineChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        initPaint();
    }

    public LineChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //自定义view 像素 宽度和高度
        Log.d(TAG, "widthSize:" + widthSize + ",heightSize:" + heightSize);
        //高度固定  150dp  == 304 px
        mHeight = heightSize;
//        if (mXAxis == null) {
//            Log.d(TAG, "mWidth:" + mWidth + ",mHeight:" + mHeight + "mXAxis:" + mXAxis);
//            return;
//        }
        //宽度通过数组长度计算
        int mWidth = mxInterval * (24 - 1) + mLeftInterval * 2;
        //1185dp  150dp
        setMeasuredDimension(mWidth, mHeight);
    }

    private void initPaint() {
        axisPaint = new Paint();
        xlinePaint = new Paint();
        pointPaint = new Paint();
        linePaint = new Paint();

        mXAxis = new ArrayList<>();
        mYAxis = new ArrayList<>();
        mWeather = new ArrayList<>();
        precipitation = new ArrayList<>();
//        mXAxis.add("N/A");
//        mYAxis.add(10);
//        mWeather.add("晴");
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (mXAxis == null || mYAxis == null || mWeather == null) {
//            return;
//        }
        if (mXAxis.size() == 0 || mYAxis.size() == 0) {
            Log.e(TAG, "数据异常");
            return;
        }
        //画坐标线的轴
        float mYAxisFontSize = 30;
        axisPaint.setTextSize(mYAxisFontSize);
        axisPaint.setColor(Color.parseColor("#000000"));
        //画 X 轴
        xlinePaint.setColor(mLineColor);
        xlinePaint.setAntiAlias(true);
        //设置线条宽度
        float mStrokeWidth = 4.0f;
        xlinePaint.setStrokeWidth(mStrokeWidth);
        //画时间线条
        int mBottomInterval = 50;
        canvas.drawLine(mLeftInterval, mHeight - mBottomInterval, (mXAxis.size() - 1) * mxInterval + mLeftInterval, mHeight - mBottomInterval, xlinePaint);

        //x轴的刻度集合
        int[] xPoints = new int[mXAxis.size()];

        for (int i = 0; i < mXAxis.size(); i++) {
            float timeWidth = axisPaint.measureText((String) mXAxis.get(i)) / 2; //时间宽度一半
            float xfloat = i * mxInterval + mLeftInterval - timeWidth;
            //画时间
            canvas.drawText((String) mXAxis.get(i), xfloat, mHeight - mBottomInterval + mYAxisFontSize, axisPaint);
            xPoints[i] = (int) (xfloat + timeWidth);
        }
        //画笔 圆点
        pointPaint.setColor(mLineColor);
        pointPaint.setStrokeWidth((float) 3.0);
        pointPaint.setStyle(Paint.Style.STROKE);
//            pointPaint.setStyle(Paint.Style.FILL);
        //画笔 线条
        linePaint.setColor(mLineColor);
        linePaint.setAntiAlias(true);
        //设置线条宽度
        linePaint.setStrokeWidth(mStrokeWidth);

        int count = 1; //相同天气个数
        //屏幕宽度
        DisplayMetrics metrics = getContext().getResources().getDisplayMetrics();
        int mScreenWidth = metrics.widthPixels;
        for (int i = 0; i < mXAxis.size(); i++) {
            int xx = mYAxis.get(i) - mYAxis.get(0);//和第一个温度的差
            int yAxisCentre = (mHeight - mBottomInterval) / 2;  // Y轴中心点
            int myInterval = 5;
            if (xx != 0) {
                yAxisCentre = yAxisCentre - xx * myInterval;
            }
            //画圆点
            float mPointRadius = 5;
            canvas.drawCircle(xPoints[i], yAxisCentre, mPointRadius, pointPaint);
            int tempWidth = (int) axisPaint.measureText("" + mYAxis.get(i)); //温度字体宽度
            //画温度
            int tempToPoint = 12;
            canvas.drawText(mYAxis.get(i) + "°", xPoints[i] - mPointRadius - tempWidth / 2, yAxisCentre - mPointRadius - tempToPoint, axisPaint);
            int yAxisCentre2 = (mHeight - mBottomInterval) / 2; // Y轴中心点
            int rightDifference = (int) (xPoints[i] - mScreenWidth - mScrollLeft);//右边看不见的点与屏幕的距离


            int IW = 60;
            int mLineToPoint = 10;
            if (0 == i) {
//                    //画虚线  第一条
                PaintDashed(canvas, xPoints[i], (int) (yAxisCentre + mPointRadius), xPoints[i], mHeight - mBottomInterval);
            } else if (i < (mXAxis.size() - 1)) {
                int xx2 = mYAxis.get(i - 1) - mYAxis.get(0);
                if (xx2 != 0) {
                    yAxisCentre2 = yAxisCentre2 - xx2 * myInterval;
                }
                //温度之间 短线
                canvas.drawLine(xPoints[i - 1] + mLineToPoint, yAxisCentre2, xPoints[i] - mLineToPoint, yAxisCentre, linePaint);
                //画天气
                //画虚线
                //虚线是天气区域的分割线
                //从第二个天气开始 循环判断天气,不同的时候才 画虚线 画天气
                if (!mWeather.get(i).equals(mWeather.get(i - 1))) {
                    //画虚线
                    PaintDashed(canvas, xPoints[i], (int) (yAxisCentre + mPointRadius), xPoints[i], mHeight - mBottomInterval);

                    float weatherFontSize = axisPaint.measureText((String) mWeather.get(i - count));//天气字体宽度
                    if (mScrollLeft > mLeftInterval) { //左边   滑动大于边距50时候 天气移动 始终在在中间
                        if (xPoints[i] - mScrollLeft < count * mxInterval) {
                            if (mScrollLeft < xPoints[i] - weatherFontSize) { //判断当天气移动到虚线边界时候 就不移动
                                float is2 = (xPoints[i - count] + count * mxInterval - mScrollLeft) / 2 + mScrollLeft;
//                                canvas.drawText(mWeather.get(i - count), is2 - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint); // 20  天气字体与x轴距离
                                rectF.set(is2 - mYAxisFontSize, mHeight - mBottomInterval - IW, is2 - mYAxisFontSize + IW, mHeight - mBottomInterval);
                                canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                            } else {
//                                canvas.drawText(mWeather.get(i - count), xPoints[i] - weatherFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                                rectF.set(xPoints[i] - weatherFontSize, mHeight - mBottomInterval - IW, xPoints[i] - weatherFontSize + IW, mHeight - mBottomInterval);
                                canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                            }
                        } else if (rightDifference > 0) {//    右边天气位置  滑动时候
                            if (mScreenWidth + mScrollLeft > xPoints[i - count] + weatherFontSize) {//判断当天气移动到虚线边界时候 就不移动
                                float x = mScreenWidth + mScrollLeft - (count * mxInterval - rightDifference) / 2;
//                                canvas.drawText(mWeather.get(i - count), x - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                                rectF.set(x - mYAxisFontSize, mHeight - mBottomInterval - IW, x - mYAxisFontSize + IW, mHeight - mBottomInterval);
                                canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                            } else {
//                                canvas.drawText(mWeather.get(i - count), xPoints[i - count], mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                                rectF.set(xPoints[i - count], mHeight - mBottomInterval - IW, xPoints[i - count] + IW, mHeight - mBottomInterval);
                                canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                            }
                        } else {//天气位置  中间天气区域
//                            canvas.drawText(mWeather.get(i - count), xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                            rectF.set(xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - IW, xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize + IW, mHeight - mBottomInterval);
                            canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                        }

                    } else { //天气位置  初始化 没有滑动时候
//                        canvas.drawText(mWeather.get(i - count), xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                        rectF.set(xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - IW, xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize + IW, mHeight - mBottomInterval);
                        canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                    }
                    count = 1;
                } else {
                    count = count + 1; //每个时间段天气相同就加1
                }
            } else if ((mXAxis.size() - 1) == i) {  //最后一个区域
                float weatherFontSize = axisPaint.measureText((String) mWeather.get(i));//天气字体宽度
                //温度之间 短线
                canvas.drawLine(xPoints[i - 1] + mLineToPoint, yAxisCentre2, xPoints[i] - mLineToPoint, yAxisCentre, linePaint);
                //画天气  最后一个天气区域
                if (rightDifference > 0) {//    //最有右边天气位置  滑动时候
                    if (mScreenWidth + mScrollLeft > xPoints[i - count] + weatherFontSize) {
                        float x = mScreenWidth + mScrollLeft - (count * mxInterval - rightDifference) / 2;
//                        canvas.drawText(mWeather.get(i - count), x - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                        rectF.set(x - mYAxisFontSize, mHeight - mBottomInterval - IW, x - mYAxisFontSize + IW, mHeight - mBottomInterval);
                        canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                    } else {
//                        canvas.drawText(mWeather.get(i - count), xPoints[i - count], mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                        rectF.set(xPoints[i - count], mHeight - mBottomInterval - IW, xPoints[i - count] + IW, mHeight - mBottomInterval);
                        canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                    }
                } else {
//                    canvas.drawText(mWeather.get(i - count), xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - mWeatherToXaxis, axisPaint);
                    rectF.set(xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize, mHeight - mBottomInterval - IW, xPoints[i - count] + count * mxInterval / 2 - mYAxisFontSize + IW, mHeight - mBottomInterval);
                    canvas.drawBitmap(chooseImage(i - count), null, rectF, axisPaint);
                }
                //画虚线  最后一条
                PaintDashed(canvas, xPoints[i], (int) (yAxisCentre + mPointRadius), xPoints[i], mHeight - mBottomInterval);
            }
        }
    }

    /**
     * 画虚线
     */
    private void PaintDashed(Canvas canvas, int moveToX, int moveToY, int lineToX, int lineToY) {
        DashPathEffect pathEffect = new DashPathEffect(new float[]{6, 4}, 0);
        Paint dashPaint = new Paint();
        dashPaint.reset();
        dashPaint.setStyle(Paint.Style.STROKE);
        dashPaint.setStrokeWidth(2);
        dashPaint.setColor(Color.WHITE);
        dashPaint.setAntiAlias(true);
        dashPaint.setPathEffect(pathEffect);
        Path path = new Path();
        path.moveTo(moveToX, moveToY);
        path.lineTo(lineToX, lineToY);
        canvas.drawPath(path, dashPaint);
    }


    /**
     * 设置Y轴文字  温度值
     */
    public void setYItem(ArrayList<Integer> yItem) {
        mYAxis = yItem;
    }

    /**
     * 设置X轴文字
     */
    public void setXItem(ArrayList xItem) {
        mXAxis = xItem;
    }

    /**
     * 设置天气
     */
    public void setWeather(ArrayList weather) {
        mWeather = weather;
    }
    /**
     * 降雨量
     */
    public void setPrecipitation(ArrayList precipitation) {
        this.precipitation = precipitation;
    }

    public void setLineColor(int color) {
        mLineColor = color;
    }

    public void setmHScrollView(HScrollView mHScrollView) {
        Log.d("SimpleLineChart2", "mHScrollView:" + mHScrollView);
        if (mHScrollView == null) {
            return;
        }
        mHScrollView.setmOnMyHScrollView(this);
    }

    public void applyChanges() {
        invalidate();
    }

    /**
     * 监听滑动事件(主要用到与左边的滑动距离)
     */
    @Override
    public void onMyScrollChanged(int l, int t, int oldl, int oldt) {
        this.mScrollLeft = l;
        invalidate();
    }

    private Bitmap chooseImage(String s) {
        return BitmapFactory.decodeResource(getResources(), R.drawable.ic_cloud_queue_black_24dp);
    }

    private Bitmap chooseImage(int index) {
        String skycon = mWeather.get(index);
        float precipitation = this.precipitation.get(index);
        return BitmapFactory.decodeResource(getResources(),
                WeatherActivity.chooseWeatherIconOnly(skycon, precipitation, WeatherActivity.HOURLY_MODE));
    }
}
