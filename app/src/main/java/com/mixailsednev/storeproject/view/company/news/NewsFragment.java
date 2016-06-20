package com.mixailsednev.storeproject.view.company.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;

import butterknife.ButterKnife;

public class NewsFragment extends Fragment {

    public static final String ARG_COMPANY_ID = "company_id";

    public static NewsFragment newInstance(@NonNull String companyId) {
        NewsFragment fragment = new NewsFragment();
        Bundle arguments = new Bundle();
        arguments.putString(NewsFragment.ARG_COMPANY_ID, companyId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_news, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
