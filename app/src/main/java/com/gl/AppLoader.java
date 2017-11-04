package com.gl;

import android.app.Application;

/**
 * @author: xiaxueyi
 * @date: 2017-11-04
 * @time: 13:06
 * @说明:
 */
public class AppLoader extends Application {

    private static AppLoader mInsance=null;

    public static AppLoader getInsance() {
        return mInsance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInsance=this;
    }
}
