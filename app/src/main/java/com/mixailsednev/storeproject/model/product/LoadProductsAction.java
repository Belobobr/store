package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoadProductsAction extends Action<Void> {

    @NonNull
    ProductRepository productRepository;

    @NonNull
    ProductLocalDataSource productLocalDataSource;

    public LoadProductsAction(@NonNull ProductRepository productRepository, @NonNull ProductLocalDataSource productLocalDataSource) {
        this.productRepository = productRepository;
        this.productLocalDataSource = productLocalDataSource;
    }

    @Override
    public void run(Void payload) {
        productRepository.setLoading(true);

        productLocalDataSource.getProductsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    productRepository.setLoading(false);
                    productRepository.setProducts(products);
                });
    }

}
