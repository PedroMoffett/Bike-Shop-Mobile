package com.example.dolphinlive.Database;

import android.app.Application;

import com.example.dolphinlive.DAO.PartDAO;
import com.example.dolphinlive.DAO.ProductDAO;
import com.example.dolphinlive.Entities.Part;
import com.example.dolphinlive.Entities.Product;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kotlin.jvm.internal.Intrinsics;

public class Repository {
    private PartDAO mPartDao;
    private ProductDAO mProductDao;
    private List<Product> mAllProducts;
    private List<Part> mAllParts;

    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor= Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application) {
        BicycleDatabaseBuilder db = BicycleDatabaseBuilder.getDatabase(application);
        mPartDao = db.partDAO();
        mProductDao = db.productDAO();

    }

        public List<Product>getAllProducts(){
            databaseExecutor.execute(()->{
                mAllProducts=mProductDao.getallProducts();
            });
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
        }
            return mAllProducts;

        }

    public void insert(Product product) {
    }
}
