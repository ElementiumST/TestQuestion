package com.example.testquestion.data.provider;

import android.content.Context;
import android.util.Log;

import com.example.testquestion.BuildConfig;
import com.example.testquestion.data.model.modules.ModelDataClass;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.Objects;

public abstract class PageProvider<T extends ModelDataClass> extends BaseGetProvider<T> {

    public PageProvider(Context context, Class<T> type, Order order) {
        super(context, type, order);
    }



    @Override
    protected void handleResponse(String response) {
        try {
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("results");
            Collections.addAll(products, T.getArrayOfObject(array, genericType));
            onLoadSuccess(products);

        } catch (JSONException e) {
            if (BuildConfig.DEBUG) {
                Log.e("Create new instance error", response+"\n"+order.getUrls().get(0));
            }
            Log.e("Create new instance error", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        } catch (InstantiationException | InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
