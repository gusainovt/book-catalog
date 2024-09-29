package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import com.example.bookcatalog.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Returns all saved books
     * @return List of all books
     */
    @Override
    public List<Book> getAllBook() {
        return bookRepository.findAllBook();
    }

    /**
     * Returns the book by id
     * @param id The integer value of the book's ID.
     * @return An instance of the {@link Book} class
     */
    @Override
    public Book getBookById(Integer id) {
        if (id >= getAllBook().size() || id < 0) {
            throw new BookNotFoundException();
        }
        return bookRepository.findBookById(id);
    }

    /**
     * Creates a new book
     * @param book An instance of the {@link Book} class
     * @return The instance of the {@link Book} class
     */
    @Override
    public Book createBook(Book book) {
        book.setId(getAllBook().size());
        return bookRepository.createBook(book);
    }

    /**
     * Updates an existing book
     * @param id The integer value of the book's ID.
     * @param book An instance of the {@link Book} class
     * @return The instance of the {@link Book} class
     */
    @Override
    public Book updateBook(Integer id, Book book) {
        if (id >= getAllBook().size() || id < 0) {
            throw new BookNotFoundException();
        }
        return bookRepository.updateBook(id,book);
    }

    /**
     * Delete a book from the list.
     * @param id The integer value of the book's ID.
     */
    @Override
    public void deleteBook(Integer id) {
        if (id >= getAllBook().size() || id < 0) {
            throw new BookNotFoundException();
        }
        bookRepository.deleteBook(getBookById(id));
    }
}
