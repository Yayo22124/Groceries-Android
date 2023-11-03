package com.example.groceries;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class GroseriesDBHelper extends SQLiteOpenHelper {
    // Variables
    public static final String DATABASE_NAME = "groseries.db";
    public static final int DATABASE_VERSION = 1;
    private static final String SQL_CREATE_PRODUCTS = "CREATE TABLE " +
            GroseriesContract.Product.TABLE_NAME + " ( " + GroseriesContract.Product.COLUMN_NAME_BARCODE + " TEXT PRIMARY KEY, " +
            GroseriesContract.Product.COLUMN_NAME_DESCRIPTION + " TEXT, " + GroseriesContract.Product.COLUMN_NAME_BRAND + " TEXT, " +
            GroseriesContract.Product.COLUMN_NAME_COST + " REAL, " + GroseriesContract.Product.COLUMN_NAME_PRICE + " REAL," +
            GroseriesContract.Product.COLUMN_NAME_STOCK + " INT)";

    private static final String SQL_DELETE_PRODUCTS = "DROP TABLE IF EXISTS " + GroseriesContract.Product.TABLE_NAME;
    public GroseriesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creación de la tabla (query)
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTS);
    }

    // Cuando se actualiza la BD se borra la tabla y vuelve a crear llamando al onCreate
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_PRODUCTS);
        onCreate(db);
    }
}
