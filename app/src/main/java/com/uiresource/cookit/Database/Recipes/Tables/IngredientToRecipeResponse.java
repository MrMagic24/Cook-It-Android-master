package com.uiresource.cookit.Database.Recipes.Tables;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class IngredientToRecipeResponse {

    @SerializedName("ingredientId")
    public String ingredientId;

    @SerializedName("amount")
    public String amount;

    public IngredientToRecipeResponse(String ingredientId, String amount) {
        this.ingredientId = ingredientId;
        this.amount = amount;
    }
}
