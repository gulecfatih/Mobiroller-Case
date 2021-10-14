package com.example.mobiroller;

public class CategoryListModel {
    private String _category;
    private String _img;


    public String get_category() {
        return _category;
    }

    public String get_img() {
        return _img;
    }

    public CategoryListModel(String _category, String _img) {
        this._category = _category;
        this._img = _img;
    }
}
