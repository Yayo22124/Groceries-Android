package com.example.groceries;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ProductDAO {

    //Comunicación con la base de datos
    private  SQLiteDatabase db;
    private final GroseriesDBHelper dbHelper;
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

    public  ArrayList<String> getAllBarcodes(){
        ArrayList<String> barcodes = new ArrayList<String>();

        // Query to db
        db = dbHelper.getReadableDatabase();
        String[] projection={
            GroseriesContract.Product.COLUMN_NAME_BARCODE
        };

        Cursor cursor = db.query(
          GroseriesContract.Product.TABLE_NAME,
          projection,
          null,
          null,
          null,
                null,
                null
        );
        while(cursor.moveToNext()){
            barcodes.add(cursor.getString(0));
        }
        cursor.close();
        return barcodes;
    }


}
