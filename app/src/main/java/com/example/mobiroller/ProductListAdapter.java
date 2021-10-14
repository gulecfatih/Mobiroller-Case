package com.example.mobiroller;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.textclassifier.ConversationAction;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder>  {
    List<ProductListModel> list;
    Context context;

    public ProductListAdapter(List<ProductListModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_list_cardview,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductListAdapter.ViewHolder holder, int position) {
    holder.productName.setText(list.get(position).get_productName());
    holder.productDescription.setText(list.get(position).get_productDescription());
    holder.categoryName.setText(list.get(position).get_category());
    holder.uploadDate.setText(list.get(position).get_uploadTime());
    holder.price.setText(String.valueOf(list.get(position).get_price()));
        Glide.with(context)
                .load(list.get(position).get_img())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView productDescription;
        TextView categoryName;
        TextView uploadDate;
        TextView price;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView){

            super(itemView);
            productName =itemView.findViewById(R.id.productName);
            productDescription =itemView.findViewById(R.id.productDescription);
            categoryName =itemView.findViewById(R.id.categoryName);
            uploadDate =itemView.findViewById(R.id.uploadDate);
            price =itemView.findViewById(R.id.price);
            imageView =itemView.findViewById(R.id.productImage);

        }

    }


}

