package com.example.groceries;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Traer los valores del EditText
    EditText txtBarcode, txtDescription, txtBrand, txtCost, txtPrice, txtStock;

    Button btnSave;
    ListView lvProducts;
    //Product DAO
    ProductDAO productDAO = new ProductDAO(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtBarcode = findViewById(R.id.txt_barcode);
        txtDescription = findViewById(R.id.txt_description);
        txtBrand = findViewById(R.id.txt_brand);
        txtCost = findViewById(R.id.txt_cost);
        txtPrice = findViewById(R.id.txt_price);
        txtStock = findViewById(R.id.txt_stock);

        btnSave = findViewById(R.id.btn_save);
        lvProducts = findViewById(R.id.lv_products);

        updateList();

        lvProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String barcode = lvProducts.getItemAtPosition(position).toString();

                Intent intent = new Intent(MainActivity.this,ProductDetail.class);
                intent.putExtra("barcode", barcode);
                startActivity(intent);
            }
        });

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
                    updateList();
                    clearFields();
                } else {
                    Toast.makeText(MainActivity.this, "Servicio no disponible, vuelva a intentarlo", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    protected  void updateList(){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                productDAO.getAllBarcodes()
        );

        lvProducts.setAdapter(adapter);
    }

    public void clearFields(){
        txtBarcode.requestFocus();
        txtBarcode.setText("");
        txtDescription.setText("");
        txtStock.setText("");
        txtBarcode.setText("");
        txtPrice.setText("");
        txtCost.setText("");
        txtBrand.setText("");
    }
}