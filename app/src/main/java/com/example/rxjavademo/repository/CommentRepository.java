package com.example.rxjavademo.repository;

import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CommentRepository {
    private static Retrofit myRetrofit;

    private CommentRepository() {
    }

    public static Retrofit getInstance() {
        if (myRetrofit == null) {
            myRetrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .client(LoggingInterceptor.getHttpClient())
                    .build();
        }
        return myRetrofit;
    }

}
