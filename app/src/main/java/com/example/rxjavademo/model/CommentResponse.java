package com.example.rxjavademo.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CommentResponse {

    @SerializedName("CommentResponse")
    private List<CommentResponseItem> commentsResponse;

    public List<CommentResponseItem> getCommentsResponse() {
        return commentsResponse;
    }

    @Override
    public String toString() {
        return
                "CommentResponse{" +
                        "commentsResponse = '" + commentsResponse + '\'' +
                        "}";
    }
}