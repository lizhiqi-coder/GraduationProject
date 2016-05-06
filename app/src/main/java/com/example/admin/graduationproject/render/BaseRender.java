package com.example.admin.graduationproject.render;

import android.content.Context;

import com.example.admin.graduationproject.R;
import com.example.admin.graduationproject.utils.ColorHelper;
import com.example.admin.graduationproject.utils.ShaderHelper;
import com.example.admin.graduationproject.utils.TextResReader;

import java.nio.Buffer;

import static android.opengl.GLES20.GL_COLOR_BUFFER_BIT;
import static android.opengl.GLES20.GL_FLOAT;
import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glClear;
import static android.opengl.GLES20.glClearColor;
import static android.opengl.GLES20.glDrawArrays;
import static android.opengl.GLES20.glEnableVertexAttribArray;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniform4f;
import static android.opengl.GLES20.glUseProgram;
import static android.opengl.GLES20.glVertexAttribPointer;

/**
 * Created by admin on 2016/5/5.
 */
public class BaseRender {
    protected Context mContext;

    public BaseRender(Context context) {

        mContext = context;

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
        glDrawArrays(GL_TRIANGLES, 0, 6);
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


}
