package com.example.dolphinlive.UI;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dolphinlive.Entities.Product;
import com.example.dolphinlive.R;

import java.util.List;

/**
 * Project: Dolphin Live
 * Package: android.carolynbicycleshop.dolphinlive.UI
 * <p>
 * User: carolyn.sher
 * Date: 12/10/2022
 * Time: 3:43 PM
 * <p>
 * Created with Android Studio
 * To change this template use File | Settings | File Templates.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    class ProductViewHolder extends RecyclerView.ViewHolder {
        private final TextView productItemView;
        private  ProductViewHolder(View itemview){
            super(itemview);
            productItemView=itemview.findViewById(R.id.textView2);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Product current=mProducts.get(position);
                    Intent intent= new Intent(context,ProductDetails.class);
                    intent.putExtra("id", current.getProductID());
                    intent.putExtra("name", current.getProductName());
                    intent.putExtra("price", current.getProductPrice());
                    context.startActivity(intent);
                }
            });
        }
    }
    private List<Product> mProducts;
    private final Context context;
    private final LayoutInflater mInflater;
    public ProductAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }
    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.product_list_item,parent,false);
        return new ProductViewHolder((itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        if(mProducts!=null){
            Product current=mProducts.get(position);
            String name=current.getProductName();
            holder.productItemView.setText(name);
        }
        else{
            holder.productItemView.setText("No Product Name");
        }

    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public void setProducts(List<Product> products){
        mProducts=products;
        notifyDataSetChanged();
    }
}