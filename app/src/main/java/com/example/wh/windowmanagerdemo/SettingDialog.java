package com.example.wh.windowmanagerdemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by hsr on 18-9-12.
 */
// 随着activity的生命周期而dismiss，按返回键dismiss，按home键可以作为悬浮窗。
public class SettingDialog extends Dialog {
    private static final String TAG = "SettingDialog";
    private Context mContext;
    private Window mSettingWindow;
    private WindowManager.LayoutParams mParams;

    public SettingDialog(@NonNull Context context) {
        // 加上这个style，可以不让背景变暗
        super(context, R.style.SettingDialog);
        mContext = context;
    }

    private void initWindow() {
        mSettingWindow = getWindow();
        mParams = mSettingWindow.getAttributes();
        mParams.height = WindowManager.LayoutParams.WRAP_CONTENT;
        mParams.width = WindowManager.LayoutParams.WRAP_CONTENT;
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
            mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        } else {
            mParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        }
        mParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mParams.format = PixelFormat.TRANSPARENT;

        mParams.gravity = Gravity.CENTER;
        mParams.y = -220;
        mSettingWindow.setAttributes(mParams);
        mSettingWindow.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.dialog_setting);
        ((Button) findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "点击了Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
