package com.example.mobiroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductEntryActivity extends AppCompatActivity implements View.OnClickListener {

    Button categoryDataEntryButton;
    Button productDataEntryButton;
    Button categoryDataDeleteButton;
    Button productDataDeleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry);
        init();
        categoryDataEntryButton.setOnClickListener(this);
        productDataEntryButton.setOnClickListener(this);
        categoryDataDeleteButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.categoryDataEntry:
                startActivity( new Intent(ProductEntryActivity.this,CategoryDataEntryActivity.class));
                break;
            case R.id.productDataEntry:
                startActivity( new Intent(ProductEntryActivity.this,ProductDataEntryActivity.class));
                break;
            case R.id.categoryDataDelete:
                startActivity( new Intent(ProductEntryActivity.this,CategoryDeleteActivity.class));
                break;

        }
    }
   void init(){
        categoryDataEntryButton = findViewById(R.id.categoryDataEntry);
        productDataEntryButton = findViewById(R.id.productDataEntry);
        categoryDataDeleteButton = findViewById(R.id.categoryDataDelete);

    }

}