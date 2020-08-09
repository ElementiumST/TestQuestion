package com.example.testquestion.data.model;

import com.example.testquestion.data.dataClasses.ArrayValue;
import com.example.testquestion.data.model.modules.ModelDataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StarShip extends ModelDataClass {
    private String name, model, manufacturer, costInCredits, length, maxAtmospheringSpeed, crew,
        passengers, cargoCapacity, consumables, hyperDriveRating, MGLT, starShipClass;
    People[] peoples;
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
            maxAtmospheringSpeed = object.getString("max_atmosphering_speed");
            crew = object.getString("crew");
            passengers = object.getString("passengers");
            cargoCapacity = object.getString("cargo_capacity");
            consumables = object.getString("consumables");
            hyperDriveRating = object.getString("hyperdrive_rating");
            MGLT = object.getString("MGLT");
            starShipClass = object.getString("starship_class");
            peoples = getArrayOfObjectByUrlArray(object.getJSONArray("pilots"), People.class);
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public HashMap<String, String> getOtherContent() {
        HashMap<String, String> data = new HashMap<>();
        data.put("Model", model);
        data.put("Manufacturer", manufacturer);
        data.put("Cost in credits", costInCredits);
        data.put("Length", length);
        data.put("Max atmophering speed", maxAtmospheringSpeed);
        data.put("Crew", crew);
        data.put("Passengers", passengers);
        data.put("Cargo capacity", cargoCapacity);
        data.put("Consumables", consumables);
        data.put("Hyper drive rating", hyperDriveRating);
        data.put("MGLT", MGLT);
        data.put("Star ship class", starShipClass);
        return data;
    }
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ModelDataClass> List<ArrayValue<T>> getArrays() {
        List<ArrayValue<T>> list = new ArrayList<>();
        list.add((ArrayValue<T>) new ArrayValue<>(People.class, "Pilots", peoples));
        list.add((ArrayValue<T>) new ArrayValue<>(Film.class, "Films", films));
        return list;
    }
}
