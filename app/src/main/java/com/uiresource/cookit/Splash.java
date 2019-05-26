package com.uiresource.cookit;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsDao;
import com.uiresource.cookit.Database.Ingredients.IngredientsViewModel;

import java.util.ArrayList;

import static com.uiresource.cookit.Database.ImportFromJSON.IngredientsGetList;

public class Splash extends BaseActivity {

    private String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";

    private static ArrayList<Ingredients> IngListJSON;
    //private IngredientsDao ingredientsDao;

    private IngredientsViewModel ingredientsViewModel;

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        ingredientsViewModel = ViewModelProviders.of(this).get(IngredientsViewModel.class);
        getIngredients();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Splash.this, Main.class));
                finish();
            }
        }, 2*1000);
    }

    private void getIngredients(){
        IngListJSON = IngredientsGetList();

        //Log.i("GSON", "Количество из БД: " + ingredientsDao.getAll().size());

        /*int sizeOfIngDB = 0;

        if (!ingredientsDao.getAll().){
            sizeOfIngDB = ingredientsDao.getAll().size();
        }*/

        //boolean checkItem = false;
        //boolean checkUpdate = false;

        ingredientsViewModel.deleteAllIngredients();

        Log.i("GSON", "Количество записей JSON: " + IngListJSON.size());

        for(int i = 0; i < IngListJSON.size(); i++){

            /*if (sizeOfIngDB > 0){
                for(int j = 0; j < sizeOfIngDB; j++){
                    if (ingredientsDao.getAll().get(j).getId().equals(IngListJSON.get(i).getId())){
                        checkItem = true;

                    *//*if (AccListJSON.get(i).getUserName() == null){AccListJSON.get(i).userName = "null";}
                    if (AccListJSON.get(i).getEmail() == null){AccListJSON.get(i).email = "null";}
                    if (AccListJSON.get(i).getFirstName() == null){AccListJSON.get(i).firstName = "null";}
                    if (AccListJSON.get(i).getAboutYourself() == null){AccListJSON.get(i).aboutYourself = "null";}
                    if (AccListJSON.get(i).getPathToAvatar() == null){AccListJSON.get(i).pathToAvatar = "null";}*//*

                    *//*if (!(accountList.get(j).getUserName().equals(AccListJSON.get(i).getUserName())) ||
                            !(accountList.get(j).getEmail().equals(AccListJSON.get(i).getEmail())) ||
                            !(accountList.get(j).getFirstName().equals(AccListJSON.get(i).getFirstName())) ||
                            !(accountList.get(j).getAboutYourself().equals(AccListJSON.get(i).getAboutYourself())) ||
                            !(accountList.get(j).getPathToAvatar().equals(AccListJSON.get(i).getPathToAvatar())) ||
                            accountList.get(j).isGender() != AccListJSON.get(i).isGender() ||
                            accountList.get(j).getRecipiesCount() != AccListJSON.get(i).getRecipiesCount() ||
                            accountList.get(j).getReviewsCount() != AccListJSON.get(i).getReviewsCount() ||
                            accountList.get(j).getRateReviewsCount() != AccListJSON.get(i).getRateReviewsCount()){*//*

                        Ingredients ThisIngredient = new Ingredients(IngListJSON.get(i).getId(), IngListJSON.get(i).getName(), IngListJSON.get(i).getTypeFoodId(), IngListJSON.get(i).getPathToIcon(),IngListJSON.get(i).getRecipiesCount());
                        ingredientsDao.update(ThisIngredient);
                        checkUpdate = true;
                        Log.i("GSON", "Activity - Аккаунт Ingredients обновлен! \nID: " + IngListJSON.get(i).getId() + "\nИмя: " + IngListJSON.get(i).getName());
                        //}
                    }
                }
            }*/

            //if (!checkItem){
                Ingredients NewIngredient = new Ingredients(IngListJSON.get(i).getId(), IngListJSON.get(i).getName(), IngListJSON.get(i).getTypeFoodId(), IngListJSON.get(i).getPathToIcon(),IngListJSON.get(i).getRecipiesCount());
                ingredientsViewModel.insert(NewIngredient);
                Log.i("GSON", "Activity - Аккаунт Ingredients добавлен! \nID: " + IngListJSON.get(i).getId() + "\nИмя: " + IngListJSON.get(i).getName());
            //}

            //if (!checkUpdate && checkItem){
                //Log.i("GSON", "Activity - Данные Ingredients не изменены \nID: ");
            //}

            //checkItem = false;
        }


        //checkImport = true;
        //accountViewModel.insert(account);

        Toast.makeText(getApplicationContext(),"Download Ingredients is Success!", Toast.LENGTH_SHORT).show();
    }

}
