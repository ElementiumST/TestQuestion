package com.example.testquestion.data.model;

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
}
