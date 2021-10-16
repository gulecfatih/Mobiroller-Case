package com.example.mobiroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailScreenActivity extends AppCompatActivity {

    ImageView detailImageview;
    TextView TextviewProductName,TextviewProductDescription,TextviewUploadDate,TextviewCategoryName,TextviewPrice;
    String detailProductDescription,detailProductName,detailUploadDate,detailCategoryName,detailImage;
    int detailPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_screen);
        
        init();

        TextviewProductName.setText(detailProductName);
        TextviewProductDescription.setText(detailProductDescription);
        TextviewPrice.setText(String.valueOf(detailPrice));
        TextviewUploadDate.setText(detailUploadDate);
        TextviewCategoryName.setText(detailCategoryName);
        Glide.with(getApplicationContext())
                .load(detailImage)
                .into(detailImageview);


    }

      void init(){
          Intent intent = getIntent();
          detailProductName = intent.getStringExtra("productName");
          detailProductDescription = intent.getStringExtra("productDescription");
          detailPrice =intent.getExtras().getInt("price"); // hata
          detailUploadDate = intent.getStringExtra("uploadDate");
          detailCategoryName = intent.getStringExtra("categoryName");
          detailImage =intent.getStringExtra("img");


          TextviewProductName =findViewById(R.id.detailProductName);
          TextviewProductDescription=findViewById(R.id.detailproductDescription);
          TextviewPrice=findViewById(R.id.detailPrice);
          TextviewUploadDate=findViewById(R.id.detailUploadDate);
          TextviewCategoryName=findViewById(R.id.detailCategoryName);
          detailImageview=findViewById(R.id.detailImage);
      }
}