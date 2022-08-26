package com.gerikk.android.birthday_app_front.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.gerikk.android.birthday_app_front.adapters.BirthdayItem;
import com.gerikk.android.birthday_app_front.adapters.ListItem;
import com.gerikk.android.birthday_app_front.adapters.MonthItem;
import com.gerikk.android.birthday_app_front.models.Birthday;
import com.gerikk.android.birthday_app_front.models.User;

import org.json.JSONException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

public class Util {

    private static final String PREF_FILE = "pref_file";
    private static final String USER = "user";

    private static final String BEARER = "bearer";

    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat FORMAT_INPUT = new SimpleDateFormat("dd/MM/yyyy");


    public static void setUser(Context context, String json) {

        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit().putString(USER, json).apply();
    }

    public static void setBearer(Context context, String bearer){
        context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).edit().putString(BEARER, bearer).apply();
    }

    public static String getBearer(Context context){
        return context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getString(BEARER, "");
    }


    public static User getUser(Context context) throws JSONException, ParseException {
        return new User(context.getSharedPreferences(PREF_FILE, Context.MODE_PRIVATE).getString(USER, ""));
    }

    public static boolean isUserNameValid(String userName) {

        if(userName == null|| TextUtils.isEmpty(userName)){
            return false;
        }

        return userName.trim().length()>5;
    }

    public static boolean isPasswordValid(String password) {

        if(password == null|| TextUtils.isEmpty(password)){
            return false;
        }

        return password.trim().length()>5;
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

    public static ArrayList<ListItem> createListItems(ArrayList<Birthday> birthdays) {

        ArrayList<ListItem> listItems = new ArrayList<>();


        int monthNumber = 100;
        String[] months = {"Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet", "Aout", "Septembre", "Octobre", "Novembre", "Décembre"};

        // TODO : trier la liste en fonction des mois d'anniversaire


        for (String month:months) {

            listItems.add(new MonthItem(monthNumber, month));

            monthNumber+=100;
        }

        for (Birthday birthday:birthdays) {
            listItems.add(new BirthdayItem(birthday));
        }

        Collections.sort(listItems);

        return listItems;
    }

    public static boolean isDateValid(String string) {
        try {
            FORMAT_INPUT.parse(string);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static Date initDateFromEditText(String str) throws ParseException {
        return FORMAT_INPUT.parse(str);
    }

}
