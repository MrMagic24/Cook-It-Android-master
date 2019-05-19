package com.uiresource.cookit.Database.TypeFoods;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class TypeFoods {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("icon")
    String icon;

    @SerializedName("ingredientsCount")
    int ingredientsCount;
}
