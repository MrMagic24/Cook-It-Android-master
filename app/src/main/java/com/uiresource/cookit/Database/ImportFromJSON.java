package com.uiresource.cookit.Database;

import android.util.Log;

//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.*;
import com.uiresource.cookit.Database.Test.Trasnlation;

public class ImportFromJSON {

    private static String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/";

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static String resp;
    private static String response;

    private static String Import(String url){
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
    }

    /*public static ArrayList<AccountList> AccountGetList(){

        //response = Import(URL + "Account/GetList");

        //response = Import("https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList");

        //response = "https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList";



        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        final AccountImport trans = gson.fromJson(response, AccountImport.class);

        //accountListDao.deleteAll();

        Log.i("GSON", "ID из JSON: " + trans.id);

        return trans.getAccountsFromJSON();

        //return accountListDao.getAllAccounts();
    }*/

    public static void AccountGetListVoid(){

        //response = Import(URL + "Account/GetList");

        //response = "https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList";

        okHttpClient = new OkHttpClient();;
        request = new Request.Builder().url("https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList").build();

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

                    //accountListDao.deleteAll();

                    //Log.i("GSON", "JSON: " + myResponse);
                    Log.i("GSON", "ID из JSON: " + acc.setResult());
                }
            }
        });



        //return trans.getAccountsFromJSON();

        //return accountListDao.getAllAccounts();
    }
}
