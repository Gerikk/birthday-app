package com.gerikk.android.birthday_app_front.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if ()
        startActivity(new Intent(this, LoginActivity.class));
else
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}