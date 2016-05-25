package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RemoveProductAction extends Action<Product> {

    @NonNull
    private ProductRepository productRepository;

    @NonNull
    private ProductLocalDataSource productLocalDataSource;

    public RemoveProductAction(@NonNull ProductRepository productRepository, @NonNull ProductLocalDataSource productLocalDataSource) {
        this.productRepository = productRepository;
        this.productLocalDataSource = productLocalDataSource;
    }

    @Override
    public void run(@NonNull Product product) {
        productLocalDataSource.removeProductObservable(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(removed -> {
                    productRepository.removeProduct(product);
                }, (error) -> {
                    Log.e(RemoveProductAction.class.getSimpleName(), "Can't remove product");
                });

    }
}
