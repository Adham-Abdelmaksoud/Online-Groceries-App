package com.example.online_groceries_app.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.online_groceries_app.R;
import com.example.online_groceries_app.databinding.ItemProductBinding;
import com.example.online_groceries_app.models.Product;
import com.example.online_groceries_app.util.Configs;
import com.example.online_groceries_app.views.activities.ProductDetailActivity;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> implements Filterable {
    private ArrayList<Product> products;
    private ArrayList<Product> fullProducts;
    private ItemProductBinding binding;
    private Context context;
    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Product> filteredProducts = new ArrayList<>();
            if(constraint == null || constraint.length() == 0){
                filteredProducts.addAll(fullProducts);
            }
            else{
                String filterPattern = constraint.toString().toLowerCase().trim();
                for(Product product : fullProducts){
                    if(product.getTitle().toLowerCase().contains(filterPattern)){
                        filteredProducts.add(product);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredProducts;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            products.clear();
            products.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return filter;
    }

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        fullProducts = new ArrayList<>(products);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_product, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        if(product != null){
            binding.productTitle.setText(product.getTitle());
            binding.productPrice.setText("$"+product.getPrice());
            Glide.with(context).load(product.getImage()).into(binding.productImage);
        }
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ItemProductBinding binding;

        public ViewHolder(ItemProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.binding.itemLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*Bundle bundle = new Bundle();
                    bundle.putString("img", products.get(getAdapterPosition()).getImages().get(0));
                    bundle.putString("title", products.get(getAdapterPosition()).getTitle());
                    bundle.putLong("price", products.get(getAdapterPosition()).getPrice());
                    bundle.putDouble("rating", products.get(getAdapterPosition()).getRating());
                    bundle.putString("description", products.get(getAdapterPosition()).getDescription());*/

                    Intent intent = new Intent(context, ProductDetailActivity.class);
                    intent.putExtra("product", products.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
            this.binding.addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!existsInCart(products.get(getAdapterPosition()))){
                        Toast.makeText(context, "Item added to cart", Toast.LENGTH_SHORT).show();
                        Configs.cartProducts.add(products.get(getAdapterPosition()));
                        Configs.cartProductCounts.add(1);
                    }
                    else{
                        Toast.makeText(context, "Item is already in cart", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
        private boolean existsInCart(Product product) {
            for(int i = 0; i<Configs.cartProducts.size() ; i++){
                if(product.getId().intValue() == Configs.cartProducts.get(i).getId().intValue())
                    return true;
            }
            return false;
        }
    }
}
