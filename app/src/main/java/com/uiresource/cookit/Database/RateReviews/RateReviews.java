package com.uiresource.cookit.Database.RateReviews;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class RateReviews {

    @PrimaryKey
    @NonNull
    @SerializedName("reviewId")
    String reviewId;

    @SerializedName("userWhoGiveMarkId")
    String userWhoGiveMarkId;

    @SerializedName("isCool")
    boolean isCool;
}
