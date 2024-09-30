package com.example.bookcatalog.controller;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @Operation(summary = "Get all books")
    @GetMapping
    public List<Book> getAllBook() {
        return bookService.getAllBook();
    }
    @Operation(summary = "Get the book by ID")
    @GetMapping("/{id}")
    public Book findBookById(@PathVariable Integer id) {
        return bookService.getBookById(id);
    }
    @Operation(summary = "Create a new book")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }
    @Operation(summary = "Update the book by ID")
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable Integer id, @RequestBody Book book) {
        return bookService.updateBook(id,book);
    }
    @Operation(summary = "Delete the book by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?>  deleteBook(@PathVariable Integer id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
