package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Product {

    @Nullable
    private Long id;
    private String name;
    private String cost;
    private String description;

    public Product(@Nullable Long id, String name, String cost, String description) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.description = description;
    }

    public void update(@NonNull Product product) {
        name = product.getName();
        cost = product.getCost();
        description = product.getDescription();
    }

    @Nullable
    public Long getId() {
        return id;
    }

    @Nullable
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        return id != null ? id.equals(product.id) : product.id == null;
    }
}
