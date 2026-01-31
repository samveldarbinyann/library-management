package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Category;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;

    public  CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listCategories(Model model){
        model.addAttribute("categories", categoryRepository.findAll());
        return  "categories";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model){
        Category category = new Category();
        model.addAttribute("category", category);
        return  "addCategory";
    }
    @PostMapping("/add")
    public String saveCategory(@ModelAttribute("category") Category category){
        categoryRepository.save(category);
        return   "redirect:/categories";
    }
}
