package com.example.groceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class ProductDAO {

    //Comunicación con la base de datos
    private SQLiteDatabase db;
    private GroseriesDBHelper dbHelper;
    public ProductDAO(Context context) {
        dbHelper = new GroseriesDBHelper(context);
    }

    public long insertProduct(Product product) {
        long result = 0;
        db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        // Traer los datos en pares de Product (clave - valor)
        values.put(GroseriesContract.Product.COLUMN_NAME_BARCODE, product.getBarcode());
        values.put(GroseriesContract.Product.COLUMN_NAME_DESCRIPTION, product.getDescription());
        values.put(GroseriesContract.Product.COLUMN_NAME_BRAND, product.getBrand());
        values.put(GroseriesContract.Product.COLUMN_NAME_COST, product.getCost());
        values.put(GroseriesContract.Product.COLUMN_NAME_PRICE, product.getPrice());
        values.put(GroseriesContract.Product.COLUMN_NAME_STOCK, product.getStock());

        result = db.insert(GroseriesContract.Product.TABLE_NAME, null, values);

        return  result;
    }
}
