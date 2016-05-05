package com.example.admin.graduationproject.geometry;

/**
 * Created by admin on 2016/5/5.
 */
public class Point {
    public final float x, y, z;

    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point translateY(float distance) {
        return new Point(x, y + distance, z);
    }

    public Point translate(Vector vector) {
        return new Point(x + vector.x,
                y + vector.y,
                z + vector.z);
    }
}
