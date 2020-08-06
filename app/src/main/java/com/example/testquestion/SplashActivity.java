package com.example.testquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        bindViews();
        bar.setProgress(50);
        bar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.colorBackgroundLight), android.graphics.PorterDuff.Mode.SRC_IN);
        startUploadData();
    }



    private void bindViews() {
        bar = findViewById(R.id.progressBar);
    }
    private void startUploadData() {

    }
}
