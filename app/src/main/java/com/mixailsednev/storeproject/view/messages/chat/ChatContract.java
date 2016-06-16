package com.mixailsednev.storeproject.view.messages.chat;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.messages.chat.Message;

import java.util.List;

public class ChatContract {

    public interface ChatView {
        void setMessages(@NonNull List<Message> messages);
    }

    public interface ActionsListener {
        void addMessage(@NonNull String messageContent);
    }
}