package cn.com.tianyudg.loadingimageview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

/**
 * Author : WaterFlower.
 * Created on 2017/9/14.
 * Desc :
 */

public class LoadingImageView extends ImageView implements ViewTreeObserver.OnGlobalLayoutListener {

    private static final String TAG = "LoadingImageView";
    private float progress;
    private int mWidth;
    private int mHeight;
    private int xCenter;
    private int yCenter;
    private int mRadius;
    private Paint mPaint;
    private Paint mTextPaint;

    private RectF mBorder;
    
    private int mColor=Color.parseColor("#00999999");
    private int mTextColor=Color.BLACK;
    private float mStrokeWidth=20f;
    private float mTextSize=30f;


    public void setProgress(float progress) {
        this.progress = progress;
        Log.e(TAG, "setProgress: progress=" + progress);
        invalidate();
    }

    public LoadingImageView(Context context) {
        this(context,null);
//        initPaint();
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
//        initPaint();
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr,0);
//        initPaint();
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        initPaint();
    }

    private void initPaint() {
        mPaint = new Paint();
        mTextPaint = new Paint();

        mPaint.setAntiAlias(true);
        mTextPaint.setAntiAlias(true);

        mPaint.setColor(mColor);
        mTextPaint.setColor(mTextColor);

        mPaint.setStrokeWidth(mStrokeWidth);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setStrokeWidth(mStrokeWidth);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        initParams();
    }

    private void initParams() {
        mWidth = getWidth();
        mHeight = getHeight();
        if (mHeight == 0 || mWidth == 0) return;
        xCenter = mWidth / 2;
        yCenter = mHeight / 2;
        mRadius = mHeight > mWidth ? mWidth / 3 : mHeight / 3;
        mBorder = new RectF(xCenter - mRadius, yCenter - mRadius, xCenter + mRadius, yCenter + mRadius);
        Log.e(TAG, "onGlobalLayout: mRadius=" + mRadius);
    }


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mRadius == 0 || progress >= 1) {
            return;
        }

        canvas.drawArc(mBorder, 0, 360 * progress, true, mPaint);
        String text = Math.round(progress * 100) + "%";
        float textWidth = mTextPaint.measureText(text);
        canvas.drawText(text, xCenter - (textWidth / 2), yCenter, mTextPaint);

    }


}