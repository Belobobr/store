package com.mixailsednev.storeproject.view.product.edit;

import android.content.ContentUris;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.common.DataChangeListener;
import com.mixailsednev.storeproject.model.content_provider.ProductStoreContentProviderContract;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductStore;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.edit.ProductEditContract.ProductEditView;

public class ProductEditPresenter extends BasePresenter<ProductEditView> {

    public static final String TAG = ProductEditPresenter.class.getSimpleName();

    @NonNull
    ProductStore productStore;

    @Nullable
    Long productId;

    public ProductEditPresenter(@NonNull ProductEditView productEditView,
                                @NonNull ProductStore productStore,
                                @Nullable Long productId) {
        super(productEditView);
        this.productStore = productStore;
        this.productId = productId;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener
    ) {
        if (productId != null) {
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
}
