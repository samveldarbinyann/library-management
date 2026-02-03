package com.example.librarymanagementsystem.service;

import com.example.librarymanagementsystem.model.Book;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    List<Book> searchBooks(String title);

    List<Book> getBooksByCategory(int categoryId);

    Book saveBook(Book book, MultipartFile multipartFile);

    void deleteBook(int id);

    Book getBookById(int id);
}