package com.gerikk.android.birthday_app_front.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import com.gerikk.android.birthday_app_front.models.User;

import org.json.JSONException;

import java.text.ParseException;

import java.util.Date;

public class Util {

    private static final String PREF_FILE = "pref_file";
    private static final String USER = "user";

    public static void setUser(Context context, String json) {

        // TODO : sauvegarder
    }

    public static User getUser(Context context) throws JSONException, ParseException {
        // TODO : restaurer
        return null;
    }

    public static boolean isUserNameValid(String userName) {
        // TODO : écrire votre règle pour un username valide
        return false;
    }

    public static boolean isPasswordValid(String password) {
        // TODO : écrire votre règle pour un password valide
       return false;
    }

    public static Date initDateFromDB(String date) {
        //TODO : Ecrire méthode init
        return false;
    }
}
