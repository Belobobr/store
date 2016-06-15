package com.mixailsednev.storeproject.view.product.edit;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductsRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.edit.ProductEditContract.ActionsListener;
import com.mixailsednev.storeproject.view.product.edit.ProductEditContract.ProductEditView;

public class ProductEditPresenter extends BasePresenter<ProductEditView>
        implements ActionsListener {

    public static final String TAG = ProductEditPresenter.class.getSimpleName();

    @NonNull
    ProductsRepository productsRepository;

    @Nullable
    String productId;

    public ProductEditPresenter(@Nullable String productId,
                                @NonNull ProductEditView productEditView,
                                @NonNull ProductsRepository productsRepository) {
        super(productEditView);
        this.productsRepository = productsRepository;
        this.productId = productId;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener
    ) {
        if (productId != null) {
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

    @Override
    public void saveProduct(@NonNull Product product) {
        if (product.getId() == null) {
            productsRepository.addProduct(product);
        } else {
            productsRepository.updateProduct(product);
        }
    }
}
