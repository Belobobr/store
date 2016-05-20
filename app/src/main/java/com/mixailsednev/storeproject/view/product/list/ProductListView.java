package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.entity.Product;

import java.util.List;

public interface ProductListView {
    void setCases(@NonNull List<Product> cases);
    void setLoading(boolean loading);
}

