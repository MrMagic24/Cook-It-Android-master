package com.uiresource.cookit.Database.Ingredients;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.uiresource.cookit.Database.AppDatabase;

import java.util.List;

public class IngredientsRepository {
    private IngredientsDao ingredientsDao;
    private LiveData<List<Ingredients>> allIngredients;

    public IngredientsRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        ingredientsDao = database.ingredientsDao();
        allIngredients = ingredientsDao.getAllIngredients();
    }

    public void insert(Ingredients Ingredients){
        new InsertIngredientsAsyncTask(ingredientsDao).execute(Ingredients);
    }

    public void update(Ingredients Ingredients){
        new UpdateIngredientsAsyncTask(ingredientsDao).execute(Ingredients);
    }

    public void delete(Ingredients Ingredients){
        new DeleteIngredientsAsyncTask(ingredientsDao).execute(Ingredients);
    }

    public void deleteAllIngredients(){
        new DeleteAllIngredientsAsyncTask(ingredientsDao).execute();
    }

    public LiveData<List<Ingredients>> getAllIngredients(){
        return allIngredients;
    }

    private static class InsertIngredientsAsyncTask extends AsyncTask<Ingredients, Void, Void> {
        private IngredientsDao IngredientsDao;

        private InsertIngredientsAsyncTask(IngredientsDao IngredientsDao){
            this.IngredientsDao = IngredientsDao;
        }

        @Override
        protected Void doInBackground(Ingredients... Ingredientss){
            IngredientsDao.insert(Ingredientss[0]);
            return null;
        }
    }

    private static class UpdateIngredientsAsyncTask extends AsyncTask<Ingredients, Void, Void>{
        private IngredientsDao IngredientsDao;

        private UpdateIngredientsAsyncTask(IngredientsDao IngredientsDao){
            this.IngredientsDao = IngredientsDao;
        }

        @Override
        protected Void doInBackground(Ingredients... Ingredientss){
            IngredientsDao.update(Ingredientss[0]);
            return null;
        }
    }

    private static class DeleteIngredientsAsyncTask extends AsyncTask<Ingredients, Void, Void>{
        private IngredientsDao IngredientsDao;

        private DeleteIngredientsAsyncTask(IngredientsDao IngredientsDao){
            this.IngredientsDao = IngredientsDao;
        }

        @Override
        protected Void doInBackground(Ingredients... Ingredientss){
            IngredientsDao.delete(Ingredientss[0]);
            return null;
        }
    }

    private static class DeleteAllIngredientsAsyncTask extends AsyncTask<Void, Void, Void>{
        private IngredientsDao IngredientsDao;

        private DeleteAllIngredientsAsyncTask(IngredientsDao IngredientsDao){
            this.IngredientsDao = IngredientsDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            IngredientsDao.deleteAll();
            return null;
        }
    }
}
