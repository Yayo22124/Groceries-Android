package com.example.groceries;

import android.provider.BaseColumns;

public final class GroseriesContract {
    // Método constructor (instanciacion de la clase)
    private  GroseriesContract(){}
    // hacemos herencia multiple a traves de una interface (implements) y estas clases permiten crear tablas en cada clase
    public static class Product implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_NAME_BARCODE = "barcode";
        public static final String  COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_BRAND = "brand";
        public static final String COLUMN_NAME_COST = "cost";
        public static final String COLUMN_NAME_PRICE = "price";
        public static final String COLUMN_NAME_STOCK = "stock";
    }

}
