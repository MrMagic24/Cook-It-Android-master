package com.uiresource.cookit;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsViewModel;
import com.uiresource.cookit.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;

public class AddRecipeActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private ConstraintSet constraintSet;

    private int checkLayouts[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0};

    private EditText recipeInputName, recipeInputDescription, recipeInputIngredients;

    private TextInputLayout recipeInputLayoutIngredients1, recipeInputLayoutIngredients2, recipeInputLayoutIngredients3, recipeInputLayoutIngredients4, recipeInputLayoutIngredients5,
            recipeInputLayoutIngredients6, recipeInputLayoutIngredients7, recipeInputLayoutIngredients8, recipeInputLayoutIngredients9, recipeInputLayoutIngredients10,
            recipeInputLayoutIngredients11, recipeInputLayoutIngredients12, recipeInputLayoutIngredients13, recipeInputLayoutIngredients14, recipeInputLayoutIngredients15;

    private TextInputLayout recipeInputLayoutAmount1, recipeInputLayoutAmount2, recipeInputLayoutAmount3, recipeInputLayoutAmount4, recipeInputLayoutAmount5,
            recipeInputLayoutAmount6, recipeInputLayoutAmount7, recipeInputLayoutAmount8, recipeInputLayoutAmount9, recipeInputLayoutAmount10,
            recipeInputLayoutAmount11, recipeInputLayoutAmount12, recipeInputLayoutAmount13, recipeInputLayoutAmount14, recipeInputLayoutAmount15, imgLayoutView;

    private AutoCompleteTextView recipeInputIngredients1, recipeInputIngredients2, recipeInputIngredients3, recipeInputIngredients4, recipeInputIngredients5,
            recipeInputIngredients6, recipeInputIngredients7, recipeInputIngredients8, recipeInputIngredients9, recipeInputIngredients10,
            recipeInputIngredients11, recipeInputIngredients12, recipeInputIngredients13, recipeInputIngredients14, recipeInputIngredients15;

    private EditText recipeInputIngredientsAmount1, recipeInputIngredientsAmount2, recipeInputIngredientsAmount3, recipeInputIngredientsAmount4, recipeInputIngredientsAmount5,
            recipeInputIngredientsAmount6, recipeInputIngredientsAmount7, recipeInputIngredientsAmount8, recipeInputIngredientsAmount9, recipeInputIngredientsAmount10,
            recipeInputIngredientsAmount11, recipeInputIngredientsAmount12, recipeInputIngredientsAmount13, recipeInputIngredientsAmount14, recipeInputIngredientsAmount15;

    private Button btnAdd, btnImgChoose;
    private ImageView imgView;
    private Bitmap bitmap;
    private final int IMG_REQUEST = 1;

    private int EditTextIngredientWidth = 0, EditTextIngredientHeight = 0;
    private int EditTextAmountWidth = 0, EditTextAmountHeight = 0;
    private int ImgLayoutViewWidth = 0;

    private boolean checkResult;

    private IngredientsViewModel ingredientsViewModel;
    private ArrayList<String> IngredientsArrayList;

    private String URL = "https://surviveonsotkanewfromkirill.azurewebsites.net/api/";

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    private static final MediaType MEDIA_TYPE =
            MediaType.parse("application/json");

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        imgLayoutView = findViewById(R.id.recipe_input_layout_image);
        imgView = findViewById(R.id.recipe_input_image);
        recipeInputName = (EditText) findViewById(R.id.login_input_email);
        recipeInputDescription = (EditText) findViewById(R.id.recipe_input_description);
        recipeInputIngredients1 = findViewById(R.id.recipe_input_autocomplete_ingredients1);
        recipeInputIngredients2 = findViewById(R.id.recipe_input_autocomplete_ingredients2);
        recipeInputIngredients3 = findViewById(R.id.recipe_input_autocomplete_ingredients3);
        recipeInputIngredients4 = findViewById(R.id.recipe_input_autocomplete_ingredients4);
        recipeInputIngredients5 = findViewById(R.id.recipe_input_autocomplete_ingredients5);
        recipeInputIngredients6 = findViewById(R.id.recipe_input_autocomplete_ingredients6);
        recipeInputIngredients7 = findViewById(R.id.recipe_input_autocomplete_ingredients7);
        recipeInputIngredients8 = findViewById(R.id.recipe_input_autocomplete_ingredients8);
        recipeInputIngredients9 = findViewById(R.id.recipe_input_autocomplete_ingredients9);
        recipeInputIngredients10 = findViewById(R.id.recipe_input_autocomplete_ingredients10);
        recipeInputIngredients11 = findViewById(R.id.recipe_input_autocomplete_ingredients11);
        recipeInputIngredients12 = findViewById(R.id.recipe_input_autocomplete_ingredients12);
        recipeInputIngredients13 = findViewById(R.id.recipe_input_autocomplete_ingredients13);
        recipeInputIngredients14 = findViewById(R.id.recipe_input_autocomplete_ingredients14);
        recipeInputIngredients15 = findViewById(R.id.recipe_input_autocomplete_ingredients15);

        recipeInputIngredientsAmount1 = findViewById(R.id.recipe_input_ingredients_amount1);
        recipeInputIngredientsAmount2 = findViewById(R.id.recipe_input_ingredients_amount2);
        recipeInputIngredientsAmount3 = findViewById(R.id.recipe_input_ingredients_amount3);
        recipeInputIngredientsAmount4 = findViewById(R.id.recipe_input_ingredients_amount4);
        recipeInputIngredientsAmount5 = findViewById(R.id.recipe_input_ingredients_amount5);
        recipeInputIngredientsAmount6 = findViewById(R.id.recipe_input_ingredients_amount6);
        recipeInputIngredientsAmount7 = findViewById(R.id.recipe_input_ingredients_amount7);
        recipeInputIngredientsAmount8 = findViewById(R.id.recipe_input_ingredients_amount8);
        recipeInputIngredientsAmount9 = findViewById(R.id.recipe_input_ingredients_amount9);
        recipeInputIngredientsAmount10 = findViewById(R.id.recipe_input_ingredients_amount10);
        recipeInputIngredientsAmount11 = findViewById(R.id.recipe_input_ingredients_amount11);
        recipeInputIngredientsAmount12 = findViewById(R.id.recipe_input_ingredients_amount12);
        recipeInputIngredientsAmount13 = findViewById(R.id.recipe_input_ingredients_amount13);
        recipeInputIngredientsAmount14 = findViewById(R.id.recipe_input_ingredients_amount14);
        recipeInputIngredientsAmount15 = findViewById(R.id.recipe_input_ingredients_amount15);


        recipeInputLayoutIngredients1 = findViewById(R.id.recipe_input_layout_ingredients1);
        recipeInputLayoutIngredients2 = findViewById(R.id.recipe_input_layout_ingredients2);
        recipeInputLayoutIngredients3 = findViewById(R.id.recipe_input_layout_ingredients3);
        recipeInputLayoutIngredients4 = findViewById(R.id.recipe_input_layout_ingredients4);
        recipeInputLayoutIngredients5 = findViewById(R.id.recipe_input_layout_ingredients5);
        recipeInputLayoutIngredients6 = findViewById(R.id.recipe_input_layout_ingredients6);
        recipeInputLayoutIngredients7 = findViewById(R.id.recipe_input_layout_ingredients7);
        recipeInputLayoutIngredients8 = findViewById(R.id.recipe_input_layout_ingredients8);
        recipeInputLayoutIngredients9 = findViewById(R.id.recipe_input_layout_ingredients9);
        recipeInputLayoutIngredients10 = findViewById(R.id.recipe_input_layout_ingredients10);
        recipeInputLayoutIngredients11 = findViewById(R.id.recipe_input_layout_ingredients11);
        recipeInputLayoutIngredients12 = findViewById(R.id.recipe_input_layout_ingredients12);
        recipeInputLayoutIngredients13 = findViewById(R.id.recipe_input_layout_ingredients13);
        recipeInputLayoutIngredients14 = findViewById(R.id.recipe_input_layout_ingredients14);
        recipeInputLayoutIngredients15 = findViewById(R.id.recipe_input_layout_ingredients15);

        recipeInputLayoutAmount1 = findViewById(R.id.recipe_input_layout_ingredients_amount1);
        recipeInputLayoutAmount2 = findViewById(R.id.recipe_input_layout_ingredients_amount2);
        recipeInputLayoutAmount3 = findViewById(R.id.recipe_input_layout_ingredients_amount3);
        recipeInputLayoutAmount4 = findViewById(R.id.recipe_input_layout_ingredients_amount4);
        recipeInputLayoutAmount5 = findViewById(R.id.recipe_input_layout_ingredients_amount5);
        recipeInputLayoutAmount6 = findViewById(R.id.recipe_input_layout_ingredients_amount6);
        recipeInputLayoutAmount7 = findViewById(R.id.recipe_input_layout_ingredients_amount7);
        recipeInputLayoutAmount8 = findViewById(R.id.recipe_input_layout_ingredients_amount8);
        recipeInputLayoutAmount9 = findViewById(R.id.recipe_input_layout_ingredients_amount9);
        recipeInputLayoutAmount10 = findViewById(R.id.recipe_input_layout_ingredients_amount10);
        recipeInputLayoutAmount11 = findViewById(R.id.recipe_input_layout_ingredients_amount11);
        recipeInputLayoutAmount12 = findViewById(R.id.recipe_input_layout_ingredients_amount12);
        recipeInputLayoutAmount13 = findViewById(R.id.recipe_input_layout_ingredients_amount13);
        recipeInputLayoutAmount14 = findViewById(R.id.recipe_input_layout_ingredients_amount14);
        recipeInputLayoutAmount15 = findViewById(R.id.recipe_input_layout_ingredients_amount15);


        EditTextIngredientHeight = recipeInputLayoutIngredients1.getHeight();
        EditTextIngredientWidth = recipeInputLayoutIngredients1.getWidth();
        EditTextAmountHeight = recipeInputLayoutAmount1.getHeight();
        EditTextAmountWidth = recipeInputLayoutAmount1.getWidth();
        ImgLayoutViewWidth = imgLayoutView.getWidth();

        //final ConstraintLayout.LayoutParams lparams = new ConstraintLayout.LayoutParams(1,1); // Width , height
        //final ScrollView.LayoutParams llparams = new ScrollView.LayoutParams(0,0); // Width , height

        constraintLayout = findViewById(R.id.recipe_input_Constraintlayout);
        constraintSet = new ConstraintSet();
        constraintSet.clone(constraintLayout);

        //imgView.setLayoutParams(lparams);

        setSmallLayout(new ConstraintLayout.LayoutParams(1,1));
        correctLayoutParams();

        constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
        constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients1, ConstraintSet.BOTTOM, 30);
        constraintSet.clear(R.id.btn_add_recipe, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.btn_add_recipe, ConstraintSet.TOP, R.id.btn_choose_image_recipe, ConstraintSet.BOTTOM);
        constraintSet.applyTo(constraintLayout);

        //recipeInputLayoutIngredients15.setVisibility(View.INVISIBLE);

        //imgView = (ImageView) findViewById(R.id.recipe_input_image);
        //imgView.setLayoutParams(lparams);

        btnAdd = (Button) findViewById(R.id.btn_add_recipe);
        btnImgChoose = (Button) findViewById(R.id.btn_choose_image_recipe);
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

        recipeInputIngredients1.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients1.getText().toString().equals("") && checkLayouts[0] == 0){
                    recipeInputLayoutIngredients2.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount2.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients2, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[0] = 1;
                }

                Log.i("Bytes", recipeInputIngredients1.getText().toString());
                Log.i("Bytes", String.valueOf(recipeInputLayoutIngredients2.getHeight()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients2.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients2.getText().toString().equals("") && checkLayouts[1] == 0){
                    recipeInputLayoutIngredients3.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount3.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients3, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[1] = 1;
                }

                Log.i("Bytes", recipeInputIngredients2.getText().toString());
                Log.i("Bytes", String.valueOf(recipeInputLayoutIngredients3.getHeight()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients3.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients3.getText().toString().equals("") && checkLayouts[2] == 0){
                    recipeInputLayoutIngredients4.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount4.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients4, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[2] = 1;
                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients4.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients4.getText().toString().equals("") && checkLayouts[3] == 0){
                    recipeInputLayoutIngredients5.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount5.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients5, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[3] = 1;
                }

                constraintSet.applyTo(constraintLayout);
                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients5.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients5.getText().toString().equals("") && checkLayouts[4] == 0){
                    recipeInputLayoutIngredients6.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount6.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients6, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[4] = 1;
                }

                constraintSet.applyTo(constraintLayout);
                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients6.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients6.getText().toString().equals("") && checkLayouts[5] == 0){
                    recipeInputLayoutIngredients7.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount7.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients7, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[5] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients7.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients7.getText().toString().equals("") && checkLayouts[6] == 0){
                    recipeInputLayoutIngredients8.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount8.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients8, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[6] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients8.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients8.getText().toString().equals("") && checkLayouts[7] == 0){
                    recipeInputLayoutIngredients9.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount9.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients9, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[7] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients9.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients9.getText().toString().equals("") && checkLayouts[8] == 0){
                    recipeInputLayoutIngredients10.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount10.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients10, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[8] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients10.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients10.getText().toString().equals("") && checkLayouts[9] == 0){
                    recipeInputLayoutIngredients11.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount11.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients11, ConstraintSet.BOTTOM, 30);
                    recipeInputLayoutAmount12.setVisibility(View.INVISIBLE);
                    recipeInputLayoutAmount13.setVisibility(View.INVISIBLE);
                    recipeInputLayoutAmount14.setVisibility(View.INVISIBLE);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[9] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients11.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients11.getText().toString().equals("") && checkLayouts[10] == 0){
                    recipeInputLayoutAmount12.setVisibility(View.VISIBLE);
                    recipeInputLayoutIngredients12.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount12.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients12, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[10] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients12.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients12.getText().toString().equals("") && checkLayouts[11] == 0){
                    recipeInputLayoutAmount13.setVisibility(View.VISIBLE);
                    recipeInputLayoutIngredients13.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount13.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients13, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[11] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients13.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients13.getText().toString().equals("") && checkLayouts[12] == 0){
                    recipeInputLayoutAmount14.setVisibility(View.VISIBLE);
                    recipeInputLayoutIngredients14.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount14.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients14, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[12] = 1;

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        recipeInputIngredients14.addTextChangedListener(new TextWatcher(){
            @Override
            public void afterTextChanged(Editable s) {
                // Прописываем то, что надо выполнить после изменения текста
                if (!recipeInputIngredients14.getText().toString().equals("") && checkLayouts[13] == 0){
                    recipeInputLayoutIngredients15.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                    recipeInputLayoutAmount15.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));

                    correctLayoutParams();

                    constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.TOP);
                    constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients15, ConstraintSet.BOTTOM, 30);
                    constraintSet.applyTo(constraintLayout);
                    checkLayouts[13] = 1;

                }

                Log.i("Bytes", recipeInputIngredients14.getText().toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        btnImgChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                imgLayoutView.setLayoutParams(new ConstraintLayout.LayoutParams(ImgLayoutViewWidth, ConstraintLayout.LayoutParams.WRAP_CONTENT));

                constraintSet.clear(R.id.btn_choose_image_recipe, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.BOTTOM, R.id.recipe_input_layout_image, ConstraintSet.TOP);
                constraintSet.clear(R.id.recipe_input_layout_image, ConstraintSet.TOP);
                constraintSet.connect(R.id.recipe_input_layout_image, ConstraintSet.TOP, R.id.btn_choose_image_recipe, ConstraintSet.BOTTOM);
                constraintSet.clear(R.id.btn_add_recipe, ConstraintSet.TOP);
                constraintSet.connect(R.id.btn_add_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_image, ConstraintSet.BOTTOM);

                constraintSet.applyTo(constraintLayout);

                selectImage();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //recipeInputIngredients3.setEnabled(true);
                //constraintSet.applyTo(recipeInputLayoutIngredients1);
                recipeInputLayoutIngredients15.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextIngredientWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                recipeInputLayoutAmount15.setLayoutParams(new ConstraintLayout.LayoutParams(EditTextAmountWidth,ConstraintLayout.LayoutParams.WRAP_CONTENT));
                constraintSet.clear(R.id.btn_add_recipe, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.recipe_input_layout_ingredients15, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients14, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.recipe_input_layout_ingredients_amount15, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount14, ConstraintSet.BOTTOM);
                constraintSet.connect(R.id.btn_add_recipe, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount15, ConstraintSet.BOTTOM);
                //constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.TOP, R.id.btn_choose_image_recipe, ConstraintSet.BOTTOM, 30);
                //constraintSet.connect(R.id.btn_choose_image_recipe, ConstraintSet.LEFT, R.id.btn_choose_image_recipe, ConstraintSet.LEFT);

                /*constraintSet.connect(R.id.recipe_input_layout_ingredients2, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients1, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients3, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients2, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients4, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients3, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients5, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients4, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients6, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients5, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients7, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients6, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients8, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients7, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients9, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients8, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients10, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients9, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients11, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients10, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients12, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients11, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients13, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients12, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients14, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients13, ConstraintSet.BOTTOM, 0);
                constraintSet.connect(R.id.recipe_input_layout_ingredients15, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients14, ConstraintSet.BOTTOM, 0);*/

                //recipeInputLayoutIngredients3.setLayoutParams(new ConstraintLayout.LayoutParams(585,-2));
                //constraintSet.connect(R.id.recipe_input_layout_ingredients3, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients2, ConstraintSet.BOTTOM, 0);
                //constraintSet.connect(R.id.recipe_input_autocomplete_ingredients2, ConstraintSet.TOP, R.id.recipe_input_autocomplete_ingredients1, ConstraintSet.BOTTOM, 10);
                constraintSet.applyTo(constraintLayout);

                //recipeInputLayoutIngredients2.getLayoutParams().
                Log.i("Bytes", String.valueOf(recipeInputLayoutIngredients2.getLayoutParams().width));
                Log.i("Bytes", String.valueOf(recipeInputLayoutIngredients2.getLayoutParams().height));
                //recipeInputIngredientsAmount3.setEnabled(true);
                //recipeInputIngredientsAmount3.setLayoutParams(new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,ScrollView.LayoutParams.WRAP_CONTENT));
            }
        });
    }

    private void selectImage(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
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

    private void correctLayoutParams(){
        constraintSet.connect(R.id.recipe_input_layout_ingredients2, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients1, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount2, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount1, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients3, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients2, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount3, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount2, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients4, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients3, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount4, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount3, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients5, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients4, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount5, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount4, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients6, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients5, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount6, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount5, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients7, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients6, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount7, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount6, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients8, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients7, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount8, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount7, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients9, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients8, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount9, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount8, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients10, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients9, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount10, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount9, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients11, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients10, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount11, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount10, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients12, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients11, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount12, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount11, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients13, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients12, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount13, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount12, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients14, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients13, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount14, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount13, ConstraintSet.BOTTOM);

        constraintSet.connect(R.id.recipe_input_layout_ingredients15, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients14, ConstraintSet.BOTTOM);
        constraintSet.connect(R.id.recipe_input_layout_ingredients_amount15, ConstraintSet.TOP, R.id.recipe_input_layout_ingredients_amount14, ConstraintSet.BOTTOM);
    }

    private void setSmallLayout(ConstraintLayout.LayoutParams lparams){
        recipeInputLayoutIngredients2.setLayoutParams(lparams);
        recipeInputLayoutAmount2.setLayoutParams(lparams);
        recipeInputLayoutIngredients3.setLayoutParams(lparams);
        recipeInputLayoutAmount3.setLayoutParams(lparams);
        recipeInputLayoutIngredients4.setLayoutParams(lparams);
        recipeInputLayoutAmount4.setLayoutParams(lparams);
        recipeInputLayoutIngredients5.setLayoutParams(lparams);
        recipeInputLayoutAmount5.setLayoutParams(lparams);
        recipeInputLayoutIngredients6.setLayoutParams(lparams);
        recipeInputLayoutAmount6.setLayoutParams(lparams);
        recipeInputLayoutIngredients7.setLayoutParams(lparams);
        recipeInputLayoutAmount7.setLayoutParams(lparams);
        recipeInputLayoutIngredients8.setLayoutParams(lparams);
        recipeInputLayoutAmount8.setLayoutParams(lparams);
        recipeInputLayoutIngredients9.setLayoutParams(lparams);
        recipeInputLayoutAmount9.setLayoutParams(lparams);
        recipeInputLayoutIngredients10.setLayoutParams(lparams);
        recipeInputLayoutAmount10.setLayoutParams(lparams);
        recipeInputLayoutIngredients11.setLayoutParams(lparams);
        recipeInputLayoutAmount11.setLayoutParams(lparams);
        recipeInputLayoutIngredients12.setLayoutParams(lparams);
        recipeInputLayoutAmount12.setLayoutParams(lparams);
        recipeInputLayoutIngredients13.setLayoutParams(lparams);
        recipeInputLayoutAmount13.setLayoutParams(lparams);
        recipeInputLayoutIngredients14.setLayoutParams(lparams);
        recipeInputLayoutAmount14.setLayoutParams(lparams);
        recipeInputLayoutIngredients15.setLayoutParams(lparams);
        recipeInputLayoutAmount15.setLayoutParams(lparams);
    }
}
