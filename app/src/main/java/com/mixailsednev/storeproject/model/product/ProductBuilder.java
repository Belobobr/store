package com.mixailsednev.storeproject.model.product;

public class ProductBuilder {
    private Long id;
    private String name;
    private String cost;
    private String description;

    public ProductBuilder setId(Long id) {
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