package com.example.mobiroller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder>{
    List<CategoryListModel> list;
    Context context;

    public CategoryListAdapter(List<CategoryListModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public CategoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_list_cardview,parent,false);
        return new CategoryListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListAdapter.ViewHolder holder, int position) {
        holder.categoryName.setText(list.get(position).get_category());
        Glide.with(context)
                .load(list.get(position).get_img())
                .into(holder.imageView);
        String category = list.get(position).get_category();
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context,ProductListActivity.class);

                intent.putExtra("category",category );
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView categoryName;
        ImageView imageView;
        CardView cardView;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            categoryName =itemView.findViewById(R.id.categoryName);
            imageView = itemView.findViewById(R.id.categoryImage);
            cardView =itemView.findViewById(R.id.categoryCardView);
        }
    }
}