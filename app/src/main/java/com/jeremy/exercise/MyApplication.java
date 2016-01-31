package com.jeremy.exercise;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Jeremy on 2016/1/26.
 * Mail:jyan.lin@foxmail.com
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //initRealm
        RealmConfiguration config = new RealmConfiguration.Builder(getApplicationContext()).build();
        Realm.setDefaultConfiguration(config);


    }
}
