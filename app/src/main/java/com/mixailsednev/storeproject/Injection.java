package com.mixailsednev.storeproject;

import com.mixailsednev.storeproject.model.product.LoadProductsAction;
import com.mixailsednev.storeproject.model.product.ProductDao;
import com.mixailsednev.storeproject.model.product.ProductStore;

public class Injection {

    public static ProductStore provideProductStore() {
        return ProductStore.getInstance();
    }

    public static ProductDao provideProductDao() {
        return ProductDao.newInstance();
    }

    public static LoadProductsAction provideLoadProductsAction() {
        return new LoadProductsAction(Injection.provideProductStore(), Injection.provideProductDao());
    }

}
