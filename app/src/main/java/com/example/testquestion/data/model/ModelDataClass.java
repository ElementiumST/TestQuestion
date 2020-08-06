package com.example.testquestion.data.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;

public abstract class ModelDataClass implements Serializable {
    protected String URL;
    protected String created, edited;

    public ModelDataClass(String URL) {
        this.URL = URL;
    }
    public ModelDataClass(JSONObject object) {
        try {
            URL = object.getString("url");
            created = object.getString("created");
            edited = object.getString("edited");
        } catch (JSONException e) {
            if(e.getMessage() != null)
                Log.e("JSON parse error", e.getMessage());
        }
    }

    public String getURL() {
        return URL;
    }

    public String getCreated() {
        return created;
    }

    public String getEdited() {
        return edited;
    }

    public static <T extends ModelDataClass> T[] getArrayOfObjectByUrlArray(JSONArray array, Class<T> clazz)
            throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T[] objectArray = (T[]) Array.newInstance(clazz, array.length());
        for(int i = 0; i < array.length(); i++) {
            objectArray[i] = clazz.getConstructor(String.class).newInstance(array.getJSONObject(i).toString());
        }
        return objectArray;
    }
    public static <T extends ModelDataClass> T[] getArrayOfObject(JSONArray array, Class<T> clazz)
            throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T[] objectArray = (T[]) Array.newInstance(clazz, array.length());
        for(int i = 0; i < array.length(); i++) {
            objectArray[i] = clazz.getConstructor(JSONObject.class).newInstance(array.getJSONObject(i));
        }
        return objectArray;
    }

}
