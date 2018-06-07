package top.maweihao.weather.android_view.dynamicweather;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.animation.AnimationUtils;

import java.lang.ref.WeakReference;

import top.maweihao.weather.android_view.dynamicweather.BaseDrawer.Type;

public class DynamicWeatherView extends SurfaceView implements SurfaceHolder.Callback {

    //    static final String TAG = "DynamicWeatherView";
    private DrawThread mDrawThread;
    private static final Object OBJ_LOCK = new Object(); //better

    public DynamicWeatherView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private BaseDrawer preDrawer, curDrawer;
    private float curDrawerAlpha = 0f;
    private
    @Type
    int curType = Type.UNKNOWN_D;
    private int mWidth, mHeight;

    private void init() {
        curDrawerAlpha = 0f;
        mDrawThread = new DrawThread(this);
        final SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
        mDrawThread.start();
    }

    private void setDrawer(@NonNull BaseDrawer baseDrawer) {
//        if (baseDrawer == null) {
//            return;
//        }
        curDrawerAlpha = 0f;
        if (this.curDrawer != null) {
            this.preDrawer = curDrawer;
        }
        this.curDrawer = baseDrawer;
        // updateDrawerSize(getWidth(), getHeight());
        // invalidate();
    }

    public void setDrawerType(@Type int type) {
//        if (type == null) {
//            return;
//        }
        // UiUtil.toastDebug(getContext(), "setDrawerType->" + type.name());
        if (type != curType) {
            curType = type;
            setDrawer(BaseDrawer.makeDrawerByType(getContext(), curType));

        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // updateDrawerSize(w, h);
        mWidth = w;
        mHeight = h;
//        Log.i(TAG, "onSizeChanged : w " + w + "  h " + h);
    }


    private void drawSurface(Canvas canvas) {
        final int w = mWidth;
        final int h = mHeight;
        if (w == 0 || h == 0) {
            return;
        }
//        boolean needDrawNextFrame = false;
        // Log.d(TAG, "curDrawerAlpha->" + curDrawerAlpha);
        if (curDrawer != null) {
            curDrawer.setSize(w, h);
            curDrawer.draw(canvas, curDrawerAlpha);
        }
        if (preDrawer != null && curDrawerAlpha < 1f) {
//            needDrawNextFrame = true;
            preDrawer.setSize(w, h);
            preDrawer.draw(canvas, 1f - curDrawerAlpha);
        }
        if (curDrawerAlpha < 1f) {
            curDrawerAlpha += 0.04f;
            if (curDrawerAlpha > 1) {
                curDrawerAlpha = 1f;
                preDrawer = null;
            }
        }
//		 if (needDrawNextFrame) {
//		 ViewCompat.postInvalidateOnAnimation(this);
//		 }
//        return needDrawNextFrame;
    }

    public void onResume() {
        // Let the drawing thread resume running.
        if (!mDrawThread.mRunning)
            synchronized (OBJ_LOCK) {
                mDrawThread.mRunning = true;
                OBJ_LOCK.notify();
//                Log.i(TAG, "onResume");
            }

    }

    public void onPause() {
        // Make sure the drawing thread is not running while we are paused.
        if (mDrawThread.mRunning)
//            synchronized (OBJ_LOCK) {
            mDrawThread.mRunning = false;
//                OBJ_LOCK.notify();
//                Log.i(TAG, "onPause");
//            }

    }

    public void onDestroy() {
        // Make sure the drawing thread goes away.
//        mDrawThread.mQuit = true;
//        synchronized (OBJ_LOCK) {
        mDrawThread.mQuit = true;
//            OBJ_LOCK.notify();
//        }
//        Log.i(TAG, "onDestroy");
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Tell the drawing thread that a surface is available.
        synchronized (OBJ_LOCK) {
            mDrawThread.mSurface = holder;
            OBJ_LOCK.notify();
        }
//        Log.i(TAG, "surfaceCreated");
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.i("DynamicWeatherView", "surfaceDestroyed");
        // We need to tell the drawing thread to stop, and block until
        // it has done so.
        mDrawThread.mSurface = holder;

        mDrawThread.mRunning = false;


        holder.removeCallback(this);

        curDrawer = null;
        preDrawer = null;

    }

    private static class DrawThread extends Thread {
        // These are protected by the Thread's lock.
        SurfaceHolder mSurface;
        boolean mRunning;
        boolean mQuit;
        private WeakReference<DynamicWeatherView> viewWeakReference;

        DrawThread(DynamicWeatherView view){
            this.viewWeakReference = new WeakReference<>(view);
        }

        @Override
        public void run() {
            Canvas canvas;
            long startTime;
            while (!mQuit) {
                // Log.i(TAG, "DrawThread run..");
                // Synchronize with activity: block until the activity is ready
                // and we have a surface; report whether we are active or
                // inactive
                // at this point; exit thread when asked to quit.
                synchronized (OBJ_LOCK) {
                    if (mSurface == null || !mRunning) {
//                        Log.i(TAG, "wait()");
                        try {
                            OBJ_LOCK.wait();
                        } catch (InterruptedException e) {
                        }
                    } else {

                        startTime = AnimationUtils.currentAnimationTimeMillis();

                        // Lock the canvas for drawing.
                        canvas = mSurface.lockCanvas();

                        if (canvas != null) {
                            canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                            // Update graphics.

                            viewWeakReference.get().drawSurface(canvas);
                            //logger.addSplit("drawSurface");
                            // All done!
                            mSurface.unlockCanvasAndPost(canvas);

                            final long drawTime = AnimationUtils.currentAnimationTimeMillis() - startTime;
                            final long needSleepTime = 16 - drawTime;
//                    Log.v(TAG, "drawSurface drawTime->" + drawTime + " needSleepTime->" + Math.max(0, needSleepTime));// needSleepTime);
                            if (needSleepTime > 0) {
                                try {
                                    Thread.sleep(needSleepTime);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

}
