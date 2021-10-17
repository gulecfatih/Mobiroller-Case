package com.example.mobiroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CategoryDeleteActivity extends AppCompatActivity {

    List<String> categoryList=new ArrayList<String>();
    Button CategoryButton;
    DatabaseReference databaseReference;
    Spinner CategorySpinner;
    ArrayAdapter<String> dataAdapterCategory;
    String FirebaseCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_delete);
        init();
        FirebaseChooseCategory();
        CategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            FirebaseDelete();

            }
        });


    }

    void FirebaseDelete(){
        ListClear();
        databaseReference = FirebaseDatabase.getInstance().getReference("Category").child(FirebaseCategory);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                databaseReference.removeValue();
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }

        });


    }


    void Spinner(){


        dataAdapterCategory = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categoryList);
        dataAdapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        CategorySpinner.setAdapter(dataAdapterCategory);
        CategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FirebaseCategory = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void FirebaseChooseCategory(){

        databaseReference = FirebaseDatabase.getInstance().getReference("Category");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                int count=0;
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    if(count==0){
                        FirebaseCategory = dataSnapshot.getKey().toString();
                    }
                    categoryList.add(dataSnapshot.getKey().toString());
                    count++;
                }
                Spinner();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
    void init(){
        CategorySpinner = findViewById(R.id.spinnerCategoryDelete);
        CategoryButton = findViewById(R.id.categoryDeleteButton);
    }

    void ListClear(){
        categoryList.clear();
    }
}