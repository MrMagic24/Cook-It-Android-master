package com.uiresource.cookit.Database.Recipes;

import com.google.gson.annotations.SerializedName;

public class TagsInRecipeResponse {
    public TagsInRecipeResponse(String tag) {
        this.tag = tag;
    }

    @SerializedName("tag")
    String tag;
}
