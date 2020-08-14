package com.example.testquestion.data.model.modules;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Objects;

/**
 * суперкласс для всех классов данных, получаемых с API.
 * Имеет конструктор для URL, в которой находится ссылка на обьект в API
 * и для JSON, В котором содержатся все данные
 */
public abstract class ModelDataClass implements Serializable, ModelClass {
    private String URL;
    private String created, edited;


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


    /**
     * Метод для преобразования JSON массива в соответствующий массив обьектов
     * @param array массив из строк, каждая из которых является URL
     * @param clazz Класс, на основе которого будет создаваться обьект
     * @param <T> Тип возвращаемых обьектов
     * @return Массив обьектов типа T
     */
    protected static <T extends ModelDataClass> T[] getArrayOfObjectByUrlArray(JSONArray array, Class<T> clazz)
            throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T[] objectArray = (T[]) Array.newInstance(clazz, array.length());
        for(int i = 0; i < array.length(); i++) {
            objectArray[i] = clazz.getConstructor(String.class).newInstance(array.getString(i));
        }
        return objectArray;
    }
    /**
     * Метод для преобразования JSON массива в соответствующий массив обьектов
     * @param array массив из строк, каждая из которых является JSON обьектом
     * @param clazz Класс, на основе которого будет создаваться обьект
     * @param <T> Тип возвращаемых обьектов
     * @return Массив обьектов типа T
     */
    public static <T extends ModelDataClass> T[] getArrayOfObject(JSONArray array, Class<T> clazz)
            throws JSONException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        @SuppressWarnings("unchecked")
        T[] objectArray = (T[]) Array.newInstance(clazz, array.length());
        for(int i = 0; i < array.length(); i++) {
            objectArray[i] = clazz.getConstructor(JSONObject.class).newInstance(array.getJSONObject(i));
        }
        return objectArray;
    }
    protected void sendJSONException(Exception e) {
        Log.e("JSON parse error", e.getMessage() +"\n" + Arrays.toString(e.getStackTrace()));
    }
}
