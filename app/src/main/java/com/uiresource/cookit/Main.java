package com.uiresource.cookit;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.SubMenu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.uiresource.cookit.Database.AppDatabase;
import com.uiresource.cookit.Database.Ingredients.IngredientsDao;
import com.uiresource.cookit.Database.Recipes.Recipes;
import com.uiresource.cookit.Database.Recipes.RecipesDao;
import com.uiresource.cookit.Database.Recipes.RecipesViewModel;
import com.uiresource.cookit.utils.CircleGlide;
import com.uiresource.cookit.utils.CustomTypefaceSpan;

import java.util.ArrayList;

public class Main extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Устанавливает главную надпись COOK IT
        setupToolbar(R.id.toolbar, "COOK IT", R.color.colorPink, R.color.colorWhiteTrans, R.drawable.ic_burger);

        // Загружает список рецептов на главный экран (генерит объекты FragmentHome)
        FragmentTransaction ft;
        FragmentHome fragmentHome = new FragmentHome();
        ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, fragmentHome).commit();

        // Создает выдвижную панель DrawerLayout (шторку)
        // Объект toggle отслеживает нажатие кнопки значка для выдвижения шторки
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Применение шрифтов (надо разобраться)
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }
            applyFontToMenuItem(mi);
        }

        // Вставка фото пользователя в шторке
        View header = navigationView.getHeaderView(0);
        ImageView imageView = (ImageView) header.findViewById(R.id.header_account_image);
        Glide.with(this)
                //.load(Uri.parse("https://s3.amazonaws.com/uifaces/faces/twitter/jsa/128.jpg"))
                .load(Uri.parse("https://pp.userapi.com/c623816/v623816502/313c0/VGkX9pCO8tI.jpg"))
                .transform(new CircleGlide(this))
                .into(imageView);
    }
    private void applyFontToMenuItem(MenuItem mi) {
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/SourceSansPro-Semibold.otf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , font), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        mi.setTitle(mNewTitle);
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);  // OPEN DRAWER
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.new_recipe) {

            startActivity(new Intent(this, AddRecipeActivity.class));
            // Handle the camera action
        } else if (id == R.id.recipes) {

            startActivity(new Intent(this, TestActivity.class));

        } else if (id == R.id.saved) {
            startActivity(new Intent(this, UpdateAccountActivity.class));

        } else if (id == R.id.shop_list) {

        } else if (id == R.id.setting) {
            startActivity(new Intent(this, LoginActivity.class));

        }

        // Закрытие шторки после выбора одного из пунктов меню
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public int con(String a){return 0;}
}
