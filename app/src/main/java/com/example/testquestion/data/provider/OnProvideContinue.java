package com.example.testquestion.data.provider;

import java.util.List;

public interface OnProvideContinue<T> {
    void onSuccess(List<T> data);
    void onFail(String errorResponse);
}
