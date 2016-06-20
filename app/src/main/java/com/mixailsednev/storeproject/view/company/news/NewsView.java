package com.mixailsednev.storeproject.view.company.news;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsView extends FrameLayout {

    @BindView(R.id.news_author_image)
    protected ImageView authorImage;

    @BindView(R.id.news_author_name)
    protected TextView authorName;

    @BindView(R.id.news_date)
    protected TextView date;

    @BindView(R.id.news_message)
    protected TextView message;

    @BindView(R.id.like_news)
    protected ImageButton like;

    @BindView(R.id.view_news_comments)
    protected ImageButton newsComments;

    public NewsView(Context context) {
        super(context);
        init();
    }

    public NewsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public NewsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_news, this, true);
        ButterKnife.bind(this, view);
    }
}
