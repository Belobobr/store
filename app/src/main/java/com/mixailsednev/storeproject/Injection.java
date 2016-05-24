package com.mixailsednev.storeproject;

import com.mixailsednev.storeproject.model.product.CreateProductAction;
import com.mixailsednev.storeproject.model.product.LoadProductsAction;
import com.mixailsednev.storeproject.model.product.ProductDao;
import com.mixailsednev.storeproject.model.product.ProductRepository;
import com.mixailsednev.storeproject.model.product.RemoveProductAction;
import com.mixailsednev.storeproject.model.product.UpdateProductAction;

public class Injection {

    public static ProductRepository provideProductStore() {
        return ProductRepository.getInstance();
    }

    public static ProductDao provideProductDao() {
        return ProductDao.newInstance();
    }

    public static LoadProductsAction provideLoadProductsAction() {
        return new LoadProductsAction(Injection.provideProductStore(), Injection.provideProductDao());
    }

    public static UpdateProductAction provideUpdateProductAction() {
        return new UpdateProductAction(Injection.provideProductStore(), Injection.provideProductDao());
    }

    public static CreateProductAction provideCreateProductAction() {
        return new CreateProductAction(Injection.provideProductStore(), Injection.provideProductDao());
    }

    public static RemoveProductAction provideRemoveProductAction() {
        return new RemoveProductAction(Injection.provideProductStore(), Injection.provideProductDao());
    }

}
