package com.mixailsednev.storeproject.view.product.details;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductsRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.details.ProductDetailsContract.ProductDetailsView;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    public static final String TAG = ProductDetailsPresenter.class.getSimpleName();

    @NonNull
    ProductsRepository productsRepository;

    @NonNull
    String productId;

    public ProductDetailsPresenter(@NonNull ProductDetailsView productDetailsView,
                                   @NonNull ProductsRepository productsRepository,
                                   @NonNull String productId) {
        super(productDetailsView);
        this.productsRepository = productsRepository;
        this.productId = productId;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener
    ) {
        compositeDataChangeListener.addListener(productsRepository, () -> {
            Product product = productsRepository.getProduct(productId);
            if (product == null) {
                Log.e(TAG, "Product with id: " + productId + " not found");
            } else {
                getView().setProduct(product);
            }
        });
    }
}
