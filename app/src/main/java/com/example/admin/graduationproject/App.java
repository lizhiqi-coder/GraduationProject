package com.example.admin.graduationproject;

import android.app.Application;
import android.content.Context;

/**
 * Created by admin on 2016/5/5.
 */
public class App extends Application {
    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext() {
        return mContext;
    }

}
