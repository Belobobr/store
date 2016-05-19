package com.mixailsednev.storeproject;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mixailsednev.storeproject.dummy.DummyContent;

public class ProductDetailFragment extends Fragment {

    public static final String TAG = "ProductEditFragment";
    public static final String ARG_PRODUCT_ID = "product_id";

    private DummyContent.DummyItem mItem;

    public static ProductDetailFragment newInstance(@NonNull String productId) {
        ProductDetailFragment fragment =  new ProductDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        fragment.setArguments(arguments);
        return fragment;
    }

    public ProductDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_PRODUCT_ID)) {

            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_PRODUCT_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.product_detail)).setText(mItem.details);
        }

        return rootView;
    }
}
