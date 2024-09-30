package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBook();

    Book getBookById(Integer id);

    Book createBook(Book book);

    Book updateBook(Integer id, Book book);

    void deleteBook(Integer id);
}
