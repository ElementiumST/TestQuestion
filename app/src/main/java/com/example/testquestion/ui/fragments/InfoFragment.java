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
import com.example.testquestion.ui.views.InfoView;
import com.example.testquestion.ui.views.carousel.CarouselView;
import com.example.testquestion.utils.URLProvider;

import java.util.Map;

public class InfoFragment extends Fragment {

    private DataStack stack;
    LinearLayout socket;

    public InfoFragment(DataStack stack) {
        this.stack = stack;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_info, container, false);
        TextView titleView = root.findViewById(R.id.title);
        ImageView imageView = root.findViewById(R.id.image);
        imageView.setImageResource(URLProvider.getImageResource(stack.getClazz().getSimpleName()));
        titleView.setText(stack.getElement().getName());
        socket = root.findViewById(R.id.socket);

        for (Map.Entry<String, String> entry : stack.getElement().getOtherContent().entrySet()) {
            InfoView v = new InfoView(requireContext());
            socket.addView(v);
            v.setContent(entry.getKey(), entry.getValue());
        }
        uploadCarousels();
        return root;
    }
    private<T extends ModelDataClass> void uploadCarousels() {
        for (ArrayValue entry:
            stack.getElement().getArrays()) {
            CarouselView view = new CarouselView(requireContext());
            view.setContent(entry);
            socket.addView(view);
        }
    }
}
