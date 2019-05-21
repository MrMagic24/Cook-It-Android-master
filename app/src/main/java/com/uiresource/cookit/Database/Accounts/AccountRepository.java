package com.uiresource.cookit.Database.Accounts;

import android.accounts.Account;
import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Update;
import android.os.AsyncTask;

import com.uiresource.cookit.Database.AppDatabase;

import java.util.List;

public class AccountRepository {
    private AccountListDao AccountDao;
    private LiveData<List<AccountList>> allAccounts;

    public AccountRepository(Application application){
        AppDatabase database = AppDatabase.getInstance(application);
        AccountDao = database.accountListDao();
        allAccounts = AccountDao.getAllAccounts();
    }

    public void insert(AccountList account){
        new InsertAccountAsyncTask(AccountDao).execute(account);
    }

    public void update(AccountList account){
        new UpdateAccountAsyncTask(AccountDao).execute(account);
    }

    public void delete(AccountList account){
        new DeleteAccountAsyncTask(AccountDao).execute(account);
    }

    public void deleteAllAccounts(){
        new DeleteAccountAsyncTask(AccountDao).execute();
    }

    public LiveData<List<AccountList>> getAllAccounts(){
        return allAccounts;
    }

    private static class InsertAccountAsyncTask extends AsyncTask<AccountList, Void, Void>{
        private AccountListDao accountListDao;

        private InsertAccountAsyncTask(AccountListDao accountListDao){
            this.accountListDao = accountListDao;
        }

        @Override
        protected Void doInBackground(AccountList... Accounts){
            accountListDao.insert(Accounts[0]);
            return null;
        }
    }

    private static class UpdateAccountAsyncTask extends AsyncTask<AccountList, Void, Void>{
        private AccountListDao accountListDao;

        private UpdateAccountAsyncTask(AccountListDao accountListDao){
            this.accountListDao = accountListDao;
        }

        @Override
        protected Void doInBackground(AccountList... Accounts){
            accountListDao.update(Accounts[0]);
            return null;
        }
    }

    private static class DeleteAccountAsyncTask extends AsyncTask<AccountList, Void, Void>{
        private AccountListDao accountListDao;

        private DeleteAccountAsyncTask(AccountListDao accountListDao){
            this.accountListDao = accountListDao;
        }

        @Override
        protected Void doInBackground(AccountList... Accounts){
            accountListDao.delete(Accounts[0]);
            return null;
        }
    }

    private static class DeleteAllAccountsAsyncTask extends AsyncTask<Void, Void, Void>{
        private AccountListDao accountListDao;

        private DeleteAllAccountsAsyncTask(AccountListDao accountListDao){
            this.accountListDao = accountListDao;
        }

        @Override
        protected Void doInBackground(Void... voids){
            accountListDao.deleteAll();
            return null;
        }
    }
}
