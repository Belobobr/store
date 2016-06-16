package com.mixailsednev.storeproject;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.messages.chat.ChatRepository;
import com.mixailsednev.storeproject.model.messages.chat.FirebaseChatRepository;
import com.mixailsednev.storeproject.model.messages.userChat.FirebaseUserChatsRepository;
import com.mixailsednev.storeproject.model.messages.userChat.UserChatsRepository;
import com.mixailsednev.storeproject.model.product.FirebaseProductsRepository;
import com.mixailsednev.storeproject.model.product.ProductsRepository;

public class Injection {

    public static ProductsRepository provideProductRepository() {
        return FirebaseProductsRepository.getInstance();
    }

    public static UserChatsRepository provideUserChatsRepository() {
        return FirebaseUserChatsRepository.getInstance();
    }

    public static ChatRepository provideChatRepository(@NonNull String chatId) {
        return FirebaseChatRepository.getInstance(chatId);
    }
}
