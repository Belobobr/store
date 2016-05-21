package com.mixailsednev.storeproject;

import com.mixailsednev.storeproject.model.product.ProductStore;

public class Injection {

    public static ProductStore provideProductStore() {
        return ProductStore.getInstance();
    }

}
