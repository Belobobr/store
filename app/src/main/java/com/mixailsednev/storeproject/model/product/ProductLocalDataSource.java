package com.mixailsednev.storeproject.model.product;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mixailsednev.storeproject.model.common.StoreDbHelper;
import com.mixailsednev.storeproject.model.common.StorePersistenceContract.ProductEntry;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

public class ProductLocalDataSource {

    private final static String TAG = ProductLocalDataSource.class.getSimpleName();

    private StoreDbHelper mDbHelper;

    private static ProductLocalDataSource instance;

    public static ProductLocalDataSource newInstance(@NonNull Context context) {
        if (instance == null) {
            instance = new ProductLocalDataSource(context);
        }
        return instance;
    }

    private ProductLocalDataSource(@NonNull Context context) {
        mDbHelper = new StoreDbHelper(context);
    }

    public Observable<List<Product>> getProductsObservable() {
        return Observable.create(subscriber -> {
            Observable.just(getProducts()).subscribe(subscriber);
        });
    }

    @NonNull
    public List<Product> getProducts() {
        ArrayList<Product> products = new ArrayList<>();
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                ProductEntry._ID,
                ProductEntry.COLUMN_NAME_NAME,
                ProductEntry.COLUMN_NAME_COST,
                ProductEntry.COLUMN_NAME_DESCRIPTION
        };

        Cursor productsCursor = db.query(
                ProductEntry.TABLE_NAME, projection, null, null, null, null, null);

        if (productsCursor != null) {
            try {
                while (productsCursor.moveToNext()) {
                    int productIdIndex = productsCursor.getColumnIndex(
                            ProductEntry._ID);
                    int productNameIndex = productsCursor.getColumnIndex(
                            ProductEntry.COLUMN_NAME_NAME);
                    int productCostIndex = productsCursor.getColumnIndex(
                            ProductEntry.COLUMN_NAME_COST);
                    int productDescriptionIndex = productsCursor.getColumnIndex(
                            ProductEntry.COLUMN_NAME_DESCRIPTION);
                    Product productFromCursor = new ProductBuilder()
                            .setId(productsCursor.getLong(productIdIndex))
                            .setName(productsCursor.getString(productNameIndex))
                            .setCost(productsCursor.getString(productCostIndex))
                            .setDescription(productsCursor.getString(productDescriptionIndex))
                            .createProduct();
                    products.add(productFromCursor);
                }
            } finally {
                productsCursor.close();
            }
        }

        db.close();

        return products;
    }

    public Observable<Long> createProductObservable(@NonNull Product creatingProduct) {
        return Observable.create(subscriber -> {
            Observable.just(createProduct(creatingProduct)).subscribe(subscriber);
        });
    }

    @Nullable
    public Long createProduct(@NonNull Product creatingProduct) throws RuntimeException {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ProductEntry.COLUMN_NAME_NAME, creatingProduct.getName());
        values.put(ProductEntry.COLUMN_NAME_COST, creatingProduct.getCost());
        values.put(ProductEntry.COLUMN_NAME_DESCRIPTION, creatingProduct.getDescription());

        long id = db.insert(ProductEntry.TABLE_NAME, null, values);

        db.close();

        if (id == 0) {
            return id;
        } else {
            throw new RuntimeException("Can't create product");
        }
    }

    public Observable<Integer> updateProductObservable(@NonNull Product updatingProduct) {
        return Observable.create(subscriber -> {
            Observable.just(updateProduct(updatingProduct)).subscribe(subscriber);
        });
    }

    //TODO refactor to throw Exception on error
    public Integer updateProduct(@NonNull Product updatingProduct) {
        if (updatingProduct.getId() != null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(ProductEntry.COLUMN_NAME_NAME, updatingProduct.getName());
            values.put(ProductEntry.COLUMN_NAME_COST, updatingProduct.getCost());
            values.put(ProductEntry.COLUMN_NAME_DESCRIPTION, updatingProduct.getDescription());

            String selection = ProductEntry._ID + " = ?";
            String[] selectionArgs = {updatingProduct.getId().toString()};

            int updated = db.update(ProductEntry.TABLE_NAME, values, selection, selectionArgs);

            db.close();

            return updated;
        } else {
            Log.e(TAG, "Trying to update product with id == null");
            return 0;
        }
    }

    public Observable<Integer> removeProductObservable(@NonNull Product product) {
        return Observable.create(subscriber -> {
            Observable.just(removeProduct(product)).subscribe(subscriber);
        });
    }

    public Integer removeProduct(@NonNull Product removingProduct) {
        if (removingProduct.getId() != null) {
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            String selection = ProductEntry._ID + " = ?";
            String[] selectionArgs = {removingProduct.getId().toString()};

            int deleted = db.delete(ProductEntry.TABLE_NAME, selection, selectionArgs);

            db.close();

            return deleted;
        } else {
            Log.e(TAG, "Trying to delete product with id == null");
            return 0;
        }
    }
}
