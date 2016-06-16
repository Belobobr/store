package com.mixailsednev.storeproject.view.messages.userChat;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.messages.userChat.UserChat;

import java.util.List;

public class UserChatsContract {

    public interface UserChatsView {
        void setUserChats(@NonNull List<UserChat> userChats);
    }

    public interface ActionsListener {
        void removeUserChat(@NonNull UserChat userChat);
    }
}
