package com.mixailsednev.storeproject.view.messages.userChat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.messages.userChat.UserChat;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class UserChatsRecyclerViewAdapter extends RecyclerView.Adapter<UserChatsRecyclerViewAdapter.ViewHolder> {

    public interface UserChatRemovedListener {
        void userChatRemoved(@NonNull UserChat userChat);
    }

    private List<UserChat> userChats;
    private UserChatsFragment.UserChatSelectedListener userChatSelectedListener;
    private UserChatRemovedListener userChatRemovedListener;

    public UserChatsRecyclerViewAdapter(List<UserChat> userChats,
                                        UserChatsFragment.UserChatSelectedListener userChatSelectedListener,
                                        UserChatRemovedListener userChatRemovedListener) {
        this.userChats = userChats;
        this.userChatSelectedListener = userChatSelectedListener;
        this.userChatRemovedListener = userChatRemovedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_user_chat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        UserChat userChat = userChats.get(position);
        holder.userChat = userChat;
        holder.chatNameTextView.setText(userChat.getName());
        holder.chatLastMessageTextView.setText(userChat.getLastMessage());
        holder.chatLastMessageTimeTextView.setText(userChat.getLastMesageTime().toString());

        holder.view.setOnClickListener((view) -> {
            userChatSelectedListener.userChatSelected(holder.userChat.getId());
        });

    }

    public void setUserChats(@NonNull List<UserChat> userChats) {
        this.userChats = userChats;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return userChats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.chat_name)
        public TextView chatNameTextView;
        @BindView(R.id.chat_last_message)
        public TextView chatLastMessageTextView;
        @BindView(R.id.chat_last_message_time)
        public TextView chatLastMessageTimeTextView;
        public View view;
        public UserChat userChat;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}