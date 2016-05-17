package com.example.admin.graduationproject.render;

/**
 * Created by admin on 2016/5/9.
 */

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.model.Skybox;
import com.example.admin.graduationproject.programs.SkyboxShaderProgram;
import com.example.admin.graduationproject.utils.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.perspectiveM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.scaleM;
import static android.opengl.Matrix.setIdentityM;

/**
 * Created by admin on 2016/5/5.
 */
public class SkyboxRender extends BaseRender implements GLSurfaceView.Renderer {

    private SkyboxShaderProgram program;
    private Skybox skybox;
    private int texture;


    public SkyboxRender(Context context) {
        super(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {


        clearScreenByColor(R.color.black);

        program = new SkyboxShaderProgram(mContext);
        skybox = new Skybox();

        texture = TextureHelper.loadCubeMap(mContext, new int[]{

                R.mipmap.captain_america, R.mipmap.iron_man,
                R.mipmap.ground, R.mipmap.sky,
                R.mipmap.super_man, R.mipmap.bat_man
        });


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        glViewport(0, 0, w, h);

        adapterScreen(w, h);

        perspectiveM(projectMatrix, 0, 45f, (float) w / (float) h, 1f, 10f);

        Point eye = new Point(0f, 0f, 1f);
        Point look = new Point(0f, 0f, 0f);
        Point head = new Point(0f, 0.8f, 0f);
        setVisualAngle(eye, look, head);


    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        clearScreenBuffer();
        drawSkyBox();


    }

    private void drawSkyBox() {
        setIdentityM(modelMatrix, 0);
        rotateM(modelMatrix, 0, -yRotation, 1f, 0f, 0f);
        rotateM(modelMatrix, 0, -xRotation, 0f, 1f, 0f);

        scaleM(modelMatrix, 0, scale, scale, scale);
        updateMatrix();

        program.useProgram();
        program.setData(modelViewProjectMatrix, texture);
        skybox.bindProgram(program);
        skybox.draw();

    }
}
