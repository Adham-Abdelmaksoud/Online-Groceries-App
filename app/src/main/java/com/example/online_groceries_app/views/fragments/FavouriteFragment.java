package com.example.online_groceries_app.views.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.adapters.FavouriteAdapter;
import com.example.online_groceries_app.databinding.FragmentFavouriteBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.util.Configs;

public class FavouriteFragment extends Fragment {
    private FragmentFavouriteBinding binding;
    private FavouriteAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourite, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new FavouriteAdapter(Configs.favouriteProducts, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerFavourite.setLayoutManager(layoutManager);
        binding.recyclerFavourite.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerFavourite.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        binding.addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0 ; i<Configs.favouriteProducts.size() ; i++){
                    if(!existsInCart(Configs.favouriteProducts.get(i))){
                        Configs.cartProducts.add(Configs.favouriteProducts.get(i));
                        Configs.cartProductCounts.add(1);
                    }
                }
                Toast.makeText(getContext(), "Items added to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean existsInCart(Product product) {
        for(int i=0 ; i<Configs.cartProducts.size() ; i++){
            if(product.getId().intValue() == Configs.cartProducts.get(i).getId().intValue())
                return true;
        }
        return false;
    }
}