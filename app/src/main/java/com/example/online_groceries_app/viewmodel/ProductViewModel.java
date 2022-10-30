package com.example.online_groceries_app.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.retrofit.repo.ProductsApiRepository;
import com.example.online_groceries_app.util.Configs;

import java.util.ArrayList;

public class ProductViewModel extends AndroidViewModel {
    private final MediatorLiveData<ArrayList<Product>> mediatorLiveDataProductsList;
    private final ProductsApiRepository apiRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
        Configs.application = application;
        this.mediatorLiveDataProductsList = new MediatorLiveData<>();
        this.mediatorLiveDataProductsList.setValue(null);
        this.apiRepository = new ProductsApiRepository();
    }

    public MutableLiveData<ArrayList<Product>> getProductsList(){
        return Configs.allProducts;
    }
}