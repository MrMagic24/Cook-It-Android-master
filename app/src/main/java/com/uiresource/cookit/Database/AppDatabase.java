package com.uiresource.cookit.Database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.uiresource.cookit.Database.Accounts.AccountList;
import com.uiresource.cookit.Database.Accounts.AccountListDao;
import com.uiresource.cookit.Database.Ingredients.Ingredients;
import com.uiresource.cookit.Database.Ingredients.IngredientsDao;
import com.uiresource.cookit.Database.Recipes.Recipes;
import com.uiresource.cookit.Database.Recipes.RecipesDao;

@Database(entities = {AccountList.class, Ingredients.class, Recipes.class}, version = 6, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract AccountListDao accountListDao();
    public abstract IngredientsDao ingredientsDao();
    public abstract RecipesDao recipesDao();

    public static synchronized AppDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsynkTask(instance).execute();
        }
    };

    private static class PopulateDbAsynkTask extends AsyncTask<Void, Void, Void>{
        private AccountListDao accountListDao;
        private IngredientsDao ingredientsDao;
        private RecipesDao recipesDao;

        private PopulateDbAsynkTask(AppDatabase db){
            accountListDao = db.accountListDao();
            ingredientsDao = db.ingredientsDao();
            recipesDao = db.recipesDao();
        }

        @Override
        protected Void doInBackground(Void... voids){

            accountListDao.deleteAll();
            //accountListDao.insert(new AccountList("44d838a0-d66b-4884-8b40-7bd17f281656", "kek", "svin", "","","", true, 0,0,0));
            //accountListDao.insert(new AccountList("9de8f6ee-552a-454e-8431-1c58ffe0f36f","hey@mail.ru", "Yo Man", "svinGames", "","", true, 0,0,0));
            return null;
        }
    }
}
