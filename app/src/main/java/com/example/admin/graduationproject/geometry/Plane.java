package com.example.admin.graduationproject.geometry;

/**
 * Created by admin on 2016/5/5.
 */
public class Plane {

    public final Point point;
    public final Vector normal;

    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal;
    }
}
