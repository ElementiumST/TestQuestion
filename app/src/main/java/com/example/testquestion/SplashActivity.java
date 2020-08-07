package com.example.testquestion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.ModelDataClass;
import com.example.testquestion.data.model.People;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.Specie;
import com.example.testquestion.data.model.StarShip;
import com.example.testquestion.data.model.Vehicle;
import com.example.testquestion.data.provider.Order;
import com.example.testquestion.data.provider.PageProvider;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class SplashActivity extends AppCompatActivity {
    ProgressBar bar;
    private static final int DATA_COUNT = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        bindViews();
        bar.getProgressDrawable().setColorFilter(
                getResources().getColor(R.color.colorBackgroundLight), android.graphics.PorterDuff.Mode.SRC_IN);
        startUploadData();
    }

    private void bindViews() {
        bar = findViewById(R.id.progressBar);
    }
    Intent intent;
    private void startUploadData() {
        intent = new Intent(this, MainActivity.class);
        (new FirstPageProvider<>(this, Film.class, Film.getBasePageUrl())).provide();
        (new FirstPageProvider<>(this, People.class, People.getBasePageUrl())).provide();
        (new FirstPageProvider<>(this, Planet.class, Planet.getBasePageUrl())).provide();
        (new FirstPageProvider<>(this, Specie.class, Specie.getBasePageUrl())).provide();
        (new FirstPageProvider<>(this, StarShip.class, StarShip.getBasePageUrl())).provide();
        (new FirstPageProvider<>(this, Vehicle.class, Vehicle.getBasePageUrl())).provide();
    }
    void dataLoadSuccess() {
        startActivity(intent);
        finish();
        overridePendingTransition(0, R.anim.activity_end);
    }
    class FirstPageProvider<T extends ModelDataClass> extends PageProvider<T> {
        private static final int percent = 100/DATA_COUNT+1;

        public FirstPageProvider(Context context, Class<T> type, String url) {
            super(context, type);
            this.order = new Order();
            order.addPage(url, 1);

        }


        @Override
        public void onLoadSuccess(List<T> data) {
            System.out.println(genericType.getName());
            intent.putExtra(genericType.getName()
                    .replace("com.example.testquestion.data.model.", ""),
                    data.toArray());
            bar.setProgress(bar.getProgress()+percent);
            if(bar.getProgress() >= 100)
                dataLoadSuccess();
        }
    }
}
