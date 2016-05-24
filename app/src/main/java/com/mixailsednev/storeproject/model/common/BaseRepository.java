package com.mixailsednev.storeproject.model.common;

import java.util.ArrayList;

public class BaseRepository {

    private ArrayList<DataChangeListener> mDataChangeListeners = new ArrayList<>();

    public DataChangeListener addListener(DataChangeListener dataChangeListener) {
        mDataChangeListeners.add(dataChangeListener);
        dataChangeListener.newDataReceived();
        return dataChangeListener;
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

}