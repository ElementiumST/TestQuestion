package com.example.testquestion.ui.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.testquestion.R;

import java.util.HashMap;
import java.util.Map;

public class MapView extends LinearLayout {
    public MapView(Context context) {
        super(context);
        initView();
    }

    public MapView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MapView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }
    private void initView() {
        setOrientation(VERTICAL);
    }

    public static int fieldCount = 7;

    @SuppressLint("SetTextI18n")
    public void setContent(HashMap<String, String> map) {
        this.removeAllViews();
        int iterator = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getValue().length() >30) continue;
            if(iterator > fieldCount) break;
            createTextView(entry.getKey()+":", entry.getValue());
            iterator++;
        }
    }
    private void createTextView(String key, String text) {
        Context context = getContext();
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(HORIZONTAL);

        TextView keyView = new TextView(context);
        keyView.setTextColor(getResources().getColor(R.color.colorBackgroundLight));
        keyView.setText(key);
        TextView valueView = new TextView(context);
        valueView.setTextColor(getResources().getColor(R.color.colorBackgroundLight));
        valueView.setText(text);


        LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.weight = 1;
        params.setMargins(2, 4, 2, 0);
        layout.setWeightSum(3);
        keyView.setLayoutParams(params);
        params.weight = 2;
        valueView.setLayoutParams(params);

        layout.addView(keyView);
        layout.addView(valueView);
        this.addView(layout);
    }
}
