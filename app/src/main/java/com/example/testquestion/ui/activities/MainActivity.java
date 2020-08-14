package com.example.testquestion.ui.activities;

import android.os.Bundle;

import com.example.testquestion.R;
import com.example.testquestion.ui.adapters.DataAdapter;
import com.example.testquestion.utils.MainViewModel;
import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.data.model.People;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.Specie;
import com.example.testquestion.data.model.StarShip;
import com.example.testquestion.utils.URLProvider;
import com.example.testquestion.data.model.Vehicle;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    HashMap modelMap = new HashMap<String, String>();
    NavController navController;
    BottomNavigationView navView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_planet, R.id.navigation_statistics)
                .build();
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        Objects.requireNonNull(getSupportActionBar()).hide();

        initModels();

    }
    ViewModelProvider provider;
    private void initModels() {
        provider = new ViewModelProvider(this);
        createModel(Film.class);
        createModel(Vehicle.class);
        createModel(People.class);
        createModel(Planet.class);
        createModel(StarShip.class);
        createModel(Specie.class);
    }

    @SuppressWarnings("unchecked")
    private <T extends ModelDataClass> void createModel(Class<T> modelClass) {
        MainViewModel<T> model = (MainViewModel<T>)
                provider.get(URLProvider.getURL(modelClass.getSimpleName()), MainViewModel.class);
        model.setClass(modelClass);
        model.setData(getIntent());
        model.setContext(this);
        modelMap.put(URLProvider.getURL(modelClass.getSimpleName()), model);
    }
    public MainViewModel getModel(String url) {
        return (MainViewModel) modelMap.get(url);
    }

    @SuppressWarnings("unchecked")
    public <T extends ModelDataClass> DataAdapter<T> getAdapter(String className) {
        return
                new DataAdapter<T>(
                        getModel(URLProvider.getURL(className)),
                        this,
                        URLProvider.getClass(className));
    }

    private Class currentListClass = Planet.class;
    public void setActiveInfoFragment(Class clazz) {
        currentListClass = clazz;
        navController.navigate(R.id.navigation_planet);
        navView.getMenu().findItem(R.id.navigation_planet).setTitle(clazz.getSimpleName()+"s");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("class", currentListClass.getSimpleName());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        setActiveInfoFragment(URLProvider.getClass(
                Objects.requireNonNull(savedInstanceState.getString("class"))));
        super.onRestoreInstanceState(savedInstanceState);
    }

    public Class getCurrentListClass() {
        return currentListClass;
    }
}
