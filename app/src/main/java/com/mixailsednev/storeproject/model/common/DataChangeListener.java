package com.mixailsednev.storeproject.model.common;

import android.net.Uri;
import android.support.annotation.NonNull;

public abstract class DataChangeListener {

    @NonNull
    private Uri uri;

    public DataChangeListener(@NonNull Uri uri) {
        this.uri = uri;
    }

    @NonNull
    public Uri getUri() {
        return uri;
    }

    public abstract void newDataReceived();
}
