package com.example.rxjavademo.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rxjavademo.R;
import com.example.rxjavademo.model.CommentResponseItem;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {

    private final List<CommentResponseItem> commentList;

    public CommentAdapter(List<CommentResponseItem> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item_row, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentResponseItem commentData = commentList.get(position);
        holder.tvCommentName.setText(commentData.getName());
        holder.tvCommentEmail.setText(commentData.getEmail());
        holder.tvCommentBody.setText(commentData.getBody());
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    static class CommentViewHolder extends RecyclerView.ViewHolder {

        TextView tvCommentName;
        TextView tvCommentEmail;
        TextView tvCommentBody;

        public CommentViewHolder(View itemView) {
            super(itemView);
            tvCommentName = itemView.findViewById(R.id.tvCommentName);
            tvCommentEmail = itemView.findViewById(R.id.tvCommentEmail);
            tvCommentBody = itemView.findViewById(R.id.tvCommentBody);
        }

    }

}
