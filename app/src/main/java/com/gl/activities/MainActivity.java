package com.gl.activities;

import android.app.ActivityManager;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.gl.R;
import com.gl.utils.GLRenderer;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGlSurfaceView;

    private boolean supportsEs2=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        initIsGLSurfaceView();

        initOpenGl();
    }


    /**
     * 检查设备是否支持OpenGL
     */
    private void initIsGLSurfaceView(){
        //判断设备是否支持OpenGL
        ActivityManager activityManager=(ActivityManager)getSystemService(ACTIVITY_SERVICE);
        ConfigurationInfo configurationInfo=activityManager.getDeviceConfigurationInfo();
        supportsEs2=configurationInfo.reqGlEsVersion>=0x2000;

        boolean isEmulator = Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                && (Build.FINGERPRINT.startsWith("generic")
                || Build.FINGERPRINT.startsWith("unknown")
                || Build.MODEL.contains("google_sdk")
                || Build.MODEL.contains("Emulator")
                || Build.MODEL.contains("Android SDK built for x86"));

        supportsEs2 = supportsEs2 || isEmulator;
    }


    private void initOpenGl(){
        //如果设备支持OpenGl
        if(supportsEs2){
            mGlSurfaceView=new GLSurfaceView(this);
            mGlSurfaceView.setRenderer(new GLRenderer());
            setContentView(mGlSurfaceView); //显示布局
        }else{
            setContentView(R.layout.activity_main);
            Toast.makeText(this,"设备不支持OpenGL",Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if(mGlSurfaceView!=null){
            mGlSurfaceView.onResume();      //防止程序重新恢复的时候，GLOpen还在绘制，导致程序崩溃的bug
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mGlSurfaceView!=null){
            mGlSurfaceView.onPause();      //防止程序重新恢复的时候，GLOpen还在绘制，导致程序崩溃的bug
        }
    }
}
