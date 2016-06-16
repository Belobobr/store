package com.mixailsednev.storeproject.model.messages.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.database.Exclude;

public class Message {

    @Exclude
    @Nullable
    private String id;
    @NonNull
    private String content;
    @NonNull
    private String owner;

    public Message() {
    }

    public Message(@Nullable String id, @NonNull String content, @NonNull String owner) {
        this.id = id;
        this.content = content;
        this.owner = owner;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    @NonNull
    public String getContent() {
        return content;
    }

    public void setContent(@NonNull String content) {
        this.content = content;
    }

    @NonNull
    public String getOwner() {
        return owner;
    }

    public void setOwner(@NonNull String owner) {
        this.owner = owner;
    }
}
