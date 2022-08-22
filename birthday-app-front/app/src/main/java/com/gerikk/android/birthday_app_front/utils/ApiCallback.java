package com.gerikk.android.birthday_app_front.utils;

// TODO : comprendre cette interface
public interface ApiCallback {

    void fail(String json);
    void success(String json);
}
