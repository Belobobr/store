package com.mixailsednev.storeproject.model.common;

import java.util.ArrayList;

public class BaseRepository {

    private ArrayList<DataChangeListener> mDataChangeListeners = new ArrayList<>();

    public void addListener(DataChangeListener dataChangeListener) {
        mDataChangeListeners.add(dataChangeListener);
        dataChangeListener.newDataReceived();
    }

    public void removeListener(DataChangeListener dataDataChangeListener) {
        mDataChangeListeners.remove(dataDataChangeListener);
    }

    public void notifyDataChanged() {
        for (DataChangeListener listener : mDataChangeListeners) {
            listener.newDataReceived();
        }
    }

    public interface DataChangeListener {
        void newDataReceived();
    }

    public int getListenersCount() {
        return mDataChangeListeners.size();
    }

}