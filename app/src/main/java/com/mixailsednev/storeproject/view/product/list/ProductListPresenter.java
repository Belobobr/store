package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductsRepository;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.list.ProductListContract.ProductListView;

public class ProductListPresenter extends BasePresenter<ProductListView>
        implements ProductListContract.ActionsListener {

    @NonNull
    ProductsRepository productsRepository;

    public ProductListPresenter(@NonNull ProductListView productListView,
                                @NonNull ProductsRepository productsRepository
    ) {
        super(productListView);
        this.productsRepository = productsRepository;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(
                productsRepository,
                () -> getView().setProducts(productsRepository.getProducts())
        );
    }

    @Override
    public void removeProduct(@NonNull Product product) {
        productsRepository.removeProduct(product);
    }
}
