package com.mixailsednev.storeproject.view.messages.chat;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mixailsednev.storeproject.Injection;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.messages.chat.Message;
import com.mixailsednev.storeproject.view.common.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatFragment extends BaseFragment<ChatPresenter> implements ChatContract.ChatView {

    public static final String TAG = ChatFragment.class.getSimpleName();
    public static final String ARG_CHAT_ID = "chat_id";

    public static ChatFragment newInstance(@NonNull String chat_id) {
        ChatFragment fragment = new ChatFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ChatFragment.ARG_CHAT_ID, chat_id);
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    private String chatId;

    @BindView(R.id.messages_list)
    protected RecyclerView recyclerView;
    @BindView(R.id.progress)
    protected View progressLayout;
    @BindView(R.id.comment_message)
    protected EditText messageEditText;

    private ChatRecyclerViewAdapter chatRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;

    @Override
    public ChatPresenter createPresenter() {
        return new ChatPresenter(this, Injection.provideChatRepository(chatId));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_CHAT_ID)) {
            String chatId = getArguments().getString(ARG_CHAT_ID);
            if (chatId != null) {
                this.chatId = chatId;
            } else {
                throw new IllegalArgumentException("Chat ID can't be null");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, rootView);

        chatRecyclerViewAdapter = new ChatRecyclerViewAdapter(new ArrayList<>());
        linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatRecyclerViewAdapter);
        return rootView;
    }

    @Override
    public void onNewViewStateInstance() {
    }

    @Override
    public void setMessages(@NonNull List<Message> messages) {
        int messageCount = chatRecyclerViewAdapter.getItemCount();
        int lastVisiblePosition =
                linearLayoutManager.findLastCompletelyVisibleItemPosition();
        boolean scrollToLastMessage = lastVisiblePosition == messageCount - 1;

        chatRecyclerViewAdapter.setMessages(messages);

        if (scrollToLastMessage) {
            recyclerView.scrollToPosition(chatRecyclerViewAdapter.getItemCount() - 1);
        }
    }

    @OnClick(R.id.send)
    protected void onSendButtonClick() {
        getPresenter().addMessage(messageEditText.getText().toString());
        messageEditText.setText("");
    }
}
