package com.example.phundal.stackoverflow;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by phundal on 5/7/18.
 */

public class StackOverflowApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
