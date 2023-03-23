package com.example.rxjavademo.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.rxjavademo.databinding.ActivityTextInputLayoutExampleBinding;

public class TextInputLayoutExample extends AppCompatActivity {

    private ActivityTextInputLayoutExampleBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTextInputLayoutExampleBinding.inflate(LayoutInflater.from(TextInputLayoutExample.this));
        setContentView(binding.getRoot());
    }
}