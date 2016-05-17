package com.example.admin.graduationproject.render;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.model.Ball;
import com.example.admin.graduationproject.programs.BallProgram;
import com.example.admin.graduationproject.utils.LogUtils;
import com.example.admin.graduationproject.utils.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_DEPTH_BUFFER_BIT;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glViewport;
import static android.opengl.Matrix.frustumM;
import static android.opengl.Matrix.rotateM;
import static android.opengl.Matrix.scaleM;
import static android.opengl.Matrix.setIdentityM;
import static android.opengl.Matrix.translateM;


public class BallRender extends BaseRender implements Renderer {
    private static final String TAG = "ball_render";


    private BallProgram ballProgram;

    private int textureID;

    private Ball mBall;

    private int textureResId;

    public BallRender(Context context) {
        super(context);
    }


    public float xAngle;
    public float yAngle;
    public float zAngle;


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        clearScreenByColor(R.color.black);

        mBall = new Ball();
        ballProgram = new BallProgram(mContext);

        if (textureResId <= 0) {

            textureResId = R.mipmap.overall_view03;
        }
        textureID = TextureHelper.loadBallMap(mContext, textureResId);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        glViewport(0, 0, width, height);
        GLES20.glEnable(GLES20.GL_CULL_FACE);
        float ratio = width / (float) height;
        frustumM(projectMatrix, 0, -ratio, ratio, -1, 1, 1, 20);

        setIdentityM(modelMatrix, 0);
        setIdentityM(viewMatrix, 0);
        setIdentityM(modelViewProjectMatrix, 0);

        translateM(projectMatrix, 0, 0, 0, -2);
        // rotateM(projectMatrix, 0, -90, 1, 0, 0);
        scaleM(projectMatrix, 0, 4, 4, 4);


    }

    @Override
    public void onDrawFrame(GL10 arg0) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        drawBall();

    }

    public void setTextrueResId(int id) {
        textureResId = id;
        textureID = TextureHelper.loadBallMap(mContext, textureResId);
    }


    private void drawBall() {
        setIdentityM(modelMatrix, 0);

        rotateM(modelMatrix, 0, -xAngle, 1, 0, 0);
        rotateM(modelMatrix, 0, -yAngle, 0, 1, 0);
        rotateM(modelMatrix, 0, -zAngle, 0, 0, 1);

        LogUtils.d(TAG, "xAngle--> " + xAngle);
        LogUtils.d(TAG, "yAngle--> " + yAngle);
        LogUtils.d(TAG, "zAngle--> " + zAngle);

        scaleM(modelMatrix, 0, scale, scale, scale);

        updateMatrix();

        ballProgram.useProgram();
        ballProgram.setData(modelViewProjectMatrix, textureID);
        mBall.bindProgram(ballProgram);
        mBall.draw();

    }

    @Override
    public void handleTouchDrag(float deltaX, float deltaY) {
        yAngle += deltaX * 0.3f;// 设置填充椭圆绕y轴旋转的角度
        xAngle += deltaY * 0.3f;// 设置填充椭圆绕x轴旋转的角度
    }
}
