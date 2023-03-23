package com.example.rxjavademo.repository;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class LoggingInterceptor {

    private LoggingInterceptor() {
    }

    public static OkHttpClient getHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(interceptor)
                .build();
    }
}
