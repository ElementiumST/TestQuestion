package com.example.testquestion.ui.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.testquestion.R;
import com.example.testquestion.data.dataClasses.DataStack;
import com.example.testquestion.ui.fragments.InfoFragment;
import com.example.testquestion.utils.URLProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoreInfoActivity extends AppCompatActivity {

    InfoFragment activeFragment;
    FragmentManager fragmentManager;
    ScrollView scrollView;
    DataStack activeStack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Objects.requireNonNull(getSupportActionBar()).hide();

        activeStack = (DataStack) getIntent().getSerializableExtra("data");

        scrollView = findViewById(R.id.scroll);
        ImageView imageView = findViewById(R.id.background);
        imageView.setImageResource(URLProvider.getImageResource(
                activeStack.getClazz().getSimpleName()));
        LinearLayout layout = findViewById(R.id.root);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        activeFragment = new InfoFragment(activeStack);
        transaction.replace(layout.getId(), activeFragment);
        transaction.commit();
    }
    public void changePage(InfoFragment from, DataStack to){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.remove(from);
        activeStack = to;
        activeFragment = new InfoFragment(to);
        transaction.add(R.id.root, activeFragment);
        transaction.commit();
        scrollView.fullScroll(ScrollView.FOCUS_UP);
    }
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("stack", activeStack);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        DataStack stack = (DataStack) savedInstanceState.getSerializable("stack");
        if(stack != null)
            changePage(activeFragment, stack);
        super.onRestoreInstanceState(savedInstanceState);
    }

    public DataStack getActiveStack() {
        return activeStack;
    }
}
