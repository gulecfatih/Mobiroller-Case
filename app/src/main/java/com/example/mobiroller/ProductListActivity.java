package com.example.mobiroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;

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
        FirebaseExtractionData();


    }

    void FirebaseExtractionData(){
        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child(Category);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){


                    if(!dataSnapshot.getKey().toString().equals("img_ctg")){
                        // if kullanma sebebimiz kategorinin resmini child olarak tutuyoruz ve
                        // resmin olduğu düğümüde kategori sanıyor onu engellemek için if kullandık
                        String productName = dataSnapshot.getKey().toString();
                        String price =dataSnapshot.child("Price").getValue().toString();
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
        Intent intent = getIntent();
        Category = intent.getStringExtra("category");
        recyclerView = findViewById(R.id.productRecylerview);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        MenuItem item =menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) item.getActionView();

        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()



        {
            @Override
            public boolean onQueryTextSubmit(String query) {
                    return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                productListAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}