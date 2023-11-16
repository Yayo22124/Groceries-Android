package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductDetail extends AppCompatActivity {
    EditText txtBarcode, txtDescription, txtBrand, txtCost, txtPrice, txtStock;

    Button btnUpdate, btnDelete;

    ProductDAO productDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        txtBarcode=findViewById(R.id.txt_barcode);
        txtDescription=findViewById(R.id.txt_description);
        txtBrand=findViewById(R.id.txt_brand);
        txtCost=findViewById(R.id.txt_cost);
        txtStock=findViewById(R.id.txt_stock);
        txtPrice=findViewById(R.id.txt_price);

        btnDelete=findViewById(R.id.btn_delete);
        btnUpdate=findViewById(R.id.btn_update);

        // Recuperar el barcode
        String barcode = getIntent().getStringExtra("barcode");

        productDAO =new  ProductDAO(this);

        // llamar metodo
        getProductByBarcode(barcode);

        // actualizar
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String barcode = String.valueOf(txtBarcode.getText());
                // Updated Product
                Product productUpdated = new Product();
                productUpdated.setBarcode(txtBarcode.getText().toString());
                productUpdated.setDescription(txtDescription.getText().toString());
                productUpdated.setBrand(txtBrand.getText().toString());
                productUpdated.setCost(Float.parseFloat(txtCost.getText().toString()));
                productUpdated.setPrice(Float.parseFloat(txtPrice.getText().toString()));
                productUpdated.setStock(Integer.parseInt(txtStock.getText().toString()));


                if (productDAO.updateProduct(barcode, productUpdated) > 0){
                    Toast.makeText(ProductDetail.this, "Producto " + barcode + " actualizado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ProductDetail.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(ProductDetail.this, "Servicio fallando intente de nuevo  más tarde.", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    protected void getProductByBarcode(String barcode) {
        Product productResult = productDAO.getProductByBarcode(barcode);
        if (productResult!=null){
            txtBarcode.setText(productResult.getBarcode());
            txtDescription.setText(productResult.getDescription());
            txtCost.setText(String.valueOf(productResult.getCost()));
            txtPrice.setText(String.valueOf(productResult.getPrice()));
            txtBrand.setText(productResult.getBrand());
            txtStock.setText(String.valueOf(productResult.getStock()));
        }
    }
}