package com.uiresource.cookit.Database.Recipes.RateReview;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Recipe {

    @SerializedName("name")
    public String name;

    @SerializedName("description")
    public String description;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RecipeInCategories getCategories() {
        return categories;
    }

    public String getUserId() {
        return userId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getPathToPhotos() {
        return pathToPhotos;
    }

    public TagsInRecipe getTags() {
        return tags;
    }

    public String getTimeForCooking() {
        return timeForCooking;
    }

    public String getTimeForPreparetion() {
        return timeForPreparetion;
    }

    public IngredientToRecipe getIngredients() {
        return ingredients;
    }

    public Step getSteps() {
        return steps;
    }

    public String getId() {
        return id;
    }

    public Recipe(String name, String description, RecipeInCategories categories, String userId, String dateCreated, String pathToPhotos, TagsInRecipe tags, String timeForCooking, String timeForPreparetion, IngredientToRecipe ingredients, Step steps, String id) {
        this.name = name;
        this.description = description;
        this.categories = categories;
        this.userId = userId;
        this.dateCreated = dateCreated;
        this.pathToPhotos = pathToPhotos;
        this.tags = tags;
        this.timeForCooking = timeForCooking;
        this.timeForPreparetion = timeForPreparetion;
        this.ingredients = ingredients;
        this.steps = steps;
        this.id = id;
    }

    @Embedded(prefix = "categories")
    @SerializedName("categories")
    public RecipeInCategories categories;

    @SerializedName("userId")
    public String userId;

    @SerializedName("dateCreated")
    public String dateCreated;

    @SerializedName("pathToPhotos")
    public String pathToPhotos;

    @Embedded(prefix = "tags")
    @SerializedName("tags")
    public TagsInRecipe tags;

    @SerializedName("timeForCooking")
    public String timeForCooking;

    @SerializedName("timeForPreparetion")
    public String timeForPreparetion;

    @Embedded(prefix = "ingredients")
    @SerializedName("ingredients")
    public IngredientToRecipe ingredients;

    @Embedded(prefix = "steps")
    @SerializedName("steps")
    public Step steps;

    @SerializedName("id")
    @ColumnInfo(name = "idRecipe")
    public String id;
}
