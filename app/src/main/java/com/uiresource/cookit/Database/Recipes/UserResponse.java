package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserResponse {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public String id;

    /*@SerializedName("id")
    public String idServer;*/

    @SerializedName("email")
    public String email;

    @SerializedName("userName")
    public String userName;

    @SerializedName("firstName")
    public String firstName;

    @SerializedName("lastName")
    public String lastName;

    @SerializedName("pathToAvatar")
    public String pathToAvatar;

    @SerializedName("aboutYourself")
    public String aboutYourself;

    @SerializedName("gender")
    public boolean gender;

    @SerializedName("recipiesCount")
    public int recipiesCount;

    @SerializedName("reviewsCount")
    public int reviewsCount;

    @SerializedName("rateReviewsCount")
    public int rateReviewsCount;
}
