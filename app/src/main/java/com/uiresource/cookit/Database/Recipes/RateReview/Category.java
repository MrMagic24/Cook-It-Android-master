package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Category {
    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDescriptrion() {
        return descriptrion;
    }

    public int getPathToIcon() {
        return pathToIcon;
    }

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public Category(@NonNull String id, String name, int descriptrion, int pathToIcon, int parentCategoryId) {
        this.id = id;
        this.name = name;
        this.descriptrion = descriptrion;
        this.pathToIcon = pathToIcon;
        this.parentCategoryId = parentCategoryId;
    }

    @SerializedName("id")
    @ColumnInfo(name = "idCategory")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("descriptrion")
    public int descriptrion;

    @SerializedName("pathToIcon")
    public int pathToIcon;

    @SerializedName("parentCategoryId")
    public int parentCategoryId;
}
