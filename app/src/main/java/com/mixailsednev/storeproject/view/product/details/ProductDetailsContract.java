package com.mixailsednev.storeproject.view.product.details;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.product.Product;

public class ProductDetailsContract {
    public interface ProductDetailsView {
        void setProduct(@NonNull Product product);
    }
}
