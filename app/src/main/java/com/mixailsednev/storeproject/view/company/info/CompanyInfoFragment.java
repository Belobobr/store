package com.mixailsednev.storeproject.view.company.info;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyInfoFragment extends Fragment {

    public static final String ARG_COMPANY_ID = "company_id";

    @BindView(R.id.company_info)
    CompanyInfoView companyInfoView;

    public static CompanyInfoFragment newInstance(@NonNull String companyId) {
        CompanyInfoFragment fragment = new CompanyInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putString(CompanyInfoFragment.ARG_COMPANY_ID, companyId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_info, container, false);
        ButterKnife.bind(this, rootView);
        companyInfoView.setAddress("ул. Маяковоского 72-23");
        companyInfoView.setRating("Рейтинг: 4.2");
        return rootView;
    }


}
