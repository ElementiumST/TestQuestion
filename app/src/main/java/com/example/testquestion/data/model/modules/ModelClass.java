package com.example.testquestion.data.model.modules;

import com.example.testquestion.data.dataClasses.ArrayValue;

import java.util.HashMap;
import java.util.List;

public interface ModelClass {
    String getName();
    HashMap<String, String> getOtherContent();
    <T extends ModelDataClass> List<ArrayValue<T>> getArrays();
}
