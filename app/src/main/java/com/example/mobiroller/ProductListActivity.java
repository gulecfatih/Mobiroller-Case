package com.example.mobiroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends AppCompatActivity {

    DatabaseReference databaseReference;
    List<ProductListModel> productModels = new ArrayList<>();
    RecyclerView recyclerView;
    ProductListAdapter productListAdapter;
    String Category;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_list);
        init();
        Intent intent = getIntent();
        Category = intent.getStringExtra("category");
        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child(Category);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){

                    if(!dataSnapshot.getKey().toString().equals("img_ctg")){
                        // if kullanma sebebimiz kategorinin resmini child olarak tutuyoruz ve
                        // resmin olduğu düğümüde kategori sanıyor onu engellemek için if kullandık
                        String productName = dataSnapshot.getKey().toString();
                        int price =Integer.parseInt(dataSnapshot.child("Price").getValue().toString());
                        String productDescription = dataSnapshot.child("Description").getValue().toString();
                        String uploadTime = dataSnapshot.child("Upload date").getValue().toString();
                        String img = dataSnapshot.child("img_ctg").getValue().toString();

                        ProductListModel productListModels = new ProductListModel(productName,price,productDescription,uploadTime,Category,img);
                        productModels.add(productListModels);
                    }
                }
                RecyclerView();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    void RecyclerView(){
        productListAdapter = new ProductListAdapter(productModels,this);
        recyclerView.setAdapter(productListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    void init(){
        recyclerView = findViewById(R.id.productRecylerview);
    }


}