package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.BaseStore;

import java.util.ArrayList;
import java.util.List;

public class ProductStore extends BaseStore<ProductState> {

    public static String TAG = ProductStore.class.getSimpleName();

    public ProductStore(ProductState productState) {
        super(productState);
    }

    private static ProductStore instance = new ProductStore();

    public static ProductStore getInstance() {
        return instance;
    }

    public ProductStore() {
        super(new ProductState(new ArrayList<Product>(), false));
    }

    public void setProducts(@NonNull List<Product> cases) {
        getState().setProducts(cases);
        notifyDataChanged();
    }

    public void setLoading(boolean loading) {
        getState().setLoading(loading);
        notifyDataChanged();
    }
}
