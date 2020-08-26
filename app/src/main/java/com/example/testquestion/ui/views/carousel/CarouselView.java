package com.example.testquestion.ui.views.carousel;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.example.testquestion.R;
import com.example.testquestion.data.dataClasses.ArrayValue;
import com.example.testquestion.data.model.modules.ModelDataClass;
import com.example.testquestion.data.provider.BaseGetProvider;
import com.example.testquestion.data.provider.Order;
import com.example.testquestion.ui.adapters.CategoryAdapter;
import com.example.testquestion.ui.adapters.DataAdapter;
import com.example.testquestion.ui.adapters.OnItemClickListener;
import com.example.testquestion.ui.views.LoadAnimatedView;

import java.util.List;

public class CarouselView extends FrameLayout {
    public CarouselView(@NonNull Context context) {
        super(context);
        initView();
    }

    AttributeSet attributeSet;
    public CarouselView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        attributeSet = attrs;
        initView();
    }
    View root;
    TextView titleView;
    FrameLayout listViewSocket;
    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        root = inflater.inflate(R.layout.nested_list_view, this, false);
        titleView = root.findViewById(R.id.title);
        listViewSocket = root.findViewById(R.id.listSocket);
        if(attributeSet != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(
                    attributeSet,
                    R.styleable.carousel,
                    0, 0);
            try {
                titleView.setText(a.getString(R.styleable.carousel_text));
            } finally {
                a.recycle();
            }
        }
        this.addView(root);
    }
    public void setContent(List<Class> classes, OnItemClickListener listener) {
        CategoryAdapter adapter = new CategoryAdapter(classes);
        adapter.setOnItemClickListener(listener);
        RecyclerView view = createRecyclerView();
        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) listViewSocket.getLayoutParams();
        params.height = 0;
        listViewSocket.setLayoutParams(params);
        CardView root = this.findViewById(R.id.root);
        root.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view.setAdapter(adapter);
    }

    OnItemClickListener listener;

    @SuppressWarnings("unchecked")
    public  <T extends ModelDataClass> void setContent(ArrayValue value, OnItemClickListener listener) {

        titleView.setText(value.getViewName());
        LoadAnimatedView loadView = new LoadAnimatedView(getContext());
        loadView.setSize(ViewGroup.LayoutParams.MATCH_PARENT, 96);
        listViewSocket.addView(loadView);
        this.listener = listener;
        Order order = new Order();
        for (T element :
                (T[]) value.getArray()) {
            order.addItem(element);
        }
        DataProvider<T> provider = new DataProvider<T>(getContext(), value.getArrayClass(), order);
        provider.provide();

    }


    RecyclerView createRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(getContext());
        CarouselManager manager = new CarouselManager(getContext(), RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        SnapHelper snapHelper = new LinearSnapHelper();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            //После того, как пользователь заканчивает прокрутку и она останавливается по инерции,
            //наш recycler view автоматичиски скроллит к ближайшему эллементу
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == 0) {
                    View view =  snapHelper.findSnapView(manager);
                    assert view != null;
                    manager.smoothScrollToPosition(recyclerView, null,
                            recyclerView.getChildAdapterPosition(view));
                }
            }
        });
        listViewSocket.removeAllViews();
        listViewSocket.addView(recyclerView);
        return recyclerView;
    }
    class DataProvider<T extends ModelDataClass> extends BaseGetProvider<T> {
        DataProvider(Context context, Class<T> type, Order order) {
            super(context, type, order);
        }

        @Override
        public void onLoadSuccess(List<T> data) {
            DataAdapter<T> adapter = new DataAdapter<>(data, genericType);
            adapter.setOnItemClickListener(listener);
            RecyclerView recyclerView = createRecyclerView();
            recyclerView.setAdapter( adapter);
        }
    }

}
