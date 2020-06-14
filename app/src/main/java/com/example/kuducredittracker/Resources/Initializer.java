package com.example.kuducredittracker.Resources;

import android.app.Application;

import com.example.kuducredittracker.BuildConfig;

import net.gotev.uploadservice.UploadService;

public class Initializer extends Application
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        UploadService.NAMESPACE = BuildConfig.APPLICATION_ID;
        UploadService.NAMESPACE = "com.example.kuducredittracker";
    }
}
