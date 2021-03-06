package com.example.mobiroller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> implements Filterable {
    List<ProductListModel> list;
    List<ProductListModel> FilterList;
    Context context;


    public ProductListAdapter(List<ProductListModel> list,Context context) {
        this.list = list;
        this.context = context;
        this.FilterList = new ArrayList<>(list);
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
            holder.price.setText("Price: " +list.get(position).get_price());
            Glide.with(context)
                    .load(list.get(position).get_img())
                    .into(holder.imageView);



        String productName = list.get(position).get_productName();
        String productDescription = list.get(position).get_productDescription();
        String price = list.get(position).get_price();
        String categoryName = list.get(position).get_category();
        String uploadTime = list.get(position).get_uploadTime();
        String img = list.get(position).get_img();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(context,DetailScreenActivity.class);
                intent.putExtra("productName",productName );
                intent.putExtra("price",price );
                intent.putExtra("categoryName",categoryName );
                intent.putExtra("productDescription",productDescription );
                intent.putExtra("uploadDate",uploadTime );
                intent.putExtra("img",img );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<ProductListModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (ProductListModel item : FilterList) {
                    if (item.get_productName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView productName;
        TextView productDescription;
        TextView categoryName;
        TextView uploadDate;
        TextView price;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView){

            super(itemView);
            productName =itemView.findViewById(R.id.productName);
            productDescription =itemView.findViewById(R.id.productDescription);
            categoryName =itemView.findViewById(R.id.categoryName);
            uploadDate =itemView.findViewById(R.id.uploadDate);
            price =itemView.findViewById(R.id.price);
            imageView =itemView.findViewById(R.id.productImage);
            cardView = itemView.findViewById(R.id.productCardView);
        }

    }


}

