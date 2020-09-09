package com.ak.facedetectionapp.Helper;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

// for rectangloral on detected faces
public class RectOverlay extends GraphicOverlay.Graphic {
    private int mRectColor = Color.GREEN;
    private float mStrokeWidth=4.0f;
    private Paint mRactPaint;
    private GraphicOverlay graphicOverlay;
    private Rect rect;

    public RectOverlay(GraphicOverlay graphicOverlay,Rect rect) {
        super(graphicOverlay);

        mRactPaint=new Paint();
        mRactPaint.setColor(mRectColor);
        mRactPaint.setStyle(Paint.Style.STROKE);
        mRactPaint.setStrokeWidth(mStrokeWidth);

        this.graphicOverlay=graphicOverlay;
        this.rect=rect;

        postInvalidate();
    }

    @Override
    public void draw(Canvas canvas) {
        RectF rectF =new RectF(rect);
        rectF.left=translateX(rectF.left);
        rectF.right=translateX(rectF.right);
        rectF.top=translateX(rectF.top);
        rectF.bottom=translateX(rectF.bottom);

        canvas.drawRect(rectF,mRactPaint);

    }
}
