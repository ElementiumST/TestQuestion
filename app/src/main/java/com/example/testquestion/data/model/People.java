package com.example.testquestion.data.model;

import org.json.JSONObject;

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
    }
}
