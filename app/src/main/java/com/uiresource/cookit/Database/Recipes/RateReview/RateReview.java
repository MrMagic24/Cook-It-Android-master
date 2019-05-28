package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;

import com.google.gson.annotations.SerializedName;

public class RateReview {
    @SerializedName("reviewId")
    String reviewId;

    @SerializedName("userId")
    String userId;

    @Embedded(prefix = "user")
    @SerializedName("user")
    UserReview user;

    public RateReview(String reviewId, String userId, UserReview user, Review review, boolean isCool) {
        this.reviewId = reviewId;
        this.userId = userId;
        this.user = user;
        this.review = review;
        this.isCool = isCool;
    }

    @Embedded(prefix = "review")
    @SerializedName("review")
    Review review;

    @SerializedName("isCool")
    boolean isCool;

    public String getReviewId() {
        return reviewId;
    }

    public String getUserId() {
        return userId;
    }

    public UserReview getUser() {
        return user;
    }

    public Review getReview() {
        return review;
    }

    public boolean isCool() {
        return isCool;
    }
}
