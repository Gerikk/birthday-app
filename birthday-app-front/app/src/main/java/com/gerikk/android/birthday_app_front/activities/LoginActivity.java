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

import com.gerikk.android.birthday_app_front.R;
import com.gerikk.android.birthday_app_front.utils.ApiCallback;
import com.gerikk.android.birthday_app_front.utils.Util;
import com.gerikk.android.birthday_app_front.utils.UtilApi;
import com.google.android.material.snackbar.Snackbar;

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

            // TODO : Appeler la méthode permettant de faire un appel API via POST
            UtilApi.post(UtilApi.URL_LOGIN, map, new ApiCallback() {
                @Override
                public void fail(String json) {


                    runOnUiThread(() -> {
                        Log.d("info", json);
                        //Snackbar.make(findViewById(R.id.coordinator_root), "Error", Snackbar.LENGTH_SHORT).show();
                    });

                }

                @Override
                public void success(String json) {
                    runOnUiThread(() -> {

                        Log.d("info", "success");
                       //Snackbar.make(findViewById(R.id.coordinator_root), "Success", Snackbar.LENGTH_SHORT).show();
                    });
                }
            });

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
            Snackbar.make(findViewById(R.id.coordinator_root), "Erreur", Snackbar.LENGTH_SHORT).show();

        });
    }

    @Override
    public void success(final String json) {

        handler.post(() -> {
            Log.d("Reussi", "success: " + json);

            Snackbar.make(findViewById(R.id.coordinator_root), "Anniversaire rajouté", Snackbar.LENGTH_SHORT).show();

            startActivity(new Intent(this, MainActivity.class));
            finish();
            
        });
    }
}