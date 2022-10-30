package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityLoginBinding;
import com.example.online_groceries_app.util.Configs;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configs.cartProducts = new ArrayList<>();
                Configs.favouriteProducts = new ArrayList<>();
                Configs.cartProductCounts = new ArrayList<>();
                Intent intent = new Intent(LoginActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}