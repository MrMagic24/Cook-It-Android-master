package com.uiresource.cookit.Database.Accounts;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.uiresource.cookit.Database.ImportFromJSON;
import com.uiresource.cookit.R;

import java.util.ArrayList;
import java.util.List;

import static com.uiresource.cookit.Database.ImportFromJSON.AccountGetList;

public class AccountAdapter extends ListAdapter<AccountList, AccountAdapter.AccountHolder> {

    private List<AccountList> accounts = new ArrayList<>();
    private onItemClickListener listener;

    public AccountAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<AccountList> DIFF_CALLBACK = new DiffUtil.ItemCallback<AccountList>() {
        @Override
        public boolean areItemsTheSame(AccountList oldItem, @NonNull AccountList newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(AccountList oldItem, AccountList newItem) {
            return oldItem.getUserName().equals(newItem.getUserName()) &&
                    oldItem.getEmail().equals(newItem.getEmail()) &&
                    oldItem.getId().equals(newItem.getId());
        }

        /*@Override
        public boolean areItemsTheSame(@NonNull AccountList accountList, @NonNull AccountList t1) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull AccountList accountList, @NonNull AccountList t1) {
            return false;
        }*/
    };

    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acc_item, parent, false);
        return new AccountHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        AccountList currentAccount = getItem(position);
        holder.textViewID.setText(currentAccount.getId());
        holder.textViewEmail.setText(currentAccount.getEmail());
        holder.textViewUsername.setText(currentAccount.getUserName());
    }

    /*@Override
    public int getItemCount() {
        return accounts.size();
    }*/

    public int getItemsFirst() {
        return accounts.size();
    }

    /*public void setAccounts(List<AccountList> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }*/

    public AccountList getAccountAt(int position) {
        return getItem(position);
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
                        listener.onItemClick(getItem(position));
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
