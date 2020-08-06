package com.example.testquestion.data.model;

import org.json.JSONObject;

public class Film extends ModelDataClass {
    private String title, openingCrawl, director, producer, releaseDate;
    private int episodeId;
    People[] peoples;
    Planet[] planets;
    StarShip[] starShips;
    Vehicle[] vehicles;
    Specie[] species;

    public Film(String URL) {
        super(URL);
    }

    public Film(JSONObject object) {
        super(object);
    }
}
