package com.mixailsednev.storeproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

public class ProductEditFragment extends Fragment {

    public static final String TAG = "ProductEditFragment";
    public static final String ARG_PRODUCT_ID = "product_id";

    public ProductEditFragment() {
    }

    public static ProductEditFragment newInstance(@NonNull String productId) {
        ProductEditFragment fragment =  new ProductEditFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    private String productId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PRODUCT_ID)) {
            productId =  getArguments().getString(ARG_PRODUCT_ID, "");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
