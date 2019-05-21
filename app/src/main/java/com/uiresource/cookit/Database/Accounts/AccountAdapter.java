package com.uiresource.cookit.Database.Accounts;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uiresource.cookit.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    private List<AccountList> accounts = new ArrayList<>();

    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acc_item, parent, false);
        return new AccountHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        AccountList currentAccount = accounts.get(position);
        holder.textViewID.setText(currentAccount.getId());
        holder.textViewEmail.setText(currentAccount.getEmail());
        holder.textViewUsername.setText(currentAccount.getUserName());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setAccounts(List<AccountList> accounts){
        this.accounts = accounts;
        notifyDataSetChanged();
    }

    class AccountHolder extends RecyclerView.ViewHolder{
        private TextView textViewID;
        private TextView textViewEmail;
        private TextView textViewUsername;

        public AccountHolder(View itemView){
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_ID);
            textViewEmail = itemView.findViewById(R.id.text_view_Email);
            textViewUsername = itemView.findViewById(R.id.text_view_username);
        }
    }
}
