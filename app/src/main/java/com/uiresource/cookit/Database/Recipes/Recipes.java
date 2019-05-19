package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Recipes {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("categoriesCount")
    int categoriesCount;

    @SerializedName("reviewsCount")
    int reviewsCount;

   /* @SerializedName("user")
    AccountList user;

    @SerializedName("dateCreated")
    Date dateCreated;*/

    @SerializedName("pathToPhotos")
    String pathToPhotos;

    @SerializedName("timeForCooking")
    String timeForCooking;

    @SerializedName("timeForPreparetion")
    String timeForPreparetion;

    @SerializedName("rate")
    int rate;

    /*@SerializedName("ingredients")
    Ingredients ingredients;

    @SerializedName("steps")
    Step steps;

    @SerializedName("tags")
    Tags tags;*/
}
