package com.uiresource.cookit.Database.Categories;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CategoriesListDao {

    @Query("SELECT * FROM CategoriesList")
    List<CategoriesList> getAll();

    @Query("SELECT * FROM CategoriesList WHERE id = :id")
    CategoriesList getById(String id);

    @Insert
    void insert(CategoriesList Acc);

    @Update
    void update(CategoriesList Acc);

    @Delete
    void delete(CategoriesList Acc);

    @Query("DELETE FROM CategoriesList")
    void deleteAll();
}
