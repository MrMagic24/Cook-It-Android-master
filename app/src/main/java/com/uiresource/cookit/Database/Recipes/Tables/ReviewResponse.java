package com.uiresource.cookit.Database.Recipes.Tables;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;
import com.uiresource.cookit.Database.Recipes.RateReview.RateReview;

import java.util.ArrayList;

public class ReviewResponse {

    @SerializedName("id")
    String id;

    @SerializedName("recipeId")
    String recipeId;

    @Embedded(prefix = "author")
    @SerializedName("author")
    UserResponse author;

    @Embedded(prefix = "rateReviews")
    @SerializedName("rateReviews")
    ArrayList<RateReview> rateReviews;

    @SerializedName("text")
    String text;

    @SerializedName("dateCreated")
    String dateCreated;

    public String getId() {
        return id;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public UserResponse getAuthor() {
        return author;
    }

    public String getText() {
        return text;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public ArrayList<RateReview> getRateReviews() {
        return rateReviews;
    }

    public int getLikes() {
        return likes;
    }

    public int getDisLikes() {
        return disLikes;
    }

    @SerializedName("likes")
    int likes;

    @SerializedName("disLikes")
    int disLikes;

    public ReviewResponse(String id, String recipeId, UserResponse author, String text, String dateCreated, ArrayList<RateReview> rateReviews, int likes, int disLikes) {
        this.id = id;
        this.recipeId = recipeId;
        this.author = author;
        this.text = text;
        this.dateCreated = dateCreated;
        this.rateReviews = rateReviews;
        this.likes = likes;
        this.disLikes = disLikes;
    }
}
