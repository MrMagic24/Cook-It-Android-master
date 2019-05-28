package com.uiresource.cookit.Database.Recipes.Tables;

import com.google.gson.annotations.SerializedName;

public class StepResponse {
    @SerializedName("numberStep")
    int numberStep;

    @SerializedName("description")
    String description;

    public int getNumberStep() {
        return numberStep;
    }

    public String getDescription() {
        return description;
    }

    public StepResponse(int numberStep, String description) {
        this.numberStep = numberStep;
        this.description = description;
    }
}
