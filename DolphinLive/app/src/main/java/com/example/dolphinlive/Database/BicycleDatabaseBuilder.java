package com.example.dolphinlive.Database;

import android.content.Context;

import com.example.dolphinlive.DAO.PartDAO;
import com.example.dolphinlive.DAO.ProductDAO;
import com.example.dolphinlive.Entities.Part;
import com.example.dolphinlive.Entities.Product;

@Database(entities = {Product.class, Part.class}, version=1, exportSchema = false)
public class BicycleDatabaseBuilder extends RoomDatabase{
    public abstract ProductDAO productDAO();
    public abstract PartDAO partDAO();

    private static volatile BicycleDatabaseBuilder INSTANCE;

    static BicycleDatabaseBuilder getDatabase(final Context context){
        if(INSTANCE==null) {
            synchronized (BicycleDatabaseBuilder.class){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),BicycleDatabaseBuilder.class, "MyBicycleDatabase.db")
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries
                            .build();
                }
            }

        }
        return INSTANCE;
    }
}
