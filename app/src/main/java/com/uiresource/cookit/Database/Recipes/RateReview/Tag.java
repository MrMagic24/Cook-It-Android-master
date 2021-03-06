package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;

import com.google.gson.annotations.SerializedName;

public class Tag {
    @SerializedName("name")
    public String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public Tag(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @SerializedName("id")
    @ColumnInfo(name = "idTag")
    public String id;
}
