package com.example.dolphinlive.UI;



import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dolphinlive.Database.Repository;
import com.example.dolphinlive.Entities.Product;
import com.example.dolphinlive.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductList extends AppCompatActivity {
    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_product_list);
        RecyclerView recyclerView = findViewById(id.productrecyclerview);
        final ProductAdapter productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository = new Repository(getApplication());
        List<Product> allProducts = repository.getAllProducts();
        FloatingActionButton fab = findViewById(id.floatingActionButton);
        productAdapter.setProducts(allProducts);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductList.this, ProductDetails.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        List<Product> allProducts = repository.getAllProducts();
        RecyclerView recyclerView = findViewById(R.id.productrecyclerview);
        final ProductAdapter productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter.setProducts(allProducts);

    }
}