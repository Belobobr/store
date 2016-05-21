package com.mixailsednev.storeproject.view.common;

import android.support.annotation.NonNull;
import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;


public class BasePresenter<View> {

    @NonNull
    private View view;
    private CompositeDataChangeListener compositeDataChangeListener;

    public BasePresenter(@NonNull View view) {
        compositeDataChangeListener = new CompositeDataChangeListener();
        this.view = view;
    }

    @NonNull
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
