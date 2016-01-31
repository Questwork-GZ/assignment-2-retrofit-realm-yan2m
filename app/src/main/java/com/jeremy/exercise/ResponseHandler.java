package com.jeremy.exercise;

/**
 * Created by Jeremy on 2016/1/23.
 * Mail:jyan.lin@foxmail.com
 */
public interface ResponseHandler<T> {
    void onSuccess(T object);
    void onFail(Throwable t);
}
