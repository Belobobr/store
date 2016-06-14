package com.mixailsednev.storeproject;

import com.mixailsednev.storeproject.model.product.FirebaseProductRepository;
import com.mixailsednev.storeproject.model.product.ProductRepository;

public class Injection {

    public static ProductRepository provideProductRepository() {
        return FirebaseProductRepository.getInstance();
    }
}
