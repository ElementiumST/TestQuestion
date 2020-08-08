package com.example.testquestion.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquestion.R;
import com.example.testquestion.data.model.ModelDataClass;
import com.example.testquestion.views.MapView;

import java.util.HashMap;

public class DataViewHolder<T extends ModelDataClass> extends RecyclerView.ViewHolder {
    TextView nameView;
    MapView mapView;
    public DataViewHolder(@NonNull View itemView) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        mapView = itemView.findViewById(R.id.mapView);
    }
    public void bind(T element) {
        HashMap<String, String> data = element.getOtherContent();
        nameView.setText(element.getName());
        mapView.setContent(data);

    }
}