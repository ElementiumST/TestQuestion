package com.example.testquestion.data.provider;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.testquestion.data.model.modules.ModelDataClass;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Ну решил сделать удобную систему для подгрузки данных с API.
 * Не знаю на сколько это удобно, но обычные запросы по 100 штук втыкать не самое приятное занятие.
 * Провайдер - обьект класса BaseGetProvider или его наследника
 * Поставка - ответ API на все запросы, отправленные провайдером
 * В конструктор передаются все нужные данные
 * provide() запускает отпревку запроса
 * onLoadSuccess(List<T> data) вызывается по окончанию поставки
 */
public abstract class BaseGetProvider<T extends ModelDataClass> implements Provider<T> {
    protected Order order;
    protected Context context;
    private RequestQueue requestQueue;
    protected final Class<T> genericType;

    protected BaseGetProvider(Context context, Class<T> type, Order order) {
        this.order = order;
        this.context = context;
        createRequestQueue();
        this.genericType = type;
    }


    public void provide() {
        number = order.getUrls().size();
        for (String item:
                order.getUrls()) {
            executeProvideFor(item);
        }
    }

    private void createRequestQueue() {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    ArrayList<T> products = new ArrayList<>();

    private void executeProvideFor(String url) {
        StringRequest request = new StringRequest(Request.Method.GET,
                url, this::handleResponse
        , error -> {
            checkToComplete();
            if(error.getMessage() != null)
                Log.e("Provide error", Objects.requireNonNull(error.getMessage()));
        });
        requestQueue.add(request);
    }
    protected void handleResponse(String response){
        try {
            T instance = genericType.getConstructor(JSONObject.class).newInstance(new JSONObject(response));
            //Добавляем товар в нашу поставку
            products.add(instance);
            //проверка на завершенность сбора товара для поставки
            checkToComplete();
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
                InvocationTargetException | ClassCastException | JSONException e) {
            Log.e("Create new instance error", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }
    }

    // подсчет полученных ответов, что бы завершение поставки было по завершению всех запросов
    private int counter = 0;
    private int number;
    private void checkToComplete() {
        counter++;
        if(counter < number) return;
        onLoadSuccess(products);
    }


}
