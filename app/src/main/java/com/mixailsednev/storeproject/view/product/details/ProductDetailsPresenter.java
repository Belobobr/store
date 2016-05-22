package com.mixailsednev.storeproject.view.product.details;

import android.content.ContentUris;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.common.DataChangeListener;
import com.mixailsednev.storeproject.model.content_provider.ProductStoreContentProviderContract;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductStore;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.details.ProductDetailsContract.ProductDetailsView;

public class ProductDetailsPresenter extends BasePresenter<ProductDetailsView> {

    public static final String TAG = ProductDetailsPresenter.class.getSimpleName();

    @NonNull
    ProductStore productStore;

    @NonNull
    Long productId;

    public ProductDetailsPresenter(@NonNull ProductDetailsView productDetailsView,
                                   @NonNull ProductStore productStore,
                                   @NonNull Long productId) {
        super(productDetailsView);
        this.productStore = productStore;
        this.productId = productId;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener
    ) {
        Uri productUri = ContentUris.withAppendedId(ProductStoreContentProviderContract.PRODUCT_CONTENT_URI, productId);

        compositeDataChangeListener.addListener(productStore, new DataChangeListener(productUri) {
            @Override
            public void newDataReceived() {
                Product product = productStore.getProduct(productId);
                if (product == null) {
                    Log.e(TAG, "Product with id: " + productId + " not found");
                } else {
                    getView().setProduct(product);
                }

            }
        });
    }
}
