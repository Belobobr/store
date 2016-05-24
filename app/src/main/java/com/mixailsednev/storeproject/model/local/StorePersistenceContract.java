package com.mixailsednev.storeproject.model.local;

import android.provider.BaseColumns;


public final class StorePersistenceContract {

    public StorePersistenceContract() {}

    public static abstract class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "product";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_COST = "cost";
    }
}
