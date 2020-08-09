package com.example.testquestion.ui.planet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testquestion.ui.activities.MainActivity;
import com.example.testquestion.utils.OnScrollListener;
import com.example.testquestion.R;

public class ListFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_planets, container, false);

        RecyclerView recyclerView = root.findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        RecyclerView.LayoutManager manager = new LinearLayoutManager(requireContext());
        recyclerView.setLayoutManager(manager);

        MainActivity activity = (MainActivity) requireActivity();
        recyclerView.setAdapter(activity.getAdapter(activity.getCurrentListClass().getSimpleName()));
        recyclerView.addOnScrollListener(new OnScrollListener());

        return root;
    }
}
