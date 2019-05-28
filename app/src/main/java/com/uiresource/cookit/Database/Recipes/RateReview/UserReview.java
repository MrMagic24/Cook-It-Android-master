package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UserReview {
    @SerializedName("id")
    @ColumnInfo(name = "idUser")
    public String id;

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

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
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

    public UserReview(String id, String email, String userName, String firstName, String lastName, String pathToAvatar, String aboutYourself, boolean gender) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pathToAvatar = pathToAvatar;
        this.aboutYourself = aboutYourself;
        this.gender = gender;
    }
}
