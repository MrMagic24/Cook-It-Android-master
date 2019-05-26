package com.uiresource.cookit.Database;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountAdapter;
import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.*;
import com.uiresource.cookit.Database.Accounts.AccountRepository;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsImport;
import com.uiresource.cookit.Database.Test.Trasnlation;
import com.uiresource.cookit.R;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.constraint.Constraints.TAG;
import static java.lang.Thread.*;

public class ImportFromJSON {

    private static String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static String response;
    private static ArrayList<AccountList> ListAcc;
    private static ArrayList<Ingredients> IngAcc;

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    public static ArrayList<AccountList> AccountGetList(){

        if (ListAcc != null){
            ListAcc = null;
        }

        getJSON("Account/GetList");

        while (ListAcc == null){
            try {
                sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("GSON", "Вышел из метода AccountGetList");
        return ListAcc;
    }

    public static ArrayList<Ingredients> IngredientsGetList(){

        if (IngAcc != null){
            IngAcc = null;
        }

        getJSON("Recipes/GetList");

        while (IngAcc == null){
            try {
                sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("GSON", "Вышел из метода IngredientsGetList");
        return IngAcc;
    }

    private static void getJSON(String path){
        response = URL + path;

        okHttpClient = new OkHttpClient();
        request = new Request.Builder().url(response).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();

                    final IngredientsImport acc = gson.fromJson(myResponse, IngredientsImport.class);

                    IngAcc = acc.getIngredientsFromJSON();
                    Log.i("GSON", "Присвоил IngAcc");
                }
            }
        });
    }

    public static void AccountRegisterToServer(AccountList account){

        RegisterUser registerUser = new RegisterUser(account.email, "AaT1234.", "AaT1234.");

        Log.i("GSON", "Вызван метод AccountExportToServer");

        okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(registerUser));

        final Request request = new Request.Builder()
                .url(URL + "Account/Register")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "text/plain")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("GSON",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("GSON", result);
            }
        });

        Log.i("GSON", "Завершен метод AccountExportToServer");
    }

    public static void AccountLoginToServer(AccountList account){

        LoginUser loginUser = new LoginUser(account.email, "AaT1234.", true);

        Log.i("GSON", "Вызван метод AccountLoginToServer");

        okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(loginUser));

        final Request request = new Request.Builder()
                .url(URL + "Account/Login")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "text/plain")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("GSON",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("GSON", result);
            }
        });

        Log.i("GSON", "Завершен метод AccountLoginToServer");
    }

    public static void AccountUpdateToServer(AccountList account){

        Log.i("GSON", "Вызван метод AccountUpdateToServer");

        okHttpClient = new OkHttpClient();

        JSONObject postdata = new JSONObject();
        try {
            postdata.put("firstName", "Ultra Pacan");
            postdata.put("lastName", "Mega Pacan");
            postdata.put("gender", account.gender);
            postdata.put("aboutYourself", "asdsdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd");
            postdata.put("avatar", JSONObject.NULL);

        } catch(JSONException e){
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Log.i("GSON", postdata.toString());
        RequestBody body = RequestBody.create(MEDIA_TYPE,
                postdata.toString());

        final Request request = new Request.Builder()
                .url(URL + "Account/UpdateUser")
                .post(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "text/plain")
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("GSON",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i("GSON", result);
            }
        });

        Log.i("GSON", "Завершен метод AccountUpdateToServer");
    }

    public static class RegisterUser {
        String email;
        String password;
        String passwordConfirm;

        public RegisterUser(String email, String password, String passwordConfirm) {
            this.email = email;
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }
    }

    public static class LoginUser {
        String email;
        String password;
        boolean rememberMe;

        public LoginUser(String email, String password, boolean rememberMe) {
            this.email = email;
            this.password = password;
            this.rememberMe = rememberMe;
        }
    }

    /*public static class UpdateUser {
        String firstName;
        String lastName;
        boolean gender;
        String aboutYourself;
        avatar avatar;

        public UpdateUser(String firstName, String lastName, boolean gender, String aboutYourself, avatar avatar) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.gender = gender;
            this.aboutYourself = aboutYourself;
            this.avatar = avatar;
        }
    }

    public static class avatar {

        public avatar() {
        }
    }*/
}
