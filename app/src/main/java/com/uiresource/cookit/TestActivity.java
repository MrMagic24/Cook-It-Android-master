package com.uiresource.cookit;

import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Collection;
import java.util.List;

import com.google.gson.*;

import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;
import com.uiresource.cookit.Database.AppDatabase;
import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.Database.Test.Trasnlation;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class TestActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Request request;
    private String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList";
    private static final String TAG = "MainActivity";
    private TextView textView;
    private String JSONText = "{\"items\":[{\"id\":\"5eee1be1-d52b-4c38-d87e-08d6d8651ad9\",\"email\":\"vladisa375@gmail.com\",\"userName\":\"vladisa375@gmail.com\",\"firstName\":null,\"lastName\":null,\"pathToAvatar\":null,\"gender\":false,\"aboutYourself\":null,\"recipiesCount\":0,\"reviewsCount\":0,\"rateReviewsCount\":0}],\"sort\":\"Id\",\"page\":0,\"pageSize\":10,\"totalItemsCount\":1,\"pagesCount\":1}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        textView  = (TextView) findViewById(R.id.textViewTestTextView);
        okHttpClient = new OkHttpClient();

        request = new Request.Builder().url(URL).build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();

                    AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "database").build();

                    final AccountListDao accountListDao = db.accountListDao();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    final AccountImport trans = gson.fromJson(URL, AccountImport.class);

                    //ImportFromJSON imp = new ImportFromJSON();

                    //List<AccountList> list = imp.AccountGetList(db, accountListDao);

                    /*for (int i = 0; i < list.size(); i++){
                        st = st + list.get(i).id + "\n";
                    }*/

                    TestActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            /*GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            final Trasnlation trans = gson.fromJson(myResponse, Trasnlation.class);

                            textView.setText(trans.setResult());

                            Log.i("GSON", "Имя: " + trans.setResult() *//*+ "\nID: " + acc.id + " \n"*//*);*/
                        }
                    });
                }
            }
        });

        //textView.setText(v);
    }
}
