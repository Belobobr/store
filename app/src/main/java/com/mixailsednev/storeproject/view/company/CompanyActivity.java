package com.mixailsednev.storeproject.view.company;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.mixailsednev.storeproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyActivity extends AppCompatActivity {
    @BindView(R.id.company_toolbar)
    protected Toolbar companyToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            if (getCompanyId() != null) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.company_container, CompanyFragment.newInstance(getCompanyId()), CompanyFragment.TAG)
                        .commit();
            } else {
                throw new IllegalArgumentException("Company ID can't be null");
            }
        }

        companyToolbar.setTitle(getString(R.string.company));
        companyToolbar.setNavigationIcon(R.drawable.back);
        companyToolbar.setNavigationOnClickListener((view) -> finish());

    }


    @Nullable
    private String getCompanyId() {
        return getIntent().getExtras() == null ? null
                : getIntent().getExtras().getString(CompanyFragment.ARG_COMPANY_ID);
    }

}
