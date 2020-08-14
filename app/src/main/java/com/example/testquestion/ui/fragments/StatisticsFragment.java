package com.example.testquestion.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.testquestion.R;
import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.People;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.Specie;
import com.example.testquestion.data.model.StarShip;
import com.example.testquestion.data.model.Vehicle;
import com.example.testquestion.ui.activities.MainActivity;
import com.example.testquestion.ui.views.carousel.CarouselView;

import java.util.ArrayList;
import java.util.List;

public class StatisticsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_statistics, container, false);
        CarouselView view = root.findViewById(R.id.carousel);
        List<Class> classes = new ArrayList<>();
        classes.add(Film.class);
        classes.add(Planet.class);
        classes.add(People.class);
        classes.add(Specie.class);
        classes.add(StarShip.class);
        classes.add(Vehicle.class);
        MainActivity activity = (MainActivity) requireActivity();
        view.setContent(classes, dataStack -> activity.setActiveInfoFragment(dataStack.getClazz()));
        return root;
    }
}
