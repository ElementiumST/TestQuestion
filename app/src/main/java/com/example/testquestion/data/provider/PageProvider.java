package com.example.testquestion.data.provider;

import android.content.Context;
import android.util.Log;

import com.example.testquestion.data.model.ModelDataClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Objects;

public abstract class PageProvider<T extends ModelDataClass> extends BaseGetProvider<T> {
    public PageProvider(Context context, Order order) {
        super(context, order, null);
    }

    @Override
    protected void handleResponse(String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("results");
            Collections.addAll(products, T.getArrayOfObject(array, genericType));
            checkToComplete();
        } catch (JSONException e) {
            Log.e("Create new instance error", Objects.requireNonNull(e.getMessage()));
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
