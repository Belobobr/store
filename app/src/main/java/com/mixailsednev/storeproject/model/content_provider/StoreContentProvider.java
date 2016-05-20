package com.mixailsednev.storeproject.model.content_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

//TODO просмотреть код =)
public class StoreContentProvider extends ContentProvider {
    final String TAG = "StoreContentProvider";

    // // Константы для БД
    // БД
    static final String DB_NAME = "storeDb";
    static final int DB_VERSION = 1;

    //// UriMatcher
    // общий Uri
    static final int URI_PRODUCTS = 1;

    // Uri с указанным ID
    static final int URI_PRODUCT_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(StoreContentProviderContract.AUTHORITY, StoreContentProviderContract.PRODUCT_PATH, URI_PRODUCTS);
        uriMatcher.addURI(StoreContentProviderContract.AUTHORITY, StoreContentProviderContract.PRODUCT_PATH + "/#", URI_PRODUCT_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase db;

    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    // чтение
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query, " + uri.toString());
        // проверяем Uri
        switch (uriMatcher.match(uri)) {
            case URI_PRODUCTS: // общий Uri
                Log.d(TAG, "URI_PRODUCTS");
                // если сортировка не указана, ставим свою - по имени
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = StoreContentProviderContract.PRODUCT_NAME + " ASC";
                }
                break;
            case URI_PRODUCT_ID: // Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_PRODUCT_ID, " + id);
                // добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = StoreContentProviderContract.PRODUCT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + StoreContentProviderContract.PRODUCT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.query(StoreContentProviderContract.PRODUCTS_TABLE, projection, selection,
                selectionArgs, null, null, sortOrder);
        // просим ContentResolver уведомлять этот курсор
        // об изменениях данных в CASE_CONTENT_URI
        if (getContext() == null) {
            Log.e(TAG, "Context is null");
        } else {
            cursor.setNotificationUri(getContext().getContentResolver(),
                    StoreContentProviderContract. CASE_CONTENT_URI);
        }
        return cursor;
    }

    public Uri insert(Uri uri, ContentValues values) {
        Log.d(TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_PRODUCTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        db = dbHelper.getWritableDatabase();
        long rowID = db.insert(StoreContentProviderContract.PRODUCTS_TABLE, null, values);
        Uri resultUri = ContentUris.withAppendedId(StoreContentProviderContract.CASE_CONTENT_URI, rowID);
        // уведомляем ContentResolver, что данные по адресу resultUri изменились
        if (getContext() == null) {
            Log.e(TAG, "Context is null");
        } else {
            getContext().getContentResolver().notifyChange(resultUri, null);
        }
        return resultUri;
    }

    public int bulkInsert(Uri uri, ContentValues[] values){
        int numInserted = 0;
        String table;

        int uriType = uriMatcher.match(uri);

        switch (uriType) {
            case URI_PRODUCTS:
                table = StoreContentProviderContract.PRODUCTS_TABLE;
                break;
            default:
                return 0;
        }
        SQLiteDatabase sqlDB = dbHelper.getWritableDatabase();
        sqlDB.beginTransaction();
        try {
            for (ContentValues cv : values) {
                long newID = sqlDB.insertOrThrow(table, null, cv);
                if (newID <= 0) {
                    throw new SQLException("Failed to insert row into " + uri);
                }
            }
            sqlDB.setTransactionSuccessful();
            if (getContext() == null) {
                Log.e(TAG, "Context is null");
            } else {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            numInserted = values.length;
        } catch(Exception exception) {
            numInserted = 0;
        } finally {
            sqlDB.endTransaction();
        }
        return numInserted;
    }

    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_PRODUCTS:
                Log.d(TAG, "URI_PRODUCTS");
                break;
            case URI_PRODUCT_ID:
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_PRODUCT_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = StoreContentProviderContract.PRODUCT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + StoreContentProviderContract.PRODUCT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.delete(StoreContentProviderContract.PRODUCTS_TABLE, selection, selectionArgs);
        if (getContext() == null) {
            Log.e(TAG, "Context is null");
        } else {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cnt;
    }

    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        Log.d(TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_PRODUCTS:
                Log.d(TAG, "URI_PRODUCTS");

                break;
            case URI_PRODUCT_ID:
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_PRODUCT_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = StoreContentProviderContract.PRODUCT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + StoreContentProviderContract.PRODUCT_ID + " = " + id;
                }
                break;
            default:
                throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        db = dbHelper.getWritableDatabase();
        int cnt = db.update(StoreContentProviderContract.PRODUCTS_TABLE, values, selection, selectionArgs);
        if (getContext() == null) {
            Log.e(TAG, "Context is null");
        } else {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return cnt;
    }

    public String getType(Uri uri) {
        Log.d(TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_PRODUCTS:
                return StoreContentProviderContract.CASE_CONTENT_TYPE;
            case URI_PRODUCT_ID:
                return StoreContentProviderContract.CASE_CONTENT_ITEM_TYPE;
        }
        return null;
    }

    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(StoreContentProviderContract.DB_CREATE);
            final int initialItemCount = 10;

            ContentValues cv = new ContentValues();
            for (int i = 1; i <= initialItemCount; i++) {
                cv.put(StoreContentProviderContract.PRODUCT_NAME, "Product name " + i);
                cv.put(StoreContentProviderContract.PRODUCT_COST, "Product cost " + i);
                cv.put(StoreContentProviderContract.PRODUCT_DESCRIPTION, "Product details" + i);
                db.insert(StoreContentProviderContract.PRODUCTS_TABLE, null, cv);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
}