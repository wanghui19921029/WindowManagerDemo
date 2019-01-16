package com.example.wh.windowmanagerdemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by hsr on 18-9-12.
 */
// 随着activity的生命周围而dismiss，按返回键dismiss，按home键可以作为悬浮窗。
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
        mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
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
        setContentView(R.layout.dialog_setting);
        initWindow();
    }
}
