package com.uiresource.cookit.utils;

import android.app.ProgressDialog;
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
import com.uiresource.cookit.LoginActivity;
import com.uiresource.cookit.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";
    private String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";
    ProgressDialog progressDialog;

    private boolean checkResult;

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    private EditText signupInputEmail, signupInputPassword, signupInputPasswordConfirm;
    private Button btnSignUp;
    private Button btnLinkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        signupInputEmail = (EditText) findViewById(R.id.signup_input_email);
        signupInputPasswordConfirm = (EditText) findViewById(R.id.signup_input_passwordConfirm);
        signupInputPassword = (EditText) findViewById(R.id.signup_input_password);

        btnSignUp = (Button) findViewById(R.id.btn_signup);
        btnLinkLogin = (Button) findViewById(R.id.btn_link_login);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (signupInputEmail.getText().toString().trim().isEmpty() || signupInputPassword.getText().toString().trim().isEmpty() || signupInputPasswordConfirm.getText().toString().trim().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Please insert data", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    registerUser();
                    //finish();
                }


            }
        });
        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                /*Intent i = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(i);*/
            }
        });
    }

    private void registerUser() {
        // Tag used to cancel the request

        String cancel_req_tag = "login";
        progressDialog.setMessage("Register you in...");
        showDialog();

        checkResult = false;

        ImportFromJSON.RegisterUser registerUser = new ImportFromJSON.RegisterUser(signupInputEmail.getText().toString(), signupInputPassword.getText().toString(), signupInputPasswordConfirm.getText().toString());

        Log.i("TAG", "Вызван метод registerUser");

        OkHttpClient okHttpClient = new OkHttpClient();

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

                if (response.isSuccessful()){
                    checkResult = true;
                    Log.i("GSON","Регистрация произведена!");

                }

                else {
                    Log.i("GSON","Регистрация НЕ произведена!");
                }

                hideDialog();
                CheckResult(result);
            }
        });

        Log.i(TAG, "Завершен метод registerUser");
    }

    private void CheckResult(final String result) {
        RegisterActivity.this.runOnUiThread(new Runnable() {
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
                        case "{\"PasswordConfirm\":[\"Пароли не совпадают\"]}":
                            alert = "Пароли не совпадают";
                            break;
                        case "{\"Email\":[\"The Email field is not a valid e-mail address.\"],\"PasswordConfirm\":[\"Пароли не совпадают\"]}":
                            alert = "Введен неверный формат Email";
                            break;
                        case "{\"User cannot be created\":[\"Passwords must be at least 6 characters.\",\"Passwords must have at least one non alphanumeric character.\",\"Passwords must have at least one lowercase ('a'-'z').\",\"Passwords must have at least one uppercase ('A'-'Z').\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
                            break;
                        case "{\"User cannot be created\":[\"Passwords must be at least 6 characters.\",\"Passwords must have at least one non alphanumeric character.\",\"Passwords must have at least one digit ('0'-'9').\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
                            break;
                        case "{\"User cannot be created\":[\"Passwords must be at least 6 characters.\",\"Passwords must have at least one non alphanumeric character.\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
                            break;
                        case "{\"User cannot be created\":[\"Passwords must be at least 6 characters.\",\"Passwords must have at least one non alphanumeric character.\",\"Passwords must have at least one uppercase ('A'-'Z').\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
                            break;
                        case "{\"User cannot be created\":[\"Passwords must have at least one non alphanumeric character.\"]}":
                            alert = "Пароль должен включать в себя цифры (0 - 9), большие ('A'-'Z') и маленькие ('a'-'z') латинские буквы, один пунктуационный символ и содержать не менее 6 символов.";
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
                            "Зарегестрирован", Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });

    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }
}
