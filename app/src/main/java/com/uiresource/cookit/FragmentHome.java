package com.uiresource.cookit;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.uiresource.cookit.Database.AppDatabase;
import com.uiresource.cookit.Database.Recipes.Recipes;
import com.uiresource.cookit.Database.Recipes.RecipesAdapter;
import com.uiresource.cookit.Database.Recipes.RecipesDao;
import com.uiresource.cookit.Database.Recipes.RecipesViewModel;
import com.uiresource.cookit.recycler.ItemRecipe;
import com.uiresource.cookit.recycler.RecipeAdapter;
import com.uiresource.cookit.recycler.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Dytstudio.
 */

public class FragmentHome extends Fragment{
    private List<ItemRecipe> itemList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecipeAdapter mAdapter;
    private AppCompatActivity appCompatActivity;

    private RecipesViewModel recipesViewModel;
    List<Recipes> resi;

    public FragmentHome(){
        setHasOptionsMenu(true);
    }
    public void onCreate(Bundle a){
        super.onCreate(a);
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, null, false);

        ((Main)getActivity()).setupToolbar(R.id.toolbar, "DESSERT", R.color.colorPink, R.color.colorWhiteTrans, R.drawable.ic_burger);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        /*recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        resi = recipesViewModel.getAllRecipes().getValue();*/

        //mAdapter = new RecipeAdapter(setupRecipe(), getActivity());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), 2);
        mLayoutManager.setAutoMeasureEnabled(true);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setAdapter(mAdapter);

        appCompatActivity = (AppCompatActivity) getActivity();

        final RecipesAdapter adapter = new RecipesAdapter();
        recyclerView.setAdapter(adapter);

        recipesViewModel = ViewModelProviders.of(this).get(RecipesViewModel.class);
        //accountViewModel.deleteAllAccounts();
        recipesViewModel.getAllRecipes().observe(this, new Observer<List<Recipes>>() {
            @Override
            public void onChanged(@Nullable List<Recipes> recipes) {
                adapter.submitList(recipes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                recipesViewModel.delete(adapter.getRecipeAt(viewHolder.getAdapterPosition()));
                Toast.makeText(appCompatActivity,"Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new RecipesAdapter.onItemClickListener() {
            @Override
            public void onItemClick(Recipes account) {

            }
        });

        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Detail.class);
                int i = 2;
                intent.putExtra("key", 1);
                startActivity(new Intent(getActivity(), Detail.class));
                    //Detail.navigate(appCompatActivity, view.findViewById(R.id.iv_recipe));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        return view;
    }

    // Список рецептов ЗДЕСЬ!!11!!
    private List<ItemRecipe> setupRecipe(){
        itemList = new ArrayList<>();



        String recipe[] = {"BLOOD ORANGE CAKE", "SEMIFREDDO TIRAMISU", "MARBLE CAKE", "RICE PUDDING", "RAINBOW CAKE", "ICE CREAM", "STROWBERRY CAKE", "CUPCAKE FRUIT"};
        String img[] = {"https://images.pexels.com/photos/53468/dessert-orange-food-chocolate-53468.jpeg?h=350&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/159887/pexels-photo-159887.jpeg?h=350&auto=compress",
                "https://images.pexels.com/photos/136745/pexels-photo-136745.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/39355/dessert-raspberry-leaf-almond-39355.jpeg?h=350&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/239578/pexels-photo-239578.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/8382/pexels-photo.jpg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/51186/pexels-photo-51186.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb",
                "https://images.pexels.com/photos/55809/dessert-strawberry-tart-berry-55809.jpeg?w=1260&h=750&auto=compress&cs=tinysrgb"};
        String time[] = {"1h 5'", "30m", "1h 10'", "50m", "20m", "1h 20'", "20m", "1h 20'"};
        float rating[] = {3, 4, 4, 3, 5, 4, 4, 3};

        for (int i = 0; i<recipe.length; i++){
        //for (int i = 0; i<res.size(); i++){
            ItemRecipe item = new ItemRecipe();
            item.setRecipe(recipe[i]);
            item.setTime(time[i]);
            item.setRating(rating[i]);
            item.setImg(img[i]);

           /* ItemRecipe item = new ItemRecipe();
            item.setRecipe(res.get(i).getName());
            item.setTime(res.get(i).getTimeForCooking());
            item.setRating((float) res.get(i).getRate());
            item.setImg(res.get(i).getPathToPhotos());*/
            itemList.add(item);
        }
        return itemList;
    }


    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_home, menu);
    }
}
