package com.example.testquestion.ui.adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquestion.data.dataClasses.DataStack;
import com.example.testquestion.ui.activities.MoreInfoActivity;
import com.example.testquestion.R;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.ui.views.MapView;
import com.example.testquestion.utils.URLProvider;

import java.util.HashMap;

class DataViewHolder<T extends ModelDataClass> extends RecyclerView.ViewHolder {
    private TextView nameView;
    private MapView mapView;
    private T element;

    DataViewHolder(@NonNull View itemView, DataAdapter adapter, Class<T> clazz) {
        super(itemView);
        nameView = itemView.findViewById(R.id.name);
        mapView = itemView.findViewById(R.id.mapView);
        ImageView imageView = itemView.findViewById(R.id.image);
        imageView.setImageResource(URLProvider.getImageResource(clazz.getSimpleName()));
        itemView.setOnClickListener(view ->{
            if(adapter.listener != null)
                adapter.listener.OnClick(new DataStack<>(element, clazz));
            }

        );
    }
    void bind(T element) {
        this.element = element;
        HashMap<String, String> data = element.getOtherContent();
        nameView.setText(element.getName().toLowerCase());
        mapView.setContent(data);
    }

}