package com.example.testquestion.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquestion.R;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.data.provider.Order;
import com.example.testquestion.ui.activities.MainActivity;
import com.example.testquestion.utils.MainViewModel;
import com.example.testquestion.utils.URLProvider;

import java.util.List;

public class DataAdapter<T extends ModelDataClass> extends RecyclerView.Adapter<DataViewHolder<T>> {
    private List<T> data;
    private Class<T> clazz;
    private MainViewModel<T> model;
    OnItemClickListener listener;
    private int page = 1;
    public DataAdapter(MainViewModel<T> model, MainActivity activity, Class<T> clazz) {
        model.getObservableCollection().observe(activity, ts -> {
            if(data != null && data.size()<ts.size()) page++;
            this.data = ts;
            notifyDataSetChanged();
        });
        this.model = model;
        this.clazz = clazz;
    }
    public DataAdapter(List<T> data, Class<T> clazz) {
        this.data = data;
        this.clazz = clazz;
    }

    @NonNull
    @Override
    public DataViewHolder<T> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_data, parent, false);
        return new DataViewHolder<>(view, this, clazz);
    }


    @Override
    public void onBindViewHolder(@NonNull DataViewHolder<T> holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void uploadMoreData() {
        Order order = new Order();
        order.addPage(URLProvider.getURL(clazz.getSimpleName()), page+1);
        model.uploadPage(order);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
