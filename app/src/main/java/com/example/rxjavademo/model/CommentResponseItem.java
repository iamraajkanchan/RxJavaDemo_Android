package com.example.rxjavademo.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import com.google.gson.annotations.SerializedName;

public class CommentResponseItem implements Parcelable {

    @SerializedName("name")
    private String name;

    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("body")
    private String body;

    @SerializedName("email")
    private String email;

    protected CommentResponseItem(Parcel in) {
        name = in.readString();
        postId = in.readInt();
        id = in.readInt();
        body = in.readString();
        email = in.readString();
    }

    public static final Creator<CommentResponseItem> CREATOR = new Creator<CommentResponseItem>() {
        @Override
        public CommentResponseItem createFromParcel(Parcel in) {
            return new CommentResponseItem(in);
        }

        @Override
        public CommentResponseItem[] newArray(int size) {
            return new CommentResponseItem[size];
        }
    };

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getPostId() {
        return postId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(postId);
        dest.writeInt(id);
        dest.writeString(body);
        dest.writeString(email);
    }

    @Override
    public String toString() {
        return
                "CommentResponseItem{" +
                        "name = '" + name + '\'' +
                        ",postId = '" + postId + '\'' +
                        ",id = '" + id + '\'' +
                        ",body = '" + body + '\'' +
                        ",email = '" + email + '\'' +
                        "}";
    }
}