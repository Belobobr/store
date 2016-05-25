package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CreateProductAction extends Action<Product> {

    @NonNull
    private ProductRepository productRepository;

    @NonNull
    private ProductLocalDataSource productLocalDataSource;

    public CreateProductAction(@NonNull ProductRepository productRepository, @NonNull ProductLocalDataSource productLocalDataSource) {
        this.productRepository = productRepository;
        this.productLocalDataSource = productLocalDataSource;
    }

    @Override
    public void run(@NonNull Product product) {
        productLocalDataSource.createProductObservable(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productId -> {
                    product.setId(productId);
                    productRepository.addProduct(product);
                }, (error) -> {
                    Log.e(CreateProductAction.class.getSimpleName(), "Can't create product");
                });

    }
}
