package com.example.testquestion.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

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
        try {
            title = object.getString("title");
            episodeId = object.getInt("episode_id");
            openingCrawl = object.getString("opening_crawl");
            director = object.getString("director");
            producer = object.getString("producer");
            releaseDate = object.getString("release_date");
            peoples = getArrayOfObjectByUrlArray(object.getJSONArray("characters"), People.class);
            planets = getArrayOfObjectByUrlArray(object.getJSONArray("planets"), Planet.class);
            starShips = getArrayOfObjectByUrlArray(object.getJSONArray("starships"), StarShip.class);
            vehicles = getArrayOfObjectByUrlArray(object.getJSONArray("vehicles"), Vehicle.class);
            species = getArrayOfObjectByUrlArray(object.getJSONArray("species"), Specie.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }
    }

    @Override
    public HashMap<String, String> getOtherContent() {
        HashMap<String, String> data = new HashMap<>();
        data.put("Title", title);
        data.put("Episode number", String.valueOf(episodeId));
        data.put("Opening crawl", openingCrawl);
        data.put("Director", director);
        data.put("Release date", releaseDate);
        return data;
    }
    @Override
    public String getName() {
        return title;
    }

    public String getOpeningCrawl() {
        return openingCrawl;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getEpisodeId() {
        return episodeId;
    }

    public People[] getPeoples() {
        return peoples;
    }

    public Planet[] getPlanets() {
        return planets;
    }

    public StarShip[] getStarShips() {
        return starShips;
    }

    public Vehicle[] getVehicles() {
        return vehicles;
    }

    public Specie[] getSpecies() {
        return species;
    }
}
