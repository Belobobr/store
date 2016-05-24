package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class UpdateProductAction extends Action<Product> {

    @NonNull
    private ProductRepository productRepository;

    @NonNull
    private ProductLocalDataSource productLocalDataSource;

    public UpdateProductAction(@NonNull ProductRepository productRepository, @NonNull ProductLocalDataSource productLocalDataSource) {
        this.productRepository = productRepository;
        this.productLocalDataSource = productLocalDataSource;
    }

    //TODO catch error in on error
    @Override
    public void run(@NonNull Product product) {
        productLocalDataSource.updateProductObservable(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(updated -> {
                    if (updated == 1) {
                        productRepository.updateProduct(product);
                    }
                });

    }
}
