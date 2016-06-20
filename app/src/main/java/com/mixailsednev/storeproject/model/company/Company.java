package com.mixailsednev.storeproject.model.company;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Company {

    @Nullable
    String id;
    @NonNull
    String name;

    public Company(@Nullable String id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }
}
