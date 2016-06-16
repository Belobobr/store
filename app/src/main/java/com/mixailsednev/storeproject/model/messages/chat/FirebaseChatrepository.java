package com.mixailsednev.storeproject.model.messages.chat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mixailsednev.storeproject.model.firebase.FirebaseUtils;
import com.mixailsednev.storeproject.model.messages.userChat.UserChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirebaseChatRepository extends ChatRepository {

    public static String TAG = FirebaseChatRepository.class.getSimpleName();

    private static FirebaseChatRepository instance = null;

    public static FirebaseChatRepository getInstance(@NonNull String chatId) {
        if (instance == null || !instance.chatId.equals(chatId)) {
            instance = new FirebaseChatRepository(chatId);
        }
        return instance;
    }

    private ValueEventListener messagesEventListener;

    @NonNull
    private List<Message> messages;
    @NonNull
    private String chatId;

    public FirebaseChatRepository(@NonNull String chatId) {
        this.messages = new ArrayList<>();
        this.chatId = chatId;
    }

    //TODO если кто то наблюдает за репозитроием, то ему необходимо наблюдать за состоянием сервера
    @Override
    public void addListener(DataChangeListener dataChangeListener) {
        super.addListener(dataChangeListener);
        if (getListenersCount() == 1) {
            messagesEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final List<Message> messages = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Message message = snapshot.getValue(Message.class);
                        message.setId(snapshot.getKey());
                        messages.add(message);
                    }
                    FirebaseChatRepository.this.messages = messages;
                    notifyDataChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, databaseError.getMessage());
                }
            };

            FirebaseUtils.getChatsRef().child(chatId).child("messages").addValueEventListener(messagesEventListener);
        }

    }

    @Override
    public void removeListener(DataChangeListener dataDataChangeListener) {
        super.removeListener(dataDataChangeListener);
        if (getListenersCount() <= 0) {
            FirebaseUtils.getChatsRef().child(chatId).child("messages").removeEventListener(messagesEventListener);
        }
    }

    @NonNull
    @Override
    public List<Message> getMessages() {
        return messages;
    }

    @Nullable
    @Override
    public UserChat getMessage(@NonNull String messageId) {
        return null;
    }


    public void addMessage(@NonNull String messageContent) {
        String currentUserId = FirebaseUtils.getCurrentUserId();

        if (currentUserId != null) {
            Message message = new Message();
            message.setContent(messageContent);
            message.setOwner(currentUserId);

            FirebaseUtils.getChatsRef().child(chatId).child("messages").push().setValue(message, (databaseError, databaseReference) -> {
                if (databaseError != null) {
                    Log.e(TAG, "Error add message to chat: " + databaseError.getMessage());
                }
            });

            DatabaseReference currentUserRef = FirebaseUtils.getCurrentUserRef();
            if (currentUserRef != null) {
                Map<String, Object> userChatValues = new HashMap<>();
                userChatValues.put("lastMessage", message.getContent());
                currentUserRef.child("chats").child(chatId).updateChildren(userChatValues, (databaseError, databaseReference) -> {
                    if (databaseError != null) {
                        Log.e(TAG, "Error update user chat: " + databaseError.getMessage());
                    }
                });
            }
        } else {
            Log.e(TAG, "Not authorized users can't create messages");
        }
    }
}
