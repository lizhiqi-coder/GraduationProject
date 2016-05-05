package com.example.admin.graduationproject.geometry;

/**
 * Created by admin on 2016/5/5.
 */
public class Geometry {

    public static Vector vectorBetween(Point from, Point to) {
        return new Vector(
                to.x - from.x,
                to.y - from.y,
                to.z - from.y);
    }


    /**
     * 相交
     *
     * @param sphere
     * @param ray
     * @return
     */
    public static boolean intersects(Sphere sphere, Ray ray) {
        return distanceBetween(sphere.center, ray) < sphere.radius;
    }

    public static float distanceBetween(Point point, Ray ray) {
        Vector p1ToPoint = vectorBetween(ray.point, point);
        Vector p2ToPoint = vectorBetween(ray.point.translate(ray.vector), point);

        float areaOfTriangleTimesTwo = p1ToPoint.crossProduct(p2ToPoint).length();
        float lenghtOfBase = ray.vector.length();

        float distanceFromPointToRay = areaOfTriangleTimesTwo / lenghtOfBase;

        return distanceFromPointToRay;
    }

    public static Point intersectionPoint(Ray ray, Plane plane) {

        Vector rayToPlaneVector = vectorBetween(ray.point, plane.point);
        float scaleFactor = rayToPlaneVector.dotProduct(plane.normal) / ray.vector.dotProduct(plane.normal);
        Point intersectPoint = ray.point.translate(ray.vector.scale(scaleFactor));

        return intersectPoint;
    }

}
