package com.mixailsednev.storeproject.view.common;

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

public class CommentView extends FrameLayout {

    @BindView(R.id.comment_author_image)
    protected ImageView authorImage;

    @BindView(R.id.comment_author_name)
    protected TextView authorName;

    @BindView(R.id.comment_date)
    protected TextView date;

    @BindView(R.id.comment_message)
    protected TextView message;

    @BindView(R.id.like_comment)
    protected ImageButton like;

    public CommentView(Context context) {
        super(context);
        init();
    }

    public CommentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CommentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_comment, this, true);
        ButterKnife.bind(this, view);
    }
}
