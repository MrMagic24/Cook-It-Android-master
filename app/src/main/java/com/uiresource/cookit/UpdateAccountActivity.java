package com.uiresource.cookit;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountViewModel;
import com.uiresource.cookit.Database.ImportFromJSON;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static java.lang.Thread.sleep;

public class UpdateAccountActivity extends AppCompatActivity {

    private static String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";

    private EditText accountInputFirstName, accountInputLastName, accountInputAboutYourself;
    private Button btnUpload, btnChooseImg;
    private ImageView imgView;
    private final int IMG_REQUEST = 1;
    private Bitmap bitmap;
    private ArrayList<String> StringImg;
    private String st;
    ProgressDialog progressDialog;

    private boolean checkResult;

    private AccountViewModel accountViewModel;

    private static String COOKIE = "";

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_account);

        accountInputFirstName = (EditText) findViewById(R.id.account_input_firstName);
        accountInputLastName = (EditText) findViewById(R.id.account_input_lastName);
        accountInputAboutYourself = (EditText) findViewById(R.id.account_input_aboutYourself);

        btnUpload = (Button) findViewById(R.id.btn_update_account);
        btnChooseImg = (Button) findViewById(R.id.btn_choose_image_account);

        imgView = (ImageView) findViewById(R.id.account_input_image);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        accountViewModel.getAllAccounts().observe(this, new Observer<List<AccountList>>() {
            @Override
            public void onChanged(@Nullable List<AccountList> accountLists) {
                try {
                    COOKIE = accountLists.get(0).getCookie();
                } catch (NullPointerException e){
                    Log.i("Error", "Cookie: " + e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.i("Error", "Cookie: " + e);
                } catch (IndexOutOfBoundsException e) {
                    Log.i("Error", "Cookie: " + e);
                }
            }
        });

        btnChooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                selectImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                while (COOKIE == ""){

                }

                updateUser();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imgView.setImageBitmap(bitmap);
                imgView.setVisibility(View.VISIBLE);
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private String ImageToString(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100, byteArrayOutputStream);
        byte[] imgBytes = byteArrayOutputStream.toByteArray();

        StringImg = new ArrayList<String>();

        /*int StrLen = 0;
        int LastNumber = 0;
        int AllBytes = 0;
        int LastBytes = 0;
        st = "";

        byte[] imgByte = new byte[100];

        for (int i = 0; i < imgBytes.length; i++){

            if (i == imgBytes.length - 1){
                LastNumber = (imgBytes.length % 100);
                byte[] LastImgByte = new byte[LastNumber];

                for (int j = 0; j < LastNumber; j++){
                    LastImgByte[j] = imgByte[j];
                    LastBytes++;
                }

                //st = Base64.encodeToString(imgByte,Base64.DEFAULT);
                StringImg.add(Base64.encodeToString(LastImgByte,Base64.DEFAULT));
            }
            else {
                if (StrLen == 100){
                    //st = st + Base64.encodeToString(imgByte,Base64.DEFAULT);
                    StringImg.add(Base64.encodeToString(imgByte,Base64.DEFAULT));
                    st = "";
                    StrLen = 0;

                }
                else {
                    imgByte[StrLen] = imgBytes[i];
                    StrLen++;
                    //LastNumber++;
                }

                AllBytes++;
            }
        }

        for (int j = 0; j < StringImg.size(); j++){
            Log.i("Bytes", StringImg.get(j));
        }

        Log.i("Bytes", String.valueOf(imgBytes.length));
        Log.i("Bytes", String.valueOf(AllBytes+1));
        Log.i("Bytes", String.valueOf(LastBytes));
        Log.i("Bytes", String.valueOf(StringImg.size()));*/
        return Base64.encodeToString(imgBytes,Base64.DEFAULT);
    }

    private void updateUser() {
        // Tag used to cancel the request

        //ImageToString(bitmap);

        /*StringRequest stringRequest = new StringRequest(Request.Method.POST, URL + "Account/UpdateUser",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String Response = jsonObject.getString("response");
                            Log.i("GSON", "Завершен метод StringRequest");
                            imgView.setImageResource(0);
                        } catch (Exception exx){

                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("GSON", "Ошибка StringRequest");
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map<String, String> params = new HashMap<>();
                params.put("firstName", accountInputFirstName.getText().toString());
                params.put("lastName", accountInputLastName.getText().toString());
                params.put("gender", String.valueOf(true));
                params.put("avatar", ImageToString(bitmap));
                params.put("aboutYourself", accountInputAboutYourself.getText().toString());

                return params;
            }
        };

        MySingleton.getInstance(UpdateAccountActivity.this).addToReqQueue(stringRequest);*/


        //Map<String,String> par = getParams();
        /*int kek = StringImg.size();
        Log.i("imgBytes", String.valueOf(kek));*/

        /*for (int i = 0; i < StringImg.size(); i++){
            Log.i("imgBytes", StringImg.get(i));
        }*/

        //Log.i("GSON", par.get("avatar"));
        //par.get("avatar");


        //String cancel_req_tag = "login";
        progressDialog.setMessage("Update you in...");
        showDialog();

        checkResult = false;

        ImportFromJSON.UpdateUser updateUser = new ImportFromJSON.UpdateUser(accountInputFirstName.getText().toString(), accountInputLastName.getText().toString(), true, accountInputAboutYourself.getText().toString(), /*ImageToString(bitmap)*/"");

        /*for (int i = 0; i < StringImg.size(); i++){
            Log.i("TAG", StringImg.get(i).toString());
        }*/
        //Log.i("TAG", String.valueOf(ImageToString(bitmap).length()));

        Log.i("TAG", "Вызван метод updateUser");

        OkHttpClient okHttpClient = new OkHttpClient();

        RequestBody body = RequestBody.create(MEDIA_TYPE,
                gson.toJson(updateUser));

        String newCookie = "ARRAffinity=f36c8130531c157fc790e7052450319f91d9a1fed7834277b558504530e1fd5; ";

        //Log.i("GSON",String.valueOf(gson.toJson(updateUser).length()));

        final okhttp3.Request request = new Request.Builder()
                .url(URL + "Account/UpdateUser")
                .put(body)
                .addHeader("Content-Type", "application/json")
                .addHeader("accept", "text/plain")
                .addHeader("Cookie", newCookie + COOKIE)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i("GSON",e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int res = response.code();
                Log.i("GSON", result);

                if (response.isSuccessful()){
                    checkResult = true;
                    Log.i("GSON","Обновление произведено!");

                }

                else {
                    Log.i("GSON","Обновление НЕ произведено!" + response);
                    Log.i("GSON","Обновление НЕ произведено!" + response.body().string());
                    Log.i("GSON",String.valueOf(res));
                }

                hideDialog();
                CheckResult(result);
            }
        });

        /*JSONObject response = null;
        try {
            response = new JSONObject();
            response.put("firstName", accountInputFirstName.getText().toString());
            response.put("lastName", accountInputLastName.getText().toString());
            response.put("gender", true);
            response.put("avatar", ImageToString(bitmap));
            response.put("aboutYourself", accountInputAboutYourself.getText().toString());
        } catch (Exception exx) {
        }

        okhttp3.RequestBody body = RequestBody.create(MEDIA_TYPE, response.toString());

        try {
            Log.i("GSON",response.getString("avatar"));
        } catch (Exception exx) {
        }*/



        //hideDialog();
        Log.i("GSON", "Завершен метод updateUser");
    }

    private void CheckResult(final String result) {
        UpdateAccountActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!checkResult) {

                    /*String alert;
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
                    }*/
                    Toast.makeText(getApplicationContext(),
                            result, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Обновление произведено", Toast.LENGTH_LONG).show();
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

    /*protected Map<String, String> getParams()
    {
        Map<String, String> params = new HashMap<>();
        params.put("firstName", accountInputFirstName.getText().toString());
        params.put("lastName", accountInputLastName.getText().toString());
        params.put("gender", String.valueOf(true));
        params.put("avatar", ImageToString(bitmap));
        params.put("aboutYourself", accountInputAboutYourself.getText().toString());

        return params;
    }*/
}
