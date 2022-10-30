package com.example.online_groceries_app.retrofit;

import com.example.online_groceries_app.models.Product;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CallService {
    @GET("/products")
    Call<ArrayList<Product>> getProducts();
}