package com.uiresource.cookit.Database.Tags;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Tags {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("recipiesCount")
    int recipiesCount;
}
