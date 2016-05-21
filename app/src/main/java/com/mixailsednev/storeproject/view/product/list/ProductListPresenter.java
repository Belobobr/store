package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.common.DataChangeListener;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductState;
import com.mixailsednev.storeproject.model.product.ProductStore;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.list.ProductListContract.ProductListView;

import java.util.ArrayList;
import java.util.List;

public class ProductListPresenter extends BasePresenter<ProductListView>
        implements ProductListContract.ActionsListener {

    @NonNull
    ProductStore productStore;

    public ProductListPresenter(@NonNull ProductListView productListView, @NonNull ProductStore productStore) {
        super(productListView);
        this.productStore = productStore;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(productStore, new DataChangeListener<ProductState>() {
            @Override
            public void newDataReceived(ProductState productState) {
                getView().setProducts(productState.getProducts());
                getView().setLoading(productState.isLoading());
            }
        });
    }

    @Override
    public void loadProducts() {
        List<Product> products  = new ArrayList<>();
        products.add(new Product(1L, "1", "1", "1"));
        products.add(new Product(1L, "1", "1", "1"));
        products.add(new Product(1L, "1", "1", "1"));
        products.add(new Product(1L, "1", "1", "1"));
        products.add(new Product(1L, "1", "1", "1"));

        productStore.setProducts(products);
    }
}
