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

    public Message(@Nullable String id) {
        this.id = id;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
