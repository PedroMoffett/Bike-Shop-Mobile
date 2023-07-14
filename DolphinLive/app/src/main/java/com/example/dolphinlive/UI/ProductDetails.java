package com.example.dolphinlive.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dolphinlive.Database.Repository;
import com.example.dolphinlive.Entities.Part;
import com.example.dolphinlive.Entities.Product;
import com.example.dolphinlive.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {
    EditText editName;
    EditText editPrice;
    String name;
    double price;
    int id;
    Product product;
    Product currentProduct;
    int numParts;
    Repository repository;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        editName = findViewById(R.id.productname);
        editPrice = findViewById(R.id.productprice);
        id=getIntent().getIntExtra("id",-1);
        name = getIntent().getStringExtra("name");
        price = getIntent().getDoubleExtra("price", -1.0);
        editName.setText(name);
        editPrice.setText(Double.toString(price));
        repository=new Repository(getApplication());
        RecyclerView recyclerView = findViewById(R.id.partrecyclerview);
        repository = new Repository(getApplication());
        final PartAdapter partAdapter = new PartAdapter(this);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Part> filteredParts = new ArrayList<>();
        for (Part p : repository.getAllParts()) {
            if (p.getProductID() == id) filteredParts.add(p);
        }
        partAdapter.setParts(filteredParts);
        Button button=findViewById(R.id.saveproduct);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id==-1){
                    product=new Product(0,editName.getText().toString(),Double.parseDouble(editPrice.getText().toString()));
                    repository.insert(product);

                }
                else{
                    product=new Product(id,editName.getText().toString(),Double.parseDouble(editPrice.getText().toString()));
                    repository.update(product);

                }
            }
        });
        FloatingActionButton fab = findViewById(R.id.floatingActionButton2);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetails.this, PartDetails.class);
                intent.putExtra("prodID",id);
                startActivity(intent);
            }
        });

    }
    @Override
    protected void onResume() {

        super.onResume();
        RecyclerView recyclerView = findViewById(R.id.partrecyclerview);
        final PartAdapter partAdapter = new PartAdapter(this);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Part> filteredParts = new ArrayList<>();
        for (Part p : repository.getAllParts()) {
            if (p.getProductID() == id) filteredParts.add(p);
        }
        partAdapter.setParts(filteredParts);

        //Toast.makeText(ProductDetails.this,"refresh list",Toast.LENGTH_LONG).show();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deletepart, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.deleteproduct:
                for (Product prod : repository.getAllProducts()) {
                    if (prod.getProductID() == id) currentProduct = prod;
                }

                numParts = 0;
                for (Part part : repository.getAllParts()) {
                    if (part.getProductID() == id) ++numParts;
                }

                if (numParts == 0) {
                    repository.delete(currentProduct);
                    Toast.makeText(ProductDetails.this, currentProduct.getProductName() + " was deleted", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(ProductDetails.this, "Can't delete a product with parts", Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}