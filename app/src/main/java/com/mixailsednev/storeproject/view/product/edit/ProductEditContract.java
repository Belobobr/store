package com.mixailsednev.storeproject.view.product.edit;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.product.Product;

public class ProductEditContract {
    public interface ProductEditView {
        void setProduct(@NonNull Product product);
    }
}
