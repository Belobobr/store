package com.mixailsednev.storeproject.model.messages.userChat;

import android.support.annotation.Nullable;

import com.google.firebase.database.Exclude;

public class UserChat {

    @Exclude
    @Nullable
    private String id;
    private String name;
    private String lastMessage;
    private Long lastMesageTime;

    public UserChat() {
    }

    public UserChat(@Nullable String id, String name, String lastMessage, Long lastMesageTime) {
        this.id = id;
        this.name = name;
        this.lastMessage = lastMessage;
        this.lastMesageTime = lastMesageTime;
    }

    @Nullable
    public String getId() {
        return id;
    }

    public void setId(@Nullable String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public Long getLastMesageTime() {
        return lastMesageTime;
    }

    public void setLastMesageTime(Long lastMesageTime) {
        this.lastMesageTime = lastMesageTime;
    }
}
