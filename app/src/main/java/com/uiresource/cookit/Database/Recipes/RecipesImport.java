package com.uiresource.cookit.Database.Recipes;

import android.arch.persistence.room.Embedded;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;
import com.uiresource.cookit.Database.Recipes.Tables.CategoryResponse;
import com.uiresource.cookit.Database.Recipes.Tables.IngredientToRecipeResponse;
import com.uiresource.cookit.Database.Recipes.Tables.ReviewResponse;
import com.uiresource.cookit.Database.Recipes.Tables.StepResponse;
import com.uiresource.cookit.Database.Recipes.Tables.TagsInRecipeResponse;
import com.uiresource.cookit.Database.Recipes.Tables.UserResponse;

import java.util.ArrayList;

public class RecipesImport implements Parcelable {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("description")
    String description;

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

    @Embedded(prefix = "tags")
    @SerializedName("tags")
    TagsInRecipeResponse tags;

    public RecipesImport(){ }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[] { translatedText });
    }

    public static final Parcelable.Creator<RecipesImport> CREATOR = new Parcelable.Creator<RecipesImport>() {

        @Override
        public RecipesImport createFromParcel(Parcel source) {
            return new RecipesImport();
        }

        @Override
        public RecipesImport[] newArray(int size) {
            return new RecipesImport[size];
        }
    };

    public ArrayList<InnerClassName> items = new ArrayList<InnerClassName>();
    class InnerClassName {

        String id;

        String name;

        String description;

        ArrayList<ReviewResponse> reviews;

        ArrayList<CategoryResponse> categories;

        UserResponse user;

        String dateCreated;

        String pathToPhotos;

        String timeForCooking;

        String timeForPreparetion;

        double rate;

        ArrayList<IngredientToRecipeResponse> ingredients;

        ArrayList<StepResponse> steps;

        ArrayList<TagsInRecipeResponse> tags;
    }

    public String setResult(){
        return "name: " + items.get(0).name + "\nID: " + items.get(0).id + "\n" + items.get(0).timeForPreparetion;
    }

    public int getSize(){
        return items.size();
    }

    public ArrayList<Recipes> getRecipesFromJSON(){

        ArrayList<Recipes> RecipesList = new ArrayList<Recipes>();

        for (int i = 0; i < items.size(); i++){



            Recipes acc = new Recipes(
                    items.get(i).id,
                    items.get(i).name,
                    items.get(i).description,
                    items.get(i).reviews,
                    items.get(i).categories,
                    items.get(i).user,
                    items.get(i).dateCreated,
                    items.get(i).pathToPhotos,
                    items.get(i).timeForCooking,
                    items.get(i).timeForPreparetion,
                    items.get(i).rate,
                    items.get(i).ingredients,
                    items.get(i).steps,
                    items.get(i).tags);

            //if (items.get(i).id == null) {items.set(i, acc)}

            Log.i("GSON", "Рецепт добавлен!");

            RecipesList.add(acc);

            //accountListDao.insert(acc);
        }

        return RecipesList;
    }
}
