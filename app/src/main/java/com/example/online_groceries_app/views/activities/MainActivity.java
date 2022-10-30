package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.retrofit.repo.ProductsApiRepository;
import com.example.online_groceries_app.util.Configs;
import com.example.online_groceries_app.util.SessionManagement;

public class MainActivity extends AppCompatActivity {

    private Handler handler;
    private SessionManagement management;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        management = new SessionManagement(getApplicationContext());
        management.logout();

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                ProductsApiRepository apiRepository = new ProductsApiRepository();
                Configs.allProducts = apiRepository.callAPI();
                return null;
            }
            @Override
            protected void onPostExecute(Void result) {
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, OnBoardingActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }, 3000);
            }
        }.execute();
    }
}