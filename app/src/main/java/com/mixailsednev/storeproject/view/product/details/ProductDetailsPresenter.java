package com.mixailsednev.storeproject.view.product.details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.details.ProductDetailsContract.ProductDetailsView;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    public static final String TAG = ProductDetailsPresenter.class.getSimpleName();

    @NonNull
    ProductRepository productRepository;

    @NonNull
    String productId;

    public ProductDetailsPresenter(@NonNull ProductDetailsView productDetailsView,
                                   @NonNull ProductRepository productRepository,
                                   @NonNull String productId) {
        super(productDetailsView);
        this.productRepository = productRepository;
        this.productId = productId;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener
    ) {
        compositeDataChangeListener.addListener(productRepository, () -> {
            Product product = productRepository.getProduct(productId);
            if (product == null) {
                Log.e(TAG, "Product with id: " + productId + " not found");
            } else {
                getView().setProduct(product);
            }
        });
    }
}
