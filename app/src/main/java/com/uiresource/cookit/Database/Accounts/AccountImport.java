package com.uiresource.cookit.Database.Accounts;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AccountImport implements Parcelable {

    @SerializedName("id")
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

    @SerializedName("recipiesCount")
    public int recipiesCount;

    @SerializedName("reviewsCount")
    public int reviewsCount;

    @SerializedName("rateReviewsCount")
    public int rateReviewsCount;

    public AccountImport(){

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        //dest.writeStringArray(new String[] { translatedText });
    }

    public static final Parcelable.Creator<AccountImport> CREATOR = new Parcelable.Creator<AccountImport>() {

        @Override
        public AccountImport createFromParcel(Parcel source) {
            return new AccountImport();
        }

        @Override
        public AccountImport[] newArray(int size) {
            return new AccountImport[size];
        }
    };

    public ArrayList<InnerClassName> items = new ArrayList<InnerClassName>();
    class InnerClassName {
        //String translatedText;
        @NonNull
        public String id;
        @NonNull
        public String email;
        @NonNull
        public String userName;
        @NonNull
        public String firstName;
        @NonNull
        public String lastName;
        @NonNull
        public String pathToAvatar;
        @NonNull
        public String aboutYourself;

        public boolean gender;

        public int recipiesCount;

        public int reviewsCount;

        public int rateReviewsCount;
    }

    public String setResult(){
        return "email: " + items.get(0).email + "\nID: " + items.get(0).id + "\n" + items.get(0).lastName;
    }

    public int getSize(){
        return items.size();
    }

    public ArrayList<AccountList> getAccountsFromJSON(){

        ArrayList<AccountList> AccList = new ArrayList<AccountList>();

        for (int i = 0; i < items.size(); i++){



            AccountList acc = new AccountList(
                    items.get(i).id,
                    items.get(i).email,
                    items.get(i).userName,
                    items.get(i).firstName,
                    items.get(i).pathToAvatar,
                    items.get(i).aboutYourself,
                    items.get(i).gender,
                    items.get(i).recipiesCount,
                    items.get(i).reviewsCount,
                    items.get(i).rateReviewsCount);

            //if (items.get(i).id == null) {items.set(i, acc)}

            Log.i("GSON", "Аккаунт добавлен!");

            AccList.add(acc);

            //accountListDao.insert(acc);
        }

        return AccList;
    }
}
