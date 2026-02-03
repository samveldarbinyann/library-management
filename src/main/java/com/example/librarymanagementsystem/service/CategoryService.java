package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();

    Category saveCategory(Category category);

    Category getCategoryById(int id);
}