package com.mixailsednev.storeproject.view.company.services;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.company.services.Service;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesFragment extends Fragment implements ServicesRecyclerViewAdapter.ServiceSelectedListener {

    public static final String ARG_COMPANY_ID = "company_id";

    @BindView(R.id.services_list)
    RecyclerView recyclerView;

    ServicesRecyclerViewAdapter servicesRecyclerViewAdapter;

    public static ServicesFragment newInstance(@NonNull String companyId) {
        ServicesFragment fragment = new ServicesFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ServicesFragment.ARG_COMPANY_ID, companyId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company_services, container, false);
        ButterKnife.bind(this, rootView);

        List<Service> services = new ArrayList<>();
        services.add(new Service("afds", "Покраска волос"));
        services.add(new Service("afds", "Покраска волос"));
        services.add(new Service("afds", "Покраска волос"));
        services.add(new Service("afds", "Покраска волос"));
        services.add(new Service("afds", "Покраска волос"));
        services.add(new Service("afds", "Покраска волос"));

        servicesRecyclerViewAdapter = new ServicesRecyclerViewAdapter(new ArrayList<>(), this);
        servicesRecyclerViewAdapter.setServices(services);
        recyclerView.setAdapter(servicesRecyclerViewAdapter);
        return rootView;
    }

    @Override
    public void serviceSelected(@NonNull String serviceId) {

    }
}
