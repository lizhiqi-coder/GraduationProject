package com.example.admin.graduationproject.activitys;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.admin.graduationproject.render.MainRender;
import com.example.admin.graduationproject.utils.AppUtils;

/**
 * Created by admin on 2016/5/5.
 */
public class DisplayActivity extends BaseActivity {

    private GLSurfaceView glSurfaceView;

    private MainRender mRender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        glSurfaceView = new GLSurfaceView(this);

        mRender = new MainRender(this);
        if (AppUtils.isOpenGl2Support()) {
            glSurfaceView.setEGLContextClientVersion(2);

            glSurfaceView.setRenderer(mRender);
        } else {
            Toast.makeText(this, "this device does support opengl ES 2.0", Toast.LENGTH_SHORT).show();

            return;
        }

        setContentView(glSurfaceView);

        initTouchListener();

    }

    private void initTouchListener() {

        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {


                return false;
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
