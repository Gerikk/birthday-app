package com.gerikk.android.birthday_app_front.adapters;

public abstract class ListItem {

    public static final int TYPE_MONTH = 0;
    public static final int TYPE_BIRTHDAY = 1;

    abstract public int getType();
}
