package com.example.online_groceries_app.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ItemCartBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.util.Configs;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder>{
    private ArrayList<Product> products;
    private ItemCartBinding binding;
    private Context context;

    public CartAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_cart, parent, false);
        return new CartAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        if(product != null){
            binding.itemTitle.setText(product.getTitle());
            Glide.with(context).load(product.getImage()).into(binding.productImg);

            double price = product.getPrice()*Configs.cartProductCounts.get(position);
            binding.itemPrice.setText("$"+String.format("%.2f", price));
            binding.counter.setText(String.valueOf(Configs.cartProductCounts.get(position)));
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemCartBinding binding;

        public ViewHolder(ItemCartBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            this.binding.incrementBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int count = Configs.cartProductCounts.get(getAdapterPosition());
                    count++;
                    Configs.cartProductCounts.set(getAdapterPosition(), count);
                    binding.counter.setText(String.valueOf(count));

                    double price = products.get(getAdapterPosition()).getPrice()*count;
                    binding.itemPrice.setText("$"+String.format("%.2f", price));

                    Configs.totalPrice = getTotalPrice();
                }
            });
            this.binding.decrementBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    int count = Configs.cartProductCounts.get(getAdapterPosition());
                    if(count>1){
                        count--;
                        Configs.cartProductCounts.set(getAdapterPosition(), count);
                        binding.counter.setText(String.valueOf(count));

                        double price = products.get(getAdapterPosition()).getPrice()*count;
                        binding.itemPrice.setText("$"+String.format("%.2f", price));

                        Configs.totalPrice = getTotalPrice();
                    }
                }
            });
            this.binding.remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Item removed from cart", Toast.LENGTH_LONG).show();
                    Configs.cartProducts.remove(getAdapterPosition());
                    Configs.cartProductCounts.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());

                    Configs.totalPrice = getTotalPrice();
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
}
