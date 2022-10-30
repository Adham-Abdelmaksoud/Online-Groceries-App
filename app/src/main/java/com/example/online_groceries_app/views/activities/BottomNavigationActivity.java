package com.example.online_groceries_app.views.activities;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityBottomNavigationBinding;
import com.example.online_groceries_app.views.fragments.AccountFragment;
import com.example.online_groceries_app.views.fragments.CartFragment;
import com.example.online_groceries_app.views.fragments.ExploreFragment;
import com.example.online_groceries_app.views.fragments.FavouriteFragment;
import com.example.online_groceries_app.views.fragments.ShopFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BottomNavigationActivity extends AppCompatActivity {

    private ActivityBottomNavigationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ShopFragment()).commit();
        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.shopTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ShopFragment()).commit();
                        return true;
                    case R.id.exploreTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new ExploreFragment()).commit();
                        return true;
                    case R.id.cartTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new CartFragment()).commit();
                        return true;
                    case R.id.favouriteTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new FavouriteFragment()).commit();
                        return true;
                    case R.id.accountTab:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new AccountFragment()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}