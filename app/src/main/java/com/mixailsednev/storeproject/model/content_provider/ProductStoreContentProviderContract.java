package com.mixailsednev.storeproject.model.content_provider;

import android.net.Uri;

public class ProductStoreContentProviderContract {
    // // Uri
    // authority
    public static final String AUTHORITY = "com.mixailsednev.storeproject";

    // path
    public static final String PRODUCT_PATH = "product";

    // Таблица
    public static final String PRODUCTS_TABLE = "products";
    // Поля
    public static final String PRODUCT_ID = "_id";
    public static final String PRODUCT_NAME = "name";
    public static final String PRODUCT_COST = "cost";
    public static final String PRODUCT_DESCRIPTION = "description";

    // Скрипт создания таблицы
    static final String DB_CREATE = "create table " + PRODUCTS_TABLE + "("
            + PRODUCT_ID + " integer primary key autoincrement, "
            + PRODUCT_NAME + " text, "
            + PRODUCT_COST + " text, "
            + PRODUCT_DESCRIPTION + " text"
            + ");";

    // Общий Uri
    public static final Uri PRODUCT_CONTENT_URI = Uri.parse("content://"
            + AUTHORITY + "/" + PRODUCT_PATH);

    // Типы данных
    // набор строк
    static final String PRODUCT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "." + PRODUCT_PATH;

    // одна строка
    static final String PRODUCT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd."
            + AUTHORITY + "." + PRODUCT_PATH;
}