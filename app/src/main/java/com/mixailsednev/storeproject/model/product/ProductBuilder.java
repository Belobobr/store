package com.mixailsednev.storeproject.model.product;

import android.support.annotation.Nullable;

public class ProductBuilder {

    @Nullable
    private String id;
    private String name;
    private String cost;
    private String description;

    public ProductBuilder setId(@Nullable String id) {
        this.id = id;
        return this;
    }

    public ProductBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ProductBuilder setCost(String cost) {
        this.cost = cost;
        return this;
    }

    public ProductBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public Product createProduct() {
        return new Product(id, name, cost, description);
    }
}