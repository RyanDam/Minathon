package com.rstudio.minathon.minathon.view.custom;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Ryan on 1/7/17.
 */

public class CircleTime extends View {

    public float getProcess() {
        return process;
    }

    int duration = 100;

    Paint backP;
    Paint circleP;
    Paint processP;
    Paint textPaint;

    String time = "00:00";

    int background = Color.argb(255, 23, 197, 88);
    int circleColor = Color.argb((int)(0.2*255), 255, 255, 255);
    int progressColor = Color.argb((int)(0.4*255), 255, 255, 255);

    ValueAnimator vl;

    public void setProcess(float process) {
        this.process = process;
        int tt = (int)(duration * (1 - process));
        int hour = tt / (60*60);
        int minute = (tt - hour*60*60) / 60;
        int second = tt - hour*60*60 - minute*60;
        time = "";
        if (hour > 0) time = time + String.format("%02d:", hour);
        time = time + String.format("%02d:", minute);
        time = time + String.format("%02d", second);
        invalidate();
    }

    float process = 0.0f;

    public CircleTime(Context context) {
        super(context);
        setup();
    }

    public CircleTime(Context context, AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public CircleTime(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setup();
    }

    public void startClock(int duration, final CircleTimeListener ls) {
        this.duration = duration;
        vl = ValueAnimator.ofFloat(0f, 1f);
        vl.setDuration(duration*1000);
        vl.setInterpolator(new LinearInterpolator());
        ls.onClockStart();
        vl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float p = ((float) valueAnimator.getAnimatedValue());
                setProcess(p);
                ls.onClockUpdate(p);
                if ((int)p == 1) ls.onClockEnd();
            }
        });
        vl.start();
    }

    public void stopClock() {
        if (vl != null && vl.isRunning()) {
            vl.end();
        }
    }

    private void setup() {
        circleP = new Paint();
        circleP.setColor(circleColor);
        circleP.setStrokeWidth(30);
        circleP.setStyle(Paint.Style.FILL);
        circleP.setAntiAlias(true);

        processP = new Paint();
        processP.setColor(progressColor);
        processP.setStrokeWidth(30);
        processP.setStyle(Paint.Style.FILL);
        processP.setAntiAlias(true);

        backP = new Paint();
        backP.setColor(background);
        backP.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setARGB(255, 255, 255, 255);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);
        textPaint.setTextSize(180);

//        setBackgroundColor(background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int h = canvas.getHeight();
        int w = canvas.getWidth();
        int r = (int)(((h > w ? w:h) / 2)*0.9);
//        canvas.drawRect(0,0, w, h, backP);
        canvas.drawCircle(w/2, h/2, r, circleP);
        float currentAngle = 360f*process;
        RectF f = new RectF((w - r*2)/2, (h - r*2)/2, (w - r*2)/2 + r*2, (h - r*2)/2 + r*2);
        canvas.drawArc(f, -90, currentAngle, true, processP);
        int xPos = w / 2;
        int yPos = (int) (h / 2 - ((textPaint.descent() + textPaint.ascent()) / 2)) ;
        canvas.drawText(time, xPos, yPos, textPaint);
    }
}
