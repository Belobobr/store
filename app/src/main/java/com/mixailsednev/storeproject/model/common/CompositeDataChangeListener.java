package com.mixailsednev.storeproject.model.common;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.BaseRepository.DataChangeListener;

import java.util.HashMap;
import java.util.Map;

public class CompositeDataChangeListener {

    private Map<BaseRepository, DataChangeListener> dataChangeListeners;

    public CompositeDataChangeListener() {
        dataChangeListeners = new HashMap<>();
    }

    public void addListener(@NonNull BaseRepository store, @NonNull DataChangeListener dataChangeListener) {
        dataChangeListeners.put(store, dataChangeListener);
    }

    public void subscribe() {
        for (BaseRepository store : dataChangeListeners.keySet()) {
            store.addListener(dataChangeListeners.get(store));
        }
    }

    public void unSubscribe() {
        for (BaseRepository store : dataChangeListeners.keySet()) {
            store.removeListener(dataChangeListeners.get(store));
        }
    }

}
