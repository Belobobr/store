package com.mixailsednev.storeproject.model.product;

import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.common.Action;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RemoveProductAction extends Action<Product> {

    @NonNull
    private ProductStore productStore;

    @NonNull
    private ProductDao productDao;

    public RemoveProductAction(@NonNull ProductStore productStore, @NonNull ProductDao productDao) {
        this.productStore = productStore;
        this.productDao = productDao;
    }

    //TODO catch error in on error
    @Override
    public void run(@NonNull Product product) {
        productDao.removeProductObservable(product)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(removed -> {
                    if (removed == 1) {
                        productStore.removeProduct(product);
                    }
                });

    }
}
