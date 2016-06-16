package com.mixailsednev.storeproject.model.messages.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mixailsednev.storeproject.model.common.BaseRepository;
import com.mixailsednev.storeproject.model.messages.userChat.UserChat;

import java.util.List;

public abstract class ChatRepository extends BaseRepository {

    @NonNull
    abstract public List<Message> getMessages();

    @Nullable
    abstract public UserChat getMessage(@NonNull String messageId);

    abstract public void addMessage(@NonNull Message message);
}