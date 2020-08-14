package com.example.testquestion.data.provider;

import java.util.List;
// С одной стороны можно было просто использовать абстрактные методы в абстрактном классе,
// но это менее наглядно, поэтому я вынес нужные мне методы в отдельный интерфейс
public interface Provider<T> {
    void provide();
    void onLoadSuccess(List<T> data);
}
