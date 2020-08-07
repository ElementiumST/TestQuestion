package com.example.testquestion.data.model;

import org.json.JSONObject;

public class Planet extends ModelDataClass {
    private String name, rotationPeriod, orbitalPeriod, diameter, climate, gravity, terrain,
            surface_water, population;
    People[] peoples;
    Film[] films;

    public Planet(String URL) {
        super(URL);
    }

    public String getName() {
        return name;
    }

    public String getRotationPeriod() {
        return rotationPeriod;
    }

    public String getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public String getDiameter() {
        return diameter;
    }

    public String getClimate() {
        return climate;
    }

    public String getGravity() {
        return gravity;
    }

    public String getTerrain() {
        return terrain;
    }

    public String getSurface_water() {
        return surface_water;
    }

    public String getPopulation() {
        return population;
    }

    public People[] getPeoples() {
        return peoples;
    }

    public Film[] getFilms() {
        return films;
    }

    public Planet(JSONObject object) {
        super(object);
        try {
            name = object.getString("name");
            rotationPeriod = object.getString("rotation_period");
            orbitalPeriod = object.getString("orbital_period");
            diameter = object.getString("diameter");
            climate = object.getString("climate");
            gravity = object.getString("gravity");
            terrain = object.getString("terrain");
            surface_water = object.getString("surface_water");
            population = object.getString("population");
            peoples = getArrayOfObjectByUrlArray(object.getJSONArray("residents"), People.class);
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
        } catch (Exception e) {
            sendJSONException(e);
        }

    }

    public static String getBasePageUrl() {
        return API_URL + "planets/";
    }

}
