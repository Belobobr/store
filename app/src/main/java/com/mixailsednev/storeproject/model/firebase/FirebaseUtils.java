package com.mixailsednev.storeproject.model.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {

    public static DatabaseReference getBaseRef() {
        return FirebaseDatabase.getInstance().getReference();
    }

    @Nullable
    public static String getCurrentUserId() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            return user.getUid();
        }
        return null;
    }

    @Nullable
    public static DatabaseReference getCurrentUserRef() {
        String uid = getCurrentUserId();
        if (uid != null) {
            return getBaseRef().child("users").child(getCurrentUserId());
        }
        return null;
    }


    public static DatabaseReference getProductsRef() {
        return getBaseRef().child("products");
    }

    public static String getProductsPath() {
        return "products/";
    }


    public static DatabaseReference getChatsRef() {
        return getBaseRef().child("chats");
    }

    public static DatabaseReference getChatRef(@NonNull String chatId) {
        return getChatsRef().child(chatId);
    }

}

