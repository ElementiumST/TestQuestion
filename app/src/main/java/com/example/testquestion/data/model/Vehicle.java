package com.example.testquestion.data.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class Vehicle extends ModelDataClass{
    private String name, model, manufacturer, costInCredits, length, maxAtmospheringSpeed, crew,
            passengers, cargoCapacity, consumables, vehicleClass;
    People[] peoples;
    Film[] films;

    public Vehicle(String URL) {
        super(URL);
    }

    public Vehicle(JSONObject object) {
        super(object);
        try {
            name = object.getString("name");
            model = object.getString("model");
            manufacturer = object.getString("manufacturer");
            costInCredits = object.getString("cost_in_credits");
            length = object.getString("length");
            maxAtmospheringSpeed = object.getString("max_atmosphering_speed");
            crew = object.getString("crew");
            passengers = object.getString("passengers");
            cargoCapacity = object.getString("cargo_capacity");
            consumables = object.getString("consumables");
            vehicleClass = object.getString("vehicle_class");
            peoples = getArrayOfObjectByUrlArray(object.getJSONArray("pilots"), People.class);
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }

    }

    public static String getBasePageUrl() {
        return API_URL + "vehicles/";
    }

}
