package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Ingredients {
    @PrimaryKey
    @NonNull
    @SerializedName("ingredientId")
    public String ingredientId;

    /*@SerializedName("id")
    public String idServer;*/

    @SerializedName("amount")
    public int amount;

    @SerializedName("price")
    public int price;

    @SerializedName("weight")
    public int weight;
}
