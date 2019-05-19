package com.uiresource.cookit.Database.Reviews;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class Reviews {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("recipeId")
    String recipeId;

    //AccountList author;

    @SerializedName("text")
    String text;

    /*@SerializedName("dateCreated")
    Date dateCreated;
*/
    @SerializedName("rate")
    int rate;

    @SerializedName("pathToPhotos")
    String pathToPhotos;
}
