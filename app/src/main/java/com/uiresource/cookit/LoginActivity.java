package com.uiresource.cookit;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountImport;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountViewModel;
import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.utils.RegisterActivity;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class LoginActivity extends AppCompatActivity {

    private EditText loginInputEmail, loginInputPassword;
    private Button btnlogin;
    private Button btnLinkSignup;

    private AccountViewModel accountViewModel;
    private static ArrayList<AccountList> ListAcc;

    private String COOKIES = "";

    private boolean checkResult;

    private String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = findViewById(R.id.toolbar);

        loginInputEmail = (EditText) findViewById(R.id.login_input_email);
        loginInputPassword = (EditText) findViewById(R.id.login_input_password);
        btnlogin = (Button) findViewById(R.id.btn_login);
        btnLinkSignup = (Button) findViewById(R.id.btn_link_signup);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        setSupportActionBar(toolbar);

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        if (ListAcc != null){
            ListAcc = null;
        }

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginInputEmail.getText().toString().trim().isEmpty() || loginInputPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    accountViewModel.deleteAllAccounts();

                    loginUser();


                }
            }
        });

        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);

            }
        });
    }

    private void loginUser() {
        // Tag used to cancel the request

        String cancel_req_tag = "login";
        progressDialog.setMessage("Logging you in...");
        showDialog();

        checkResult = false;


        ImportFromJSON.LoginUser loginUser = new ImportFromJSON.LoginUser(loginInputEmail.getText().toString(), loginInputPassword.getText().toString(), true);

        Log.i("GSON", "Вызван метод loginUser");

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(loginUser));

        final Request request = new Request.Builder()
                .url(URL + "Account/Login")
                .post(body)
                .addHeader("Content-Type", "application/json-patch+json")
                .addHeader("accept", "text/plain")
                .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 YaBrowser/19.4.3.370 Yowser/2.5 Safari/537.36")
                .addHeader("Cookie", "ARRAffinity=f36c8130531c157fc790e7052450319f91d9a1fed7834277b558504530e1fd5")
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

                if (response.isSuccessful()){
                    checkResult = true;
                    Log.i("GSON","Вход произведен!");
                    //Log.i("GSON",response.headers("Set-Cookie"));

                    int index = 0;

                    while (response.headers("Set-Cookie").get(0).charAt(index)!= ';')
                    {
                        COOKIES = COOKIES + String.valueOf(response.headers("Set-Cookie").get(0).charAt(index));
                        index++;
                    }

                    //Log.i("GSON", String.valueOf(request.headers().size()));

                    Log.i("GSON", COOKIES);


                    final AccountList acc = gson.fromJson(result, AccountList.class);

                    acc.Cookie = COOKIES;

                    accountViewModel.insert(acc);
                    Log.i("GSON", "LoginActivity - Аккаунт загружен и добавлен в БД! \nID: " + acc.getId() + "\nCookie: " + acc.getCookie());
                }

                else {
                    Log.i("GSON","LoginActivity - Вход НЕ произведен!");
                }

                hideDialog();
                CheckResult(result);
            }
        });

        Log.i("GSON", "Завершен метод loginUser");


    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void CheckResult(final String result) {
        LoginActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!checkResult) {

                    String alert;
                    switch(result) {
                        case "{\"User cannot be created\":[\"Passwords must be at least 6 characters.\",\"Passwords must have at least one non alphanumeric character.\",\"Passwords must have at least one digit ('0'-'9').\",\"Passwords must have at least one uppercase ('A'-'Z').\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
                            break;
                        case "{\"Email\":[\"The Email field is not a valid e-mail address.\"]}":
                            alert = "Введен неверный формат Email";
                            break;
                        case "{\"Email\":[\"The Email field is not a valid e-mail address.\"],\"PasswordConfirm\":[\"Пароли не совпадают\"]}":
                            alert = "Введен неверный формат Email";
                            break;
                        case "Password or email are incorrect ":
                            alert = "Неверный Email или пароль";
                            break;
                        case "{\"StatusCode\":400,\"Message\":\"Incorrect user password or email \\n innerException: \"}":
                            alert = "Неверный Email или пароль";
                            break;
                        default:
                            alert = "Ошибка сервера";
                            Toast.makeText(getApplicationContext(),
                                    result, Toast.LENGTH_LONG).show();
                            break;
                    }
                    Toast.makeText(getApplicationContext(),
                            alert, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Вход произведен", Toast.LENGTH_LONG).show();
                }

                ImportFromJSON.checkAuth(COOKIES);
            }
        });

    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    private void checkAuth(){

        OkHttpClient okHttpClient;
        Request request;
        String response;

        response = URL + "Account/IsAuthorized";

        okHttpClient = new OkHttpClient();
        request = new Request.Builder()
                .url(response)
                .addHeader("Cookie", COOKIES)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //Log.i(TAG,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();

                }

                Log.i("ON1", "Присвоил ListIngredients");
                Log.i("ON1", response.body().string() + "\n" + response.code());
            }
        });
    }
}
