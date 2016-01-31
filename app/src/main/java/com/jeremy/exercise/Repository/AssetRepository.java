package com.jeremy.exercise.Repository;

import io.realm.RealmObject;

/**
 * Created by Jeremy on 2016/1/26.
 * Mail:jyan.lin@foxmail.com
 */
public class AssetRepository extends RealmObject {
    private long timeStamp;
    private String fullpath;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }
}
