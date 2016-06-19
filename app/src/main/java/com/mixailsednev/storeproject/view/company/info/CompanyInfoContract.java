package com.mixailsednev.storeproject.view.company.info;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.company.CompanyInfo;

public class CompanyInfoContract {

    public interface CompanyView {
        void setCompany(@NonNull CompanyInfo companyInfo);
    }

    public interface ActionsListener {
        void addComment(@NonNull String commentContent);
    }
}
