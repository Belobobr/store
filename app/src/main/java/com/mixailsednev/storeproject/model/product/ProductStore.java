package com.mixailsednev.storeproject.model.product;

import android.content.ContentUris;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.BaseStore;

import java.util.ArrayList;
import java.util.List;

public class ProductStore extends BaseStore {

    public static String TAG = ProductStore.class.getSimpleName();

    private List<Product> products;
    private boolean loading;

    private static ProductStore instance = new ProductStore();

    public static ProductStore getInstance() {
        return instance;
    }

    public ProductStore() {
        this.products = new ArrayList<>();
        this.loading = false;
    }

    public void setProducts(@NonNull List<Product> products) {
        this.products = products;
        notifyDataChanged(PRODUCT_CONTENT_URI);
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyDataChanged(PRODUCT_CONTENT_URI);
    }

    public void removeProduct(@NonNull Product product) {
        products.remove(product);
        notifyDataChanged(PRODUCT_CONTENT_URI);
    }

    public void addProduct(@NonNull Product product) {
        products.remove(product);
        notifyDataChanged(PRODUCT_CONTENT_URI);
    }

    public void updateProduct(@NonNull Product product) {
        if (products.indexOf(product) == -1) {
            Log.e(TAG, "Trying to update product: " + product + ", that not in ProductStore");
        } else {
            products.get(products.indexOf(product)).update(product);
        }
        Uri uri = ContentUris.withAppendedId(PRODUCT_CONTENT_URI, product.getId());
        notifyDataChanged(uri);
    }

    public List<Product> getProducts() {
        return products;
    }

    public boolean isLoading() {
        return loading;
    }
}
