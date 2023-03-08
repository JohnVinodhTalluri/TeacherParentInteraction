package com.jarvis.john.securitydetections;

import android.app.Application;
import android.content.Context;


public class JarvisApplication extends Application {

    private static Context mContext = null;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}
