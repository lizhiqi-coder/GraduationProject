package com.example.admin.graduationproject.activitys;

import android.content.Intent;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.Toast;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.render.SkyboxRender;
import com.example.admin.graduationproject.utils.AppUtils;
import com.example.admin.graduationproject.widget.MyDialog;

/**
 * Created by admin on 2016/5/15.
 */
public class SkyboxActivity extends BaseActivity {

    private GLSurfaceView glSurfaceView;
    private SkyboxRender mRender;
    private boolean hasSetRender = false;

    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skybox);

        glSurfaceView = (GLSurfaceView) findViewById(R.id.skybox_surface);
        fab = (FloatingActionButton) findViewById(R.id.skybox_fab);

        mRender = new SkyboxRender(this);

        if (AppUtils.isOpenGl2Support()) {
            glSurfaceView.setEGLContextClientVersion(2);

            glSurfaceView.setRenderer(mRender);

            hasSetRender = true;
        } else {
            Toast.makeText(this, "this device does support opengl ES 2.0", Toast.LENGTH_SHORT).show();

            return;
        }


        initTouchListener(glSurfaceView, mRender);

        final MyDialog myDialog = new MyDialog(this, "title", "tip");
        myDialog.positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SkyboxActivity.this
                        , BallActivity.class);
                startActivity(intent);

                myDialog.dismiss();
            }
        });
        myDialog.negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.dismiss();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDialog.show();
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
