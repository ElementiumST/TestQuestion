package com.example.testquestion.data.provider;

import java.util.List;

public interface Provider<T> {
    void provide();
    void onLoadSuccess(List<T> data);
}
