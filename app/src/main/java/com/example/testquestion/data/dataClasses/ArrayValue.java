package com.example.testquestion.data.dataClasses;

import com.example.testquestion.data.model.modules.ModelDataClass;

public class ArrayValue<T extends ModelDataClass> {
    private String viewName;
    private T[] array;
    private Class<T> clazz;

    public ArrayValue(Class<T> clazz, String viewName, T[] array) {
        this.viewName = viewName;
        this.array = array;
        this.clazz = clazz;
    }

    public String getViewName() {
        return viewName;
    }

    public T[] getArray() {
        return array;
    }

    public Class<T> getArrayClass() {
        return clazz;
    }
}