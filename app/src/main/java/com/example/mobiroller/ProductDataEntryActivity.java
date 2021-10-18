package com.example.mobiroller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ProductDataEntryActivity extends AppCompatActivity {

    List<String> categoryList=new ArrayList<String>();
    Button ProductButton;
    DatabaseReference databaseReference;
     Spinner CategorySpinner;
     ArrayAdapter<String> dataAdapterCategory;
     String FirebaseCategory;
     EditText ProductNameEdit,DescriptionEdit,PriceEdit,ImageEdit;
    String productName,description,price,image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_data_entry);
        init();
        FirebaseChooseCategory();



        ProductButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputData();
                if(productName!=null&& !productName.equals("")){

                    if(description!=null && !description.equals("")){

                        if(price!=null && !price.equals("")){

                            if(image!=null && !image.equals("")){

                                FirebaseAdd();

                                Toast.makeText(getApplicationContext(), "Product Added",Toast.LENGTH_LONG).show();
                            }
                            else Toast.makeText(getApplicationContext(), "Image Link Not Entered",Toast.LENGTH_LONG).show();
                        }
                        else Toast.makeText(getApplicationContext(), "No Price Entered",Toast.LENGTH_LONG).show();
                    }
                    else Toast.makeText(getApplicationContext(), "Description Not Entered",Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(), "Product Name Not Entered",Toast.LENGTH_LONG).show();
            }
        });
    }

    void EditTextClear(){
        ProductNameEdit.setText("");
        DescriptionEdit.setText("");
        PriceEdit.setText("");
        ImageEdit.setText("");

    }

     void InputData(){
         productName = ProductNameEdit.getText().toString();
         description = DescriptionEdit.getText().toString();
         price = PriceEdit.getText().toString();
         image = ImageEdit.getText().toString();
     }




    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }

     void FirebaseAdd(){
         boolean priceboll =isInteger(price,10);
         if(priceboll){
             String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
             FirebaseDatabase database = FirebaseDatabase.getInstance();
             DatabaseReference myRef = database.getReference("Category").child(FirebaseCategory).child(productName);
             myRef.child("Description").setValue(description);
             myRef.child("Price").setValue(price);
             myRef.child("Upload date").setValue(currentDate);
             myRef.child("img_ctg").setValue(image);
             EditTextClear();
         }
         else{
              Toast.makeText(getApplicationContext(), "enter the price value as a number",Toast.LENGTH_LONG).show();
         }


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
        CategorySpinner = findViewById(R.id.spinnerCategoryName);
        ProductButton = findViewById(R.id.productDataButton);
        ProductNameEdit = findViewById(R.id.editProductName);
        DescriptionEdit = findViewById(R.id.editProductDescription);
        PriceEdit = findViewById(R.id.editPrice);
        ImageEdit = findViewById(R.id.editImage);


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


}