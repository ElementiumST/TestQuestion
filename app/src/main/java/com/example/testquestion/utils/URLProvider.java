package com.example.testquestion.utils;

import com.example.testquestion.R;
import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.data.model.People;
import com.example.testquestion.data.model.Planet;
import com.example.testquestion.data.model.Specie;
import com.example.testquestion.data.model.StarShip;
import com.example.testquestion.data.model.Vehicle;

public class URLProvider {
    public static final String API_URL = "https://swapi.dev/api/";
    public static final String PEOPLE = API_URL+"people/";
    public static final String FILM = API_URL+"films/";
    public static final String PLANET = API_URL+"planets/";
    public static final String SPECIE = API_URL+"species/";
    public static final String STAR_SHIP = API_URL+"starships/";
    public static final String VEHICLE = API_URL+"vehicles/";

    public static String getURL(String className) {
        switch (className) {
            case "Vehicle":
                return VEHICLE;
            case "People":
                return PEOPLE;
            case "Film":
                return FILM;
            case "Planet":
                return PLANET;
            case "StarShip":
                return STAR_SHIP;
            case "Specie":
                return SPECIE;
            default:
                return API_URL;
        }
    }
    public static Class getClass(String className) {
        switch (className) {
            case "Vehicle":
                return Vehicle.class;
            case "People":
                return People.class;
            case "Film":
                return Film.class;
            case "Planet":
                return Planet.class;
            case "StarShip":
                return StarShip.class;
            case "Specie":
                return Specie.class;
            default:
                return ModelDataClass.class;
        }
    }

    public static int getImageResource(String className) {
        switch (className) {
            case "Vehicle":
                return R.drawable.background_vehicle;
            case "People":
                return R.drawable.background_peoples;
            case "Film":
                return R.drawable.background_film;
            case "Planet":
                return R.drawable.background_planet;
            case "StarShip":
                return R.drawable.background_space_ships;
            case "Specie":
                return R.drawable.background_specie;
            default:
                return -1;
        }
    }
}
