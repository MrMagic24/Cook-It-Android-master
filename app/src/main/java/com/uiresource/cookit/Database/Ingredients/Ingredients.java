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
    public String id;

    public Ingredients(@NonNull String id, String name, String typeFoodId, String icon, int recipiesCount) {
        this.id = id;
        this.name = name;
        this.typeFoodId = typeFoodId;
        this.icon = icon;
        this.recipiesCount = recipiesCount;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTypeFoodId() {
        return typeFoodId;
    }

    public String getIcon() {
        return icon;
    }

    public int getRecipiesCount() {
        return recipiesCount;
    }

    @SerializedName("name")
    public String name;

    @SerializedName("typeFoodId")
    public String typeFoodId;

    @SerializedName("icon")
    public String icon;

    @SerializedName("recipiesCount")
    public int recipiesCount;
}
