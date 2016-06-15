package com.mixailsednev.storeproject.model.messages.userChat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUserChatsRepository extends UserChatsRepository {

    public static String TAG = FirebaseUserChatsRepository.class.getSimpleName();

    private static FirebaseUserChatsRepository instance = new FirebaseUserChatsRepository();

    public static FirebaseUserChatsRepository getInstance() {
        return instance;
    }

    @NonNull
    private List<UserChat> userChats;

    public FirebaseUserChatsRepository() {
        this.userChats = new ArrayList<>();
        userChats.add(new UserChat(null, "Виктория Сафиулина", "Я ее люблю", 130L));
        userChats.add(new UserChat(null, "Виктория Сафиулина", "Я ее люблю", 130L));
        userChats.add(new UserChat(null, "Виктория Сафиулина", "Я ее люблю", 130L));
        userChats.add(new UserChat(null, "Виктория Сафиулина", "Я ее люблю", 130L));
    }

    @Override
    public void removeUserChat(@NonNull UserChat userChat) {

    }

    @Override
    public void addUserChat(@NonNull UserChat userChat) {

    }

    @Override
    public void updateUserChat(@NonNull UserChat userChat) {

    }

    @NonNull
    @Override
    public List<UserChat> getUserChats() {
        return userChats;
    }

    @Nullable
    @Override
    public UserChat getUserChat(@NonNull String userChatId) {
        return null;
    }
}
