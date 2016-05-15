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
import com.example.admin.graduationproject.render.BaseRender;

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

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                int action = motionEvent.getAction();

                if (motionEvent == null) {
                    return false;
                }


                switch (action) {

                    case MotionEvent.ACTION_DOWN:
                        previousX = motionEvent.getX();
                        previousY = motionEvent.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        final float deltaX = motionEvent.getX() - previousX;
                        final float deltaY = motionEvent.getY() - previousY;

                        previousX = motionEvent.getX();
                        previousY = motionEvent.getY();

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
