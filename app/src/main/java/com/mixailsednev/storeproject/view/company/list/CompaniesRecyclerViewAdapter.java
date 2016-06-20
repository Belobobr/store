package com.mixailsednev.storeproject.view.company.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.company.Company;
import com.mixailsednev.storeproject.model.company.services.Service;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompaniesRecyclerViewAdapter extends RecyclerView.Adapter<CompaniesRecyclerViewAdapter.ViewHolder> {

    private static String TAG = CompaniesRecyclerViewAdapter.class.getSimpleName();

    public interface CompanySelectedListener {
        void companySelected(@NonNull String companyId);
    }

    private List<Company> companies;
    private CompanySelectedListener companySelectedListener;

    public CompaniesRecyclerViewAdapter(List<Company> companies, CompanySelectedListener companySelectedListener) {
        this.companies = companies;
        this.companySelectedListener = companySelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.company = companies.get(position);
        holder.companyName.setText(companies.get(position).getName());

        holder.view.setOnClickListener((view) -> {
            if (holder.company.getId() != null) {
                companySelectedListener.companySelected(holder.company.getId());
            } else {
                Log.e(TAG, "Service id can't be null");
            }
        });
    }

    public void setCompanies(@NonNull List<Company> companies) {
        this.companies = companies;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.company_icon)
        ImageView companyIcon;
        @BindView(R.id.company_name)
        TextView companyName;

        public View view;
        public Company company;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            ButterKnife.bind(this, view);
        }

        @Override
        public String toString() {
            return super.toString() + " '";
        }
    }
}