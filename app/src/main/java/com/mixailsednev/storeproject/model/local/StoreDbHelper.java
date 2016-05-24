package com.mixailsednev.storeproject.model.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StoreDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "Store.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + StorePersistenceContract.ProductEntry.TABLE_NAME + " (" +
                    StorePersistenceContract.ProductEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT, " +
                    StorePersistenceContract.ProductEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    StorePersistenceContract.ProductEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    StorePersistenceContract.ProductEntry.COLUMN_NAME_COST + TEXT_TYPE +
            " )";

    public StoreDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Not required as at version 1
    }
}