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
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import com.google.gson.*;
import com.uiresource.cookit.Database.Test.Trasnlation;

import static java.lang.Thread.*;

public class ImportFromJSON {

    private static String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/";

    private static OkHttpClient okHttpClient;
    private static Request request;
    private static String resp;
    private static String response;
    public static ArrayList<AccountList> ListAcc;

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

    public static ArrayList<AccountList> AccountGetList(){

        getJSON();

        try {
            sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Log.i("GSON", "Вышел из метода");
        Log.i("GSON", "Количество из JSON: " + ListAcc.size());

        return ListAcc;
    }

    private static void getJSON(){
        response = URL + "Account/GetList";

        okHttpClient = new OkHttpClient();;
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

                    ListAcc = acc.getAccountsFromJSON();

                    //accountListDao.deleteAll();

                    //Log.i("GSON", "JSON: " + myResponse);
                    Log.i("GSON", "ID из JSON: " + ListAcc.get(0).getIdServer());
                    //Log.i("GSON", "Количество из JSON: " + ListAcc.size());
                }
            }
        });

        timer = new Timer();

        timerTask = new TimerTask(){
            @Override
            public void run()
            {
                Log.i("GSON", "Количество из JSON: " + ListAcc.size());
                timer.cancel();
                //Log.i("GSON", "Количество в БД: " + adapter.getItemsCount());
            }
        };

        timer.schedule(timerTask, 2000, 2000);

        /*Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                Log.i("GSON", "Количество из JSON: " + ListAcc.size());
            }
        };
        handler.postDelayed(r, 5000);*/


        //return trans.getAccountsFromJSON();

        //return accountListDao.getAllAccounts();
    }
}
