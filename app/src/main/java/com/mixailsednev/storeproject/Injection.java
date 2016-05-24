package com.mixailsednev.storeproject;

import android.content.Context;
import android.support.annotation.NonNull;

import com.mixailsednev.storeproject.model.product.CreateProductAction;
import com.mixailsednev.storeproject.model.product.LoadProductsAction;
import com.mixailsednev.storeproject.model.product.ProductLocalDataSource;
import com.mixailsednev.storeproject.model.product.ProductRepository;
import com.mixailsednev.storeproject.model.product.RemoveProductAction;
import com.mixailsednev.storeproject.model.product.UpdateProductAction;

public class Injection {

    public static ProductRepository provideProductStore() {
        return ProductRepository.getInstance();
    }

    public static ProductLocalDataSource provideProductLocalDataSource(@NonNull Context context) {
        return ProductLocalDataSource.newInstance(context);
    }

    public static LoadProductsAction provideLoadProductsAction(@NonNull Context context) {
        return new LoadProductsAction(Injection.provideProductStore(), Injection.provideProductLocalDataSource(context));
    }

    public static UpdateProductAction provideUpdateProductAction(@NonNull Context context) {
        return new UpdateProductAction(Injection.provideProductStore(), Injection.provideProductLocalDataSource(context));
    }

    public static CreateProductAction provideCreateProductAction(@NonNull Context context) {
        return new CreateProductAction(Injection.provideProductStore(), Injection.provideProductLocalDataSource(context));
    }

    public static RemoveProductAction provideRemoveProductAction(@NonNull Context context) {
        return new RemoveProductAction(Injection.provideProductStore(), Injection.provideProductLocalDataSource(context));
    }

}
