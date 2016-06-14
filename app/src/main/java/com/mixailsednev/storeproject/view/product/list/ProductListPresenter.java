package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.list.ProductListContract.ProductListView;

public class ProductListPresenter extends BasePresenter<ProductListView>
        implements ProductListContract.ActionsListener {

    @NonNull
    ProductRepository productRepository;

    public ProductListPresenter(@NonNull ProductListView productListView,
                                @NonNull ProductRepository productRepository
    ) {
        super(productListView);
        this.productRepository = productRepository;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(
                productRepository,
                () -> getView().setProducts(productRepository.getProducts())
        );
    }

    @Override
    public void removeProduct(@NonNull Product product) {
        productRepository.removeProduct(product);
    }
}
