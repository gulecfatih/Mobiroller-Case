package com.example.mobiroller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CategoryDataEntryActivity extends AppCompatActivity {

    Button CategoryButton;
    EditText CategoryData,CategoryImage;
    String categoryData,categoryImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_data_entry);

        init();

        CategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                InputData();
                System.out.println(categoryData);
                if(categoryData!=null && !categoryData.equals("")){
                    if(categoryImage!=null && !categoryImage.equals("")){
                        FirebaseAdd();
                        EditTextClear();
                        Toast.makeText(getApplicationContext(), "Category Created",Toast.LENGTH_LONG).show();

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Image Link Not Entered",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(), "No Data Entered",Toast.LENGTH_LONG).show();

                }
            }
        });

    }

   void EditTextClear(){
       CategoryImage.setText("");
       CategoryData.setText("");
    }

   void FirebaseAdd(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Category");
        myRef.child(categoryData).child("img_ctg").setValue(categoryImage);
    }

    void InputData(){
        categoryData = CategoryData.getText().toString();
        categoryImage =CategoryImage.getText().toString();
    }
    void init(){

        CategoryButton = findViewById(R.id.categoryDataButton);
        CategoryData = findViewById(R.id.editCategoryData);
        CategoryImage = findViewById(R.id.editImage);


    }
}