package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class TypeFood {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public String getId() {
        return id;
    }

    public TypeFood(String name, String pathToIcon, String id) {
        this.name = name;
        this.pathToIcon = pathToIcon;
        this.id = id;
    }

    @SerializedName("pathToIcon")
    public String pathToIcon;

    @SerializedName("id")
    @ColumnInfo(name = "idTypeFood")
    public String id;
}
