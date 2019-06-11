package com.aliya.compat.sample;

import android.support.multidex.MultiDexApplication;

import com.aliya.compat.CrashCompat;

/**
 * App
 *
 * @author a_liYa
 * @date 2019-06-10 17:14.
 */
public class App extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        CrashCompat.fixBug();
    }
}
