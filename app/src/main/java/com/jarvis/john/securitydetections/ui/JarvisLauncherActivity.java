package com.jarvis.john.securitydetections.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.jarvis.john.NativeLib;
import com.jarvis.john.interfaces.JarvisUtilsInterface;
import com.jarvis.john.securitydetections.adapter.JarvisAdapter;
import com.jarvis.john.tpi.R;
import com.jarvis.john.utils.JarvisUtils;
import com.jarvis.john.utils.EncryptionDecryptionUtils;
import com.jarvis.john.utils.RetrieveAppSignature;

import java.util.ArrayList;
import java.util.List;


public class JarvisLauncherActivity extends AppCompatActivity {

    private static  String KEY_STRING;
    public static Context mContext;
    private RetrieveAppSignature mRetrieveAppSignature;
    private String appSignature;
    private String encryptedString;
    public static Activity getCurrentActivityContext() {
        return (Activity) mContext;
    }
    private NativeLib nativeLibObj;
    private int returnedValue;

    private static List<JarvisUtilsInterface> listeners = new ArrayList<JarvisUtilsInterface>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jarvis_launcher);
        mContext = this;
        mRetrieveAppSignature = new RetrieveAppSignature(JarvisLauncherActivity.this);
        appSignature = mRetrieveAppSignature.getSignature();
        nativeLibObj = new NativeLib();
        KEY_STRING = nativeLibObj.fetchStringFromJNI();
        if(KEY_STRING != null || !KEY_STRING.isEmpty()) {
            encryptedString = EncryptionDecryptionUtils.encryptString(appSignature, KEY_STRING);
            returnedValue = nativeLibObj.aICheck(encryptedString);
        }

        if(returnedValue ==0) {
            JarvisAdapter adapter = new JarvisAdapter();
            for (JarvisUtilsInterface listener : listeners)
                listener.invokeAppLauncherActivity(JarvisLauncherActivity.this);
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Your Application has been modified", Toast.LENGTH_LONG).show();
            JarvisUtils.eAWC(21, mContext);
        }

    }

    public static void addListener(JarvisUtilsInterface jarvisUtilsInterface) {
        listeners.add(jarvisUtilsInterface);
    }
}