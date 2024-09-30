package com.example.bookcatalog.controller;

import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@WebMvcTest(controllers = BookControllerTest.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private ObjectMapper objectMapper;

    private List<Book> books;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
        books = Arrays.asList(
                new Book(0, "Книга 1", "Автор 1", "Описание 1", 12.3),
                new Book(1, "Книга 2", "Автор 2", "Описание 2", 134.4));
    }

    @Test
    public void testGetAllBooks() throws Exception {
        when(bookService.getAllBook()).thenReturn(books);

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Книга 1"));

        verify(bookService, times(1)).getAllBook();
    }

    @Test
    public void testFindBookById() throws Exception {

        when(bookService.getBookById(0)).thenReturn(books.get(0));

        mockMvc.perform(get("/books/{id}",0))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Книга 1"));

        verify(bookService, times(1)).getBookById(0);
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book =  new Book(0, "Новая книга", "Новый автор", "Новое описание", 123.4);
        when(bookService.createBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Новая книга"));

        verify(bookService, times(1)).createBook(any(Book.class));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book updatedBook = new Book(null, "Обновленная книга", "Обновленный автор", "Обновленное описание", 123.4);
        when(bookService.updateBook(eq(1), any(Book.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/books/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("title").value("Обновленная книга"));

        verify(bookService, times(1)).updateBook(eq(1), any(Book.class));
    }

    @Test
    public void testDeleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1);

        mockMvc.perform(delete("/books/{id}", 1))
                .andExpect(status().isOk());

        verify(bookService, times(1)).deleteBook(1);
    }
}