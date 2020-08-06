package com.example.testquestion;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.People;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.Specie;
import com.example.testquestion.data.model.StarShip;
import com.example.testquestion.data.model.Vehicle;

import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Film>> films = new MutableLiveData<>();
    private MutableLiveData<List<People>> peoples = new MutableLiveData<>();
    private MutableLiveData<List<Planet>> planets = new MutableLiveData<>();
    private MutableLiveData<List<Specie>> species = new MutableLiveData<>();
    private MutableLiveData<List<StarShip>> starShips = new MutableLiveData<>();
    private MutableLiveData<List<Vehicle>> vehicles = new MutableLiveData<>();

    public MainViewModel() {
        
    }
}
