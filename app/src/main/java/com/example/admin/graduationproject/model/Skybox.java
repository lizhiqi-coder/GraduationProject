package com.example.admin.graduationproject.model;

import com.example.admin.graduationproject.construction.VertexArray;
import com.example.admin.graduationproject.programs.SkyboxShaderProgram;

import java.nio.ByteBuffer;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.GL_UNSIGNED_BYTE;
import static android.opengl.GLES20.glDrawElements;

/**
 * Created by admin on 2016/5/9.
 */
public class Skybox extends Base3DModel<SkyboxShaderProgram>{
    private static final int SKYBOX_STEP = 3;

    private final VertexArray vertexArray;
    private final ByteBuffer indexArray;

    public Skybox() {

        vertexArray = new VertexArray(new float[]{
                -1, 1, 1,//0
                1, 1, 1,//1
                -1, -1, 1,//2
                1, -1, 1,//3
                -1, 1, -1,//4
                1, 1, -1,//5
                -1, -1, -1,//6
                1, -1, -1//7
        });

        indexArray = ByteBuffer.allocate(6 * 6).put(new byte[]{

                //front
                1, 3, 0,
                0, 3, 2,

                //back
                4, 6, 5,
                5, 6, 7,

                //left
                0, 2, 4,
                4, 2, 6,

                //right
                5, 7, 1,
                1, 7, 3,

                //top
                5, 1, 4,
                4, 1, 0,

                //bottom
                6, 2, 7,
                7, 2, 3

        });
        indexArray.position(0);

    }

    public void bindProgram(SkyboxShaderProgram program) {
        vertexArray.setVertexAttribPointer(0, program.getAPositionLocation(), SKYBOX_STEP, 0);
    }

    public void draw() {
        glDrawElements(GL_TRIANGLES, 36, GL_UNSIGNED_BYTE, indexArray);
    }
}
