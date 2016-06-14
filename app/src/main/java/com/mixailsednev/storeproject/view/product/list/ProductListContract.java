package com.mixailsednev.storeproject.view.product.list;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.product.Product;

import java.util.List;

public class ProductListContract {

    public interface ProductListView {
        void setProducts(@NonNull List<Product> products);
    }

    public interface ActionsListener {
        void removeProduct(@NonNull Product product);
    }
}
