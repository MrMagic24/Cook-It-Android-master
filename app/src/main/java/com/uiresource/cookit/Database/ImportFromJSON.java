package com.uiresource.cookit.Database;

import android.os.Handler;
import android.util.Log;

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
import com.uiresource.cookit.Database.Test.Trasnlation;

import org.json.JSONException;
import org.json.JSONObject;

import static android.support.constraint.Constraints.TAG;
import static java.lang.Thread.*;

public class ImportFromJSON {

    private static String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/";

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static String resp;
    private static String response;
    private static ArrayList<AccountList> ListAcc;
    private static AccountListDao accountListDao;
    private static AccountRepository AccRepos;

    private static Timer timer;
    private static TimerTask timerTask;

    /*private static String Import(String url){
        resp = "";
        okHttpClient = new OkHttpClient();;
        request = new Request.Builder().url(url).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    //return myResponse;

                    resp = myResponse;
                }
            }
        });

        return resp;
    }*/

    //ArrayList<AccountList>
    public static ArrayList<AccountList> AccountGetList(){

        if (ListAcc != null){
            ListAcc = null;
        }

        getJSON();

        while (ListAcc == null){
            try {
                sleep(60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.i("GSON", "Вышел из метода");
        //Log.i("GSON", "Количество из JSON: " + ListAcc.size());

        return ListAcc;
    }

    private static void getJSON(){
        response = URL + "Account/GetList";

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

                    final AccountImport acc = gson.fromJson(myResponse, AccountImport.class);

                    /*try {
                        sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }*/

                    ListAcc = acc.getAccountsFromJSON();
                    Log.i("GSON", "Присвоил ListAcc");
                }
            }
        });
    }

    public static void AccountExportToServer(AccountList account){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        RegUser regUser = new RegUser(account.email, "AaT1234.", "AaT1234.");

        Log.i("GSON", "Вызван метод AccountExportToServer");

        final MediaType MEDIA_TYPE =
                MediaType.parse("application/json");

        okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(regUser));

        final Request request = new Request.Builder()
                .url("https://surviveonsotka20190514062215.azurewebsites.net/api/Account/Register")
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

    public static class RegUser {
        String email;
        String password;
        String passwordConfirm;

        public RegUser(String email, String password, String passwordConfirm) {
            this.email = email;
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }
    }
}
