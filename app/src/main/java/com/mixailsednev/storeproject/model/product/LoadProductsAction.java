package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.Action;

import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoadProductsAction extends Action<Void> {

    @NonNull
    ProductStore productStore;

    @NonNull
    ProductDao productDao;

    public LoadProductsAction(@NonNull ProductStore productStore, @NonNull ProductDao productDao) {
        this.productStore = productStore;
        this.productDao = productDao;
    }

    @Override
    public void run(Void payload) {
        productStore.setLoading(true);

        productDao.getProductsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(products -> {
                    productStore.setLoading(false);
                    productStore.setProducts(products);
                });
    }

}
