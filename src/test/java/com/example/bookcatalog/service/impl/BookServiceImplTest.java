package com.example.bookcatalog.service.impl;

import com.example.bookcatalog.exception.BookNotFoundException;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {


    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepository bookRepository;

    private List<Book> books;

    @BeforeEach
    public void setUp() {
        books = new ArrayList<>();
        books.add(new Book(0, "Книга 1", "Автор 1", "Описание 1", 12.3));
        books.add(new Book(1, "Книга 2", "Автор 2", "Описание 2", 134.4));

    }

    @Test
    public void testGetAllBook() {
        when(bookRepository.findAllBook()).thenReturn(books);
        List<Book> result = bookService.getAllBook();
        assertEquals(2, result.size());
        verify(bookRepository,  times(1)).findAllBook();
    }

    @Test
    public void testGetBookById_ValidId() {
        when(bookRepository.findAllBook()).thenReturn(books);
        when(bookRepository.findBookById(0)).thenReturn(books.get(0));

        Book result = bookService.getBookById(0);

        assertNotNull(result);
        assertEquals("Книга 1", result.getTitle());
        verify(bookRepository, times(1)).findBookById(0);
    }

    @Test
    public void testGetBookById_InvalidId() {
        assertThrows(BookNotFoundException.class, () -> bookService.getBookById(10));
    }

    @Test
    public void testCreateBook() {
        Book newBook = new Book(0, "Новая книга", "Новый автор", "Новое описание", 123.4);
        when(bookRepository.createBook(any(Book.class))).thenAnswer(invocation -> {
            Book bookToCreate = invocation.getArgument(0);
            bookToCreate.setId(books.size());
            books.add(bookToCreate);
            return bookToCreate;
        });

        Book result = bookService.createBook(newBook);

        assertNotNull(result);
        assertEquals("Новая книга", result.getTitle());
        assertEquals(3, books.size());
        verify(bookRepository, times(1)).createBook(newBook);
    }

    @Test
    public void testUpdateBook_ValidId() {
        Book updatedBook = new Book(null, "Обновленная книга", "Обновленный автор", "Обновленное описание", 123.4);
        when(bookRepository.findAllBook()).thenReturn(books);
        when(bookRepository.updateBook(0, updatedBook)).thenReturn(updatedBook);

        Book result = bookService.updateBook(0, updatedBook);

        assertNotNull(result);
        assertEquals("Обновленная книга", result.getTitle());
        verify(bookRepository, times(1)).updateBook(0, updatedBook);
    }

    @Test
    public void testUpdateBook_InvalidId() {
        assertThrows(BookNotFoundException.class, () -> bookService.updateBook(10, new Book()));
    }

    @Test
    public void testDeleteBook_ValidId() {
        when(bookRepository.findAllBook()).thenReturn(books);

        doNothing().when(bookRepository).deleteBook(any());

        bookService.deleteBook(0);

        verify(bookRepository, times(1)).deleteBook(any());
    }

    @Test
    public void testDeleteBook_InvalidId() {
        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook(10));
    }
}