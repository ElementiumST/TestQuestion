package com.example.testquestion.data.provider;

import android.util.Log;

import com.example.testquestion.data.model.ModelDataClass;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<String> orderList = new ArrayList<>();

    public Order() {

    }
    public <T extends ModelDataClass> void addItem(T item) {
        if(!item.getURL().contains("https"))
            orderList.add(item.getURL().replace("http", "https"));
        else
            orderList.add(item.getURL());
    }
    public void addItem(String url) {
        if(!url.contains("https"))
            orderList.add(url.replace("http", "https"));
        else
            orderList.add(url);
    }
    public void addPage(String url, int number) {
        if(!orderList.isEmpty())
            Log.e("Add page error", "You can add only 1 page and nothing more!");
        else
            addItem(url+"?page="+number);
    }

    List<String> getUrls() {
        return orderList;
    }
}
