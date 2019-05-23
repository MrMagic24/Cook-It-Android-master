package com.uiresource.cookit.Database.Test;

import com.google.gson.annotations.SerializedName;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Trasnlation implements Parcelable {
    //@SerializedName("translatedText")
    //public String translatedText;

    @SerializedName("id")
    public String id;

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

    public Trasnlation(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[] { translatedText });
    }

    public static final Parcelable.Creator<Trasnlation> CREATOR = new Parcelable.Creator<Trasnlation>() {

        @Override
        public Trasnlation createFromParcel(Parcel source) {
            return new Trasnlation();
        }

        @Override
        public Trasnlation[] newArray(int size) {
            return new Trasnlation[size];
        }
    };

    public ArrayList<InnerClassName1> items = new ArrayList<InnerClassName1>();
    class InnerClassName1 {
        //String translatedText;

        public String id;

        public String email;

        public String userName;

        public String firstName;

        public String pathToAvatar;

        public String aboutYourself;

        public boolean gender;

        public int recipiesCount;

        public int reviewsCount;

        public int rateReviewsCount;
    }

    public String setResult(){
        return "email: " + items.get(0).email + "\nID: " + items.get(0).id;
    }

    public int getSize(){
        return items.size();
    }
}
