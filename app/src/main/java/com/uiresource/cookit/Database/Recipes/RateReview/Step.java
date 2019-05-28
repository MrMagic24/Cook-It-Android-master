package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;

public class Step {

    @ColumnInfo(name = "idStep")
    String id;

    int numberStep;

    String description;

    String recipeId;

    public String getId() {
        return id;
    }

    public int getNumberStep() {
        return numberStep;
    }

    public String getDescription() {
        return description;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public Step(String id, int numberStep, String description, String recipeId) {
        this.id = id;
        this.numberStep = numberStep;
        this.description = description;
        this.recipeId = recipeId;
    }
}
