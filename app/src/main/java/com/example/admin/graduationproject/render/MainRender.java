package com.example.admin.graduationproject.render;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.model.CubeModel;
import com.example.admin.graduationproject.widget.Constant;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.perspectiveM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;
import static android.opengl.Matrix.rotateM;

/**
 * Created by admin on 2016/5/5.
 */
public class MainRender extends BaseRender implements GLSurfaceView.Renderer {

    private int uColorLocation;
    private int aPositionLocation;
    private int uMatrixLocation;

    int program;
    CubeModel model;

    public MainRender(Context context) {
        super(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {


        clearScreenByColor(R.color.black);

        program = getProgramByShaderId(R.raw.cube_vertex_shader, R.raw.cube_fragment_shader);

        useProgram(program);

        uColorLocation = getUniLocation(program, Constant.UNIFORM_COLOR);
        aPositionLocation = getAttrLocation(program, Constant.ATTR_POSITION);
        uMatrixLocation = getUniLocation(program, Constant.UNIFORM_MATRIX);

        model = new CubeModel();

        bindVertexData(aPositionLocation, model.getVertexData(), CubeModel.VERTEX_STEP);

        enableVertexArr(aPositionLocation);


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        glViewport(0, 0, w, h);

        adapterScreen(w, h);

        perspectiveM(projectMatrix, 0, 45f, (float) w / (float) h, 1f, 10f);

        setIdentityM(modelMatrix, 0);
        translateM(modelMatrix, 0, 0f, 0f, -2f);

        translateM(modelMatrix, 0, 0f, 0f, -2.5f);
        rotateM(modelMatrix, 0, -60f, 1f, 0f, 0f);

        updateMatrix();

    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        clearScreenBuffer();

        updateUniColor(uColorLocation, R.color.white);

        drawTriangles(0, 6);


        updateUniColor(uColorLocation, R.color.blue);

        drawTriangles(6, 6);


        updateUniColor(uColorLocation, R.color.red);

        drawTriangles(12, 6);

        updateUniColor(uColorLocation, R.color.yellow);

        drawTriangles(18, 6);

        updateUniColor(uColorLocation, R.color.deeppink);

        drawTriangles(24, 6);

//        updateUniColor(uColorLocation, R.color.green);
//
//        drawTriangles(30, 6);

        bindMatrix(uMatrixLocation, viewProjectMatrix);

    }
}
