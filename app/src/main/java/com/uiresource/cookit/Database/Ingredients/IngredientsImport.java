package com.uiresource.cookit.Database.Ingredients;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class IngredientsImport implements Parcelable {
    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("typeFoodId")
    public String typeFoodId;

    @SerializedName("pathToIcon")
    public String pathToIcon;

    @SerializedName("recipiesCount")
    public int recipiesCount;

    public IngredientsImport(){ }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[] { translatedText });
    }

    public static final Parcelable.Creator<IngredientsImport> CREATOR = new Parcelable.Creator<IngredientsImport>() {

        @Override
        public IngredientsImport createFromParcel(Parcel source) {
            return new IngredientsImport();
        }

        @Override
        public IngredientsImport[] newArray(int size) {
            return new IngredientsImport[size];
        }
    };

    public ArrayList<InnerClassName> items = new ArrayList<InnerClassName>();
    class InnerClassName {

        public String id;

        public String name;

        public String typeFoodId;

        public String pathToIcon;

        public int recipiesCount;
    }

    public String setResult(){
        return "email: " + items.get(0).id + "\nID: " + items.get(0).name + "\n" + items.get(0).recipiesCount;
    }

    public int getSize(){
        return items.size();
    }

    public ArrayList<Ingredients> getIngredientsFromJSON(){

        ArrayList<Ingredients> IngList = new ArrayList<Ingredients>();

        for (int i = 0; i < items.size(); i++){
            Ingredients acc = new Ingredients(
                    items.get(i).id,
                    items.get(i).name,
                    items.get(i).typeFoodId,
                    items.get(i).pathToIcon,
                    items.get(i).recipiesCount);

            Log.i("GSON", "Ингредиент добавлен!");
            IngList.add(acc);
        }

        return IngList;
    }
}
