package com.uiresource.cookit.Database.TypeFoods;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TypeFoodsDao {

    @Query("SELECT * FROM TypeFoods")
    List<TypeFoods> getAll();

    @Query("SELECT * FROM TypeFoods WHERE id = :id")
    TypeFoods getById(String id);

    @Insert
    void insert(TypeFoods Acc);

    @Update
    void update(TypeFoods Acc);

    @Delete
    void delete(TypeFoods Acc);

    @Query("DELETE FROM TypeFoods")
    void deleteAll();
}
