package com.comet.bezier;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {
    View v_one;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        v_one = findViewById(R.id.v_one);
    }

    public void oneClick(View view) {
        path2();
    }


    public void path3() {
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
                pointF.set((endValue.x - startValue.x) * fraction, (endValue.y - startValue.y) * fraction);
                return pointF;
            }

        }, new PointF(0, 0), new PointF(500, 500));
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                v_one.setX(pointF.x);
                v_one.setY(pointF.y);
            }
        });
        valueAnimator.setDuration(4000);
        valueAnimator.start();
    }

    public void path2() {
        final float[] position = new float[2];
        final Path path = new Path();
        path.moveTo(0, 0);
        path.lineTo(500, 500);
        path.quadTo(200, 500, 0, 1000);
        final PathMeasure pathMeasure = new PathMeasure();
        pathMeasure.setPath(path, false);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, pathMeasure.getLength());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float lenth = (float) animation.getAnimatedValue();
                pathMeasure.getPosTan(lenth, position, null);
                v_one.setX(position[0]);
                v_one.setY(position[1]);
            }
        });
        valueAnimator.setDuration(2000);
        valueAnimator.start();
    }


    public void path1() {
        Path path = new Path();
        path.moveTo(100, 100);
        path.lineTo(500, 500);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(v_one, "x", "y", path);
        objectAnimator.start();
    }
}
