package com.uiresource.cookit.Database;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ImportFromJSON {

    private String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/";

    private OkHttpClient okHttpClient;
    private Request request;
    private String resp;
    private String response;

    private String Import(String url){
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

                    resp = myResponse;
                }
            }
        });

        return resp;
    }

    /*public List<AccountList> AccountGetList(AppDatabase db, AccountListDao accountListDao){

        response = Import(URL + "Account/GetList");

        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        final AccountImport trans = gson.fromJson(response, AccountImport.class);

        accountListDao.deleteAll();

        trans.setAccounts(accountListDao);

        return accountListDao.getAllAccounts();
    }*/
}
