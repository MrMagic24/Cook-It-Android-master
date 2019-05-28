package com.uiresource.cookit.Database.Recipes;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.uiresource.cookit.R;

import java.util.ArrayList;
import java.util.List;

public class RecipesAdapter extends ListAdapter<Recipes, RecipesAdapter.RecipesHolder> {

    private List<Recipes> recipes = new ArrayList<>();
    private onItemClickListener listener;

    public RecipesAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Recipes> DIFF_CALLBACK = new DiffUtil.ItemCallback<Recipes>() {
        @Override
        public boolean areItemsTheSame(Recipes oldItem, @NonNull Recipes newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(Recipes oldItem, Recipes newItem) {
            return oldItem.getName().equals(newItem.getName()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getId().equals(newItem.getId());
        }

        /*@Override
        public boolean areItemsTheSame(@NonNull Recipes Recipes, @NonNull Recipes t1) {
            return false;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Recipes Recipes, @NonNull Recipes t1) {
            return false;
        }*/
    };

    @NonNull
    @Override
    public RecipesHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.acc_item, parent, false);
        return new RecipesHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipesHolder holder, int position) {
        Recipes currentAccount = getItem(position);
        holder.textViewID.setText(currentAccount.getId());
        holder.textViewEmail.setText(currentAccount.description);
        holder.textViewUsername.setText(currentAccount.getName());
    }

    /*@Override
    public int getItemCount() {
        return accounts.size();
    }*/

    public int getItemsCount() {
        return recipes.size();
    }

    /*public void setAccounts(List<Recipes> accounts) {
        this.accounts = accounts;
        notifyDataSetChanged();
    }*/

    public Recipes getRecipeAt(int position) {
        return getItem(position);
    }

    class RecipesHolder extends RecyclerView.ViewHolder {
        private TextView textViewID;
        private TextView textViewEmail;
        private TextView textViewUsername;

        public RecipesHolder(View itemView) {
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
        void onItemClick(Recipes account);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;
    }
}
