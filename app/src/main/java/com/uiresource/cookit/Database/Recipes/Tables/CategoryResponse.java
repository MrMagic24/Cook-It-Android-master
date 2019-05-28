package com.uiresource.cookit.Database.Recipes.Tables;

import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class CategoryResponse {
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public int getRecipiesCount() {
        return recipiesCount;
    }

    public int getCategoriesCount() {
        return categoriesCount;
    }

    public CategoryResponse(String id, String name, String description, String parentCategoryId, String pathToIcon, int recipiesCount, int categoriesCount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
        this.pathToIcon = pathToIcon;
        this.recipiesCount = recipiesCount;
        this.categoriesCount = categoriesCount;
    }

    @SerializedName("parentCategoryId")
    String parentCategoryId;

    @SerializedName("pathToIcon")
    String pathToIcon;

    @SerializedName("recipiesCount")
    int recipiesCount;

    @SerializedName("categoriesCount")
    int categoriesCount;
}
