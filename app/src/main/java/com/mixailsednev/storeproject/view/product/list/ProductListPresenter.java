package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.CompositeDataChangeListener;
import com.mixailsednev.storeproject.model.common.DataChangeListener;
import com.mixailsednev.storeproject.model.product.LoadProductsAction;
import com.mixailsednev.storeproject.model.product.Product;
import com.mixailsednev.storeproject.model.product.ProductStore;
import com.mixailsednev.storeproject.model.product.RemoveProductAction;
import com.mixailsednev.storeproject.view.common.BasePresenter;
import com.mixailsednev.storeproject.view.product.list.ProductListContract.ProductListView;

public class ProductListPresenter extends BasePresenter<ProductListView>
        implements ProductListContract.ActionsListener {

    @NonNull
    ProductStore productStore;

    @NonNull
    LoadProductsAction loadProductsAction;

    @NonNull
    RemoveProductAction removeProductAction;

    public ProductListPresenter(@NonNull ProductListView productListView,
                                @NonNull ProductStore productStore,
                                @NonNull LoadProductsAction loadProductsAction,
                                @NonNull RemoveProductAction removeProductAction
    ) {
        super(productListView);
        this.productStore = productStore;
        this.loadProductsAction = loadProductsAction;
        this.removeProductAction = removeProductAction;
    }

    @Override
    public void subscribeToDataStoreInternal(@NonNull CompositeDataChangeListener compositeDataChangeListener) {
        compositeDataChangeListener.addListener(productStore, new DataChangeListener(ProductStore.PRODUCT_CONTENT_URI) {

            @Override
            public void newDataReceived() {
                getView().setProducts(productStore.getProducts());
                getView().setLoading(productStore.isLoading());
            }
        });
    }

    @Override
    public void loadProducts() {
        loadProductsAction.run(null);
    }

    @Override
    public void removeProduct(@NonNull Product product) {
        removeProductAction.run(product);
    }
}
