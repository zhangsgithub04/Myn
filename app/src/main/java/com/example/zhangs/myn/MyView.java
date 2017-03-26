package com.example.zhangs.myn;

/**
 * Created by ZHANGS on 3/25/2017.
 */

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyView extends View {
    Paint paint = new Paint();
    private View mValue;
    private ImageView mImage;
    int w, h;
    int whichone=0;
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint.setColor(Color.BLACK);


        // more stuff
    }

    @Override
    public void onDraw(Canvas canvas) {
        canvas.drawLine(0, 0, 20, 20, paint);
        canvas.drawLine(20, 0, 0, 20, paint);

        Paint paint1 = new Paint();
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.FILL);
        //canvas.drawPaint(paint1);

        paint.setColor(Color.BLUE);
        canvas.drawLine(0, 0, w, h, paint1);
        canvas.drawLine(w, 0, 0, h, paint1);



        if(freeTouched){
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE);
            paint.setColor(Color.RED);
            paint.setStrokeWidth(10);

            myCanvas.drawPath(freePath, paint);
            canvas.drawBitmap(myCanvasBitmap, identityMatrix, null);

        }

        switch(whichone)
        {
            case 0:

                paint.setColor(Color.RED);
                canvas.drawText("0 kal", 50, 85, paint1);
                break;

            case 1:

                canvas.drawColor(Color.BLUE);
                canvas.drawCircle(200, 200, 100, paint);

                break;


        }
        canvas.save();
    }

    boolean freeTouched = false;
    Path freePath;
    Bitmap myCanvasBitmap = null;
    Canvas myCanvas = null;

    Matrix identityMatrix;

    public Bitmap getCanvasBitmap(){

        return myCanvasBitmap;

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        w = MeasureSpec.getSize(widthMeasureSpec);
        h = MeasureSpec.getSize(heightMeasureSpec);

        myCanvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        myCanvas = new Canvas();
        myCanvas.setBitmap(myCanvasBitmap);

        identityMatrix = new Matrix();

        setMeasuredDimension(w, h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){
            case MotionEvent.ACTION_UP:
                freeTouched = false;
                break;
            case MotionEvent.ACTION_DOWN:
                freeTouched = true;
                freePath = new Path();
                freePath.moveTo(event.getX(), event.getY());

                myCanvasBitmap.eraseColor(Color.BLACK);

                break;
            case MotionEvent.ACTION_MOVE:
                freePath.lineTo(event.getX(), event.getY());
                invalidate();
                break;
        }

        return true;
    }

}

