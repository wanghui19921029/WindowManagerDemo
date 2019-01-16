package com.example.wh.windowmanagerdemo;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

@SuppressLint("LongLogTag")
public class WindowService extends Service {
    private static final String TAG = "windowmanagerdemo--WindowService";
    private WindowManager mWindowManager;
    private Button mButton;
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
        // window 分为属于activity的和属于应用的。activity的随着activity消失，应用的不同。
        mWindowManager = (WindowManager) WindowApp.getsApplication().getSystemService(Context.WINDOW_SERVICE);
        mButton = new Button(this);
        mButton.setText("WindowManager Button");
        mButton.setAllCaps(false);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick: ");
            }
        });

        mLayoutParams = new WindowManager.LayoutParams();
        mLayoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        mLayoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        mLayoutParams.x = 1000; // 偏移量
        mLayoutParams.y = 560; // 偏移量
        // mLayoutParams.alpha = 0.6f; // 透明度
        mLayoutParams.format = PixelFormat.TRANSPARENT;
        // 更多type：https://developer.android.com/reference/android/view/WindowManager.LayoutParams.html
        // TYPE_SYSTEM_ALERT： 应用在后台运行时，window可以不消失
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        // 更多falgs:https://developer.android.com/reference/android/view/WindowManager.LayoutParams.html
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;

        mWindowManager.addView(mButton, mLayoutParams);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        mWindowManager.removeView(mButton);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
