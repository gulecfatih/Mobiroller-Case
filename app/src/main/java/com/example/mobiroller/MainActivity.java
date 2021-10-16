package com.example.mobiroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button productEntryButton;
    Button productListingButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);


        init();
        productEntryButton.setOnClickListener(this);
        productListingButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.productEntry:
                startActivity( new Intent(MainActivity.this,ProductEntryActivity.class));
                break;
            case R.id.categoryList:
                startActivity( new Intent(MainActivity.this,CategoryListActivity.class));
                break;
        }
    }

   void init(){
        productEntryButton = findViewById(R.id.productEntry);
        productListingButton = findViewById(R.id.categoryList);
    }
}