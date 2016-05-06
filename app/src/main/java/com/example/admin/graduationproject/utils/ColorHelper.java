package com.example.admin.graduationproject.utils;

import android.content.Context;

/**
 * Created by admin on 2016/5/6.
 */
public class ColorHelper {
    public static float[] parseColorToRGBA(Context context, int colorId) {

        int color = context.getResources().getColor(colorId);
        String hexColor = Integer.toHexString(color);

        float[] rgba = new float[4];

        rgba[0] = (float) Integer.parseInt(hexColor.substring(2, 4), 16) / 256f;
        rgba[1] = (float) Integer.parseInt(hexColor.substring(4, 6), 16) / 256f;
        rgba[2] = (float) Integer.parseInt(hexColor.substring(6, 8), 16) / 256f;
        rgba[3] = (float) Integer.parseInt(hexColor.substring(0, 2), 16) / 256f;

        return rgba;


    }
}
