package com.example.testquestion.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.testquestion.R;
import com.example.testquestion.data.dataClasses.DataStack;
import com.example.testquestion.ui.fragments.InfoFragment;
import com.example.testquestion.utils.URLProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MoreInfoActivity extends AppCompatActivity {

    List<DataStack> story = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_info);
        Objects.requireNonNull(getSupportActionBar()).hide();

        story.add((DataStack) getIntent().getSerializableExtra("data"));

        ImageView imageView = findViewById(R.id.background);
        imageView.setImageResource(URLProvider.getImageResource(
                story.get(story.size()-1).getClazz().getSimpleName()));

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        InfoFragment fragment = new InfoFragment(story.get(0));
        transaction.add(R.id.root, fragment);
        transaction.commit();
    }

}
