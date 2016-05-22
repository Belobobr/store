package com.mixailsednev.storeproject.view.product.details;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mixailsednev.storeproject.Injection;
import com.mixailsednev.storeproject.R;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.view.common.BaseFragment;
import com.mixailsednev.storeproject.view.custom.ProductParamView;
import com.mixailsednev.storeproject.view.product.details.ProductDetailsContract.ProductDetailsView;

public class ProductDetailFragment extends BaseFragment<ProductDetailsPresenter> implements ProductDetailsView {

    public static final String ARG_PRODUCT_ID = "product_id";

    public static ProductDetailFragment newInstance(@NonNull Long productId) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle arguments = new Bundle();
        arguments.putLong(ProductDetailFragment.ARG_PRODUCT_ID, productId);
        fragment.setArguments(arguments);
        return fragment;
    }

    @NonNull
    private Long productId;
    private ProductParamView productNameView;
    private ProductParamView productCostView;
    private ProductParamView productDescriptionView;


    @Override
    public ProductDetailsPresenter createPresenter() {
        return new ProductDetailsPresenter(this, Injection.provideProductStore(), productId);
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
        productNameView = (ProductParamView) rootView.findViewById(R.id.product_name);
        productCostView = (ProductParamView) rootView.findViewById(R.id.product_cost);
        productDescriptionView = (ProductParamView) rootView.findViewById(R.id.product_description);
        return rootView;
    }


    @Override
    public void setProduct(@NonNull Product product) {
        productNameView.setParamValue(product.getName());
        productCostView.setParamValue(product.getCost());
        productDescriptionView.setParamValue(product.getDescription());
    }
}
