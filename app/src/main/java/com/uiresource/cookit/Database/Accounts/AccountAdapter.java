package com.uiresource.cookit.Database.Accounts;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.uiresource.cookit.R;

import java.util.ArrayList;
import java.util.List;

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {

    private List<AccountList> accounts = new ArrayList<>();
    private onItemClickListener listener;

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
        holder.textViewID.setText(currentAccount.getIdServer());
        holder.textViewEmail.setText(currentAccount.getEmail());
        holder.textViewUsername.setText(currentAccount.getUserName());
    }

    @Override
    public int getItemCount() {
        return accounts.size();
    }

    public void setAccounts(List<AccountList> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }

    public AccountList getAccountAt(int position) {
        return accounts.get(position);
    }

    class AccountHolder extends RecyclerView.ViewHolder {
        private TextView textViewID;
        private TextView textViewEmail;
        private TextView textViewUsername;

        public AccountHolder(View itemView) {
            super(itemView);
            textViewID = itemView.findViewById(R.id.text_view_ID);
            textViewEmail = itemView.findViewById(R.id.text_view_Email);
            textViewUsername = itemView.findViewById(R.id.text_view_username);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION){
                        listener.onItemClick(accounts.get(position));
                    }
                }
            });
        }
    }

    public interface onItemClickListener {
        void onItemClick(AccountList account);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
