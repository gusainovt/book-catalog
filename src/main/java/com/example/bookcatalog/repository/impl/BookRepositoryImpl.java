package com.example.bookcatalog.repository.impl;

import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {
    private List<Book> books;

    public BookRepositoryImpl(List<Book> books) {
        this.books = new ArrayList<>();
    }

    @Override
    public Book createBook(Book book) {
        books.add(book);
        return book;
    }

    @Override
    public Book findBookById(Integer id) {
        return books.get(id);
    }

    @Override
    public List<Book> findAllBook() {
        return new ArrayList<>(books);
    }

    @Override
    public Book updateBook(Integer id, Book book) {
        Book updateBook = books.get(id);
        updateBook.setAuthor(book.getAuthor());
        updateBook.setDescription(book.getDescription());
        updateBook.setTitle(book.getTitle());
        updateBook.setPrice(book.getPrice());
        return books.set(id, updateBook);
    }

    @Override
    public void deleteBook(Book book) {
        books.remove(book);
    }




}
