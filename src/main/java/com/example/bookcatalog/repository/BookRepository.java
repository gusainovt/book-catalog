package com.example.bookcatalog.repository;

import com.example.bookcatalog.model.Book;

import java.util.List;

public interface BookRepository {
    Book createBook(Book book);

    Book findBookById(Integer id);

    List<Book> findAllBook();

    Book updateBook(Integer id, Book book);

    void deleteBook(Book book);
}
