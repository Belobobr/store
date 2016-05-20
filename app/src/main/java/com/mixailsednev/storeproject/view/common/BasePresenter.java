package com.mixailsednev.storeproject.view.common;

import android.support.annotation.NonNull;
import com.mixailsednev.storeproject.model.CompositeDataChangeListener;


public abstract class BasePresenter<View> {

    private View view;
    private CompositeDataChangeListener compositeDataChangeListener;

    public BasePresenter(View view) {
        compositeDataChangeListener = new CompositeDataChangeListener();
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void subscribeToDataStore() {
        subscribeToDataStoreInternal(compositeDataChangeListener);
        compositeDataChangeListener.subscribe();
    }

    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {

    }

    public void unSubscribeFromDataStore() {
        compositeDataChangeListener.unsubscribe();
    }
}
