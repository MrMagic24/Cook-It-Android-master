package com.uiresource.cookit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.uiresource.cookit.Database.Accounts.AccountAdapter;
import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountViewModel;
import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsDao;
import com.uiresource.cookit.Database.Ingredients.IngredientsViewModel;
import com.uiresource.cookit.Database.Recipes.Recipes;
import com.uiresource.cookit.Database.Recipes.RecipesViewModel;

import java.util.ArrayList;
import java.util.List;

import static com.uiresource.cookit.Database.ImportFromJSON.IngredientsGetList;
import static com.uiresource.cookit.Database.ImportFromJSON.RecipesGetList;

public class Splash extends BaseActivity {

    private String URL = "https://surviveonsotka20190524073221.azurewebsites.net/api/";

    private static ArrayList<Ingredients> IngListJSON;
    private static ArrayList<Recipes> RecipesListJSON;
    //private IngredientsDao ingredientsDao;

    private AccountViewModel accountViewModel;
    private IngredientsViewModel ingredientsViewModel;
    private RecipesViewModel recipesViewModel;

    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    public static String COOKIE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
        ingredientsViewModel = ViewModelProviders.of(this).get(IngredientsViewModel.class);
        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);

        final AccountAdapter adapter = new AccountAdapter();

        accountViewModel.getAllAccounts().observe(this, new Observer<List<AccountList>>() {
            @Override
            public void onChanged(@Nullable List<AccountList> accountLists) {
                try {
                    COOKIE = accountLists.get(0).getCookie();
                    ImportFromJSON.checkAuth(COOKIE);
                    Log.i("Cookie", "Cookie: " + COOKIE);
                } catch (NullPointerException e){
                    Log.i("Error", "Cookie: " + e);
                } catch (ArrayIndexOutOfBoundsException e) {
                    Log.i("Error", "Cookie: " + e);
                } catch (IndexOutOfBoundsException e) {
                    Log.i("Error", "Cookie: " + e);
                }
            }
        });

        //getIngredients();
        getRecipes();

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
                Ingredients NewIngredient = new Ingredients(IngListJSON.get(i).getId(), IngListJSON.get(i).getName(), IngListJSON.get(i).getTypeFoodId(), IngListJSON.get(i).getIcon(),IngListJSON.get(i).getRecipiesCount());
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

        //Toast.makeText(getApplicationContext(),"Download Ingredients is Success!", Toast.LENGTH_SHORT).show();
    }

    private void getRecipes(){
        RecipesListJSON = RecipesGetList();

        recipesViewModel.deleteAllRecipes();
        //ingredientsViewModel.deleteAllIngredients();

        Log.i("GSON", "Количество записей JSON: " + RecipesListJSON.size());

        for(int i = 0; i < RecipesListJSON.size(); i++){

            Recipes NewRecipe = new Recipes(RecipesListJSON.get(i).getId(), RecipesListJSON.get(i).getName(), RecipesListJSON.get(i).getDescription(), RecipesListJSON.get(i).getReviews(),RecipesListJSON.get(i).getCategories(), RecipesListJSON.get(i).getUser(), RecipesListJSON.get(i).getDateCreated(), RecipesListJSON.get(i).getPathToPhotos(), RecipesListJSON.get(i).getTimeForCooking(),RecipesListJSON.get(i).getTimeForPreparetion(), RecipesListJSON.get(i).getRate(), RecipesListJSON.get(i).getIngredients(), RecipesListJSON.get(i).getSteps(), RecipesListJSON.get(i).getTags());
            recipesViewModel.insert(NewRecipe);
            Log.i("GSON", "Activity - Рецепт Recipes добавлен! \nID: " + RecipesListJSON.get(i).getId() + "\nИмя: " + RecipesListJSON.get(i).getName());
        }

        Toast.makeText(getApplicationContext(),"Download Recipes is Success!", Toast.LENGTH_SHORT).show();
    }

}
