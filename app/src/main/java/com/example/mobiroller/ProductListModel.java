package com.example.mobiroller;

public class ProductListModel {
    private String _productName;
    private int _price;
    private String _productDescription;
    private String _uploadTime;
    private String _category;
    private String _img;

    public ProductListModel(String _productName, int _price, String _productDescription, String _uploadTime, String _category, String _img) {
        this._productName = _productName;
        this._price = _price;
        this._productDescription = _productDescription;
        this._uploadTime = _uploadTime;
        this._category = _category;
        this._img = _img;
    }


    public String get_img() {
        return _img;
    }

    public String get_productName() {
        return _productName;
    }

    public int get_price() {
        return _price;
    }

    public String get_productDescription() {
        return _productDescription;
    }

    public String get_uploadTime() {
        return _uploadTime;
    }

    public String get_category() {
        return _category;
    }
}
