package com.example.admin.graduationproject.activitys;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.admin.graduationproject.render.SkyboxRender;
import com.example.admin.graduationproject.utils.AppUtils;

/**
 * Created by admin on 2016/5/15.
 */
public class SkyboxActivity extends BaseActivity {

    private GLSurfaceView glSurfaceView;
    private SkyboxRender render;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new GLSurfaceView(this);
        render = new SkyboxRender(this);

        if (AppUtils.isOpenGl2Support()) {
            glSurfaceView.setEGLContextClientVersion(2);

            glSurfaceView.setRenderer(render);
        } else {
            Toast.makeText(this, "this device does support opengl ES 2.0", Toast.LENGTH_SHORT).show();

            return;
        }

        setContentView(glSurfaceView);


    }
}
