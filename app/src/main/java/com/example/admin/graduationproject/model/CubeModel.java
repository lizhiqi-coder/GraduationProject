package com.example.admin.graduationproject.model;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by admin on 2016/5/5.
 */
public class CubeModel extends Base3DModel {

    public static final int VERTEX_STEP = 2;

    private final FloatBuffer vertexData;

    private final float[] rawData = {

            -0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,

            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f


    };

    public CubeModel() {

        vertexData = ByteBuffer
                .allocateDirect(rawData.length * BYTES_PER_FLOAT)
                .order(ByteOrder.nativeOrder())
                .asFloatBuffer();

        initVertexData();

    }

    public void initVertexData() {
        vertexData.put(rawData);
        vertexData.position(0);
    }

    public FloatBuffer getVertexData() {
        return vertexData;
    }

    @Override
    public void draw(GL10 gl) {

    }
}
