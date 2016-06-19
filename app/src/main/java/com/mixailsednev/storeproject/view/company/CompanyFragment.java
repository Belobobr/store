package com.mixailsednev.storeproject.view.company;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.company.info.CompanyInfoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyFragment extends Fragment {

    public static final String TAG = CompanyFragment.class.getSimpleName();
    public static final String ARG_COMPANY_ID = "company_id";

    public static CompanyFragment newInstance(@NonNull String companyId) {
        CompanyFragment fragment = new CompanyFragment();
        Bundle arguments = new Bundle();
        arguments.putString(CompanyFragment.ARG_COMPANY_ID, companyId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    private String companyId;

    @NonNull
    @BindView(R.id.pager)
    protected ViewPager viewPager;

    @NonNull
    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_COMPANY_ID)) {
            String companyId = getArguments().getString(ARG_COMPANY_ID);
            if (companyId != null) {
                this.companyId = companyId;
            } else {
                throw new IllegalArgumentException("Company id can't be null");
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_company, container, false);
        ButterKnife.bind(this, rootView);
        CompanyPagerAdapter companyPagerAdapter =
                new CompanyPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(companyPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        return rootView;
    }

    public class CompanyPagerAdapter extends FragmentPagerAdapter {
        public CompanyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return CompanyInfoFragment.newInstance(companyId);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.info);
                case 1:
                    return getString(R.string.services);
                case 2:
                    return getString(R.string.news);
                default:
                    return "";
            }
        }
    }
}

