package com.mixailsednev.storeproject.view.product.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.view.custom.ProductParamView;

public class ProductDetailFragment extends Fragment {

    public static final String TAG = "ProductDetailFragment";
    public static final String ARG_PRODUCT_ID = "product_id";

    @NonNull
    private Long productId;

    public static ProductDetailFragment newInstance(@NonNull Long productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putLong(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        fragment.setArguments(arguments);
        return fragment;
    }

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PRODUCT_ID)) {

            productId = getArguments().getLong(ARG_PRODUCT_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        ProductParamView productDetailsParam = (ProductParamView) rootView.findViewById(R.id.product_description);


        productDetailsParam.setParamValue(productId.toString());


        return rootView;
    }


}
