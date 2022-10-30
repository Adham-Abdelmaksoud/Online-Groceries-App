package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityOrderAcceptedBinding;

public class OrderAcceptedActivity extends AppCompatActivity {
    private ActivityOrderAcceptedBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_order_accepted);

        binding.backHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(OrderAcceptedActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });
    }
}