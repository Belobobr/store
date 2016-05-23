package com.mixailsednev.storeproject.model.common;

import android.content.UriMatcher;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.mixailsednev.storeproject.model.product.ProductStore;

import java.util.ArrayList;

//TODO debug listeners
//TODO refactor
//TODO refactor uri matching
public class BaseStore {

    public static final String TAG = BaseStore.class.getSimpleName();

    public static final String AUTHORITY = "com.mixailsednev.storeproject";

    public static final String PRODUCT_PATH = "product";

    public static final Uri PRODUCT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + PRODUCT_PATH);

    static final int URI_PRODUCTS = 1;

    static final int URI_PRODUCT_ID = 2;

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(ProductStore.AUTHORITY, ProductStore.PRODUCT_PATH, URI_PRODUCTS);
        uriMatcher.addURI(ProductStore.AUTHORITY, ProductStore.PRODUCT_PATH + "/#", URI_PRODUCT_ID);
    }

    private ArrayList<DataChangeListener> mDataChangeListeners = new ArrayList<>();

    public DataChangeListener addListener(DataChangeListener dataChangeListener) {
        mDataChangeListeners.add(dataChangeListener);
        dataChangeListener.newDataReceived();
        return dataChangeListener;
    }

    public void removeListener(DataChangeListener dataDataChangeListener) {
        mDataChangeListeners.remove(dataDataChangeListener);
    }

    public void notifyDataChanged(@NonNull Uri uri) {
        String path;
        boolean single;

        switch (uriMatcher.match(uri)) {
            case URI_PRODUCTS: // общий Uri
                Log.d(TAG, "Notify URI_PRODUCTS");
                path = PRODUCT_PATH;
                single = false;
                break;
            case URI_PRODUCT_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(TAG, "Notify URI_PRODUCT_ID, " + id);
                path = PRODUCT_PATH;
                single = true;
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }

        for (DataChangeListener listener : mDataChangeListeners) {
            if (listener.getUri().getPathSegments().get(0).equals(path)) {
                boolean sameId = listener.getUri().getLastPathSegment().equals(uri.getLastPathSegment());

                if (single && sameId) {
                    listener.newDataReceived();
                } else {
                    listener.newDataReceived();
                }
            }
        }
    }
}