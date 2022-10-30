package com.example.online_groceries_app.retrofit.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.retrofit.CallService;
import com.example.online_groceries_app.retrofit.Http;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiRepository {
    private final MutableLiveData<ArrayList<Product>> allProducts;

    public ProductsApiRepository() { //application is subclass of context
        allProducts = new MutableLiveData<>();
    }

    public MutableLiveData<ArrayList<Product>> callAPI(){
        Call<ArrayList<Product>> call = Http.create(CallService.class).getProducts();
        call.enqueue(new Callback<ArrayList<Product>>() {
            @Override
            public void onResponse(Call<ArrayList<Product>> call, Response<ArrayList<Product>> response) {
                ArrayList<Product> data = response.body();
                if (data!=null){
                    allProducts.setValue(data);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Product>> call, Throwable t) {
                Log.e("ConnectionFailure", t.toString());
            }
        });
        return allProducts;
    }
}
