package com.uiresource.cookit.Database.RateReviews;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface RateReviewsDao {

    @Query("SELECT * FROM RateReviews")
    List<RateReviews> getAll();

    @Query("SELECT * FROM RateReviews WHERE reviewId = :id")
    RateReviews getById(String id);

    @Insert
    void insert(RateReviews Acc);

    @Update
    void update(RateReviews Acc);

    @Delete
    void delete(RateReviews Acc);

    @Query("DELETE FROM RateReviews")
    void deleteAll();
}
