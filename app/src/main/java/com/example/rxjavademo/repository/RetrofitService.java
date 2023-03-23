package com.example.rxjavademo.repository;

import com.example.rxjavademo.model.CommentResponseItem;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface RetrofitService {
    @GET("comments")
    Observable<List<CommentResponseItem>> getComments();
}
