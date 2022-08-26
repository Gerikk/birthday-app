package com.gerikk.android.birthday_app_front.utils;


import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class UtilApi {

    public static final String BASE_URL = "http://192.168.1.14:8080";
    public static final String URL_LOGIN = BASE_URL+"/login";
    public static final String CREATE_BIRTHDAY = BASE_URL+"/users/%s/birthdays";

    public static OkHttpClient client = new OkHttpClient();

    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public static void get(String url, final ApiCallback callback) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.fail("error");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful())
                    callback.success(response.body().string(), null);
                else {
                    callback.fail("error_on_response");
                }
            }
        });
    }

    public static void post(String url, Map<String, String> map, final ApiCallback callback, String token) {

        Request request;

        MultipartBody.Builder requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : map.entrySet()) {
            requestBody.addFormDataPart(entry.getKey(), entry.getValue());
        }

        if(token != null && !token.isEmpty()) {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", "Bearer " + token)
                    .post(requestBody.build())
                    .build();
        }else{
            request = new Request.Builder()
                    .url(url)
                    .post(requestBody.build())
                    .build();
        }


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.fail("error");
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull final Response response) throws IOException {
                if (response.isSuccessful()) {
                    callback.success(response.body().string(), response.headers().get("token"));
                }else {
                    callback.fail("error");
                }
            }
        });
    }

}
