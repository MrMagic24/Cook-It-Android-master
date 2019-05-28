package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;
import com.uiresource.cookit.Database.Recipes.Tables.CategoryResponse;
import com.uiresource.cookit.Database.Recipes.Tables.IngredientToRecipeResponse;
import com.uiresource.cookit.Database.Recipes.Tables.ReviewResponse;
import com.uiresource.cookit.Database.Recipes.Tables.StepResponse;
import com.uiresource.cookit.Database.Recipes.Tables.TagsInRecipeResponse;
import com.uiresource.cookit.Database.Recipes.Tables.UserResponse;

import java.util.ArrayList;

@Entity
public class Recipes {

    @PrimaryKey
    @NonNull
    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<ReviewResponse> getReviews() {
        return reviews;
    }

    public ArrayList<CategoryResponse> getCategories() {
        return categories;
    }

    public UserResponse getUser() {
        return user;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getPathToPhotos() {
        return pathToPhotos;
    }

    public String getTimeForCooking() {
        return timeForCooking;
    }

    public String getTimeForPreparetion() {
        return timeForPreparetion;
    }

    public double getRate() {
        return rate;
    }

    public ArrayList<IngredientToRecipeResponse> getIngredients() {
        return ingredients;
    }

    public ArrayList<StepResponse> getSteps() {
        return steps;
    }

    public ArrayList<TagsInRecipeResponse> getTags() {
        return tags;
    }

    @Embedded(prefix = "reviews")
    @SerializedName("reviews")
    ArrayList<ReviewResponse> reviews;

    @Embedded(prefix = "categories")
    @SerializedName("categories")
    ArrayList<CategoryResponse> categories;

    @Embedded(prefix = "user")
    @SerializedName("user")
    UserResponse user;

    @SerializedName("dateCreated")
    String dateCreated;

    @SerializedName("pathToPhotos")
    String pathToPhotos;

    @SerializedName("timeForCooking")
    String timeForCooking;

    @SerializedName("timeForPreparetion")
    String timeForPreparetion;

    @SerializedName("rate")
    double rate;

    @Embedded(prefix = "ingredients")
    @SerializedName("ingredients")
    ArrayList<IngredientToRecipeResponse> ingredients;

    @Embedded(prefix = "steps")
    @SerializedName("steps")
    ArrayList<StepResponse> steps;

    public Recipes(@NonNull String id, String name, String description, ArrayList<ReviewResponse> reviews, ArrayList<CategoryResponse> categories, UserResponse user, String dateCreated, String pathToPhotos, String timeForCooking, String timeForPreparetion, double rate, ArrayList<IngredientToRecipeResponse> ingredients, ArrayList<StepResponse> steps, ArrayList<TagsInRecipeResponse> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.reviews = reviews;
        this.categories = categories;
        this.user = user;
        this.dateCreated = dateCreated;
        this.pathToPhotos = pathToPhotos;
        this.timeForCooking = timeForCooking;
        this.timeForPreparetion = timeForPreparetion;
        this.rate = rate;
        this.ingredients = ingredients;
        this.steps = steps;
        this.tags = tags;
    }

    @Embedded(prefix = "tags")
    @SerializedName("tags")
    ArrayList<TagsInRecipeResponse> tags;
}
