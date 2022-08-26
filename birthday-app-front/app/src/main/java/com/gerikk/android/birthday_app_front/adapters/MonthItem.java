package com.gerikk.android.birthday_app_front.adapters;

public class MonthItem extends ListItem{

    public String month;

    public MonthItem(int number, String month) {
        this.index = number;
        this.month = month;
    }

    @Override
    public int getType() {
        return TYPE_MONTH;
    }
}
