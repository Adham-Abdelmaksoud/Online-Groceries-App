package com.example.online_groceries_app.util;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.online_groceries_app.models.Product;

import java.util.ArrayList;

public class Configs {
    public static Application application;
    public static ArrayList<Product> cartProducts;
    public static ArrayList<Integer> cartProductCounts;
    public static ArrayList<Product> favouriteProducts;
    public static String development_baseUrl="https://fakestoreapi.com";
    public static MutableLiveData<ArrayList<Product>> allProducts;
    public static double totalPrice = 0;
}
