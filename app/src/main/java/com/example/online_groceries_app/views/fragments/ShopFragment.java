package com.example.online_groceries_app.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.adapters.ProductAdapter;
import com.example.online_groceries_app.databinding.FragmentShopBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.util.SessionManagement;
import com.example.online_groceries_app.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class ShopFragment extends Fragment {
    private FragmentShopBinding binding;
    private SessionManagement management;
    private ProductViewModel productViewModel;
    private ProductAdapter adapter1;
    private ProductAdapter adapter2;
    private ProductAdapter adapter3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shop, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        management = new SessionManagement(getContext().getApplicationContext());
        binding.addressTxt.setText(management.getLocation());

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        if (productViewModel!=null){
            productViewModel.getProductsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                @Override
                public void onChanged(ArrayList<Product> products) {
                    /*for (int i=0; i < products.size(); i++){
                        Log.e("entries num "+ i +" : ","Title: "+products.get(i).getTitle()+"\n"
                                +"Description: "+products.get(i).getDescription()+"\n"
                                +"Price: "+products.get(i).getPrice()+"\n"
                                +"Images: "+products.get(i).getImage());
                    }*/
                    adapter1 = new ProductAdapter(products, getActivity());
                    RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerExclusiveOffer.setLayoutManager(layoutManager1);
                    binding.recyclerExclusiveOffer.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerExclusiveOffer.setAdapter(adapter1);

                    adapter2 = new ProductAdapter(products, getActivity());
                    RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerBestSelling.setLayoutManager(layoutManager2);
                    binding.recyclerBestSelling.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerBestSelling.setAdapter(adapter2);

                    adapter3 = new ProductAdapter(products, getActivity());
                    RecyclerView.LayoutManager layoutManager3 = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
                    binding.recyclerGroceries.setLayoutManager(layoutManager3);
                    binding.recyclerGroceries.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerGroceries.setAdapter(adapter3);
                }
            });
        }
    }
}