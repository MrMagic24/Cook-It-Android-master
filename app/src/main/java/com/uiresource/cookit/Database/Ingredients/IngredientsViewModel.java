package com.uiresource.cookit.Database.Ingredients;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class IngredientsViewModel extends AndroidViewModel {
    private IngredientsRepository repository;
    private LiveData<List<Ingredients>> allIngredients;

    public IngredientsViewModel(@NonNull Application application) {
        super(application);
        repository = new IngredientsRepository(application);
        allIngredients = repository.getAllIngredients();
    }

    public void insert(Ingredients ingredient){
        repository.insert(ingredient);
    }

    public void update(Ingredients ingredient){
        repository.update(ingredient);
    }

    public void delete(Ingredients ingredient){
        repository.delete(ingredient);
    }

    public void deleteAllIngredients(){
        repository.deleteAllIngredients();
    }

    public LiveData<List<Ingredients>> getAllIngredients(){
        return allIngredients;
    }
}
