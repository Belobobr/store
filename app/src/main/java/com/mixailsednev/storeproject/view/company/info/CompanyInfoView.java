package com.mixailsednev.storeproject.view.company.info;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyInfoView extends FrameLayout {

    @BindView(R.id.company_address)
    TextView addressTextView;

    @BindView(R.id.company_raiting)
    TextView ratingTextView;

    public CompanyInfoView(Context context) {
        super(context);
        init();
    }

    public CompanyInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CompanyInfoView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.view_company_info, this, true);
        ButterKnife.bind(this, view);
    }

    public void setAddress(@NonNull String address) {
        addressTextView.setText(address);
    }

    public void setRating(@NonNull String rating) {
        ratingTextView.setText(rating);
    }
}

