package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.BaseRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository extends BaseRepository {

    public static String TAG = ProductRepository.class.getSimpleName();

    @NonNull
    private List<Product> products;
    private boolean loading;

    private static ProductRepository instance = new ProductRepository();

    public static ProductRepository getInstance() {
        return instance;
    }

    public ProductRepository() {
        this.products = new ArrayList<>();
        this.loading = false;
    }

    public void setProducts(@NonNull List<Product> products) {
        this.products = products;
        notifyDataChanged();
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
        notifyDataChanged();
    }

    public void removeProduct(@NonNull Product product) {
        products.remove(product);
        notifyDataChanged();
    }

    public void addProduct(@NonNull Product product) {
        products.add(product);
        notifyDataChanged();
    }

    public void updateProduct(@NonNull Product product) {
        if (products.indexOf(product) == -1) {
            Log.e(TAG, "Trying to update product: " + product + ", that not in ProductRepository");
        } else {
            products.get(products.indexOf(product)).update(product);
        }
        notifyDataChanged();
    }

    @NonNull
    public List<Product> getProducts() {
        return products;
    }

    public boolean isLoading() {
        return loading;
    }

    @Nullable
    public Product getProduct(@NonNull Long productId) {
        for (Product product : products) {
            if (productId.equals(product.getId())) {
                return product;
            }
        }
        return null;
    }
}
