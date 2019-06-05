package com.uiresource.cookit;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsViewModel;
import com.uiresource.cookit.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

public class AddRecipeActivity extends AppCompatActivity {

    private EditText recipeInputName, recipeInputDescription, recipeInputIngredients;
    private AutoCompleteTextView recipeInputIngredients1;
    private Button btnAdd;

    private boolean checkResult;

    private IngredientsViewModel ingredientsViewModel;
    private ArrayList<String> IngredientsArrayList;

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
        //recipeInputIngredients = (EditText) findViewById(R.id.recipe_input_ingredients);
        recipeInputIngredients1 = findViewById(R.id.recipe_input_autocomplete_ingredients);

        /*final LayoutParams lparams = new LayoutParams(50,30); // Width , height
        recipeInputIngredients1.setLayoutParams(lparams);

        recipeInputIngredients1.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,(int) (height/2)));*/

        btnAdd = (Button) findViewById(R.id.btn_add_recipe);
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        IngredientsArrayList = new ArrayList<String>();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, IngredientsArrayList);
        recipeInputIngredients1.setAdapter(adapter);

        ingredientsViewModel = ViewModelProviders.of(this).get(IngredientsViewModel.class);
        ingredientsViewModel.getAllIngredients().observe(this, new Observer<List<Ingredients>>() {
            @Override
            public void onChanged(@Nullable List<Ingredients> ingredientsLists) {
                try {
                    for(int i = 0; i < ingredientsLists.size(); i++){
                        IngredientsArrayList.add(ingredientsLists.get(i).getName());
                    }
                }
                    //COOKIE = ingredientsLists.get(0).getName();
                catch (NullPointerException e){
                    Log.i("Error", "IngredientsArrayList: " + e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.i("Error", "IngredientsArrayList: " + e);
                } catch (IndexOutOfBoundsException e) {
                    Log.i("Error", "IngredientsArrayList: " + e);
                }

                Log.i("OK", "IngredientsArrayList: " + IngredientsArrayList.size());
            }
        });

        //final
    }
}
