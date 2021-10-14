package com.example.mobiroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryListActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<CategoryListModel> categoryLists = new ArrayList<>();
    RecyclerView recyclerView;
    CategoryListAdapter categoryListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        init();
        databaseReference = FirebaseDatabase.getInstance().getReference("Category");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){


                    CategoryListModel categoryListModel = new CategoryListModel(dataSnapshot.getKey()
                            ,dataSnapshot.child("img_ctg").getValue().toString());
                    categoryLists.add(categoryListModel);
                }
                RecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }

     void RecyclerView(){

        categoryListAdapter = new CategoryListAdapter(categoryLists,this);
        recyclerView.setAdapter(categoryListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void init(){
        recyclerView = findViewById(R.id.categoryRecylerview);
    }

}