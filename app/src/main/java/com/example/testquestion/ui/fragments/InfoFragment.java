package com.example.testquestion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.testquestion.data.dataClasses.DataStack;
import com.example.testquestion.R;
import com.example.testquestion.data.dataClasses.ArrayValue;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.ui.activities.MoreInfoActivity;
import com.example.testquestion.ui.views.InfoView;
import com.example.testquestion.ui.views.carousel.CarouselView;
import com.example.testquestion.utils.URLProvider;

import java.util.Map;

public class InfoFragment extends Fragment {

    private DataStack stack;

    public InfoFragment(DataStack stack) {
        this.stack = stack;
    }

    public InfoFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        TextView titleView = root.findViewById(R.id.title);
        ImageView imageView = root.findViewById(R.id.image);
        if(stack == null)
            stack = ((MoreInfoActivity) requireActivity()).getActiveStack();
        imageView.setImageResource(URLProvider.getImageResource(stack.getClazz().getSimpleName()));
        titleView.setText(stack.getElement().getName());
        LinearLayout socket = root.findViewById(R.id.socket);
        for (Map.Entry<String, String> entry : stack.getElement().getOtherContent().entrySet()) {
            InfoView v = new InfoView(requireContext());
            socket.addView(v);
            v.setContent(entry.getKey(), entry.getValue());
        }
        for (ArrayValue entry:
                stack.getElement().getArrays()) {
            if(entry.getArray().length == 0)
                continue;
            CarouselView view = new CarouselView(requireContext());
            view.setContent(entry, dataStack -> {
                MoreInfoActivity activity = (MoreInfoActivity) requireActivity();
                activity.changePage(this, dataStack);
            });
            socket.addView(view);
        }
        return root;
    }

    public DataStack getDataStack() {
        return stack;
    }
}
