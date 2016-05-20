package com.mixailsednev.storeproject.model;

public interface DataChangeListener<Data> {
    void newDataReceived(Data data);
}
