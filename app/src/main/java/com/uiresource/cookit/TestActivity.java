package com.uiresource.cookit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collection;
import java.util.List;

import com.google.gson.*;

import com.uiresource.cookit.Database.Accounts.AccountAdapter;
import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;
import com.uiresource.cookit.Database.Accounts.AccountViewModel;
import com.uiresource.cookit.Database.AppDatabase;
import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.Database.Test.Trasnlation;

import java.io.IOException;

import javax.xml.transform.URIResolver;

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
    private AccountViewModel accountViewModel;
    private static final int ADD_NOTE_REQUEST = 1;
    //private String JSONText = "{\"items\":[{\"id\":\"5eee1be1-d52b-4c38-d87e-08d6d8651ad9\",\"email\":\"vladisa375@gmail.com\",\"userName\":\"vladisa375@gmail.com\",\"firstName\":null,\"lastName\":null,\"pathToAvatar\":null,\"gender\":false,\"aboutYourself\":null,\"recipiesCount\":0,\"reviewsCount\":0,\"rateReviewsCount\":0}],\"sort\":\"Id\",\"page\":0,\"pageSize\":10,\"totalItemsCount\":1,\"pagesCount\":1}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        FloatingActionButton buttonAddAccount = findViewById(R.id.button_add_account);
        buttonAddAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestActivity.this, AddAccountActivity.class);
                startActivityForResult(intent,ADD_NOTE_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final AccountAdapter adapter = new AccountAdapter();
        recyclerView.setAdapter(adapter);

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        accountViewModel.getAllAccounts().observe(this, new Observer<List<AccountList>>() {
            @Override
            public void onChanged(@Nullable List<AccountList> accountLists) {
                //Toast.makeText(TestActivity.this,"onChanged", Toast.LENGTH_SHORT).show();
                adapter.setAccounts(accountLists);
            }
        });

        //textView  = (TextView) findViewById(R.id.textViewTestTextView);
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

                    /*AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "database").build();

                    final AccountListDao accountListDao = db.accountListDao();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    final AccountImport trans = gson.fromJson(URL, AccountImport.class);*/

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String Username = data.getStringExtra(AddAccountActivity.EXTRA_USERNAME);
            String Email = data.getStringExtra(AddAccountActivity.EXTRA_EMAIL);
            String ID = data.getStringExtra(AddAccountActivity.EXTRA_ID);

            AccountList account = new AccountList(ID, Email, Username, "","","", false, 0,0,0);
            accountViewModel.insert(account);

            Toast.makeText(TestActivity.this,"Saved", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(TestActivity.this,"Not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
