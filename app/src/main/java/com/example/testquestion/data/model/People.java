package com.example.testquestion.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class People extends ModelDataClass{
    private String birthYear, eyeColor, name, height, mass, hairColor, skinColor, gender, homeWorld;
    private Film[] films;
    private Specie[] species;
    private Vehicle[] vehicles;
    private StarShip[] starShips;


    public People(String URL) {
        super(URL);
    }

    public People(JSONObject object) {
        super(object);
        try {
            name = object.getString("name");
            height = object.getString("height");
            mass = object.getString("mass");
            hairColor = object.getString("hair_color");
            skinColor = object.getString("skin_color");
            eyeColor = object.getString("eye_color");
            birthYear = object.getString("birth_year");
            gender = object.getString("gender");
            homeWorld = object.getString("homeworld");
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
            species = getArrayOfObjectByUrlArray(object.getJSONArray("species"), Specie.class);
            vehicles = getArrayOfObjectByUrlArray(object.getJSONArray("vehicles"), Vehicle.class);
            starShips = getArrayOfObjectByUrlArray(object.getJSONArray("starships"), StarShip.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getName() {
        return name;
    }

    public String getHeight() {
        return height;
    }

    public String getMass() {
        return mass;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getGender() {
        return gender;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public Film[] getFilms() {
        return films;
    }

    public Specie[] getSpecies() {
        return species;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public StarShip[] getStarShips() {
        return starShips;
    }

    public static String getBasePageUrl() {
        return API_URL + "people/";
    }
}
