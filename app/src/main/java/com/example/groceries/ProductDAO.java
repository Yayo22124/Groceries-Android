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

    public int updateProduct(String barcode, Product updatedProduct){
        // Conexion a la bd de tipo escritura
        db = dbHelper.getWritableDatabase();
        // nuevos valores
        ContentValues values = new ContentValues();
        // traer los datos nuevos
        values.put(GroseriesContract.Product.COLUMN_NAME_BARCODE, updatedProduct.getBarcode());
        values.put(GroseriesContract.Product.COLUMN_NAME_DESCRIPTION, updatedProduct.getDescription());
        values.put(GroseriesContract.Product.COLUMN_NAME_BRAND, updatedProduct.getBrand());
        values.put(GroseriesContract.Product.COLUMN_NAME_COST, updatedProduct.getCost());
        values.put(GroseriesContract.Product.COLUMN_NAME_PRICE, updatedProduct.getPrice());
        values.put(GroseriesContract.Product.COLUMN_NAME_STOCK, updatedProduct.getStock());

        // Indicar las columnas a cambiar (where)
        String selection = GroseriesContract.Product.COLUMN_NAME_BARCODE + " LIKE ? ";
        String[] selectionArgs = {
            barcode
        };

        int count = db.update(
                GroseriesContract.Product.TABLE_NAME,
                values,
                selection,
                selectionArgs
        );
        return count;

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

    public Product getProductByBarcode(String barcode){
        Product productResult = null;
        // consulta
        String selection = GroseriesContract.Product.COLUMN_NAME_BARCODE + " = ?";
        // Pasar como parámetro el barcode a la consulta
        String[] selectionArgs = {
          barcode
        };
        // Abrir la base de datos
        db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query(
                GroseriesContract.Product.TABLE_NAME,
                null,
                selection,
                selectionArgs,
                null,
                null,
                null
        );

        //Validar si la consulta no devuelve un null
        if (cursor.moveToNext()){
            // guardar la referencia
            productResult = new Product();
            //asignar al objeto las propiedades obtenidas
            productResult.setBarcode(cursor.getString(0));
            productResult.setDescription(cursor.getString(1));
            productResult.setBrand(cursor.getString(2));
            productResult.setCost(cursor.getFloat(3));
            productResult.setPrice(cursor.getFloat(4));
            productResult.setStock(cursor.getInt(5));
        }
        cursor.close();
        return productResult;
    }

}
