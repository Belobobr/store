package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateProductAction extends Action<Product> {

    @NonNull
    private ProductRepository productRepository;

    @NonNull
    private ProductDao productDao;

    public CreateProductAction(@NonNull ProductRepository productRepository, @NonNull ProductDao productDao) {
        this.productRepository = productRepository;
        this.productDao = productDao;
    }

    @Override
    public void run(@NonNull Product product) {
        productDao.createProductObservable(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productId -> {
                    product.setId(productId);
                    productRepository.addProduct(product);
                });

    }
}
