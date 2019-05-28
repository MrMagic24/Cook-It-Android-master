package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("recipeId")
    public String recipeId;

    @Embedded(prefix = "recipe")
    @SerializedName("recipe")
    public Recipe recipe;

    public String getRecipeId() {
        return recipeId;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public UserReview getUser() {
        return user;
    }

    public String getUserId() {
        return userId;
    }

    public String getText() {
        return text;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public int getRate() {
        return rate;
    }

    public String getPathToPhotos() {
        return pathToPhotos;
    }

    public int getLikes() {
        return likes;
    }

    public int getDisLikes() {
        return disLikes;
    }

    public String getId() {
        return id;
    }

    public Review(String recipeId, Recipe recipe, UserReview user, String userId, String text, String dateCreated, int rate, String pathToPhotos, int likes, int disLikes, String id) {
        this.recipeId = recipeId;
        this.recipe = recipe;
        this.user = user;
        this.userId = userId;
        this.text = text;
        this.dateCreated = dateCreated;
        this.rate = rate;
        this.pathToPhotos = pathToPhotos;
        this.likes = likes;
        this.disLikes = disLikes;
        this.id = id;
    }

    @Embedded(prefix = "user")
    @SerializedName("user")
    public UserReview user;

    @SerializedName("userId")
    public String userId;

    @SerializedName("text")
    public String text;

    @SerializedName("dateCreated")
    public String dateCreated;

    @SerializedName("rate")
    public int rate;

    @SerializedName("pathToPhotos")
    public String pathToPhotos;

    @SerializedName("likes")
    public int likes;

    @SerializedName("disLikes")
    public int disLikes;

    @SerializedName("id")
    @ColumnInfo(name = "idReview")
    public String id;
}
