package com.example.online_groceries_app.views.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.online_groceries_app.R;
import com.example.online_groceries_app.adapters.CartAdapter;
import com.example.online_groceries_app.databinding.FragmentCartBinding;
import com.example.online_groceries_app.util.Configs;
import com.example.online_groceries_app.views.dialogs.CheckoutDialog;

public class CartFragment extends Fragment {
    private CartAdapter adapter;
    private FragmentCartBinding binding;
    private Handler handler;
    private Runnable runnable;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cart, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        handler = new Handler();
        handler.postDelayed(runnable = new Runnable() {
            @Override
            public void run() {
                binding.totalPrice.setText("$" + String.format("%.2f", Configs.totalPrice));
                handler.postDelayed(this, 500);
            }
        }, 500);
        super.onResume();
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Configs.totalPrice = getTotalPrice();

        adapter = new CartAdapter(Configs.cartProducts, getActivity());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerMyCart.setLayoutManager(layoutManager);
        binding.recyclerMyCart.setItemAnimator(new DefaultItemAnimator());
        binding.recyclerMyCart.setAdapter(adapter);

        binding.checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckoutDialog dialog = new CheckoutDialog(getTotalPrice());
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                transaction.add(android.R.id.content, dialog,"MemberDialog").addToBackStack(null).commit();
            }
        });
    }

    public double getTotalPrice() {
        double total = 0;
        for(int i=0 ; i<Configs.cartProducts.size() ; i++){
            double price = Configs.cartProducts.get(i).getPrice();
            int count = Configs.cartProductCounts.get(i);
            total += (price*count);
        }
        return total;
    }
}