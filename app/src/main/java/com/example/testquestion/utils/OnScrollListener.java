package com.example.testquestion.utils;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.example.testquestion.ui.adapters.DataAdapter;

import java.util.Objects;

public class OnScrollListener extends RecyclerView.OnScrollListener {

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        if (!recyclerView.canScrollVertically(1)) {
            Adapter adapter = recyclerView.getAdapter();
            Objects.requireNonNull(adapter);
            if(!adapter.getClass().equals(DataAdapter.class)) return;
            DataAdapter dataAdapter = (DataAdapter) adapter;
            dataAdapter.uploadMoreData();
        }
    }
}