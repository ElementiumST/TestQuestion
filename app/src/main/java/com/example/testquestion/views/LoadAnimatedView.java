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
        inflateView(context);
    }

    public LoadAnimatedView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        inflateView(context);
    }

    public LoadAnimatedView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflateView(context);
    }

    View view;
    private void inflateView(Context context) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(R.drawable.ic_planet);


        this.addView(imageView);
        this.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                        Math.round(LoadAnimatedView.this.getWidth()/1.2f),
                        Math.round(LoadAnimatedView.this.getHeight()/1.2f));
                params.gravity = Gravity.CENTER;
                imageView.setLayoutParams(params);
            }
        });
        /*
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.view_loader,  this, true);
        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_planet);

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = this.getWidth()/2;
        params.height = this.getHeight()/2;
        imageView.setLayoutParams(params);
        requestLayout();


         */
        Animation anim = AnimationUtils.loadAnimation(context, R.anim.pulse);
        imageView.startAnimation(anim);

    }
}
