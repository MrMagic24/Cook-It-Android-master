package com.uiresource.cookit.Database.Accounts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class AccountViewModel extends AndroidViewModel {

    private AccountRepository repository;
    private LiveData<List<AccountList>> allAccounts;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        repository = new AccountRepository(application);
        allAccounts = repository.getAllAccounts();
    }

    public void insert(AccountList account){
        repository.insert(account);
    }

    public void update(AccountList account){
        repository.update(account);
    }

    public void delete(AccountList account){
        repository.delete(account);
    }

    public void deleteAllAccounts(){
        repository.deleteAllAccounts();
    }

    public LiveData<List<AccountList>> getAllAccounts(){
        return allAccounts;
    }
}
