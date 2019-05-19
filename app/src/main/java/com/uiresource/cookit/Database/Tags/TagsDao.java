package com.uiresource.cookit.Database.Tags;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface TagsDao {

    @Query("SELECT * FROM Tags")
    List<Tags> getAll();

    @Query("SELECT * FROM Tags WHERE id = :id")
    Tags getById(String id);

    @Insert
    void insert(Tags Acc);

    @Update
    void update(Tags Acc);

    @Delete
    void delete(Tags Acc);

    @Query("DELETE FROM Tags")
    void deleteAll();
}
