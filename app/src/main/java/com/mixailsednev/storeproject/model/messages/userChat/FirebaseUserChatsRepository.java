package com.mixailsednev.storeproject.model.messages.userChat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.mixailsednev.storeproject.model.firebase.FirebaseUtils;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUserChatsRepository extends UserChatsRepository {

    public static String TAG = FirebaseUserChatsRepository.class.getSimpleName();

    private static FirebaseUserChatsRepository instance = new FirebaseUserChatsRepository();

    public static FirebaseUserChatsRepository getInstance() {
        return instance;
    }
    private ValueEventListener userChatsEventListener;

    @NonNull
    private List<UserChat> userChats;

    public FirebaseUserChatsRepository() {
        this.userChats = new ArrayList<>();
    }

    //TODO если кто то наблюдает за репозитроием, то ему необходимо наблюдать за состоянием сервера
    @Override
    public void addListener(DataChangeListener dataChangeListener) {
        super.addListener(dataChangeListener);
        if (getListenersCount() == 1) {
            userChatsEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    final List<UserChat> userChats = new ArrayList<>();
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        UserChat userChat = snapshot.getValue(UserChat.class);
                        userChat.setId(snapshot.getKey());
                        userChats.add(userChat);
                    }
                    FirebaseUserChatsRepository.this.userChats = userChats;
                    notifyDataChanged();
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.e(TAG, databaseError.getMessage());
                }
            };

            DatabaseReference currentUserRef = FirebaseUtils.getCurrentUserRef();
            if (currentUserRef != null) {
                currentUserRef.child("chats").addValueEventListener(userChatsEventListener);
            }
        }

    }

    @Override
    public void removeListener(DataChangeListener dataDataChangeListener) {
        super.removeListener(dataDataChangeListener);
        if (getListenersCount() <= 0) {
            DatabaseReference currentUserRef = FirebaseUtils.getCurrentUserRef();
            if (currentUserRef != null) {
                currentUserRef.child("chats").removeEventListener(userChatsEventListener);
            }
        }
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
