package com.gerikk.android.birthday_app_front.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.gerikk.android.birthday_app_front.R;
import com.gerikk.android.birthday_app_front.utils.ApiCallback;
import com.gerikk.android.birthday_app_front.utils.Util;
import com.gerikk.android.birthday_app_front.utils.UtilApi;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements ApiCallback {

    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    public Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handler = new Handler();

        mEmailView = findViewById(R.id.username);
        mPasswordView = findViewById(R.id.password);
        mLoginFormView = findViewById(R.id.login);
        mProgressView = findViewById(R.id.loading);

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {
                String email = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();

                mLoginFormView.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                mLoginFormView.setEnabled(Util.isUserNameValid(email) && Util.isPasswordValid(password));
            }
        };

        mEmailView.addTextChangedListener(textWatcher);
        mPasswordView.addTextChangedListener(textWatcher);

        mPasswordView.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                this.attemptLogin();
            }
            return false;
        });

        mLoginFormView.setOnClickListener(v ->
            attemptLogin()
        );
    }

    public void attemptLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!Util.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!Util.isUserNameValid(email)) {
            mEmailView.setError(getString(R.string.invalid_username));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            showProgress(true);

            Map<String, String> map = new HashMap<>();
            map.put("username", email);
            map.put("password", password);

            UtilApi.post(UtilApi.URL_LOGIN, map, LoginActivity.this, null);

        }
    }

    private void showProgress(boolean visible) {
        mProgressView.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }

    @Override
    public void fail(final String json) {
        mProgressView.setVisibility(View.INVISIBLE);
        handler.post(() -> {
            Log.d("Grosse_bourde", "fail: " + json);

            Toast.makeText(LoginActivity.this, "User Incorrect", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public void success(final String json, String token) {

        handler.post(() -> {
            Log.d("Reussi", "return_success: " + json);

            Util.setBearer(this, token);

            Util.setUser(this, json);

            startActivity(new Intent(this, MainActivity.class));
            finish();
            
        });
    }
}