package com.example.testquestion.data.dataClasses;

import com.example.testquestion.data.model.modules.ModelDataClass;

import java.io.Serializable;

public class DataStack<T extends ModelDataClass> implements Serializable {
    T element;
    Class<T> clazz;

    public DataStack(T element, Class<T> clazz) {
        this.element = element;
        this.clazz = clazz;
    }

    public Class<T> getClazz() {
        return clazz;
    }

    public T getElement() {
        return element;
    }
}