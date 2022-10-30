package com.example.online_groceries_app.views.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ActivityProductDetailBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.util.Configs;

public class ProductDetailActivity extends AppCompatActivity {

    private ActivityProductDetailBinding binding;
    private int count = 1;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);

        product = (Product)(getIntent().getSerializableExtra("product"));
        binding.productTitle.setText(product.getTitle());
        binding.productPrice.setText("$"+product.getPrice());
        binding.productDetails.setText(product.getDescription());
        binding.ratingBar.setRating(product.getRating().getRate());
        Glide.with(getApplicationContext()).load(product.getImage()).into(binding.productImg);

        int index = existsInFavourite(product);
        if(index == -1){
            binding.heartIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_gray));
        }
        else{
            binding.heartIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_red));
        }

        binding.incrementBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                count++;
                binding.counter.setText(String.valueOf(count));
                binding.productPrice.setText("$"+product.getPrice()*count);
            }
        });
        binding.decrementBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(count>1){
                    count--;
                    binding.counter.setText(String.valueOf(count));
                    binding.productPrice.setText("$"+product.getPrice()*count);
                }
            }
        });

        binding.backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetailActivity.this, BottomNavigationActivity.class);
                startActivity(intent);
            }
        });

        binding.addToBasketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!existsInCart(product)){
                    Toast.makeText(ProductDetailActivity.this, "Item added to cart", Toast.LENGTH_LONG).show();
                    Configs.cartProducts.add(product);
                    Configs.cartProductCounts.add(count);
                }
                else{
                    Toast.makeText(ProductDetailActivity.this, "Item is already in cart", Toast.LENGTH_LONG).show();
                }
            }
        });

        binding.heartIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index = existsInFavourite(product);
                if(index == -1){
                    binding.heartIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_red));
                    Configs.favouriteProducts.add(product);
                }
                else{
                    binding.heartIcon.setImageDrawable(getDrawable(R.drawable.ic_heart_gray));
                    Configs.favouriteProducts.remove(index);
                }
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
    private int existsInFavourite(Product product) {
        for(int i=0 ; i<Configs.favouriteProducts.size() ; i++){
            if(product.getId().intValue() == Configs.favouriteProducts.get(i).getId().intValue())
                return i;
        }
        return -1;
    }
}