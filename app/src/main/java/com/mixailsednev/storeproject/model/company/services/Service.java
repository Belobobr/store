package com.mixailsednev.storeproject.model.company.services;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Service {

    @Nullable
    String id;
    @NonNull
    String name;

    public Service(@Nullable String id, @NonNull String name) {
        this.name = name;
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }
}
