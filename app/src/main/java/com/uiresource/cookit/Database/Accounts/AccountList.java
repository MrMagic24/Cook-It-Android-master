package com.uiresource.cookit.Database.Accounts;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity
public class AccountList {

    @PrimaryKey @NonNull
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

    public AccountList(@NonNull String id, String email, String userName, String firstName, String pathToAvatar, String aboutYourself, boolean gender, int recipiesCount, int reviewsCount, int rateReviewsCount) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.pathToAvatar = pathToAvatar;
        this.aboutYourself = aboutYourself;
        this.gender = gender;
        this.recipiesCount = recipiesCount;
        this.reviewsCount = reviewsCount;
        this.rateReviewsCount = rateReviewsCount;
    }

    @NonNull
    public String getId() {
        return id;
    }

    /*public String getIdServer() {
        return idServer;
    }*/

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPathToAvatar() {
        return pathToAvatar;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public boolean isGender() {
        return gender;
    }

    public int getRecipiesCount() {
        return recipiesCount;
    }

    public int getReviewsCount() {
        return reviewsCount;
    }

    public int getRateReviewsCount() {
        return rateReviewsCount;
    }
}


