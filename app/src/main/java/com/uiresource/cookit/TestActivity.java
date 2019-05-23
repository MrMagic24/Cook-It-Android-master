package com.uiresource.cookit;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
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
import java.util.Timer;
import java.util.TimerTask;

import javax.xml.transform.URIResolver;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.uiresource.cookit.Database.ImportFromJSON.*;

public class TestActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Request request;
    private String URL = "https://surviveonsotka20190514062215.azurewebsites.net/api/Account/GetList";
    private static final String TAG = "MainActivity";
    private TextView textView;
    private AccountViewModel accountViewModel;
    private static ArrayList<AccountList> AccListJSON;
    private static final int ADD_NOTE_REQUEST = 1;
    private static final int EDIT_NOTE_REQUEST = 2;

    private Timer timer;
    private TimerTask timerTask;

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

        FloatingActionButton buttonDeleteAllAccounts = findViewById(R.id.button_delete_all_accounts);
        buttonDeleteAllAccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accountViewModel.deleteAllAccounts();
                Toast.makeText(TestActivity.this,"All deleted", Toast.LENGTH_SHORT).show();
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

                AccListJSON = AccountGetList();

                importAccountFromJSON(accountLists);

                adapter.submitList(accountLists);


            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                accountViewModel.delete(adapter.getAccountAt(viewHolder.getAdapterPosition()));
                Toast.makeText(TestActivity.this,"Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new AccountAdapter.onItemClickListener() {
            @Override
            public void onItemClick(AccountList account) {
                Intent intent = new Intent(TestActivity.this, AddAccountActivity.class);
                intent.putExtra(AddAccountActivity.EXTRA_ID, account.getId());
                intent.putExtra(AddAccountActivity.EXTRA_IDSERVER, account.getIdServer());
                intent.putExtra(AddAccountActivity.EXTRA_EMAIL, account.getEmail());
                intent.putExtra(AddAccountActivity.EXTRA_USERNAME, account.getUserName());
                startActivityForResult(intent, EDIT_NOTE_REQUEST);
            }
        });

        //textView  = (TextView) findViewById(R.id.textViewTestTextView);
        /*okHttpClient = new OkHttpClient();

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

                    *//*AppDatabase db =  Room.databaseBuilder(getApplicationContext(),
                            AppDatabase.class, "database").build();

                    final AccountListDao accountListDao = db.accountListDao();

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    final AccountImport trans = gson.fromJson(URL, AccountImport.class);*//*

                    //ImportFromJSON imp = new ImportFromJSON();

                    //List<AccountList> list = imp.AccountGetList(db, accountListDao);

                    *//*for (int i = 0; i < list.size(); i++){
                        st = st + list.get(i).id + "\n";
                    }*//*

                    TestActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            *//*GsonBuilder builder = new GsonBuilder();
                            Gson gson = builder.create();
                            final Trasnlation trans = gson.fromJson(myResponse, Trasnlation.class);

                            textView.setText(trans.setResult());

                            Log.i("GSON", "Имя: " + trans.setResult() *//**//*+ "\nID: " + acc.id + " \n"*//**//*);*//*
                        }
                    });
                }
            }
        });*/

        //textView.setText(v);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            String Username = data.getStringExtra(AddAccountActivity.EXTRA_USERNAME);
            String Email = data.getStringExtra(AddAccountActivity.EXTRA_EMAIL);
            String ID = data.getStringExtra(AddAccountActivity.EXTRA_IDSERVER);

            AccountList account = new AccountList(ID, ID, Email, Username, "","","", false, 0,0,0);
            accountViewModel.insert(account);

            Toast.makeText(TestActivity.this,"Saved", Toast.LENGTH_SHORT).show();
        }

        else if (requestCode == EDIT_NOTE_REQUEST && resultCode == RESULT_OK){

            String id = "-1";
            id = data.getStringExtra(AddAccountActivity.EXTRA_ID);

            if (id == "-1"){
                Toast.makeText(TestActivity.this,"Not updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String Username = data.getStringExtra(AddAccountActivity.EXTRA_USERNAME);
            String Email = data.getStringExtra(AddAccountActivity.EXTRA_EMAIL);
            String IDServer = data.getStringExtra(AddAccountActivity.EXTRA_IDSERVER);

            AccountList account = new AccountList(id, IDServer, Email, Username, "","","", false, 0,0,0);
            accountViewModel.update(account);

            Toast.makeText(TestActivity.this,"Updated", Toast.LENGTH_SHORT).show();
        }

        else {
            Toast.makeText(TestActivity.this,"Not saved", Toast.LENGTH_SHORT).show();
        }
    }

    protected void importAccountFromJSON(List<AccountList> accountLists){

        //AccountList account = new AccountList(ID, ID, Email, Username, "","","", false, 0,0,0);

        //AccListJSON = ImportFromJSON.AccountGetList();
        //ImportFromJSON.AccountGetListVoid();
        //LiveData<List<AccountList>> AccList = accountViewModel.getAllAccounts();

        //int kek = adapter.getItemsCount();

        Log.i("GSON", "Количество из БД: " + accountLists.size());

        boolean checkItem = false;

        Log.i("GSON", "Количество записей JSON: " + AccListJSON.size());

        /*for(int i = 0; i < AccListJSON.size(); i++){
            for(int j = 0; j < adapter.getItemsCount(); j++){
                if (adapter.getAccountAt(j).getIdServer() == AccListJSON.get(i).getIdServer()){
                    checkItem = true;

                    if (adapter.getAccountAt(j).getId() != AccListJSON.get(i).getId() ||
                            adapter.getAccountAt(j).getUserName() != AccListJSON.get(i).getUserName() ||
                            adapter.getAccountAt(j).getEmail() != AccListJSON.get(i).getEmail() ||
                            adapter.getAccountAt(j).getFirstName() != AccListJSON.get(i).getFirstName() ||
                            adapter.getAccountAt(j).getAboutYourself() != AccListJSON.get(i).getAboutYourself() ||
                            adapter.getAccountAt(j).getPathToAvatar() != AccListJSON.get(i).getPathToAvatar() ||
                            adapter.getAccountAt(j).isGender() != AccListJSON.get(i).isGender() ||
                            adapter.getAccountAt(j).getRecipiesCount() != AccListJSON.get(i).getRecipiesCount() ||
                            adapter.getAccountAt(j).getReviewsCount() != AccListJSON.get(i).getReviewsCount() ||
                            adapter.getAccountAt(j).getRateReviewsCount() != AccListJSON.get(i).getRateReviewsCount()){

                        accountViewModel.update(AccListJSON.get(i));
                        Log.i("GSON", "Аккаунт обновлен! \nID: " + AccListJSON.get(i).getIdServer() + "\nИмя: " + AccListJSON.get(i).getUserName());
                    }
                }
            }

            if (checkItem == false){
                accountViewModel.insert(AccListJSON.get(i));
                Log.i("GSON", "Аккаунт добавлен! \nID: " + AccListJSON.get(i).getIdServer() + "\nИмя: " + AccListJSON.get(i).getUserName());
            }
        }
*/


        //accountViewModel.insert(account);

        Toast.makeText(TestActivity.this,"Download Success!", Toast.LENGTH_SHORT).show();
    }
}
