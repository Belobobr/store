package com.mixailsednev.storeproject.model.messages.userChat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mixailsednev.storeproject.model.common.BaseRepository;

import java.util.List;

public abstract class UserChatsRepository extends BaseRepository{

    abstract public void removeUserChat(@NonNull UserChat userChat);

    abstract public void addUserChat(@NonNull UserChat userChat);

    abstract public void updateUserChat(@NonNull UserChat userChat);

    @NonNull
    abstract public List<UserChat> getUserChats();

    @Nullable
    abstract public UserChat getUserChat(@NonNull String userChatId);
}
