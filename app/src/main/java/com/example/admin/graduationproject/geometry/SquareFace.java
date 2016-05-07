package com.example.admin.graduationproject.geometry;

/**
 * Created by admin on 2016/5/6.
 */
public class SquareFace implements IGeometry {

    public static final int FACE_STEP = 4 * 6;

    private Point point1;
    private Point point2;
    private Point point3;
    private Point point4;

    private Point center;

    public Point getCenter() {
        return center;
    }

    public void setCenter(Point center) {
        this.center = center;
    }


    /**
     * 顶点按逆时针顺序输入，不然会出现意想不到的结果
     * @param point1
     * @param point2
     * @param point3
     * @param point4
     */
    public SquareFace(Point point1, Point point2, Point point3, Point point4) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.point4 = point4;
    }

    @Override
    public float[] getVertexArr() {

        float[] data = {
                point1.x, point1.y, point1.z, 1f,
                point2.x, point2.y, point2.z, 1f,
                point3.x, point3.y, point3.z, 1f,

                point1.x, point1.y, point1.z, 1f,
                point3.x, point3.y, point3.z, 1f,
                point4.x, point4.y, point4.z, 1f,

        };
        return data;
    }
}
