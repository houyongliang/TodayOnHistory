package com.fnfh.homepage;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * 1. 作用
 * 2. 作者 侯永亮
 * 3. 时间 2016/12/22.
 */

public class BaseApplication extends Application {
    private RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        mRefWatcher = LeakCanary.install(this);
    }
}
