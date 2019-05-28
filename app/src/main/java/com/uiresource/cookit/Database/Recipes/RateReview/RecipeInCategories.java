package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class RecipeInCategories {
    @SerializedName("recipeId")
    public String recipeId;

    /*@SerializedName("id")
    public String idServer;*/

    public String getRecipeId() {
        return recipeId;
    }

    public Category getCategory() {
        return category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public RecipeInCategories(String recipeId, Category category, int categoryId) {
        this.recipeId = recipeId;
        this.category = category;
        this.categoryId = categoryId;
    }

    @Embedded(prefix = "category")
    @SerializedName("category")
    public Category category;

    @SerializedName("categoryId")
    public int categoryId;
}
