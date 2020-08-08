package com.example.testquestion.ui.planet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;

import com.example.testquestion.MainActivity;
import com.example.testquestion.OnScrollListener;
import com.example.testquestion.R;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.URLProvider;
import com.example.testquestion.ui.DataAdapter;

public class PlanetFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_planets, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        LayoutManager manager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(manager);

        MainActivity activity = (MainActivity) requireActivity();
        @SuppressWarnings("unchecked")
        DataAdapter<Planet> planetDataAdapter = new DataAdapter<Planet>(activity.getModel(URLProvider.PLANET), (MainActivity) requireActivity(), Planet.class);
        recyclerView.setAdapter(planetDataAdapter);
        recyclerView.addOnScrollListener(new OnScrollListener());

        return root;
    }
}
