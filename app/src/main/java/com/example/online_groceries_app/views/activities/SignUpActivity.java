package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivitySignUpBinding;
import com.example.online_groceries_app.util.Configs;

import java.util.ArrayList;

public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        binding.signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Configs.cartProducts = new ArrayList<>();
                Configs.favouriteProducts = new ArrayList<>();
                Intent intent = new Intent(SignUpActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}