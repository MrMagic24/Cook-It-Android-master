package com.uiresource.cookit;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.R;

import okhttp3.MediaType;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText recipeInputName, recipeInputDescription, recipeInputIngredients;
    private Button btnAdd;

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
        setContentView(R.layout.activity_add_recipe);

        recipeInputName = (EditText) findViewById(R.id.login_input_email);
        recipeInputDescription = (EditText) findViewById(R.id.recipe_input_description);
        recipeInputIngredients = (EditText) findViewById(R.id.recipe_input_ingredients);
        btnAdd = (Button) findViewById(R.id.btn_add_recipe);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
    }
}
