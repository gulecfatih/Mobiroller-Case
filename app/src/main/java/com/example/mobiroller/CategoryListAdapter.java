package com.example.mobiroller;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> implements Filterable {
    List<CategoryListModel> list;
    Context context;
    List<CategoryListModel> FilterList;


    public CategoryListAdapter(List<CategoryListModel> list, Context context) {
        this.list = list;
        this.context = context;
        this.FilterList = new ArrayList<>(list);
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

    @Override
    public Filter getFilter() {
        return filter;
    }

    private Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<CategoryListModel> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(FilterList);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CategoryListModel item : FilterList) {
                    if (item.get_category().toLowerCase().contains(filterPattern)) {
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