package com.example.admin.graduationproject.geometry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/8.
 */
public class TriangleStrip implements IGeometry {

    public static final int POINT_STEP = 4;

    private List<Point> stripPoints;

    public TriangleStrip() {
        this.stripPoints = new ArrayList<>();
    }

    public void addPoint(Point point) {
        stripPoints.add(point);
    }

    public int getPointCount() {
        return stripPoints.size();
    }

    @Override
    public float[] getVertexArr() {

        float[] data = new float[POINT_STEP * stripPoints.size()];

        for (int i = 0; i < stripPoints.size(); i++) {

            System.arraycopy(stripPoints.get(i).getVertexArr4D(), 0, data, i * POINT_STEP, POINT_STEP);
        }

        return data;
    }
}
