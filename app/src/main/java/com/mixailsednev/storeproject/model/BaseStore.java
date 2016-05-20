package com.mixailsednev.storeproject.model;

import java.util.ArrayList;

public class BaseStore<Data> {

    private Data mState;
    private ArrayList<DataChangeListener<Data>> mDataChangeListeners = new ArrayList<>();

    public BaseStore(Data data) {
        this.mState = data;
    }

    public Data getState() {
        return mState;
    }

    public void setState(Data state) {
        mState = state;
        notifyDataChanged();
    }

    public DataChangeListener addListener(DataChangeListener<Data> dataChangeListener) {
        mDataChangeListeners.add(dataChangeListener);
        dataChangeListener.newDataReceived(getState());
        return dataChangeListener;
    }

    public void removeListener(DataChangeListener<Data> dataDataChangeListener) {
        mDataChangeListeners.remove(dataDataChangeListener);
    }

    public void notifyDataChanged() {
        for (DataChangeListener<Data> listener : mDataChangeListeners) {
            listener.newDataReceived(mState);
        }
    }
}