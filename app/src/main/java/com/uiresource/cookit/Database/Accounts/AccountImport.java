package com.uiresource.cookit.Database.Accounts;

import android.os.Parcel;
import android.os.Parcelable;
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

    public static final Creator<AccountImport> CREATOR = new Creator<AccountImport>() {

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

    public void setAccounts(AccountListDao accountListDao){

        for (int i = 0; i < items.size(); i++){
            /*AccountList acc = new AccountList();
            acc.id = (String) items.get(i).id;
            acc.userName = (String) items.get(i).userName;
            acc.pathToAvatar = (String) items.get(i).pathToAvatar;
            acc.aboutYourself = (String) items.get(i).aboutYourself;
            acc.email = (String) items.get(i).email;
            acc.firstName = (String) items.get(i).firstName;
            acc.gender = (boolean) items.get(i).gender;
            acc.rateReviewsCount = (int) items.get(i).rateReviewsCount;
            acc.recipiesCount = (int) items.get(i).recipiesCount;
            acc.reviewsCount = (int) items.get(i).reviewsCount;

            Log.i("GSON", "\nИмя: " + acc.userName + "\nID: " + acc.id);

            accountListDao.insert(acc);*/
        }
    }
}
