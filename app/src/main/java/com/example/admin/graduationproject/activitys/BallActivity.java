package com.example.admin.graduationproject.activitys;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.model.Ball;
import com.example.admin.graduationproject.render.BallRender;
import com.example.admin.graduationproject.utils.AppUtils;

/**
 * Created by admin on 2016/5/17.
 */
public class BallActivity extends BaseActivity {

    private GLSurfaceView glSurfaceView;
    private BallRender mRender;
    private boolean hasSetRender = false;

    private Ball mBall;

    private FloatingActionButton mFab;

    private float mPreviousY;
    private float mPreviousX;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ball);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.ball_surface);

        mFab = (FloatingActionButton) findViewById(R.id.ball_fab);

        mRender = new BallRender(this);
        mBall = new Ball(this);

        if (AppUtils.isOpenGl2Support()) {
            glSurfaceView.setEGLContextClientVersion(2);

            glSurfaceView.setRenderer(mBall);

            hasSetRender = true;
        } else {
            Toast.makeText(this, "this device does support opengl ES 2.0", Toast.LENGTH_SHORT).show();

            return;
        }



        glSurfaceView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                float y = event.getY();
                float x = event.getX();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        float dy = y - mPreviousY;// 计算触控笔Y位移
                        float dx = x - mPreviousX;// 计算触控笔X位移
                        mBall.yAngle += dx * 0.3f;// 设置填充椭圆绕y轴旋转的角度
                        mBall.xAngle += dy * 0.3f;// 设置填充椭圆绕x轴旋转的角度
                }
                mPreviousY = y;// 记录触控笔位置
                mPreviousX = x;// 记录触控笔位置
                return true;
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (hasSetRender) {
            glSurfaceView.onResume();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (hasSetRender) {
            glSurfaceView.onPause();
        }
    }
}
