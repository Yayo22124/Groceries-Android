package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Traer los valores del EditText
        EditText txtBarcode = findViewById(R.id.txt_barcode);
        EditText txtDescription = findViewById(R.id.txt_description);
        EditText txtBrand = findViewById(R.id.txt_brand);
        EditText txtCost = findViewById(R.id.txt_cost);
        EditText txtPrice = findViewById(R.id.txt_price);
        EditText txtStock = findViewById(R.id.txt_stock);

        Button btnSave = findViewById(R.id.btn_save);
        ListView lvProducts = findViewById(R.id.lv_products);

        //Product DAO
        ProductDAO productDAO = new ProductDAO(this);


        //Listener
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Product
                Product product = new Product();
                product.setBarcode(txtBarcode.getText().toString());
                product.setDescription(txtDescription.getText().toString());
                product.setBrand(txtBrand.getText().toString());
                product.setCost(Float.parseFloat(txtCost.getText().toString()));
                product.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                product.setStock(Integer.parseInt(txtStock.getText().toString()));
                
                if (productDAO.insertProduct(product) != -1){
                    Toast.makeText(MainActivity.this, "Producto Insertado Exítosamente", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Servicio no disponible, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}