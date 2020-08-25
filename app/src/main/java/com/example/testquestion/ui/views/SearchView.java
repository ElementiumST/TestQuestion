package com.example.testquestion.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testquestion.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SearchView extends FrameLayout {
    public SearchView(@NonNull Context context) {
        super(context);
        initView();
    }


    public SearchView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    @SuppressLint("ResourceAsColor")
    private void initView() {
        Context context = getContext();

        FloatingActionButton floatingActionButton = new FloatingActionButton(context);
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.END;
        floatingActionButton.setLayoutParams(params);
        floatingActionButton.setImageResource(R.drawable.ic_baseline_search_24);
        floatingActionButton.setRippleColor(R.color.colorBackgroundLight);
        floatingActionButton.setBackgroundColor(R.color.colorBackgroundLight);


        this.addView(floatingActionButton);
    }
}
