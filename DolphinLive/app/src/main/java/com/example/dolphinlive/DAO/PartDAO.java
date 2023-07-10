package com.example.dolphinlive.DAO;

import com.example.dolphinlive.Entities.Part;
import com.example.dolphinlive.Entities.Product;

import java.util.List;

@Dao
public interface PartDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Part part);
    @Update
    void update(Part part);
    @Delete
    void delete(Part part);
    @Query("SELECT * FROM PARTS ORDER BY partID ASC")
    List<Part> getAllParts();

    @Query("SELECT * FROM PARTS WHERE productID = :productID ORDER BY partID ASC")
    List<Part> getAllAssociatedParts(int productID);
}
