package com.uiresource.cookit.Database.Ingredients;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Ingredients {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("typeFoodId")
    String typeFoodId;

    @SerializedName("pathToIcon")
    String pathToIcon;

    @SerializedName("recipiesCount")
    int recipiesCount;
}
