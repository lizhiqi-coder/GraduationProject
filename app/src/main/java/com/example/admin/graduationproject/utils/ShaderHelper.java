package com.example.admin.graduationproject.utils;

/**
 * Created by admin on 2016/4/27.
 */

import android.content.Context;

import static android.opengl.GLES20.GL_COMPILE_STATUS;
import static android.opengl.GLES20.GL_FRAGMENT_SHADER;
import static android.opengl.GLES20.GL_LINK_STATUS;
import static android.opengl.GLES20.GL_VALIDATE_STATUS;
import static android.opengl.GLES20.GL_VERTEX_SHADER;
import static android.opengl.GLES20.glAttachShader;
import static android.opengl.GLES20.glCompileShader;
import static android.opengl.GLES20.glCreateProgram;
import static android.opengl.GLES20.glCreateShader;
import static android.opengl.GLES20.glDeleteProgram;
import static android.opengl.GLES20.glDeleteShader;
import static android.opengl.GLES20.glGetProgramiv;
import static android.opengl.GLES20.glGetShaderiv;
import static android.opengl.GLES20.glLinkProgram;
import static android.opengl.GLES20.glShaderSource;
import static android.opengl.GLES20.glValidateProgram;

public class ShaderHelper {

    private static final String TAG = "ShaderHelper";


    public static int compileVertexShader(String shaderCode) {
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }


    public static int compileFragmentShader(String shaderCode) {
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    public static int compileShader(int type, String shaderCode) {

        final int shaderObjectId = glCreateShader(type);
        if (shaderObjectId == 0) {

            //could not create new shader

            return 0;
        }

        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);


        final int[] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);

//        glGetShaderInfoLog(shaderObjectId);
        if (compileStatus[0] == 0) {
            glDeleteShader(shaderObjectId);

            return 0;

        }
        return shaderObjectId;
    }

    /**
     * 链接openGL 程序
     *
     * @param vertexShaderId
     * @param fragmentShaderId
     * @return
     */
    public static int linkProgram(int vertexShaderId, int fragmentShaderId) {

        final int programObjectId = glCreateProgram();
        if (programObjectId == 0) {
            return 0;
        }
        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);

        glLinkProgram(programObjectId);

        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);

        if (linkStatus[0] == 0) {
            glDeleteProgram(programObjectId);
            return 0;
        }
        return programObjectId;
    }

    public static boolean validateProgram(int programobjectId) {
        glValidateProgram(programobjectId);
        final int[] validateStatus = new int[1];
        glGetProgramiv(programobjectId, GL_VALIDATE_STATUS, validateStatus, 0);
        return validateStatus[0] != 0;
    }

    public static int getProgramByShaderId(Context context, int vertexShaderResId, int fragmentShaderResId) {

        int program = ShaderHelper.linkProgram(

                findVertexShaderById(context, vertexShaderResId),
                findFragmentShaderById(context, fragmentShaderResId)

        );

        return program;

    }

    public static int findVertexShaderById(Context context, int id) {
        String res = TextResReader.readTextFileFromRes(context, id);
        int shaderId = compileVertexShader(res);

        return shaderId;
    }

    public static int findFragmentShaderById(Context context, int id) {

        String res = TextResReader.readTextFileFromRes(context, id);
        int shaderId = compileFragmentShader(res);

        return shaderId;
    }


}
