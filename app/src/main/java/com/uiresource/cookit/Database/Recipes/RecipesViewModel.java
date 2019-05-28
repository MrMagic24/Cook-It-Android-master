package com.uiresource.cookit.Database.Recipes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class RecipesViewModel extends AndroidViewModel {
    private RecipesRepository repository;
    private LiveData<List<Recipes>> allRecipes;

    public RecipesViewModel(@NonNull Application application) {
        super(application);
        repository = new RecipesRepository(application);
        allRecipes = repository.getAllRecipes();
    }

    public void insert(Recipes account){
        repository.insert(account);
    }

    public void update(Recipes account){
        repository.update(account);
    }

    public void delete(Recipes account){
        repository.delete(account);
    }

    public void deleteAllRecipes(){
        repository.deleteAllRecipes();
    }

    public LiveData<List<Recipes>> getAllRecipes(){
        return allRecipes;
    }
}
