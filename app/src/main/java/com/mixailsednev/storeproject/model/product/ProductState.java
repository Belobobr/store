package com.mixailsednev.storeproject.model.product;

import java.util.ArrayList;
import java.util.List;

public class ProductState {

    private List<Product> products = new ArrayList<>();
    private boolean loading;

    public ProductState(List<Product> products, boolean loading) {
        this.products = products;
        this.loading = loading;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }
}
