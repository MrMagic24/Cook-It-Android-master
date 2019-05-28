package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class TagsInRecipe {
    @SerializedName("recipeId")
    public String recipeId;

    @SerializedName("tagId")
    public String tagId;

    public String getRecipeId() {
        return recipeId;
    }

    public String getTagId() {
        return tagId;
    }

    public Tag getTag() {
        return tag;
    }

    public TagsInRecipe(String recipeId, String tagId, Tag tag) {
        this.recipeId = recipeId;
        this.tagId = tagId;
        this.tag = tag;
    }

    @Embedded(prefix = "tag")
    @SerializedName("tag")
    public Tag tag;
}
