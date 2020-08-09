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
 * Работает это по следующему принципу. ViewModel запрашивает данные у провайдера, он отправляет запросы
 * и по завершению отдает данные
 * @param <T>
 */
public abstract class BaseGetProvider<T extends ModelDataClass> implements Provider<T> {
    protected OnProvideContinue<T> listener;
    protected Order order;
    protected Context context;
    protected RequestQueue requestQueue;
    protected final Class<T> genericType;

    public BaseGetProvider(Context context, Class<T> type, Order order) {
        this(context, type, order, null);
    }

    @SuppressWarnings("unchecked")
    public BaseGetProvider(Context context, Class<T> type, Order order, OnProvideContinue<T> listener) {
        this.listener = listener;
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

    protected void createRequestQueue() {
        this.requestQueue = Volley.newRequestQueue(context);
    }

    protected ArrayList<T> products = new ArrayList<>();

    void executeProvideFor(String url) {
        StringRequest request = new StringRequest(Request.Method.GET,
                url, this::handleResponse
        , error -> {
            // В логи исключение/ошибку
            if(error.getMessage() != null)
                Log.e("Provide error", Objects.requireNonNull(error.getMessage()));
            // отсутствие товара на складе тоже ответ, так что нужно ответить о том, что ответ всетаки пришел
            if(listener != null)
                listener.onFail("request error for: " + url);
        });
        // добавляем запрос в очередь
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
            listener.onFail("parse data error for: "+response);
        }
    }

    int counter = 0;
    int number;
    void checkToComplete() {
        counter++;
        if(counter < number) return;
        complete();
    }
    void complete() {
        onLoadSuccess(products);
        if(listener != null)
            listener.onSuccess(products);
    }


}
