package com.example.testquestion.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testquestion.BuildConfig;
import com.example.testquestion.MainActivity;
import com.example.testquestion.data.model.Film;
import com.example.testquestion.data.model.ModelDataClass;
import com.example.testquestion.data.provider.Order;
import com.example.testquestion.data.provider.PageProvider;

import java.util.List;
import java.util.Objects;

public class MainViewModel<T extends ModelDataClass> extends ViewModel {
    private MutableLiveData<List<T>> observableCollection = new MutableLiveData<>();
    private Class<T> clazz;
    private String className;
    private Context context;

    public void setClass(Class<T> clazz) {
        this.clazz = clazz;
        className = clazz.getName().replace("com.example.testquestion.data.model.", "");
    }

    public void setContext(Context context){
        this.context = context;
    }

    @SuppressWarnings("unchecked")
    public void setData(Intent intent) {
        if(clazz == null) {
            Log.e("Set model data exception", "class is not defined (use setClass() method)");
            return;
        }
        Objects.requireNonNull(intent.getExtras());
        observableCollection.setValue((List<T>)
                intent.getSerializableExtra(className));
    }
    private void insertData(List<T> data) {
        data.addAll(0, Objects.requireNonNull(observableCollection.getValue()));
        observableCollection.setValue(data);
    }

    private boolean pageProvideInProcess = false;

    public void uploadPage(Order order) {
        if(pageProvideInProcess) return;
        pageProvideInProcess = true;
        if(context == null) {
            Log.e("Request for load page", "context is not defined (use setContext() method)");
            return;
        }

        PageProvider<T> provider = new PageProvider<T>(context, clazz, order) {
            @Override
            public void onLoadSuccess(List<T> data) {
                insertData(data);
                pageProvideInProcess = false;
            }
        };
        provider.provide();
    }


    public MutableLiveData<List<T>> getObservableCollection() {
        return observableCollection;
    }
}
