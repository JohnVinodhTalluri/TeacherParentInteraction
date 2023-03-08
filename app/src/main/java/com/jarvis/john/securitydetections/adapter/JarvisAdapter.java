package com.jarvis.john.securitydetections.adapter;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.jarvis.john.interfaces.JarvisUtilsInterface;
import com.jarvis.john.securitydetections.ui.JarvisLauncherActivity;
import com.jarvis.john.tpi.ui.dashboard.TPIDashBoardActivity;

public class JarvisAdapter implements JarvisUtilsInterface {

    public JarvisAdapter() {
        JarvisLauncherActivity.addListener(JarvisAdapter.this);
    }

    @Override
    public void invokeAppLauncherActivity(Activity activity) {
        if(activity !=null) {
            Intent intent = new Intent(activity, TPIDashBoardActivity.class);
            activity.startActivity(intent);
        }
    }
}
