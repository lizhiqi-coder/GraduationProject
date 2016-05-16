package com.example.admin.graduationproject.render;

import android.content.Context;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.utils.ColorHelper;
import com.example.admin.graduationproject.utils.LogUtils;
import com.example.admin.graduationproject.utils.ShaderHelper;
import com.example.admin.graduationproject.utils.TextResReader;

import java.nio.Buffer;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_POINTS;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_TRIANGLE_FAN;
import static android.opengl.GLES20.GL_TRIANGLE_STRIP;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUniformMatrix4fv;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;
import static android.opengl.Matrix.multiplyMM;
import static android.opengl.Matrix.orthoM;
import static android.opengl.Matrix.setLookAtM;

/**
 * Created by admin on 2016/5/5.
 */
public class BaseRender {

    private final static String TAG = "br";

    protected Context mContext;

    //模型矩阵，用于移动对象
    protected float[] modelMatrix = new float[16];

    protected float[] viewMatrix = new float[16];
    //投影矩阵
    protected float[] projectMatrix = new float[16];

    protected float[] viewProjectMatrix = new float[16];

    protected float[] modelViewProjectMatrix = new float[16];


    protected int xRotation, yRotation;

    protected float scale;

    protected static final float SCALE_MAX = 5f;
    protected static final float SCALE_MIN = 0.1f;
    protected static final float SCALE_STEP = 0.04f;

    protected Point scalePoint;

    public void updateMatrix() {
        multiplyMM(viewProjectMatrix, 0, projectMatrix, 0, viewMatrix, 0);
        multiplyMM(modelViewProjectMatrix, 0, viewProjectMatrix, 0, modelMatrix, 0);
    }

    public BaseRender(Context context) {

        mContext = context;
        scalePoint = new Point(0, 0, 0);
        scale = 1f;

    }

    protected int findVertexShaderById(int id) {

        return findVertexShaderById(mContext, id);
    }

    protected int findVertexShaderById(Context context, int id) {
        String res = TextResReader.readTextFileFromRes(context, id);
        int shaderId = ShaderHelper.compileVertexShader(res);

        return shaderId;
    }

    protected int findFragmentShaderById(int id) {
        return findFragmentShaderById(mContext, id);
    }

    protected int findFragmentShaderById(Context context, int id) {

        String res = TextResReader.readTextFileFromRes(context, id);
        int shaderId = ShaderHelper.compileFragmentShader(res);

        return shaderId;
    }


    protected int getProgramByShaderId(int vertexShaderResId, int fragmentShaderResId) {
        return getProgramByShaderId(mContext, vertexShaderResId, fragmentShaderResId);
    }

    protected int getProgramByShaderId(Context context, int vertexShaderResId, int fragmentShaderResId) {

        int program = ShaderHelper.linkProgram(

                findVertexShaderById(context, vertexShaderResId),
                findFragmentShaderById(context, fragmentShaderResId)

        );

        return program;

    }

    protected void useProgram(int program) {
        glUseProgram(program);
    }

    protected int getUniLocation(int program, String key) {
        return glGetUniformLocation(program, key);
    }

    protected int getAttrLocation(int program, String key) {
        return glGetAttribLocation(program, key);
    }

    protected void bindVertexData(int positionLocation, Buffer vertexData, int count) {

        glVertexAttribPointer(positionLocation, count, GL_FLOAT, false, 0, vertexData);
    }

    protected void bindMatrix(int matrixLocation, float[] matrix) {
        glUniformMatrix4fv(matrixLocation, 1, false, matrix, 0);
    }

    protected void enableVertexArr(int positionLocation) {
        glEnableVertexAttribArray(positionLocation);
    }

    /**
     * ARGB
     *
     * @param colorLocation
     */
    protected void updateUniColor(int colorLocation, int colorId) {

        float[] color = ColorHelper.parseColorToRGBA(mContext, colorId);

        glUniform4f(colorLocation, color[0], color[1], color[2], color[3]);


    }

    protected void drawTriangles(int startPosition, int vertexCount) {
        glDrawArrays(GL_TRIANGLES, startPosition, vertexCount);
    }

    protected void drawTriangleFun(int startPosition, int vertexCount) {
        glDrawArrays(GL_TRIANGLE_FAN, startPosition, vertexCount);
    }

    protected void drawTriangleStrip(int startPosition, int vertexCount) {
        glDrawArrays(GL_TRIANGLE_STRIP, startPosition, vertexCount);
    }

    protected void drawPoints(int startPosition, int vertexCount) {
        glDrawArrays(GL_POINTS, startPosition, vertexCount);
    }


    /**
     * default clear color white
     */
    protected void clearScreen() {

        clearScreenByColor(R.color.white);
    }

    protected void clearScreenByColor(int ColorId) {

        float[] color = ColorHelper.parseColorToRGBA(mContext, ColorId);
        glClearColor(color[0], color[1], color[2], color[3]);
    }

    protected void clearScreenBuffer() {
        glClear(GL_COLOR_BUFFER_BIT);
    }


    protected void adapterScreen(int w, int h) {
        final float aspectRatio = w > h ?
                (float) w / (float) h :
                (float) h / (float) w;

        if (w > h) {
            orthoM(projectMatrix, 0, -aspectRatio, aspectRatio, -1f, 1f, -1f, 1f);

        } else {
            orthoM(projectMatrix, 0, -1f, 1f, -aspectRatio, aspectRatio, -1f, 1f);
        }
    }

    public void handleTouchDrag(float deltaX, float deltaY) {

        xRotation += deltaX / 16f;
        yRotation += deltaY / 16f;
        LogUtils.d(TAG, "xRotation :" + xRotation);
        LogUtils.d(TAG, "yRotation :" + yRotation);

        if (yRotation < -90) {
            yRotation = -90;
        } else if (yRotation > 90) {
            yRotation = 90;
        }
    }


    public void handleDoubleClick() {
        long intervalStart = System.currentTimeMillis();

        if (scale >= 3) {
            while (scale > 0.5f) {
                if (System.currentTimeMillis() - intervalStart > 5) {
                    downScale();
                    intervalStart = System.currentTimeMillis();
                }
            }

            return;
        }
        while (scale < 3) {
            if (System.currentTimeMillis() - intervalStart > 5) {

                upScale();
                intervalStart = System.currentTimeMillis();
            }
        }
    }


    public void upScale() {
        scale *= (1 + SCALE_STEP);
        if (scale >= SCALE_MAX) {
            scale = SCALE_MAX;
        }
    }

    public void downScale() {
        scale *= (1 - SCALE_STEP);
        if (scale <= SCALE_MIN) {
            scale = SCALE_MIN;
        }
    }

    protected void setVisualAngle(Point eyePosition, Point lookPosition, Point headPosition) {
        setLookAtM(viewMatrix, 0, eyePosition.x, eyePosition.y, eyePosition.z,
                lookPosition.x, lookPosition.y, lookPosition.z,
                headPosition.x, headPosition.y, headPosition.z);
    }
}
