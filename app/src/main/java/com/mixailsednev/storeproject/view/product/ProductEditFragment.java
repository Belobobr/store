package com.mixailsednev.storeproject.view.product;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.product.details.ProductDetailFragment;

public class ProductEditFragment extends Fragment {

    public static final String TAG = ProductEditFragment.class.getSimpleName();
    public static final String ARG_PRODUCT_ID = "product_id";

    public ProductEditFragment() {
    }

    public static ProductEditFragment newInstance(@Nullable Long productId) {
        ProductEditFragment fragment = new ProductEditFragment();
        Bundle arguments = new Bundle();
        if (productId != null) {
            arguments.putLong(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        }

        fragment.setArguments(arguments);
        return fragment;
    }

    @Nullable
    private String productId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PRODUCT_ID)) {
            productId = getArguments().getString(ARG_PRODUCT_ID);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_edit, container, false);

        return rootView;
    }
}
