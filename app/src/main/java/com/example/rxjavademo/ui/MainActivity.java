package com.example.rxjavademo.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.example.rxjavademo.R;
import com.example.rxjavademo.databinding.ActivityMainBinding;
import com.example.rxjavademo.model.CommentResponseItem;
import com.example.rxjavademo.repository.CommentRepository;
import com.example.rxjavademo.repository.RetrofitService;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RetrofitService retrofitService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(MainActivity.this));
        setContentView(binding.getRoot());
        initViews();
    }

    private void initViews() {
        configureRecyclerView();
        configureButtons();
    }

    private void configureRecyclerView() {
        Retrofit retrofit = CommentRepository.getInstance();
        retrofitService = retrofit.create(RetrofitService.class);
        binding.rvCommentsList.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        fetchData();
    }

    private void fetchData() {
        retrofitService.getComments()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResults, this::handleError);
    }

    private void handleResults(List<CommentResponseItem> commentList) {
        binding.progressbar.setVisibility(View.GONE);
        if (commentList != null && commentList.size() != 0) {
            binding.rvCommentsList.setAdapter(new CommentAdapter(commentList));
        } else {
            Snackbar.make(binding.coordinatorMain, "No Data Found", Snackbar.LENGTH_LONG).show();
        }
    }

    private void handleError(Throwable throwable) {
        binding.progressbar.setVisibility(View.GONE);
        Snackbar.make(binding.coordinatorMain, "MainActivity :: " + throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
    }

    private void configureButtons() {
        Button btnCellInfo = findViewById(R.id.btnCellInfo);
        btnCellInfo.setOnClickListener(view -> {
            Intent cellInfoIntent = new Intent(MainActivity.this, TextInputLayoutExample.class);
            cellInfoIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(cellInfoIntent);
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}