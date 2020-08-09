package com.example.testquestion.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testquestion.R;

public class InfoView extends FrameLayout {

    public InfoView(@NonNull Context context) {
        super(context);
        init();
    }
    public InfoView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private TextView title, text;
    private void init() {
        Context context = getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View root = inflater.inflate(R.layout.view_info, this, false);
        title = root.findViewById(R.id.title);
        text = root.findViewById(R.id.text);
        this.addView(root);
    }
    public void setContent(String key, String value) {
        title.setText(key);
        text.setText(value);
    }
}
