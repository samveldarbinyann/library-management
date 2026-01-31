package com.example.librarymanagementsystem.controller;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.CategoryRepository;
import org.springframework.ui.Model;
import com.example.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String listBooks(@RequestParam(required = false) String search, @RequestParam(required = false) Integer categoryId, Model model) {
        List<Book> books;
        if(search != null && !search.isEmpty()){
            books = bookRepository.findByTitleContaining(search);
        }else if(categoryId != null && categoryId>0){
            books = bookRepository.findByCategoryId(categoryId);
        }else {
            books = bookRepository.findAll();
        }
        model.addAttribute("books", books);
        model.addAttribute("categories", categoryRepository.findAll());
        return "books";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute("book") Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") int id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }
}
