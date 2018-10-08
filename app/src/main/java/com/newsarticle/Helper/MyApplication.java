package com.newsarticle.Helper;

import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;

/**
 * Created by akhil on 7/10/18.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getContext() {
        return instance;
    }



}

