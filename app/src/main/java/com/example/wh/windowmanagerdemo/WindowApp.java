package com.example.wh.windowmanagerdemo;

import android.app.Application;

public class WindowApp extends Application {

    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Application getsApplication() {
        return sApplication;
    }
}
