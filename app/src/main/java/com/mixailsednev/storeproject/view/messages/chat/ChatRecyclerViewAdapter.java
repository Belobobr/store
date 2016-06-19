package com.mixailsednev.storeproject.view.messages.chat;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.firebase.FirebaseUtils;
import com.mixailsednev.storeproject.model.messages.chat.Message;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRecyclerViewAdapter extends RecyclerView.Adapter<ChatRecyclerViewAdapter.ViewHolder> {

    private List<Message> messages;

    public ChatRecyclerViewAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Message message = messages.get(position);
        holder.message = message;
        holder.messageTextView.setText(message.getContent());
        if (message.getOwner().equals(FirebaseUtils.getCurrentUserId())) {
            holder.messageContainer.setGravity(Gravity.RIGHT);
        } else {
            holder.messageContainer.setGravity(Gravity.LEFT);
        }
    }

    public void setMessages(@NonNull List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.comment_message)
        public TextView messageTextView;
        @BindView(R.id.message_container)
        public RelativeLayout messageContainer;

        public View view;
        public Message message;

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