package com.uiresource.cookit.Database.Recipes;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.uiresource.cookit.Database.AppDatabase;

import java.util.List;

public class RecipesRepository {
    private RecipesDao recipesDao;
    private LiveData<List<Recipes>> allRecipes;

    public RecipesRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        recipesDao = database.recipesDao();
        allRecipes = recipesDao.getAllRecipes();
    }

    public void insert(Recipes account){
        new InsertAccountAsyncTask(recipesDao).execute(account);
    }

    public void update(Recipes account){
        new UpdateAccountAsyncTask(recipesDao).execute(account);
    }

    public void delete(Recipes account){
        new DeleteAccountAsyncTask(recipesDao).execute(account);
    }

    public void deleteAllRecipes(){
        new DeleteAllRecipesAsyncTask(recipesDao).execute();
    }

    public LiveData<List<Recipes>> getAllRecipes(){
        return allRecipes;
    }

    private static class InsertAccountAsyncTask extends AsyncTask<Recipes, Void, Void> {
        private RecipesDao RecipesDao;

        private InsertAccountAsyncTask(RecipesDao RecipesDao){
            this.RecipesDao = RecipesDao;
        }

        @Override
        protected Void doInBackground(Recipes... Recipes){
            RecipesDao.insert(Recipes[0]);
            return null;
        }
    }

    private static class UpdateAccountAsyncTask extends AsyncTask<Recipes, Void, Void>{
        private RecipesDao RecipesDao;

        private UpdateAccountAsyncTask(RecipesDao RecipesDao){
            this.RecipesDao = RecipesDao;
        }

        @Override
        protected Void doInBackground(Recipes... Recipes){
            RecipesDao.update(Recipes[0]);
            return null;
        }
    }

    private static class DeleteAccountAsyncTask extends AsyncTask<Recipes, Void, Void>{
        private RecipesDao RecipesDao;

        private DeleteAccountAsyncTask(RecipesDao RecipesDao){
            this.RecipesDao = RecipesDao;
        }

        @Override
        protected Void doInBackground(Recipes... Recipes){
            RecipesDao.delete(Recipes[0]);
            return null;
        }
    }

    private static class DeleteAllRecipesAsyncTask extends AsyncTask<Void, Void, Void>{
        private RecipesDao RecipesDao;

        private DeleteAllRecipesAsyncTask(RecipesDao RecipesDao){
            this.RecipesDao = RecipesDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            RecipesDao.deleteAll();
            return null;
        }
    }
}
