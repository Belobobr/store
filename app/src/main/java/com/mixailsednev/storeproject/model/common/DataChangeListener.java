package com.mixailsednev.storeproject.model.common;

public interface DataChangeListener<Data> {
    void newDataReceived(Data data);
}
