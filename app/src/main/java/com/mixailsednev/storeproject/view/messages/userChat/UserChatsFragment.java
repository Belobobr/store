package com.mixailsednev.storeproject.view.messages.userChat;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.Injection;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.messages.userChat.UserChat;
import com.mixailsednev.storeproject.view.common.BaseFragment;
import com.mixailsednev.storeproject.view.messages.userChat.UserChatsContract.UserChatsView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserChatsFragment extends BaseFragment<UserChatsPresenter> implements UserChatsView,
        UserChatsRecyclerViewAdapter.UserChatRemovedListener {

    public interface UserChatSelectedListener {
        void userChatSelected(@NonNull String userChat);
    }

    public static UserChatsFragment newInstance() {
        return new UserChatsFragment();
    }

    @BindView(R.id.product_list)
    protected RecyclerView recyclerView;
    @BindView(R.id.progress)
    protected View progressLayout;

    private UserChatsRecyclerViewAdapter userChatsRecyclerViewAdapter;
    private UserChatSelectedListener userChatSelectedListener;

    @Override
    public UserChatsPresenter createPresenter() {
        return new UserChatsPresenter(this, Injection.provideUserChatsRepository());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);
        ButterKnife.bind(this, rootView);

        userChatsRecyclerViewAdapter = new UserChatsRecyclerViewAdapter(new ArrayList<>(), userChatSelectedListener, this);
        recyclerView.setAdapter(userChatsRecyclerViewAdapter);
        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserChatSelectedListener) {
            userChatSelectedListener = (UserChatSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement UserChatSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        userChatSelectedListener = null;
    }

    @Override
    public void setUserChats(@NonNull List<UserChat> userChats) {
        userChatsRecyclerViewAdapter.setUserChats(userChats);
    }

    @Override
    public void onNewViewStateInstance() {
    }

    @Override
    public void userChatRemoved(@NonNull UserChat userChat) {
        getPresenter().removeUserChat(userChat);
    }
}
