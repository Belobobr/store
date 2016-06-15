package com.mixailsednev.storeproject.view.messages.userChat;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.messages.userChat.UserChat;
import com.mixailsednev.storeproject.model.messages.userChat.UserChatsRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;

public class UserChatsPresenter extends BasePresenter<UserChatsContract.UserChatsView>
        implements UserChatsContract.ActionsListener
{

    @NonNull
    UserChatsRepository userChatsRepository;

    public UserChatsPresenter(@NonNull UserChatsContract.UserChatsView userChatsView,
                              @NonNull UserChatsRepository userChatsRepository) {
        super(userChatsView);
        this.userChatsRepository = userChatsRepository;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(
                userChatsRepository,
                () -> getView().setUserChats(userChatsRepository.getUserChats())
        );
    }

    @Override
    public void removeUserChat(@NonNull UserChat userChat) {
        userChatsRepository.removeUserChat(userChat);
    }
}
