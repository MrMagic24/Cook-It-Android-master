package com.uiresource.cookit.Database.Recipes;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RecipesDao {

    @Query("SELECT * FROM Recipes")
    List<Recipes> getAll();

    @Query("SELECT * FROM Recipes")
    LiveData<List<Recipes>> getAllRecipes();

    @Query("SELECT * FROM Recipes WHERE id = :id")
    Recipes getById(String id);

    @Insert
    void insert(Recipes Acc);

    @Update
    void update(Recipes Acc);

    @Delete
    void delete(Recipes Acc);

    @Query("DELETE FROM Recipes")
    void deleteAll();
}
