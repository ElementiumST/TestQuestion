package com.example.testquestion.data.model;

import com.example.testquestion.data.dataClasses.ArrayValue;
import com.example.testquestion.data.model.modules.ModelDataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Specie extends ModelDataClass {
    private String name, classification, designation, averageHeight, skinColor, hairColor, eyeColor,
            averageLifespan, homeWorld, language;
    People[] peoples;
    Film[] films;

    public Specie(String URL) {
        super(URL);
    }

    public Specie(JSONObject object) {
        super(object);
        try {
            name = object.getString("name");
            classification = object.getString("classification");
            designation = object.getString("designation");
            averageHeight = object.getString("average_height");
            skinColor = object.getString("skin_colors");
            hairColor = object.getString("hair_colors");
            eyeColor = object.getString("eye_colors");
            averageLifespan = object.getString("average_lifespan");
            homeWorld = object.getString("homeworld");
            language = object.getString("language");
            peoples = getArrayOfObjectByUrlArray(object.getJSONArray("people"), People.class);
            films = getArrayOfObjectByUrlArray(object.getJSONArray("films"), Film.class);
        } catch (JSONException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            sendJSONException(e);
        }
    }

    public HashMap<String, String> getOtherContent() {
        HashMap<String, String> data = new HashMap<>();
        data.put("Classification", classification);
        data.put("Designation", designation);
        data.put("Average height", averageHeight);
        data.put("Skin color", skinColor);
        data.put("Eye color", eyeColor);
        data.put("Average lifespan", averageLifespan);
        data.put("Home world", homeWorld);
        data.put("language", language);
        return data;
    }
    public String getName() {
        return name;
    }

    public String getClassification() {
        return classification;
    }

    public String getDesignation() {
        return designation;
    }

    public String getAverageHeight() {
        return averageHeight;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getAverageLifespan() {
        return averageLifespan;
    }

    public String getHomeWorld() {
        return homeWorld;
    }

    public String getLanguage() {
        return language;
    }

    public People[] getPeoples() {
        return peoples;
    }

    public Film[] getFilms() {
        return films;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends ModelDataClass> List<ArrayValue<T>> getArrays() {
        List<ArrayValue<T>> list = new ArrayList<>();
        list.add((ArrayValue<T>) new ArrayValue<>(People.class, "Peoples", peoples));
        list.add((ArrayValue<T>) new ArrayValue<>(Film.class, "Films", films));
        return list;
    }}
