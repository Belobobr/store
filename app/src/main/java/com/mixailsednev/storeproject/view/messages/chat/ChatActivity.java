package com.mixailsednev.storeproject.view.messages.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.chat_toolbar)
    protected Toolbar chatToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            if (getChatId() != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.chat_container, ChatFragment.newInstance(getChatId()), ChatFragment.TAG)
                        .commit();
            } else {
                throw new IllegalArgumentException("Chat ID can't be null");
            }
        }

        chatToolbar.setTitle(getString(R.string.chat));
        chatToolbar.setNavigationIcon(R.drawable.back);
        chatToolbar.setNavigationOnClickListener((view) -> {
            NavUtils.navigateUpTo(ChatActivity.this, new Intent(ChatActivity.this, MainActivity.class));
        });

    }


    @Nullable
    private String getChatId() {
        return getIntent().getExtras() == null ? null
                : getIntent().getExtras().getString(ChatFragment.ARG_CHAT_ID);
    }


}
