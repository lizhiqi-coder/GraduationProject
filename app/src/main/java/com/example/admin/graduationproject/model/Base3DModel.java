package com.example.admin.graduationproject.model;

import com.example.admin.graduationproject.programs.ShaderProgram;

/**
 * Created by lizhiqi on 2016/3/21.
 */
public abstract class Base3DModel<T extends ShaderProgram> {

    public static final int BYTES_PER_FLOAT = 4;
    public static final int BYTES_PER_INT = 4;
    public static final int BYTES_PER_SHORT = 2;


    abstract public void bindProgram(T program);

    abstract public void draw();

}
