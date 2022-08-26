package com.gerikk.android.birthday_app_front.adapters;

import com.gerikk.android.birthday_app_front.models.Birthday;

public class BirthdayItem extends ListItem {

    public Birthday birthday;

    public BirthdayItem(Birthday birthday) {
        this.birthday = birthday;
    }

    @Override
    public int getType() {
        return TYPE_BIRTHDAY;
    }
}
