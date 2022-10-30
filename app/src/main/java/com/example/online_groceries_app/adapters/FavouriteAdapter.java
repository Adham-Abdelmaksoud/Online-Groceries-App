package com.example.online_groceries_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ItemFavouriteBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.views.activities.ProductDetailActivity;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.ViewHolder>{
    private ArrayList<Product> products;
    private ItemFavouriteBinding binding;
    private Context context;

    public FavouriteAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_favourite, parent, false);
        return new FavouriteAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        if(product != null){
            binding.itemTitle.setText(product.getTitle());
            binding.itemPrice.setText("$"+product.getPrice());
            Glide.with(context).load(product.getImage()).into(binding.productImg);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemFavouriteBinding binding;

        public ViewHolder(ItemFavouriteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.rightArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product", products.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
