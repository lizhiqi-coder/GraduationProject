package com.example.admin.graduationproject.construction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2016/5/5.
 */

public class ObjectBuilder {

    private static final int FLOAT_PER_VERTEX = 3;

    private final float[] vertexData;
    private int offset = 0;


    private final List<DrawCommand> drawList = new ArrayList<>();


    public ObjectBuilder(int sizeInVertices) {
        this.vertexData = new float[sizeInVertices * FLOAT_PER_VERTEX];

    }

    public static interface DrawCommand {
        void draw();
    }

    public static class GenerateData {
        final float[] vertexData;
        final List<DrawCommand> drawList;

        public GenerateData(float[] vertexData, List<DrawCommand> drawList) {
            this.vertexData = vertexData;
            this.drawList = drawList;
        }

        public GenerateData build() {
            return new GenerateData(vertexData, drawList);
        }
    }


}
