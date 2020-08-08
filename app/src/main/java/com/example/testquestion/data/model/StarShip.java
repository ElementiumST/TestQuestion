package com.example.testquestion.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class StarShip extends ModelDataClass {
    private String name, model, manufacturer, costInCredits, length, maxAtmopheringSpeed, crew,
        passengers, cargoCapacity, consumables, hyperDriveRating, MGLT, starShipClass;
    People[] pilots;
    Film[] films;

    public StarShip(String URL) {
        super(URL);
    }

    public StarShip(JSONObject object) {
        super(object);
        try {
            name = object.getString("name");
            model = object.getString("model");
            manufacturer = object.getString("manufacturer");
            costInCredits = object.getString("cost_in_credits");
            length = object.getString("length");
            maxAtmopheringSpeed = object.getString("max_atmosphering_speed");
            crew = object.getString("crew");
            passengers = object.getString("passengers");
            cargoCapacity = object.getString("cargo_capacity");
            consumables = object.getString("consumables");
            hyperDriveRating = object.getString("hyperdrive_rating");
            MGLT = object.getString("MGLT");
            starShipClass = object.getString("starship_class");
            pilots = getArrayOfObjectByUrlArray(object.getJSONArray("pilots"), People.class);
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }
    }

}
