package com.example.admin.graduationproject.model;

import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.programs.ShaderProgram;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

/**
 * Created by admin on 2016/5/5.
 */
public class CubeModel extends Base3DModel {

    public static final int VERTEX_STEP = 4;

    private final FloatBuffer vertexData;

    private Cube cube;

    /**
     * x,y,z,w
     */
    private float[] rawData = {

            -0.5f, -0.8f, 0f, 1f,
            0.5f, 0.8f, 0f, 1f,
            -0.5f, 0.8f, 0f, 1f,

            -0.5f, -0.8f, 0f, 1f,
            0.5f, -0.8f, 0f, 1f,
            0.5f, 0.8f, 0f, 1f


    };


    public CubeModel() {

        cube = new Cube(new Point(0f, 0f, 0f), 2f, 1.5f, 2f);
        rawData = cube.getVertexArr();


//        test face
//        float x = 0.5f, y = 0.8f, z = 0f;
//        SquareFace face = new SquareFace(
//                new Point(x, y, z),
//                new Point(-x, y, z),
//                new Point(-x, -y, z),
//                new Point(x, -y, z)
//
//        );
//        rawData = face.getVertexArr();


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
    public void bindProgram(ShaderProgram program) {

    }

    @Override
    public void draw() {

    }
}
