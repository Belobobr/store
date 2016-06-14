package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.mixailsednev.storeproject.model.common.BaseRepository;

import java.util.List;

public abstract class ProductRepository extends BaseRepository {

    abstract public void removeProduct(@NonNull Product product);

    abstract public void addProduct(@NonNull Product product);

    abstract public void updateProduct(@NonNull Product product);

    @NonNull
    abstract public List<Product> getProducts();

    @Nullable
    abstract public Product getProduct(@NonNull String productId);
}
