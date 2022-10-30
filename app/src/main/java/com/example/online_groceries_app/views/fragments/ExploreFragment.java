package com.example.online_groceries_app.views.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.adapters.ProductAdapter;
import com.example.online_groceries_app.databinding.FragmentExploreBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.viewmodel.ProductViewModel;

import java.util.ArrayList;

public class ExploreFragment extends Fragment {
    private ProductViewModel productViewModel;
    private ProductAdapter adapter;
    private FragmentExploreBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_explore, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        if (productViewModel!=null){
            productViewModel.getProductsList().observe(getViewLifecycleOwner(), new Observer<ArrayList<Product>>() {
                @Override
                public void onChanged(ArrayList<Product> products) {
                    for (int i=0; i < products.size(); i++){
                        /*Log.e("entries num "+ i +" : ","Title: "+products.get(i).getTitle()+"\n"
                                +"Description: "+products.get(i).getDescription()+"\n"
                                +"Price: "+products.get(i).getPrice()+"\n"
                                +"Images: "+products.get(i).getImage());*/
                    }
                    ArrayList<Product> tmpProducts = new ArrayList<>(products);
                    adapter = new ProductAdapter(tmpProducts, getActivity());
                    RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
                    binding.recyclerView.setLayoutManager(layoutManager);
                    binding.recyclerView.setItemAnimator(new DefaultItemAnimator());
                    binding.recyclerView.setAdapter(adapter);
                }
            });
        }

        binding.searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}