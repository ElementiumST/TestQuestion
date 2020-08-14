package com.example.testquestion.ui.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquestion.R;
import com.example.testquestion.data.dataClasses.DataStack;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.ui.activities.MainActivity;
import com.example.testquestion.utils.URLProvider;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    List<Class> classList;

    public CategoryAdapter(List<Class> classList) {
        this.classList = classList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.adapter_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.bind(classList.get(position));
    }

    @Override
    public int getItemCount() {
        return classList.size();
    }

    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @SuppressWarnings("unchecked")
    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        ImageView imageView;
        Class clazz;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            titleView = itemView.findViewById(R.id.title);
            imageView = itemView.findViewById(R.id.image);
            itemView.setOnClickListener(view -> {
                if(listener != null)
                    listener.OnClick(new DataStack(null, clazz));
            });
        }
        @SuppressLint("SetTextI18n")
        public void  bind(Class clazz) {
            titleView.setText(clazz.getSimpleName().toLowerCase()+"s");
            imageView.setImageResource(URLProvider.getImageResource(clazz.getSimpleName()));
            this.clazz = clazz;
        }
    }
}
