package com.uiresource.cookit.Database.Ingredients;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface IngredientsDao {

    @Query("SELECT * FROM Ingredients")
    List<Ingredients> getAll();

    @Query("SELECT * FROM Ingredients WHERE id = :id")
    Ingredients getById(String id);

    @Insert
    void insert(Ingredients Acc);

    @Update
    void update(Ingredients Acc);

    @Delete
    void delete(Ingredients Acc);

    @Query("DELETE FROM Ingredients")
    void deleteAll();
}
