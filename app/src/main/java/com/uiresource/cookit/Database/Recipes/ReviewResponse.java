package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.uiresource.cookit.Database.Recipes.RateReview.RateReview;

public class ReviewResponse {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("recipeId")
    String recipeId;

    @SerializedName("author")
    UserResponse author;

    @SerializedName("text")
    String text;

    @SerializedName("dateCreated")
    String dateCreated;


    @SerializedName("rate")
    int rate;

    @SerializedName("pathToPhotos")
    String pathToPhotos;

    @SerializedName("rateReviews")
    RateReview rateReviews;

    @SerializedName("likes")
    int likes;

    @SerializedName("disLikes")
    int disLikes;
}
