package com.example.librarymanagementsystem.service.impl;

import com.example.librarymanagementsystem.model.Book;
import com.example.librarymanagementsystem.repository.BookRepository;
import com.example.librarymanagementsystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    @Value("${library.management.upload.path}")
    private String imageDirectoryPath;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> searchBooks(String title) {
        if (title == null || title.isEmpty()) {
            return getAllBooks();
        }
        return bookRepository.findByTitleContaining(title);
    }

    @Override
    public List<Book> getBooksByCategory(int categoryId) {
        return bookRepository.findByCategoryId(categoryId);
    }

    @Override
    public Book saveBook(Book book, MultipartFile multipartFile) {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + multipartFile.getOriginalFilename();
            File file = new File(imageDirectoryPath + fileName);
            try {
                multipartFile.transferTo(file);
                book.setPictureName(fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book getBookById(int id) {
        return bookRepository.findById(id).orElse(null);
    }
}