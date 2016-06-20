package com.mixailsednev.storeproject.view.company.services;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.company.services.Service;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ServicesRecyclerViewAdapter extends RecyclerView.Adapter<ServicesRecyclerViewAdapter.ViewHolder> {

    private static String TAG = ServicesRecyclerViewAdapter.class.getSimpleName();

    public interface ServiceSelectedListener {
        void serviceSelected(@NonNull String serviceId);
    }

    private List<Service> services;
    private ServiceSelectedListener serviceSelectedListener;

    public ServicesRecyclerViewAdapter(List<Service> services, ServiceSelectedListener serviceSelectedListener) {
        this.services = services;
        this.serviceSelectedListener = serviceSelectedListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_service, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.service = services.get(position);
        holder.serviceNameTextView.setText(services.get(position).getName());

        holder.view.setOnClickListener((view) -> {
            if (holder.service.getId() != null) {
                serviceSelectedListener.serviceSelected(holder.service.getId());
            } else {
                Log.e(TAG, "Service id can't be null");
            }
        });
    }

    public void setServices(@NonNull List<Service> services) {
        this.services = services;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.service_icon)
        ImageView serviceIcon;
        @BindView(R.id.service_name)
        TextView serviceNameTextView;

        public View view;
        public Service service;

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