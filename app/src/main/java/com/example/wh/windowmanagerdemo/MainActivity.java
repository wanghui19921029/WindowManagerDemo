package com.example.wh.windowmanagerdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

@SuppressLint("LongLogTag")
public class MainActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "windowmanagerdemo--MainActivity";
    private Button mShowDialogBtn, mDismissDialogBtn, mShowWindowBtn, mDismissWindowBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);

        mShowDialogBtn = (Button) findViewById(R.id.btn);
        mDismissDialogBtn = (Button) findViewById(R.id.btn2);
        mShowWindowBtn = (Button) findViewById(R.id.btn3);
        mDismissWindowBtn = (Button) findViewById(R.id.btn4);
        mShowDialogBtn.setOnClickListener(this);
        mDismissDialogBtn.setOnClickListener(this);
        mShowWindowBtn.setOnClickListener(this);
        mDismissWindowBtn.setOnClickListener(this);

        requestPermission();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TAG, "dispatchTouchEvent: ");
        return super.dispatchTouchEvent(ev);
    }

    private SettingDialog mSettingDialog;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
                if (mSettingDialog == null) {
                    mSettingDialog = new SettingDialog(MainActivity.this);
                }
                if (!mSettingDialog.isShowing()) {
                    mSettingDialog.show();
                    Log.i(TAG, "add view");
                }
                break;
            case R.id.btn2:
                if (mSettingDialog != null && mSettingDialog.isShowing()) {
                    mSettingDialog.dismiss();
                    Log.i(TAG, "remove view");
                }
                break;
            case R.id.btn3:
                Intent intent = new Intent(MainActivity.this, WindowService.class);
                startService(intent);
                break;
            case R.id.btn4:
                Intent intent2 = new Intent(MainActivity.this, WindowService.class);
                stopService(intent2);
                break;
            default:
                break;
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivityForResult(intent, 100);
            } else {
                //TODO do something you need
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            Log.i(TAG, "onActivityResult: ");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }
}
