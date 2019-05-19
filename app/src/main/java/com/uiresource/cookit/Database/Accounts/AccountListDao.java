package com.uiresource.cookit.Database.Accounts;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;
import android.arch.persistence.room.Delete;

import java.util.List;

@Dao
public interface AccountListDao {

    @Query("SELECT * FROM AccountList")
    List<AccountList> getAll();

    @Query("SELECT * FROM AccountList WHERE id = :id")
    AccountList getById(String id);

    @Insert
    void insert(AccountList employee);

    @Update
    void update(AccountList employee);

    @Delete
    void delete(AccountList employee);

    @Query("DELETE FROM AccountList")
    void deleteAll();
}
