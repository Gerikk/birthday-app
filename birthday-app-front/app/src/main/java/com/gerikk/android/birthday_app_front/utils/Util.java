package com.gerikk.android.birthday_app_front.utils;

import android.content.Context;

import com.gerikk.android.birthday_app_front.models.User;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Util {

    private static final String PREF_FILE = "pref_file";
    private static final String USER = "user";

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat FORMAT_INPUT = new SimpleDateFormat("dd/MM/yyyy");


    public static void setUser(Context context, String json) {

        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit().putString(USER, json).apply();
    }

    public static User getUser(Context context) throws JSONException, ParseException {
        User user = new User(context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getString(USER, ""));
        return user;
    }

    public static boolean isUserNameValid(String userName) {

        return userName != null;
    }

    public static boolean isPasswordValid(String password) {

        return password != null;
    }

    public static Date initDateFromDB(String dateStr) throws ParseException {
        return FORMAT.parse(dateStr);
    }

    public static String printDate(Date date) {
        return FORMAT.format(date);
    }


    public static long getAge(Date date) {
        long diff = System.currentTimeMillis() - date.getTime();
        return diff/31622400000l ;
    }
}
