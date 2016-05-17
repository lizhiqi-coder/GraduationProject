package com.example.admin.graduationproject.model;

import com.example.admin.graduationproject.construction.VertexArray;
import com.example.admin.graduationproject.programs.BallProgram;

import java.util.ArrayList;

import static android.opengl.GLES20.GL_TRIANGLES;
import static android.opengl.GLES20.glDrawArrays;

/**
 * Created by admin on 2016/5/17.
 */
public class Ball extends Base3DModel<BallProgram> {

    private static final int BALL_VERTEX_STEP = 3;
    private static final int BALL_TEXTURE_STEP = 2;
    private VertexArray vertexArray;
    private VertexArray textureArray;
    private int mSize;

    public Ball() {
        init();

    }

    public void init() {
        int perVertex = 36;

        double perRadius = 2 * Math.PI / (float) perVertex;
        double perW = 1 / (float) perVertex;
        double perH = 1 / (float) (perVertex);

        ArrayList<Float> vetexList = new ArrayList<Float>();
        ArrayList<Float> textureList = new ArrayList<Float>();
        for (int a = 0; a < perVertex; a++) {
            for (int b = 0; b < perVertex; b++) {
                float w1 = (float) (a * perH);
                float h1 = (float) (b * perW);

                float w2 = (float) ((a + 1) * perH);
                float h2 = (float) (b * perW);

                float w3 = (float) ((a + 1) * perH);
                float h3 = (float) ((b + 1) * perW);

                float w4 = (float) (a * perH);
                float h4 = (float) ((b + 1) * perW);

                textureList.add(h1);
                textureList.add(w1);

                textureList.add(h2);
                textureList.add(w2);

                textureList.add(h3);
                textureList.add(w3);

                textureList.add(h3);
                textureList.add(w3);

                textureList.add(h4);
                textureList.add(w4);

                textureList.add(h1);
                textureList.add(w1);

                float x1 = (float) (Math.sin(a * perRadius / 2) * Math.cos(b
                        * perRadius));
                float z1 = (float) (Math.sin(a * perRadius / 2) * Math.sin(b
                        * perRadius));
                float y1 = (float) Math.cos(a * perRadius / 2);

                float x2 = (float) (Math.sin((a + 1) * perRadius / 2) * Math
                        .cos(b * perRadius));
                float z2 = (float) (Math.sin((a + 1) * perRadius / 2) * Math
                        .sin(b * perRadius));
                float y2 = (float) Math.cos((a + 1) * perRadius / 2);

                float x3 = (float) (Math.sin((a + 1) * perRadius / 2) * Math
                        .cos((b + 1) * perRadius));
                float z3 = (float) (Math.sin((a + 1) * perRadius / 2) * Math
                        .sin((b + 1) * perRadius));
                float y3 = (float) Math.cos((a + 1) * perRadius / 2);

                float x4 = (float) (Math.sin(a * perRadius / 2) * Math
                        .cos((b + 1) * perRadius));
                float z4 = (float) (Math.sin(a * perRadius / 2) * Math
                        .sin((b + 1) * perRadius));
                float y4 = (float) Math.cos(a * perRadius / 2);

                vetexList.add(x1);
                vetexList.add(y1);
                vetexList.add(z1);

                vetexList.add(x2);
                vetexList.add(y2);
                vetexList.add(z2);

                vetexList.add(x3);
                vetexList.add(y3);
                vetexList.add(z3);

                vetexList.add(x3);
                vetexList.add(y3);
                vetexList.add(z3);

                vetexList.add(x4);
                vetexList.add(y4);
                vetexList.add(z4);

                vetexList.add(x1);
                vetexList.add(y1);
                vetexList.add(z1);
            }
        }
        mSize = vetexList.size() / 3;
        float texture[] = new float[mSize * 2];
        for (int i = 0; i < texture.length; i++) {
            texture[i] = textureList.get(i);
        }

        textureArray = new VertexArray(texture);


        float vetex[] = new float[mSize * 3];
        for (int i = 0; i < vetex.length; i++) {
            vetex[i] = vetexList.get(i);
        }

        vertexArray = new VertexArray(vetex);


    }

    @Override
    public void bindProgram(BallProgram program) {
        vertexArray.setVertexAttribPointer(0, program.getAPosition(), BALL_VERTEX_STEP, 0);
        textureArray.setVertexAttribPointer(0, program.getATexture(), BALL_TEXTURE_STEP, 0);
    }

    @Override
    public void draw() {
        glDrawArrays(GL_TRIANGLES, 0, mSize);
    }

}
