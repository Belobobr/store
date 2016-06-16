package com.mixailsednev.storeproject.view.messages.chat;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.messages.chat.ChatRepository;
import com.mixailsednev.storeproject.model.messages.chat.Message;
import com.mixailsednev.storeproject.view.common.BasePresenter;

public class ChatPresenter extends BasePresenter<ChatContract.ChatView>
        implements ChatContract.ActionsListener
{

    @NonNull
    ChatRepository chatRepository;

    public ChatPresenter(@NonNull ChatContract.ChatView chatView,
                              @NonNull ChatRepository chatRepository) {
        super(chatView);
        this.chatRepository = chatRepository;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(
                chatRepository,
                () -> getView().setMessages(chatRepository.getMessages())
        );
    }

    @Override
    public void addMessage(@NonNull String messageContent) {
        chatRepository.addMessage(messageContent);
    }
}

