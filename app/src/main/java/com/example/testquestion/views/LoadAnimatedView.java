package com.example.testquestion.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testquestion.R;

public class LoadAnimatedView extends FrameLayout {
    public LoadAnimatedView(@NonNull Context context) {
        super(context);
        inflateView();
    }

    public LoadAnimatedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateView();
    }

    public LoadAnimatedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView();
    }

    View view;
    private void inflateView() {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_planet);


        this.addView(imageView);
        this.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            LayoutParams params = new LayoutParams(
                    Math.round(LoadAnimatedView.this.getWidth()/1.2f),
                    Math.round(LoadAnimatedView.this.getHeight()/1.2f));
            params.gravity = Gravity.CENTER;
            imageView.setLayoutParams(params);
        });
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.pulse);
        imageView.startAnimation(anim);

    }
}
