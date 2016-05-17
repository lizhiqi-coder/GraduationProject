package com.example.admin.graduationproject.activitys;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.render.BaseRender;
import com.example.admin.graduationproject.widget.Constant;

/**
 * Created by admin on 2016/5/5.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setTheme(R.style.AppTheme_NoActionBar);
    }

    protected void initTouchListener(final GLSurfaceView surfaceView, final BaseRender render) {
        surfaceView.setOnTouchListener(new View.OnTouchListener() {
            float previousX, previousY;
            float currentDistance;
            float lastDistance = -1;

            int clickCount = 0;

            long firstClickTime;
            long secondClickTime;


            @Override
            public boolean onTouch(View view, MotionEvent event) {


                int action = event.getAction();

                if (event == null) {
                    return false;
                }


                switch (action) {

                    case MotionEvent.ACTION_DOWN:

                        if (event.getPointerCount() == 1) {

                            previousX = event.getX();
                            previousY = event.getY();
                            clickCount++;
                            if (clickCount == 1) {
                                firstClickTime = System.currentTimeMillis();
                            } else {
                                secondClickTime = System.currentTimeMillis();
                                if (secondClickTime - firstClickTime < Constant.DOUBLE_CLICK_TIME_INTERVAL) {
                                    render.handleDoubleClick();
                                    surfaceView.queueEvent(new Runnable() {
                                        @Override
                                        public void run() {

                                            render.handleDoubleClick();
                                        }
                                    });
                                }
                                clickCount = 0;
                                firstClickTime = 0;
                                secondClickTime = 0;
                            }


                        }
                        break;

                    case MotionEvent.ACTION_MOVE:

                        if (event.getPointerCount() >= 2) {

                            float offsetX = event.getX(0) - event.getX(1);
                            float offsetY = event.getY(0) - event.getY(1);

                            currentDistance = (float) Math.sqrt(offsetX * offsetX + offsetY * offsetY);
                            if (lastDistance < 0) {
                                lastDistance = currentDistance;
                            } else {

                                Point scaleCenter = new Point(
                                        (event.getX(0) + event.getX(1)) / 2f,
                                        (event.getY(0) + event.getY(1)) / 2f,
                                        0f);

                                if (currentDistance - lastDistance > 5) {

                                    //增加
                                    surfaceView.queueEvent(new Runnable() {
                                        @Override
                                        public void run() {
                                            render.upScale();
                                        }
                                    });

                                    lastDistance = currentDistance;

                                } else if (lastDistance - currentDistance > 5) {

                                    //减少
                                    surfaceView.queueEvent(new Runnable() {
                                        @Override
                                        public void run() {
                                            render.downScale();
                                        }
                                    });
                                    lastDistance = currentDistance;

                                }
                            }

                            previousX = event.getX();
                            previousY = event.getY();

                            break;
                        }


                        final float deltaX = event.getX() - previousX;
                        final float deltaY = event.getY() - previousY;

                        previousX = event.getX();
                        previousY = event.getY();

                        surfaceView.queueEvent(new Runnable() {
                            @Override
                            public void run() {
                                render.handleTouchDrag(deltaX, deltaY);
                            }
                        });

                        break;
                }

                return true;
            }
        });

    }
}
