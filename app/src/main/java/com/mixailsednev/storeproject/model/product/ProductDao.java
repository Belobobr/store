package com.mixailsednev.storeproject.model.product;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mixailsednev.storeproject.ProductStoreApplication;
import com.mixailsednev.storeproject.model.content_provider.ProductStoreContentProviderContract;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ProductDao {

    private final static String TAG = ProductDao.class.getSimpleName();

    private static ProductDao instance = new ProductDao();

    public static ProductDao newInstance() {
        return instance;
    }

    public Observable<List<Product>> getProductsObservable() {
        return Observable.create(subscriber ->  {
            Observable.just(getProducts()).subscribe(subscriber);
        });
    }

    @NonNull
    public List<Product> getProducts() {
        Cursor productsCursor = ProductStoreApplication.getContext().getContentResolver()
                .query(ProductStoreContentProviderContract.PRODUCT_CONTENT_URI, null, null, null, null);

        ArrayList<Product> cases = new ArrayList<>();
        if (productsCursor != null) {
            try {
                while (productsCursor.moveToNext()) {
                    int productIdIndex = productsCursor.getColumnIndex(
                            ProductStoreContentProviderContract.PRODUCT_ID);
                    int productNameIndex = productsCursor.getColumnIndex(
                            ProductStoreContentProviderContract.PRODUCT_NAME);
                    int productCostIndex = productsCursor.getColumnIndex(
                            ProductStoreContentProviderContract.PRODUCT_COST);
                    int productDescriptionIndex = productsCursor.getColumnIndex(
                            ProductStoreContentProviderContract.PRODUCT_DESCRIPTION);
                    Product productFromCursor = new ProductBuilder()
                            .setId(productsCursor.getLong(productIdIndex))
                            .setName(productsCursor.getString(productNameIndex))
                            .setCost(productsCursor.getString(productCostIndex))
                            .setDescription(productsCursor.getString(productDescriptionIndex))
                            .createProduct();
                    cases.add(productFromCursor);
                }
            } finally {
                productsCursor.close();
            }
        }
        return cases;
    }

    public Observable<Long> createProductObservable(@NonNull Product creatingProduct) {
        return Observable.create(subscriber -> {
            Observable.just(createProduct(creatingProduct)).subscribe(subscriber);
        });
    }

    @Nullable
    public Long createProduct(@NonNull Product creatingProduct) {
        ContentValues cv = new ContentValues();
        cv.put(ProductStoreContentProviderContract.PRODUCT_NAME, creatingProduct.getName());
        cv.put(ProductStoreContentProviderContract.PRODUCT_COST, creatingProduct.getCost());
        cv.put(ProductStoreContentProviderContract.PRODUCT_DESCRIPTION, creatingProduct.getDescription());

        Uri createdProductUri = ProductStoreApplication.getContext()
                .getContentResolver().insert(ProductStoreContentProviderContract.PRODUCT_CONTENT_URI, cv);
        if (createdProductUri != null) {
            String id = createdProductUri.getLastPathSegment();
            return Long.parseLong(id);
        } else {
            Log.e(TAG, " insert to : "  + ProductStoreContentProviderContract.PRODUCT_CONTENT_URI + " failed.");
            return null;
        }
    }

    public Observable<Integer> updateProductObservable(@NonNull Product updatingProduct) {
        return Observable.create(subscriber -> {
            Observable.just(updateProduct(updatingProduct)).subscribe(subscriber);
        });
    }


    //TODO refactor to throw Exception on error
    public Integer updateProduct(@NonNull Product updatingProduct) {
        ContentValues cv = new ContentValues();
        cv.put(ProductStoreContentProviderContract.PRODUCT_NAME, updatingProduct.getName());
        cv.put(ProductStoreContentProviderContract.PRODUCT_COST, updatingProduct.getCost());
        cv.put(ProductStoreContentProviderContract.PRODUCT_DESCRIPTION, updatingProduct.getDescription());

        if (updatingProduct.getId() != null) {
            Uri uri = ContentUris.withAppendedId(ProductStoreContentProviderContract.PRODUCT_CONTENT_URI, updatingProduct.getId());
            return ProductStoreApplication.getContext()
                    .getContentResolver().update(uri, cv, null, null);
        } else {
            Log.e(TAG, "Trying to update product without productID");
            return -1;
        }
    }

    public boolean saveProducts(@NonNull List<Product> products) {
        ArrayList<ContentValues> contentValues = new ArrayList<ContentValues>(products.size());

        for (Product savedProduct : products) {
            ContentValues cv = new ContentValues();
            cv.put(ProductStoreContentProviderContract.PRODUCT_ID, savedProduct.getId());
            cv.put(ProductStoreContentProviderContract.PRODUCT_NAME, savedProduct.getName());
            cv.put(ProductStoreContentProviderContract.PRODUCT_COST, savedProduct.getCost());
            cv.put(ProductStoreContentProviderContract.PRODUCT_DESCRIPTION, savedProduct.getDescription());
            contentValues.add(cv);
        }

        ProductStoreApplication.getContext().getContentResolver().delete(ProductStoreContentProviderContract.PRODUCT_CONTENT_URI, null, null);

        int casesInserted = ProductStoreApplication.getContext()
                .getContentResolver().bulkInsert(
                        ProductStoreContentProviderContract.PRODUCT_CONTENT_URI,
                        contentValues.toArray(new ContentValues[contentValues.size()]));
        Log.d(TAG, " insert to : "  + ProductStoreContentProviderContract.PRODUCT_CONTENT_URI +
                " " + casesInserted + " products.");
        return true;
    }

}
