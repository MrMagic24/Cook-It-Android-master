package com.uiresource.cookit.Database.Categories;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class CategoriesList {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @SerializedName("parentCategory")
    String parentCategory;

    @SerializedName("pathToIcon")
    String pathToIcon;

    @SerializedName("recipiesCount")
    int recipiesCount;
}
