package com.example.admin.graduationproject.render;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.model.CubeModel;
import com.example.admin.graduationproject.widget.Constant;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.glViewport;

/**
 * Created by admin on 2016/5/5.
 */
public class MainRender extends BaseRender implements GLSurfaceView.Renderer {

    private int uColorLocation;
    private int aPositionLocation;
    private int uMatrixLocation;


    int program;

    public MainRender(Context context) {
        super(context);
    }

    @Override
    public void onSurfaceCreated(GL10 gl10, EGLConfig eglConfig) {


        clearScreenByColor(R.color.gray);

        program = getProgramByShaderId(R.raw.cube_vertex_shader, R.raw.cube_fragment_shader);

        useProgram(program);

        uColorLocation = getUniLocation(program, Constant.UNIFORM_COLOR);
        aPositionLocation = getAttrLocation(program, Constant.ATTR_POSITION);
        uMatrixLocation = getUniLocation(program, Constant.UNIFORM_MATRIX);

        CubeModel model = new CubeModel();

        bindVertexData(aPositionLocation, model.getVertexData(), CubeModel.VERTEX_STEP);

        enableVertexArr(aPositionLocation);


    }

    @Override
    public void onSurfaceChanged(GL10 gl10, int w, int h) {
        glViewport(0, 0, w, h);

        adapterScreen(w, h);


    }

    @Override
    public void onDrawFrame(GL10 gl10) {

        clearScreenBuffer();

        updateUniColor(uColorLocation, R.color.deeppink);

        drawTriangles(0, 6);


        bindMatrix(uMatrixLocation, projectMatrix);

    }
}
