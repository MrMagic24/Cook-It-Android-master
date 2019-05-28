package com.uiresource.cookit.Database.Recipes.Tables;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class IngredientToRecipeResponse {

    @SerializedName("ingredientId")
    public String ingredientId;

    @SerializedName("amount")
    public int amount;

    @SerializedName("price")
    public int price;

    @SerializedName("weight")
    public int weight;

    public IngredientToRecipeResponse(String ingredientId, int amount, int price, int weight) {
        this.ingredientId = ingredientId;
        this.amount = amount;
        this.price = price;
        this.weight = weight;
    }
}
