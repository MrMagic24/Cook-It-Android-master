package com.uiresource.cookit.Database.Recipes.Tables;

import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserResponse {

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

    public UserResponse(@NonNull String id, String email, String userName, String firstName, String lastName, String pathToAvatar, String aboutYourself, boolean gender, int recipiesCount, int reviewsCount, int rateReviewsCount) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pathToAvatar = pathToAvatar;
        this.aboutYourself = aboutYourself;
        this.gender = gender;
        this.recipiesCount = recipiesCount;
        this.reviewsCount = reviewsCount;
        this.rateReviewsCount = rateReviewsCount;
    }
}
