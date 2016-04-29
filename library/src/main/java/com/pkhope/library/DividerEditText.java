package com.pkhope.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.EditText;

/**
 * Created by pkhope on 2015/8/12.
 */
public class DividerEditText extends EditText {

    private float mLineWidth;
    private int mLineColor;
    private Paint mPaint;

    public DividerEditText(Context context) {
        this(context, null);
    }

    public DividerEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public DividerEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs,R.styleable.EditText,defStyleAttr,0);
        mLineColor = array.getColor(R.styleable.EditText_lineColor,Color.BLACK);
        mLineWidth = array.getFloat(R.styleable.EditText_lineWidth,1.0f);
        array.recycle();

        setGravity(Gravity.TOP);
        setBackgroundDrawable(null);

        mPaint = new Paint();
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int etHeight = getHeight();
        int etWidth = getWidth();
        int lHeight = getLineHeight();
        int baseline = getBaseline() ;
        int lineCnt = getLineCount();
        int pageLineCnt = etHeight / lHeight;
        int offset = lHeight / 5;

        if (lineCnt < pageLineCnt){
            lineCnt = pageLineCnt;
        }

        for(int i = 0; i < lineCnt; i++){

            canvas.drawLine(0,baseline + offset,etWidth,baseline + offset,mPaint);
            baseline += lHeight;

        }
    }


}
