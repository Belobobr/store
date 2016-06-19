package com.mixailsednev.storeproject.view.company.info;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.view.common.BasePresenter;

public class CompanyInfoPresenter extends BasePresenter<CompanyInfoContract.CompanyView> {

    public CompanyInfoPresenter(@NonNull CompanyInfoContract.CompanyView companyView) {
        super(companyView);
    }
}
