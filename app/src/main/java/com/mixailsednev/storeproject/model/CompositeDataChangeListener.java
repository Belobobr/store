package com.mixailsednev.storeproject.model;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class CompositeDataChangeListener {

    private Map<BaseStore, DataChangeListener> dataChangeListeners;

    public CompositeDataChangeListener() {
        dataChangeListeners = new HashMap<>();
    }

    public void addListener(@NonNull BaseStore store, @NonNull DataChangeListener dataChangeListener) {
        dataChangeListeners.put(store, dataChangeListener);
    }

    public void subscribe() {
        for (BaseStore store : dataChangeListeners.keySet()) {
            store.addListener(dataChangeListeners.get(store));
        }
    }

    public void unsubscribe() {
        for (BaseStore store : dataChangeListeners.keySet()) {
            store.removeListener(dataChangeListeners.get(store));
        }
    }

}
