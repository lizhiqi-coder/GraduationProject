package com.example.admin.graduationproject.model;

import android.util.Log;

import com.example.admin.graduationproject.geometry.IGeometry;
import com.example.admin.graduationproject.geometry.Point;
import com.example.admin.graduationproject.geometry.SquareFace;

/**
 * Created by admin on 2016/5/6.
 */
public class Cube implements IGeometry {

    private static final String TAG = "cube";
    private float width;
    private float height;
    private float length;
    Point center;

    private SquareFace[] faces;

    private Point[] vertexs;

    public Cube(Point center, float length, float width, float height) {
        this.center = center;
        this.width = width;
        this.height = height;
        this.length = length;

        faces = new SquareFace[6];

        vertexs = new Point[8];

        float tx = width / 2f;
        float ty = length / 2f;
        float tz = height / 2f;

        vertexs[0] = new Point(center.x + tx, center.y + ty, center.z - tz);
        vertexs[1] = new Point(center.x - tx, center.y + ty, center.z - tz);
        vertexs[2] = new Point(center.x - tx, center.y - ty, center.z - tz);
        vertexs[3] = new Point(center.x + tx, center.y - ty, center.z - tz);

        vertexs[4] = new Point(center.x + tx, center.y + ty, center.z + tz);
        vertexs[5] = new Point(center.x - tx, center.y + ty, center.z + tz);
        vertexs[6] = new Point(center.x - tx, center.y - ty, center.z + tz);
        vertexs[7] = new Point(center.x + tx, center.y - ty, center.z + tz);


        faces[0] = new SquareFace(vertexs[0], vertexs[1], vertexs[2], vertexs[3]);
        faces[1] = new SquareFace(vertexs[0], vertexs[4], vertexs[5], vertexs[1]);
        faces[2] = new SquareFace(vertexs[1], vertexs[5], vertexs[6], vertexs[2]);
        faces[3] = new SquareFace(vertexs[2], vertexs[6], vertexs[7], vertexs[3]);
        faces[4] = new SquareFace(vertexs[3], vertexs[7], vertexs[4], vertexs[0]);
        faces[5] = new SquareFace(vertexs[4], vertexs[7], vertexs[6], vertexs[5]);


    }

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    public float[] getVertexArr() {


        int step = SquareFace.FACE_STEP;

        float[] data = new float[6 * step];

        for (int i = 0; i < faces.length; i++) {

            System.arraycopy(faces[i].getVertexArr(), 0, data, i * step, step);
        }


        Log.d(TAG, data.toString());

        return data;
    }
}
