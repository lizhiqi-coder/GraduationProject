package com.example.admin.graduationproject.programs;

import android.content.Context;
import android.opengl.GLES20;

import com.example.admin.graduationproject.R;

import static android.opengl.GLES20.GL_TEXTURE_2D;
import static android.opengl.GLES20.glActiveTexture;
import static android.opengl.GLES20.glBindTexture;
import static android.opengl.GLES20.glGetAttribLocation;
import static android.opengl.GLES20.glGetUniformLocation;
import static android.opengl.GLES20.glUniformMatrix4fv;

/**
 * Created by admin on 2016/5/17.
 */
public class BallProgram extends ShaderProgram {
    private final int mAPositionHandler;
    private final int mUProjectMatrixHandler;
    private final int mATextureCoordHandler;

    public BallProgram(Context context) {
        super(context, R.raw.ball_vertex_shader, R.raw.ball_fragment_shader);
        mAPositionHandler = glGetAttribLocation(program, "aPosition");
        mUProjectMatrixHandler = glGetUniformLocation(program, "uProjectMatrix");
        mATextureCoordHandler = glGetAttribLocation(program, "aTextureCoord");
    }



    @Override
    public void setData(float[] matrix, int textureId) {
        glUniformMatrix4fv(mUProjectMatrixHandler, 1, false, matrix, 0);
        glActiveTexture(GLES20.GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, textureId);
    }

    public int getAPosition() {
        return mAPositionHandler;
    }

    public int getATexture() {
        return mATextureCoordHandler;
    }
}
