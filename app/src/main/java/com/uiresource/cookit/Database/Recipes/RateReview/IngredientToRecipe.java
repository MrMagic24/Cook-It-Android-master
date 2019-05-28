package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class IngredientToRecipe {
    @SerializedName("recipeId")
    public String recipeId;

    @SerializedName("ingredientId")
    public String ingredientId;

    public String getRecipeId() {
        return recipeId;
    }

    public String getIngredientId() {
        return ingredientId;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public IngredientToRecipe(String recipeId, String ingredientId, Ingredient ingredient, int amount, int price, int weight) {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
        this.ingredient = ingredient;
        this.amount = amount;
        this.price = price;
        this.weight = weight;
    }

    @Embedded(prefix = "ingredient")
    @SerializedName("ingredient")
    public Ingredient ingredient;

    @SerializedName("amount")
    public int amount;

    @SerializedName("price")
    public int price;

    @SerializedName("weight")
    public int weight;
}
