package com.mixailsednev.storeproject.view.company.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.company.Company;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompaniesFragment extends Fragment {

    @BindView(R.id.services_list)
    RecyclerView recyclerView;

    CompaniesRecyclerViewAdapter companiesRecyclerViewAdapter;
    CompaniesRecyclerViewAdapter.CompanySelectedListener companySelectedListener;

    public static CompaniesFragment newInstance() {
        CompaniesFragment fragment = new CompaniesFragment();
        Bundle arguments = new Bundle();
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CompaniesRecyclerViewAdapter.CompanySelectedListener) {
            companySelectedListener = (CompaniesRecyclerViewAdapter.CompanySelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement UserChatSelectedListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        companySelectedListener = null;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_services, container, false);
        ButterKnife.bind(this, rootView);

        List<Company> companies = new ArrayList<>();
        companies.add(new Company("afds", "Шарм"));
        companies.add(new Company("afds", "Шарм"));
        companies.add(new Company("afds", "Шарм"));
        companies.add(new Company("afds", "Шарм"));
        companies.add(new Company("afds", "Шарм"));
        companies.add(new Company("afds", "Шарм"));

        companiesRecyclerViewAdapter = new CompaniesRecyclerViewAdapter(new ArrayList<>(), companySelectedListener);
        companiesRecyclerViewAdapter.setCompanies(companies);
        recyclerView.setAdapter(companiesRecyclerViewAdapter);
        return rootView;
    }
}
