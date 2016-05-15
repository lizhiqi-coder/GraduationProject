package com.example.admin.graduationproject.model;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by lizhiqi on 2016/3/21.
 */
public abstract class Base3DModel {

    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_INT = 4;
    public static final int BYTES_PER_SHORT = 2;


    abstract public void draw(GL10 gl);

}
