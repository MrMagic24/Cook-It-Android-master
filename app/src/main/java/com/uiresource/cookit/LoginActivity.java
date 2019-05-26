package com.uiresource.cookit;

import android.app.ProgressDialog;
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
import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.utils.RegisterActivity;

import java.io.IOException;

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

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loginInputEmail.getText().toString().trim().isEmpty() || loginInputPassword.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
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

                if (response.isSuccessful()){
                    checkResult = true;
                    Log.i("GSON","Вход произведен!");

                }

                else {
                    Log.i("GSON","Вход НЕ произведен!");
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
            }
        });

    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
