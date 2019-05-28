package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    @SerializedName("name")
    public String name;

    @SerializedName("typeFoodId")
    public String typeFoodId;

    public String getName() {
        return name;
    }

    public String getTypeFoodId() {
        return typeFoodId;
    }

    public TypeFood getTypeFood() {
        return typeFood;
    }

    public String getPathToIcon() {
        return pathToIcon;
    }

    public String getId() {
        return id;
    }

    public Ingredient(String name, String typeFoodId, TypeFood typeFood, String pathToIcon, String id) {
        this.name = name;
        this.typeFoodId = typeFoodId;
        this.typeFood = typeFood;
        this.pathToIcon = pathToIcon;
        this.id = id;
    }

    @Embedded(prefix = "typeFood")
    @SerializedName("typeFood")
    public TypeFood typeFood;

    @SerializedName("pathToIcon")
    public String pathToIcon;

    @SerializedName("id")
    @ColumnInfo(name = "idIngredient")
    public String id;
}
